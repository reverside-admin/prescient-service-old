package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.ItcsSystemZone;
import za.co.prescient.model.ItcsTagRead;

@Repository
public interface ItcsSystemZoneRepository extends JpaRepository<ItcsSystemZone, Long> {
}
