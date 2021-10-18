package proyecto.business.exceptions;

public class ComentaryCreationError extends Exception{
    private String message;

    public ComentaryCreationError(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
