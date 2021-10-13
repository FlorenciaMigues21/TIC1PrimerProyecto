package proyecto.business.exceptions;

public class PublicationCreationError extends Exception{
    private String message;

    public PublicationCreationError(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
