package sd.sdbackendv2.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sd.sdbackendv2.Entity.Device;
import sd.sdbackendv2.Entity.Person;
import sd.sdbackendv2.Service.DeviceNotFoundException;
import sd.sdbackendv2.Service.DeviceService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin

public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }


    @PostMapping("/device")
    public Device addDevice(@RequestBody Device newDev) {
        return deviceService.addDevice(newDev);
    }
    @GetMapping("/devices")
    public List<Device> getAll() {
        return deviceService.getAllDevices();
    }
    @GetMapping("/devices/{id}")
    public Optional<Device> getOne(@PathVariable Long id){
        return deviceService.getOneDeviceById(id);
    }
    @PutMapping("/device/{id}")
    public Device update(@PathVariable Long id,@RequestBody Device updatedDevice){
        return deviceService.updateDevice(id,updatedDevice);
    }
    @DeleteMapping("devices/{id}")
    public void deleteDevice(@PathVariable Long id){
        deviceService.deleteDevice(id);
    }

}
