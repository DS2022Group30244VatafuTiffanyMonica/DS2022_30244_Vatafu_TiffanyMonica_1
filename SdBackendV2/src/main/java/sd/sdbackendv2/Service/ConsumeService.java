package sd.sdbackendv2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.sdbackendv2.Entity.Consumption;
import sd.sdbackendv2.Entity.Device;
import sd.sdbackendv2.Repository.ConsumptionRepo;
import sd.sdbackendv2.Repository.DeviceRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ConsumeService {
    private final DeviceRepository deviceRepo;
    private final ConsumptionRepo consRepo;

    @Autowired

    public ConsumeService(DeviceRepository deviceRepo, ConsumptionRepo consRepo) {
        this.deviceRepo = deviceRepo;
        this.consRepo = consRepo;
    }

    public Consumption addConsume(Long dev_id, Consumption cons){
        Set<Consumption> consumeSet = null;
        Device dev = deviceRepo.findById(dev_id).get();
        cons.setDevice(dev);
        return consRepo.save(cons);
    }
    public List<Consumption> getAllConsumptions(){
        return consRepo.findAll();
    }
    public Optional<Consumption> getConsumeById(Long id){
        return Optional.ofNullable(consRepo.findById(id).orElseThrow(() -> new DeviceNotFoundException(id)));
    }
    public List<Consumption> getConsumptionDateOfDevice(Long id, Date startDate,Date endDate){
        return consRepo.findByDeviceIdAndTimestampIsBetweenOrderByTimestamp(id,startDate,endDate);

    }

    public void deleteConsume(Long id){
        Consumption consumption = consRepo.findById(id).get();
        Device dev = consumption.getDevice();
        deviceRepo.delete(dev);
        consRepo.deleteById(id);
    }
}
