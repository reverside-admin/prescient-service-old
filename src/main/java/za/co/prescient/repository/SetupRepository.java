package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.co.prescient.model.Setup;

import java.util.List;

@Repository
public interface SetupRepository extends JpaRepository<Setup, Long> {

    @Query("select s from Setup s where s.touchPoint.id = ?1")
    List<Setup> findByTouchPointId(Long touchPointId);

    @Modifying
    @Transactional
    @Query("update Setup s set s.indicator=null where s.touchPoint.id = ?1")
    void resetIndicatorForTouchPointId(Long touchPointId);

    @Modifying
    @Transactional
    @Query("update Setup s set s.indicator=true where s.id = ?1")
    void setIndicator(Long setupId);

    @Query("select s from Setup s where s.touchPoint.id = ?1 and s.indicator=true")
    Setup findCurrentSetupOfATouchPoint(Long tpid);

}
