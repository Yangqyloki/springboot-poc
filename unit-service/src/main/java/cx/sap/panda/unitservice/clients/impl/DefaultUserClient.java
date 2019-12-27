package cx.sap.panda.unitservice.clients.impl;

import cx.sap.panda.unitservice.clients.UserClient;
import cx.sap.panda.unitservice.dto.CustomerDTO;
import cx.sap.panda.unitservice.dto.MemberListDTO;
import cx.sap.panda.unitservice.dto.OccCustomerDTO;
import cx.sap.panda.unitservice.dto.UserGroupListDTO;
import cx.sap.panda.unitservice.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultUserClient implements UserClient
{
	@Value("${user.service.gateway}")
	private String userServiceGateway;

	@Value("${user.service.usergroups.path}")
	private String userGroupsPath;

	@Value("${user.service.users.path}")
	private String usersPath;

//	@Value("${user.service.usergroup.set.path}")
//	private String saveUserGroupPath;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public UserGroupListDTO getUserGroups(final String userId, final String baseSiteId, final String authToken)
	{

		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("userId", userId);
		urlParams.put("baseSiteId", baseSiteId);

		HttpHeaders headers = prepareHttpHeaders(authToken);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);

		return restTemplate.exchange(UriComponentsBuilder.fromUriString(userServiceGateway + userGroupsPath).buildAndExpand(urlParams).toUri(),
				HttpMethod.GET, entity, UserGroupListDTO.class).getBody();
	}

	@Override
	public void createCustomer(final String userId, final String baseSiteId, final String authToken, final OccCustomerDTO occCustomer)
	{
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("baseSiteId", baseSiteId);

		HttpHeaders headers = prepareHttpHeaders(authToken);
		HttpEntity<OccCustomerDTO> entity = new HttpEntity<>(occCustomer, headers);

		restTemplate.exchange(UriComponentsBuilder.fromUriString(userServiceGateway + usersPath).buildAndExpand(urlParams).toUri(),
				HttpMethod.POST, entity, OccCustomerDTO.class);
	}

//	@Override
//	public void setCustomerToUserGroup(final String userId, final String baseSiteId, final String authToken, final CustomerDTO customer)
//	{
//		HttpHeaders headers = prepareHttpHeaders(authToken);
//		customer.getRoles().forEach(role -> {
//			System.out.println("YQY0: " + role);
//			Map<String, String> urlParams = new HashMap<>();
//			urlParams.put("baseSiteId", baseSiteId);
//			urlParams.put("groupId", role);
//			final MemberListDTO memberList = DTOConverter.convertMemberList(customer);
//			System.out.println("YQY1: " + memberList );
//			HttpEntity<MemberListDTO> entity = new HttpEntity<>(memberList, headers);
//			restTemplate.exchange(UriComponentsBuilder.fromUriString(userServiceGateway + saveUserGroupPath).buildAndExpand(urlParams).toUri(),
//					HttpMethod.PUT, entity, MemberListDTO.class);
//		});
//	}

	private HttpHeaders prepareHttpHeaders(final String authToken)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", authToken);
		return headers;
	}
}
