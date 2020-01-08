package cx.sap.panda.customunitservice.controllers;


import cx.sap.panda.customunitservice.dto.CustomerDTO;
import cx.sap.panda.customunitservice.dto.UnitDTO;
import cx.sap.panda.customunitservice.facades.CustomUnitFacade;
import cx.sap.panda.customunitservice.results.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unitservice")
public class CustomUnitController
{
	public static final String UNIT_SERVICE_VERSION = "/v1/";
	public static final String UNIT_SERVICE_UNITS = UNIT_SERVICE_VERSION + "{baseSiteId}/users/{userId}/units";
	public static final String UNIT_SERVICE_CREATE_CUSTOMER = UNIT_SERVICE_UNITS + "/{unitId}/customers";

	@Autowired
	private CustomUnitFacade customUnitFacade;

	@Autowired
	private OriginUnitServiceClient originUnitServiceClient;

	@GetMapping(UNIT_SERVICE_UNITS)
	public ResultDTO getUnits(@PathVariable String baseSiteId, @PathVariable String userId, @RequestHeader("Authorization") String authToken)
	{
		return customUnitFacade.getUnitsByUser(userId, baseSiteId, authToken);
	}
	@PostMapping(UNIT_SERVICE_UNITS)
	public ResultDTO createUnit(@PathVariable String baseSiteId, @PathVariable String userId, @RequestHeader("Authorization") String authToken, @RequestBody UnitDTO unit){

		return originUnitServiceClient.createUnit(baseSiteId,userId,authToken,unit);
	}


	@PostMapping(UNIT_SERVICE_CREATE_CUSTOMER)
	public ResultDTO CreateCustomerForUnit(@PathVariable String baseSiteId, @PathVariable String userId, @PathVariable String unitId, @RequestHeader("Authorization") String authToken,
									@RequestBody CustomerDTO customer)
	{
		return originUnitServiceClient.createCustomerForUnit(baseSiteId,userId,unitId,authToken,customer);
	}
}
