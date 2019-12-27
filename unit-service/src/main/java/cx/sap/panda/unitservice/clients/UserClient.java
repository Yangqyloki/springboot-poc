package cx.sap.panda.unitservice.clients;

import cx.sap.panda.unitservice.dto.CustomerDTO;
import cx.sap.panda.unitservice.dto.OccCustomerDTO;
import cx.sap.panda.unitservice.dto.UserGroupListDTO;

public interface UserClient
{
	UserGroupListDTO getUserGroups(String userId, String baseSiteId, String authToken);

	void createCustomer(String userId, String baseSiteId, String authToken, OccCustomerDTO convertCustomer);

//	void setCustomerToUserGroup(String userId, String baseSiteId, String authToken, CustomerDTO customer);
}
