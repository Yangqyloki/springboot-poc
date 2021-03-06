package cx.sap.panda.unitservice.results.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO<T> {

	private String code;

	private String msg;

	private T data;
}
