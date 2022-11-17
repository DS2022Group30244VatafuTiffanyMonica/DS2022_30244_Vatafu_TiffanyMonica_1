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
public class DeviceService {
    private final PersonRepository userRepo;
    private final DeviceRepository deviceRepo;

    @Autowired
    public DeviceService(PersonRepository Urepo, DeviceRepository Drepo) {
        this.userRepo = Urepo;
        this.deviceRepo = Drepo;
    }
    public Device addDevice(Device newDev){
        return deviceRepo.save(newDev);
    }
    public List<Device> getAllDevices() {
        return deviceRepo.findAll();
    }
    public Optional<Device> getOneDeviceById(Long id){
        return Optional.ofNullable(deviceRepo.findById(id).orElseThrow(()-> new DeviceNotFoundException(id)));

    }
    public Device updateDevice(Long id,Device updatedDevice){
        return deviceRepo.findById(id)
                .map(dev -> {
                    dev.setDescription(updatedDevice.getDescription());
                    dev.setAddress(updatedDevice.getAddress());
                    dev.setConsumptions(updatedDevice.getConsumptions());
                    dev.setLocation(updatedDevice.getLocation());
                    return deviceRepo.save(dev);
                })
                .orElseGet(() -> {
                    return deviceRepo.save(updatedDevice);
                });
    }
    public void deleteDevice(Long id){
        Device dev = deviceRepo.findById(id).get();
        Set<Person> users = dev.getUsers();
        for (Person user : users) {
            user.removeDevice(id);
            userRepo.save(user);
        }
        deviceRepo.deleteById(id);
    }

}
