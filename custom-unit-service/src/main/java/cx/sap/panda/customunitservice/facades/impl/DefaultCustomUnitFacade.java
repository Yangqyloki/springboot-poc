package cx.sap.panda.customunitservice.facades.impl;

import cx.sap.panda.customunitservice.clients.CustomUnitClient;
import cx.sap.panda.customunitservice.dto.AddressDTO;
import cx.sap.panda.customunitservice.dto.UnitDTO;
import cx.sap.panda.customunitservice.facades.CustomUnitFacade;
import cx.sap.panda.customunitservice.results.dto.ResultDTO;
import cx.sap.panda.customunitservice.results.utils.ResultDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Component
public class DefaultCustomUnitFacade implements CustomUnitFacade
{
	@Autowired
	private CustomUnitClient customUnitClient;

	@Override
	public ResultDTO getUnitsByUser(final String userId, final String baseSiteId, final String authToken)
	{
		List<UnitDTO> unitList = customUnitClient.getUnitsByUser(userId,baseSiteId,authToken);
		unitList.stream().forEach(this::addUnitAddress);
		return ResultDTOUtil.success(unitList);
	}

	private void addUnitAddress(final UnitDTO unit)
	{
		List<AddressDTO> addresses = customUnitClient.getAddressesByUnit(unit.getUnitId());
		if(CollectionUtils.isEmpty(addresses)){
			unit.setAddresses(Collections.EMPTY_LIST);
		}
		unit.setAddresses(addresses);
	}
}
