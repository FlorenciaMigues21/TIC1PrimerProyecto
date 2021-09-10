package proyecto.business.exceptions;

public class UserNotFound extends Exception{
    String message;
    public UserNotFound(String message){
        super(message);
    }
    public String getMessage(){
        return message;
    }
}
