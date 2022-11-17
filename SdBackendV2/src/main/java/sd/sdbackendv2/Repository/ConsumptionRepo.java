package sd.sdbackendv2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.sdbackendv2.Entity.Consumption;
import sd.sdbackendv2.Entity.Person;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConsumptionRepo extends JpaRepository<Consumption,Long> {
    List<Consumption> findByDeviceIdAndTimestampIsBetweenOrderByTimestamp(Long id,Date dateStart,Date dateEnd);

}
