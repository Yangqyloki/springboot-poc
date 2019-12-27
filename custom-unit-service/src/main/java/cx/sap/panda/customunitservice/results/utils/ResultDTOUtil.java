package cx.sap.panda.customunitservice.results.utils;


import cx.sap.panda.customunitservice.results.dto.ResultDTO;

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
