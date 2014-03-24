package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import za.co.prescient.model.TouchPoint;

@Repository
public interface TouchPointRepository extends JpaRepository<TouchPoint, Long> {
    public Iterable<TouchPoint> findTouchPointByHotelDepartmentId(@Param("departmentId") Long departmentId);

    public Iterable<TouchPoint> findTouchPointByHotelIdAndHotelDepartmentId(@Param("hotelId") Long hotelId, @Param("departmentId")  Long departmentId);


}
