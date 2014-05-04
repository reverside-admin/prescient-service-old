package za.co.prescient.repository.itcs;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.itcs.ItcsTagRead;

import java.util.List;

@Repository
public interface ItcsTagReadRepository extends JpaRepository<ItcsTagRead, Long> {

    @Query("select  distinct itr.guestCard from ItcsTagRead itr where itr.itcsSystemZone.zoneId = ?1")
    List<Integer> findCurrentCardsInZone(Integer zoneId);


    @Query("select itr from ItcsTagRead itr where itr.guestCard=?1 and itr.tagReadDatetime =(select max(itr.tagReadDatetime) from ItcsTagRead itr where itr.tagReadDatetime between '2010-04-01 00:00:00' and current_timestamp and itr.guestCard=?1) ")
    ItcsTagRead findGuestCardHistory(Integer guestCardId);

}
