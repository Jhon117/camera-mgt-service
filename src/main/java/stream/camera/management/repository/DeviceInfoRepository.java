package stream.camera.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stream.camera.management.model.DeviceInfo;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, String> {
    DeviceInfo findByUuid(String uuid);
}
