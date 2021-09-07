package business.exceptions;

public class UserAlreadyExist extends Exception{
    String message;
    public UserAlreadyExist(String message){
        super(message);
    }
    public String getMessage(){
        return message;
    }
}
