package cx.sap.panda.exception;

public class InsufficientUserRightsException extends EpcException {

    public InsufficientUserRightsException() {
    }

    public InsufficientUserRightsException(final String message) {
        super(message);
    }

    public InsufficientUserRightsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InsufficientUserRightsException(final Throwable cause) {
        super(cause);
    }
}
