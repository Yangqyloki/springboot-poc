package cx.sap.panda.gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gateway-forward")
public class GateWayController
{
	public static final String UNIT_SERVICE_VERSION = "/v1/";
	public static final String UNIT_SERVICE_UNITS = UNIT_SERVICE_VERSION + "{baseSiteId}/users/{userId}/units";

	@Value("${user.service.gateway}")
	private String userServiceGateway;

	@Value("${unit.service.gateway}")
	private String unitServiceGateway;

	@Autowired
	RestTemplate restTemplate;


	@GetMapping(UNIT_SERVICE_UNITS)
	public Map getUnits(@PathVariable String baseSiteId, @PathVariable String userId, @RequestHeader("Authorization") String authToken, HttpServletRequest request)
	{
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("userId", userId);
		urlParams.put("baseSiteId", baseSiteId);

		HttpHeaders headers = prepareHttpHeaders(authToken);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);

		Map<String,Object> result = new HashMap<>();
		result.put("user",restTemplate.exchange(UriComponentsBuilder.fromUriString(userServiceGateway).buildAndExpand(urlParams).toUri(),
				HttpMethod.GET, entity, Object.class).getBody());
		result.put("unit",restTemplate.exchange(UriComponentsBuilder.fromUriString(unitServiceGateway).buildAndExpand(urlParams).toUri(),
				HttpMethod.GET, entity, Object.class).getBody());
		return result;
	}

	private HttpHeaders prepareHttpHeaders(final String authToken)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", authToken);
		return headers;
	}
}
