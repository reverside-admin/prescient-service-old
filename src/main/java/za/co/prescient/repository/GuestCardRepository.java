package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.Guest;
import za.co.prescient.model.GuestCard;


import java.util.List;

@Repository
public interface GuestCardRepository extends JpaRepository<GuestCard, Long> {

    @Query("select gc.guest from GuestCard gc where gc.card.id in (?1)")
    public List<Guest> findGuestsWithTags(List<Integer> tags);

//    @Query("select gca.guestProfileDetail from GuestCardAllocation gca where gca.guestCardId in (?1)")
//    public List<GuestCard> findGuestsWithTagsInAZone(List<ItcsTagRead> tags);

//    @Query("select gca from GuestCardAllocation gca where gca.guestProfileDetail.id = ?1")
//    public GuestCard findGuestCardByGuestId(Long guestId);
}
