package cx.sap.panda.customunitservice.controllers;


import cx.sap.panda.customunitservice.facades.CustomUnitFacade;
import cx.sap.panda.customunitservice.results.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unitservice")
public class CustomUnitController
{
	public static final String UNIT_SERVICE_VERSION = "/v1/";
	public static final String UNIT_SERVICE_UNITS = UNIT_SERVICE_VERSION + "{baseSiteId}/users/{userId}/units";
	@Autowired
	private CustomUnitFacade customUnitFacade;

	@GetMapping(UNIT_SERVICE_UNITS)
	public ResultDTO getUnits(@PathVariable String baseSiteId, @PathVariable String userId, @RequestHeader("Authorization") String authToken)
	{
		return customUnitFacade.getUnitsByUser(userId, baseSiteId, authToken);
	}
}
