package cx.sap.panda.unitservice.clients;

import cx.sap.panda.unitservice.dto.CustomerDTO;
import cx.sap.panda.unitservice.dto.UnitDTO;

import java.util.List;

public interface UnitClient
{
	List<UnitDTO> getUnitsByUser(String userId);

	UnitDTO creatUnit(String userId, UnitDTO unit);

	List<UnitDTO> getAllUnits();

	UnitDTO getUnitById(String unitId);

	UnitDTO createCustomerForUnit(String unitId, CustomerDTO customer);
}
