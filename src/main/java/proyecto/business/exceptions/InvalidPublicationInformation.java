package proyecto.business.exceptions;

public class InvalidPublicationInformation extends Exception{
    private String message;

    public InvalidPublicationInformation(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
