package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.HotelDepartment;

@Repository
public interface HotelDepartmentRepository extends JpaRepository<HotelDepartment, Long> {
    public Iterable<HotelDepartment> findHotelDepartmentByHotelId(@Param("hotelId") Long hotelId);
}
