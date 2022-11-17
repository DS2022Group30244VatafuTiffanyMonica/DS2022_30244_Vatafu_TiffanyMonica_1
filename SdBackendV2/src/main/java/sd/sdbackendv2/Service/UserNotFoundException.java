package sd.sdbackendv2.Service;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException() {
        super("Could not find USER ");
    }

}
