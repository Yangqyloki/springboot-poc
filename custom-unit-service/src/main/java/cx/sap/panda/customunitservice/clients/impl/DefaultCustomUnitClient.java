package cx.sap.panda.customunitservice.clients.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cx.sap.panda.customunitservice.clients.CustomUnitClient;
import cx.sap.panda.customunitservice.dao.CustomUnitDao;
import cx.sap.panda.customunitservice.dto.AddressDTO;
import cx.sap.panda.customunitservice.dto.UnitDTO;
import cx.sap.panda.customunitservice.results.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DefaultCustomUnitClient implements CustomUnitClient
{

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private CustomUnitDao customUnitDao;

	@Value("${unit.service.gateway}")
	private String unitServiceGateway;

	@Override
	public List getUnitsByUser(final String userId, final String baseSiteId, final String authToken)
	{
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("userId", userId);
		urlParams.put("baseSiteId", baseSiteId);


		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", authToken);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);

		ResponseEntity<ResultDTO> response = restTemplate.exchange(UriComponentsBuilder.fromUriString(unitServiceGateway).buildAndExpand(urlParams).toUri(),
				HttpMethod.GET, entity, ResultDTO.class);
		ResultDTO result = response.getBody();
		Object objects = result.getData();

		ObjectMapper mapper = new ObjectMapper();
		List<UnitDTO> unitList = mapper.convertValue(objects, new TypeReference<List<UnitDTO>>()
		{
		});

		return unitList;
	}

	@Override
	public List<AddressDTO> getAddressesByUnit(final String unitId)
	{
		return customUnitDao.findAddressByUnit(unitId);
	}
}
