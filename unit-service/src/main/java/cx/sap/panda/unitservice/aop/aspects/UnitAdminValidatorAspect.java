package cx.sap.panda.unitservice.aop.aspects;

import cx.sap.panda.unitservice.clients.UserClient;
import cx.sap.panda.unitservice.dto.UserGroupListDTO;
import cx.sap.panda.unitservice.exceptions.errors.exception.UnitServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static cx.sap.panda.unitservice.constants.UnitServiceConstants.B2B_ADMIN_GROUP;
import static cx.sap.panda.unitservice.exceptions.errors.impl.UnitServiceErrors.USER_NOT_ADMIN;

@Aspect
@Component
public class UnitAdminValidatorAspect
{
	@Autowired
	private UserClient userClient;

	@Pointcut("@annotation(cx.sap.panda.unitservice.aop.annotations.UnitAdminValidator)")
	public void unitAdminValidate()
	{
	}

	@Before("unitAdminValidate()")
	public void beforeUnitAdminValidate(JoinPoint point)
	{
		for (Object arg : point.getArgs())
		{
			if (arg instanceof HttpServletRequest)
			{
				HttpServletRequest request = (HttpServletRequest) arg;
				Map<String, String> paramMap = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
				String authToken = request.getHeader("Authorization");
				String userId = paramMap.get("userId");
				String baseSiteId = paramMap.get("baseSiteId");
				if(!checkIfAdminUser(userId,baseSiteId,authToken)){
					throw new UnitServiceException(USER_NOT_ADMIN);
				}
			}
		}
	}

	private boolean checkIfAdminUser(String userId, String baseSiteId, String authToken)
	{
		UserGroupListDTO userGroupList = userClient.getUserGroups(userId, baseSiteId, authToken);
		return userGroupList.getUserGroups().stream().filter(group -> B2B_ADMIN_GROUP.equals(group.getUid()))
				.findAny().isPresent();
	}
}
