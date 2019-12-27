package cx.sap.panda.unitservice.controllers;

import cx.sap.panda.unitservice.aop.annotations.KymaValidator;
import cx.sap.panda.unitservice.aop.annotations.UnitAdminValidator;
import cx.sap.panda.unitservice.dto.CustomerDTO;
import cx.sap.panda.unitservice.dto.UnitDTO;
import cx.sap.panda.unitservice.facades.UnitFacade;
import cx.sap.panda.unitservice.results.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/unitservice")
public class UnitServiceController
{
	public static final String UNIT_SERVICE_VERSION = "/v1/";
	public static final String UNIT_SERVICE_UNITS = UNIT_SERVICE_VERSION + "{baseSiteId}/users/{userId}/units";
	public static final String UNIT_SERVICE_CREATE_CUSTOMER = UNIT_SERVICE_UNITS + "/{unitId}/customers";

	@Autowired
	private UnitFacade unitFacade;


	@GetMapping(UNIT_SERVICE_UNITS)
	@UnitAdminValidator
	public ResultDTO getUnits(@PathVariable String baseSiteId, @PathVariable String userId, @RequestHeader("Authorization") String authToken, HttpServletRequest request)
	{
		return unitFacade.getUnitsByUser(userId, baseSiteId, authToken);
	}

	@PostMapping(UNIT_SERVICE_UNITS)
	@UnitAdminValidator
	public ResultDTO createUnit(@PathVariable String baseSiteId, @PathVariable String userId, @RequestHeader("Authorization") String authToken, @RequestBody UnitDTO unit)
	{
		return unitFacade.createUnit(userId, baseSiteId, authToken, unit);
	}

	@PostMapping(UNIT_SERVICE_CREATE_CUSTOMER)
	@KymaValidator
	@UnitAdminValidator
	public ResultDTO CreateCustomerForUnit(@PathVariable String baseSiteId, @PathVariable String userId, @PathVariable String unitId, @RequestHeader("Authorization") String authToken, @RequestBody CustomerDTO customer)
	{
		return unitFacade.createCustomerForUnit(userId, baseSiteId,unitId ,authToken, customer);
	}

}
