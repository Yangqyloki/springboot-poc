package cx.sap.panda.unitservice.results.utils;

import cx.sap.panda.unitservice.exceptions.errors.impl.UnitServiceErrors;
import cx.sap.panda.unitservice.results.dto.ResultDTO;

public class ResultDTOUtil
{
	public static ResultDTO success(Object object){
		ResultDTO result = new ResultDTO();
		result.setData(object);
		result.setCode("200");
		result.setMsg("Success");
		return result;
	}


//	public static ResultDTO fail(UnitServiceErrors error){
//		ResultDTO result = new ResultDTO();
//		result.setCode(error.getCode());
//		result.setMsg(error.getMessage());
//		return result;
//	}
}
