package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.GuestStayHistory;

import java.util.List;

@Repository
public interface GuestStayHistoryRepository extends JpaRepository<GuestStayHistory, Long> {

    // TODO: Add filter to get latest history
    @Query("select gsh from GuestStayHistory gsh where gsh.guest.id = ?1")
    public GuestStayHistory findByGuestId(Integer guestId );


    @Query("select gsh from GuestStayHistory gsh where gsh.currentStayIndicator = true and gsh.hotel.id= ?1 ")
    public List<GuestStayHistory> findCheckedInByHotelId(Long hotelId);

}
