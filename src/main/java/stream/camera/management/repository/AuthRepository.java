package stream.camera.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stream.camera.management.model.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {
    Auth findByUuid(String uuid);
}
