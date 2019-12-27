package cx.sap.panda.customunitservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "b2b_commerce_org.unit")
public class UnitDTO
{

    private String unitId;

    private String unitName;

    private String parentUnit;

    private String approvalProcess;

    private String path;

    private List<CustomerDTO> unitCustomers;

    private CustomerDTO administrator;

    private List<AddressDTO> addresses;


}
