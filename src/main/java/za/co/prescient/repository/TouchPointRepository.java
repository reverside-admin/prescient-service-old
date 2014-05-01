package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.TouchPoint;

import java.util.List;

@Repository
public interface TouchPointRepository extends JpaRepository<TouchPoint, Long> {

    //TODO : remove param annotation
    public List<TouchPoint> findTouchPointByDepartmentId(@Param("departmentId") Long departmentId);

}
