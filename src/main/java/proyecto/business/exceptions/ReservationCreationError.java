package proyecto.business.exceptions;

public class ReservationCreationError extends Exception{
    private String message;

    public ReservationCreationError(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
