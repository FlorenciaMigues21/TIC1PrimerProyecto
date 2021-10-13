package proyecto.business.exceptions;

public class PublicationsLoadError extends Exception{
    private String message;

    public PublicationsLoadError(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
