package cx.sap.panda.unitservice.exceptions.errors.exception;

import cx.sap.panda.unitservice.exceptions.errors.Error;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Data
public class UnitServiceException extends ResponseStatusException
{
	private String code;

	public UnitServiceException(Error error) {
		this(error.getMessage(), error.getCode());
	}

	private UnitServiceException(String message, String code) {
		super(HttpStatus.BAD_REQUEST,message);
		this.code = code;
	}
}
