package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Iterable<Department> findDepartmentByHotelId(@Param("hotelId") Long hotelId);
}
