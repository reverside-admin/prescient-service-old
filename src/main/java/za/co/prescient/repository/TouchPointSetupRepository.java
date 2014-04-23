package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.co.prescient.model.TouchPointSetup;

import java.util.List;

@Repository
public interface TouchPointSetupRepository extends JpaRepository<TouchPointSetup, Long> {

    @Query("select tps from TouchPointSetup tps where tps.touchPointId = ?1")
    List<TouchPointSetup> findTpSetupsByTpId(Long tpid);

    @Modifying
    @Transactional
    @Query("update TouchPointSetup tps set tps.setupIndicator=null where tps.touchPointId = ?1")
    void resetSetupIndicator(Long tpId);

    @Modifying
    @Transactional
    @Query("update TouchPointSetup tps set tps.setupIndicator=true where tps.id = ?1")
    void setCurrentSetupIndicator(Long setupId);

    @Query("select tps from TouchPointSetup tps where tps.touchPointId = ?1 and tps.setupIndicator=true")
    TouchPointSetup findCurrentTpSetupsByTpId(Long tpid);

}
