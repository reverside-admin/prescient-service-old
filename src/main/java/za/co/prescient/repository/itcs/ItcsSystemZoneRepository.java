package za.co.prescient.repository.itcs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.itcs.ItcsSystemZone;

@Repository
public interface ItcsSystemZoneRepository extends JpaRepository<ItcsSystemZone, Long> {
}
