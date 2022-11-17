package sd.sdbackendv2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sd.sdbackendv2.Entity.Device;
import sd.sdbackendv2.Entity.Person;
import sd.sdbackendv2.Repository.DeviceRepository;
import sd.sdbackendv2.Repository.PersonRepository;
import sd.sdbackendv2.Service.UserNotFoundException;
import sd.sdbackendv2.Service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(){
        return "Index working";
    }
    @PostMapping("/user")
    public Person addUser(@RequestBody Person newUser) {
        return userService.addUser(newUser);
    }
    @GetMapping("/users")
    public List<Person> getAll(){
        return userService.getAllUsers();
    }
    @GetMapping("/users/{id}")
    public Optional<Person> one(@PathVariable Long id){
        return userService.getOneUserById(id);
    }
    @PutMapping("/user/{id}")
    public Person update(@PathVariable Long id,@RequestBody Person updatedUser){
        return userService.updateUser(id,updatedUser);
    }
    @GetMapping("/user/{username}/{psw}")
    public Optional<Person> oneByName(@PathVariable String username,@PathVariable String psw){
        return userService.getOneUserByNameAndPassword(username,psw);
    }
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
    @PutMapping("/user/{user_id}/device/{device_id}")
    public Person assignDeviceToUser(
            @PathVariable Long user_id,
            @PathVariable Long device_id
    ){
        return userService.mapDeviceToUser(user_id,device_id);

    }
}

