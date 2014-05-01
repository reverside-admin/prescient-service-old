package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.GuestCardAllocation;
import za.co.prescient.model.GuestProfileDetail;
import za.co.prescient.model.ItcsTagRead;

import java.util.List;

@Repository
public interface GuestCardAllocationRepository extends JpaRepository<GuestCardAllocation, Long> {

    @Query("select gca.guestProfileDetail from GuestCardAllocation gca where gca.guestCardId in (?1)")
    public List<GuestProfileDetail> findGuestsWithTags(List<Integer> tags);

    @Query("select gca.guestProfileDetail from GuestCardAllocation gca where gca.guestCardId in (?1)")
    public List<GuestProfileDetail> findGuestsWithTagsInAZone(List<ItcsTagRead> tags);

    @Query("select gca from GuestCardAllocation gca where gca.guestProfileDetail.id = ?1")
    public GuestCardAllocation findGuestCardByGuestId(Long guestId);
}
