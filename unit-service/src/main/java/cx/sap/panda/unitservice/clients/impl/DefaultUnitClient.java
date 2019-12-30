package cx.sap.panda.unitservice.clients.impl;

import cx.sap.panda.unitservice.clients.UnitClient;
import cx.sap.panda.unitservice.dao.UnitDao;
import cx.sap.panda.unitservice.dto.CustomerDTO;
import cx.sap.panda.unitservice.dto.NotificationDTO;
import cx.sap.panda.unitservice.dto.OccCustomerDTO;
import cx.sap.panda.unitservice.dto.UnitDTO;
import cx.sap.panda.unitservice.rabbitmq.QueueProducer;
import cx.sap.panda.unitservice.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultUnitClient implements UnitClient
{
	@Autowired
	private UnitDao unitDao;

	@Autowired
	private QueueProducer queueProducer;

	@Override
	public List<UnitDTO> getUnitsByUser(final String userId)
	{
		return unitDao.findUnitsByUser(userId);
	}

	@Override
	public UnitDTO creatUnit(final String userId, final UnitDTO unit)
	{
		return unitDao.createUnit(unit);
	}

	@Override
	public List<UnitDTO> getAllUnits(){
		return unitDao.findAllUnits();
	}

	@Override
	public UnitDTO getUnitById(final String unitId)
	{
		return unitDao.findUnitByUnitId(unitId);
	}

	@Override
	public void createCustomerForUnit(final String unitId, final CustomerDTO customer)
	{
		System.out.println("createCustomerForUnit");
		unitDao.saveUnitCustomer(unitId, customer);

		final OccCustomerDTO message = DTOConverter.convertCustomer(customer);
		try {
			queueProducer.produce(new NotificationDTO(NotificationDTO.CREATE_USER, message));
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

}
