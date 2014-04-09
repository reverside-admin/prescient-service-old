package za.co.prescient.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.ItcsTagRead;

import java.util.List;

@Repository
public interface ItcsTagReadRepository extends JpaRepository<ItcsTagRead, Long> {

    List<ItcsTagRead> findByZone(int zoneId);

    @Query("select t from ItcsTagRead t where t.zone = ?1")
    List<ItcsTagRead> findTagsInZone(int zoneId);

}
