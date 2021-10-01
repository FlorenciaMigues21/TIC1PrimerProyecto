package proyecto.business.exceptions;

public class InvalidUserInformation extends Exception{
    private String message;
    public InvalidUserInformation(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
