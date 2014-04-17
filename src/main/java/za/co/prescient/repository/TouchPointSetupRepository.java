package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.TouchPointSetup;

import java.util.List;

@Repository
public interface TouchPointSetupRepository extends JpaRepository<TouchPointSetup, Long> {

    @Query("select tps from TouchPointSetup tps where tps.touchPointId = ?1")
    List<TouchPointSetup> findTpSetupsByTpId(Long tpid);

}
