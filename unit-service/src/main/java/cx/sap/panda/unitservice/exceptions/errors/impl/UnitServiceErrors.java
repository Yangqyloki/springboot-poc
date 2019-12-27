package cx.sap.panda.unitservice.exceptions.errors.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import cx.sap.panda.unitservice.exceptions.errors.Error;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UnitServiceErrors implements Error
{

	INVALID_EMAIL_ADDRESS("9999", "Email address is invalid"),
	INVALID_MOBILE_NUMBER("9998", "Mobile number is invalid"),
	USER_NOT_ADMIN("9997", "User is not unit administrator"),
	UNIT_ALREADY_EXIST("9996", "Unit is already existed"),
	UNIT_NOT_EXIST("9995", "Unit is already existed"),
	PARENT_UNIT_NOT_FOUND("9994", "Parent unit not exist");

	private final String code;
	private final String message;

	UnitServiceErrors(final String code, final String message)
	{
		this.code = code;
		this.message = message;
	}

	public String getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}
}
