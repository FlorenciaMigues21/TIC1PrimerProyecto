package proyecto.business.exceptions;

public class UserAlreadyExist extends Exception{
    String message;
    public UserAlreadyExist(String message){
        super(message);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
