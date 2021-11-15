package proyecto.business.exceptions;

public class IncompleteObjectException extends Exception{
    private String message;

    public IncompleteObjectException(String message) {
        super(message);
        this.message = message;
    }

    public IncompleteObjectException(String message, String InternalMessage) {
        super(message);
        this.message = InternalMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
