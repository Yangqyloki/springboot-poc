package sap.commerce.org.epcservice.exception;

public class EpcException extends RuntimeException{
    public EpcException() {
    }

    public EpcException(final String message) {
        super(message);
    }

    public EpcException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EpcException(final Throwable cause) {
        super(cause);

    }
}