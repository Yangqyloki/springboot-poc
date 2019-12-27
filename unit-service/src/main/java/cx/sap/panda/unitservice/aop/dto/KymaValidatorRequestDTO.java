package cx.sap.panda.unitservice.aop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KymaValidatorRequestDTO
{
    String email;
    String mobile;
}
