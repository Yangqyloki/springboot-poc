package cx.sap.panda.unitservice.facades;

import cx.sap.panda.unitservice.dto.CustomerDTO;
import cx.sap.panda.unitservice.dto.UnitDTO;
import cx.sap.panda.unitservice.results.dto.ResultDTO;

public interface UnitFacade
{
	ResultDTO getUnitsByUser(String userId, String baseSiteId, String authToken);

	ResultDTO createUnit(String userId, String baseSiteId, String authToken, UnitDTO unit);

	ResultDTO createCustomerForUnit(String userId, String baseSiteId, String unitId,String authToken, CustomerDTO customer);
}
