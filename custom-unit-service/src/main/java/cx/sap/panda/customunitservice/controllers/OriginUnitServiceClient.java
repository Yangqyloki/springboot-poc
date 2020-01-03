package cx.sap.panda.customunitservice.controllers;

import cx.sap.panda.customunitservice.dto.CustomerDTO;
import cx.sap.panda.customunitservice.dto.UnitDTO;
import cx.sap.panda.customunitservice.results.dto.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@Component
@FeignClient(name = "unit-service")
public interface OriginUnitServiceClient
{
	String UNIT_SERVICE_VERSION = "/unitservice/v1/";
	String UNIT_SERVICE_UNITS = UNIT_SERVICE_VERSION + "{baseSiteId}/users/{userId}/units";
	String UNIT_SERVICE_CREATE_CUSTOMER = UNIT_SERVICE_UNITS + "/{unitId}/customers";

	@GetMapping(UNIT_SERVICE_UNITS)
	ResultDTO getUnits(@PathVariable String baseSiteId, @PathVariable String userId, @RequestHeader("Authorization") String authToken, HttpServletRequest request);


	@PostMapping(UNIT_SERVICE_UNITS)
	ResultDTO createUnit(@PathVariable String baseSiteId, @PathVariable String userId, @RequestHeader("Authorization") String authToken, @RequestBody UnitDTO unit);


	@PostMapping(UNIT_SERVICE_CREATE_CUSTOMER)
	ResultDTO createCustomerForUnit(@PathVariable String baseSiteId, @PathVariable String userId, @PathVariable String unitId, @RequestHeader("Authorization") String authToken,
									@RequestBody CustomerDTO customer);

}
