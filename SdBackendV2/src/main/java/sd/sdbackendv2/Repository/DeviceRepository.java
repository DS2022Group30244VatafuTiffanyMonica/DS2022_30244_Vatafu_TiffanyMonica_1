package sd.sdbackendv2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.sdbackendv2.Entity.Device;

public interface DeviceRepository extends JpaRepository<Device,Long> {
}
