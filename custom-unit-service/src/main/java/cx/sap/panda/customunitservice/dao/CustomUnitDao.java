package cx.sap.panda.customunitservice.dao;

import cx.sap.panda.customunitservice.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUnitDao
{
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<AddressDTO> findAddressByUnit(final String unitId) {
		final Query queryUnit = new Query();
		queryUnit.addCriteria(Criteria.where("unitId").is(unitId));
		return mongoTemplate.find(queryUnit, AddressDTO.class, "b2b_commerce_org.address");
	}
}
