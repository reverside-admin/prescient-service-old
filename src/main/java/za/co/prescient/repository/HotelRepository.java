package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.Hotel;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

}
