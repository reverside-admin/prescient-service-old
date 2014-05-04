package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.Guest;
import za.co.prescient.model.GuestCard;
import za.co.prescient.model.itcs.ItcsTagRead;

import java.util.List;

@Repository
public interface GuestCardRepository extends JpaRepository<GuestCard, Long> {

    @Query("select gc.guest from GuestCard gc where gc.card.id in (?1)")
    public List<Guest> findGuestsWithTags(List<Integer> tags);

    @Query("select gca.guest from GuestCard gca where gca.card in (?1)")
    public List<GuestCard> findGuestsWithTagsInAZone(List<ItcsTagRead> tags);

    @Query("select gca from GuestCard gca where gca.guest.id = ?1")
    public GuestCard findGuestCardByGuestId(Long guestId);
}
