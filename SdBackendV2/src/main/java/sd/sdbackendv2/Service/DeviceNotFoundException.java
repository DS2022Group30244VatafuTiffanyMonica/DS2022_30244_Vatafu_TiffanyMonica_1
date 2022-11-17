package sd.sdbackendv2.Service;

public class DeviceNotFoundException extends RuntimeException{
    DeviceNotFoundException(Long id) {
        super("Could not find USER " + id);
    }

}
