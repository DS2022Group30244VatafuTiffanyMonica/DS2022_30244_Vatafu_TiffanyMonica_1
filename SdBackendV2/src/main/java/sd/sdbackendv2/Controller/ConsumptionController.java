package sd.sdbackendv2.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sd.sdbackendv2.Entity.Consumption;
import sd.sdbackendv2.Entity.Device;
import sd.sdbackendv2.Service.ConsumeService;
import sd.sdbackendv2.Service.DeviceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
public class ConsumptionController {

    private final ConsumeService consumeService;

    @Autowired
    public ConsumptionController(ConsumeService consumeService) {
        this.consumeService = consumeService;
    }

    @PostMapping("/device/{dev_id}/consume")
    public Consumption addConsume(
            @PathVariable Long dev_id,
            @RequestBody Consumption cons) {
      return  consumeService.addConsume(dev_id,cons);
    }

    @GetMapping("/consumes")
    public List<Consumption> getAll() {
        return consumeService.getAllConsumptions();
    }

    @GetMapping("/consumes/{id}")
    public Optional<Consumption> getOne(@PathVariable Long id) {
        return consumeService.getConsumeById(id);
    }
    @GetMapping("device/{id}/consumes/{startDate}/{endDate}")
    public List<Consumption> getBetween(
            @PathVariable Long id,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date endDate) {
        return consumeService.getConsumptionDateOfDevice(id,startDate,endDate);
    }

    @DeleteMapping("consumes/{id}")
    public void deleteConsume(@PathVariable Long id) {
        consumeService.deleteConsume(id);
    }
}