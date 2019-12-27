package cx.sap.panda.customunitservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO
{

    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String parentUnit;
    private List<String> roles;


}
