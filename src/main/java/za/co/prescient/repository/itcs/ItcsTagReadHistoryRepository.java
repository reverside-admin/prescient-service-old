package za.co.prescient.repository.itcs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.co.prescient.model.itcs.ItcsTagReadHistory;

import java.util.List;

public interface ItcsTagReadHistoryRepository extends JpaRepository<ItcsTagReadHistory, Long> {

    @Query("select itr from ItcsTagReadHistory itr where itr.guestCard=?1 and itr.tagReadDatetime in (select max(itr.tagReadDatetime) from ItcsTagReadHistory itr where itr.tagReadDatetime between '2010-04-01 00:00:00' and current_timestamp and itr.guestCard=?1 group by itr.itcsSystemZone.zoneId) ")
    List<ItcsTagReadHistory> findGuestHistory(Integer guestCardId);

}
