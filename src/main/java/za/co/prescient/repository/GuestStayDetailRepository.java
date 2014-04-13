package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.GuestStayDetail;

@Repository
public interface GuestStayDetailRepository extends JpaRepository<GuestStayDetail, Long> {

    @Query("select gsd from GuestStayDetail gsd where gsd.guestId = ?1")
    public GuestStayDetail findGuestDetailByGId(Integer gId );

}
