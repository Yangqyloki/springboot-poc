package cx.sap.panda.unitservice.aop.aspects;

import cx.sap.panda.unitservice.aop.dto.KymaValidatorResponseDTO;
import cx.sap.panda.unitservice.dto.CustomerDTO;
import cx.sap.panda.unitservice.exceptions.errors.exception.UnitServiceException;
import cx.sap.panda.unitservice.utils.DTOConverter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static cx.sap.panda.unitservice.exceptions.errors.impl.CommonErrors.INVALID_REQUEST;
import static cx.sap.panda.unitservice.exceptions.errors.impl.UnitServiceErrors.INVALID_EMAIL_ADDRESS;
import static cx.sap.panda.unitservice.exceptions.errors.impl.UnitServiceErrors.INVALID_MOBILE_NUMBER;

@Aspect
@Component
public class KymaValidatorAspect
{
	@Autowired
	RestTemplate restTemplate;

	@Value("${kyma.gateway}")
	private String kymaGateway;

	@Pointcut("@annotation(cx.sap.panda.unitservice.aop.annotations.KymaValidator)")
	public void kymaValidate()
	{
	}

	@Before("kymaValidate()")
	public void beforeKymaValidate(JoinPoint point)
	{
		for (Object arg : point.getArgs())
		{
			if (arg instanceof CustomerDTO)
			{
				KymaValidatorResponseDTO kymaValidatorResponseDTO = restTemplate.postForEntity(kymaGateway, DTOConverter.convertValidateRequestBody((CustomerDTO) arg), KymaValidatorResponseDTO.class).getBody();
				if (Objects.isNull(kymaValidatorResponseDTO)) {
					throw new UnitServiceException(INVALID_REQUEST);
				} else if (!kymaValidatorResponseDTO.isValidEmailAddress()) {
					throw new UnitServiceException(INVALID_EMAIL_ADDRESS);
				} else if (!kymaValidatorResponseDTO.isValidMobileNumber()) {
					throw new UnitServiceException(INVALID_MOBILE_NUMBER);
				}
			}
		}
	}

}
