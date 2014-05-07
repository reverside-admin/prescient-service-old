package za.co.prescient.repository.local;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.Guest;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long>{

    @Query("select guest from Guest guest where guest.hotel.id=?1")
    public List<Guest> findByHotelId(Long hotelId);
}
