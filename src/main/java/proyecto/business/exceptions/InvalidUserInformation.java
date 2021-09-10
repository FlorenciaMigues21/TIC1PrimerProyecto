package proyecto.business.exceptions;

public class InvalidUserInformation extends Exception{
    String message;
    public InvalidUserInformation(String message){
        super(message);
    }
    public String getMessage(){
        return message;
    }
}
