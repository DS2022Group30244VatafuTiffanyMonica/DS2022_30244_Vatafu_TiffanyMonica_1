package sd.sdbackendv2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.sdbackendv2.Entity.Device;
import sd.sdbackendv2.Entity.Person;
import sd.sdbackendv2.Repository.DeviceRepository;
import sd.sdbackendv2.Repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final PersonRepository userRepo;
    private final DeviceRepository deviceRepo;

    @Autowired
    public UserService(PersonRepository userRepo, DeviceRepository deviceRepo) {
        this.userRepo = userRepo;
        this.deviceRepo = deviceRepo;
    }

    public Person addUser(Person newUser){
        return userRepo.save(newUser);
    }

    public List<Person> getAllUsers(){
        return userRepo.findAll();
    }
    public Optional<Person> getOneUserById(Long id){
        return Optional.ofNullable(userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException()));
    }
    public Person updateUser(Long id,Person updatedUser){
        return userRepo.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setRole(updatedUser.getRole());
                    user.setUserpassword(updatedUser.getUserpassword());
                    return userRepo.save(user);
                })
                .orElseGet(() -> {
                    return userRepo.save(updatedUser);
                });
    }
    public Optional<Person> getOneUserByNameAndPassword(String username,String psw){
        return Optional.ofNullable(userRepo.findByUsernameAndUserpassword(username,psw)
                .orElseThrow(() -> new UserNotFoundException()));
    }
    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }
    public Person mapDeviceToUser(Long user_id,Long device_id){
        Set<Device> deviceSet = null;
        Person user = userRepo.findById(user_id).get();
        Device dev = deviceRepo.findById(device_id).get();
        deviceSet = user.getDevice();
        deviceSet.add(dev);
        user.setDevice(deviceSet);
        return userRepo.save(user);
    }
}
