package cx.sap.panda.unitservice.aop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KymaValidatorResponseDTO
{
    boolean validEmailAddress;
    boolean validMobileNumber;
}
