package proyecto.business.exceptions;

public class DataBaseError extends Exception{
    private String message;

    public DataBaseError(String message,String logError) {
        super(logError);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getLogError(){
        return super.getMessage();
    }
}
