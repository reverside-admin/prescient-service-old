package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.prescient.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

}
