package cx.sap.panda.customunitservice.clients;

import cx.sap.panda.customunitservice.dto.AddressDTO;
import cx.sap.panda.customunitservice.dto.UnitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface CustomUnitClient
{
	List<UnitDTO> getUnitsByUser(String userId,  String baseSiteId,  String authToken);
	List<AddressDTO> getAddressesByUnit( String unitId);
}
