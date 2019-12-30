package cx.sap.panda.unitservice.facades.impl;

import cx.sap.panda.unitservice.clients.UnitClient;
import cx.sap.panda.unitservice.clients.UserClient;
import cx.sap.panda.unitservice.dto.CustomerDTO;
import cx.sap.panda.unitservice.dto.UnitDTO;
import cx.sap.panda.unitservice.exceptions.errors.exception.UnitServiceException;
import cx.sap.panda.unitservice.facades.UnitFacade;
import cx.sap.panda.unitservice.results.dto.ResultDTO;
import cx.sap.panda.unitservice.results.utils.ResultDTOUtil;
import cx.sap.panda.unitservice.utils.DTOConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static cx.sap.panda.unitservice.exceptions.errors.impl.UnitServiceErrors.PARENT_UNIT_NOT_FOUND;
import static cx.sap.panda.unitservice.exceptions.errors.impl.UnitServiceErrors.UNIT_ALREADY_EXIST;
import static cx.sap.panda.unitservice.exceptions.errors.impl.UnitServiceErrors.UNIT_NOT_EXIST;

@Component
public class DefaultUnitFacade implements UnitFacade
{
	@Autowired
	private UserClient userClient;

	@Autowired
	private UnitClient unitClient;

	@Override
	public ResultDTO getUnitsByUser(final String userId, final String baseSiteId, final String authToken)
	{
		List<UnitDTO> unitList = unitClient.getUnitsByUser(userId);
		return ResultDTOUtil.success(unitList);
	}

	@Override
	public ResultDTO createUnit(final String userId, final String baseSiteId, final String authToken, final UnitDTO unit)
	{
		List<UnitDTO> allUnits = unitClient.getAllUnits();
		if (allUnits.stream().parallel().filter(u -> u.getUnitId().equals(unit.getUnitId())).findAny().isPresent())
		{
			throw new UnitServiceException(UNIT_ALREADY_EXIST);
		} else if (allUnits.stream().parallel().filter(u -> u.getUnitId().equals(unit.getParentUnit())).findAny()
				.isEmpty())
		{
			throw new UnitServiceException(PARENT_UNIT_NOT_FOUND);
		}
		UnitDTO result = unitClient.creatUnit(userId, unit);
		return ResultDTOUtil.success(result);
	}

	@Override
	public ResultDTO createCustomerForUnit(final String userId, final String baseSiteId, final String unitId, final String authToken, final CustomerDTO customer)
	{

		UnitDTO unit = unitClient.getUnitById(unitId);
		if (Objects.isNull(unit) || StringUtils.isBlank(unit.getUnitId()))
		{
			throw new UnitServiceException(UNIT_NOT_EXIST);
		}
		userClient.createCustomer(userId, baseSiteId, authToken, DTOConverter.convertCustomer(customer));
//			userClient.setCustomerToUserGroup(userId,baseSiteId,authToken, customer);
		unitClient.createCustomerForUnit(unitId, customer);
		UnitDTO unitResult = unitClient.getUnitById(unitId);
		return ResultDTOUtil.success(unitResult);
	}


}
