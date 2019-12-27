package cx.sap.panda.customunitservice.facades;

import cx.sap.panda.customunitservice.results.dto.ResultDTO;
import org.springframework.stereotype.Component;

@Component
public interface CustomUnitFacade
{
	ResultDTO getUnitsByUser(String userId, String baseSiteId, String authToken);
}
