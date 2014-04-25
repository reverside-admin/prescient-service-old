package za.co.prescient.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.ItcsTagRead;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface ItcsTagReadRepository extends JpaRepository<ItcsTagRead, Long> {

    List<ItcsTagRead> findByZone(int zoneId);

    @Query("select  distinct t.guestCard from ItcsTagRead t where t.zone = ?1")
    List<ItcsTagRead> findTagsInZone(int zoneId);


    /*@Query("select itr from ItcsTagRead itr where itr.guestCard=?1 ")
    List<ItcsTagRead> findGuestCardHistory(Integer guestCardId);*/


//    @Query("select itr from ItcsTagRead itr where itr.guestCard=?1 and itr.tagReadDatetime = (select max (itr.tagReadDatetime) from ItcsTagRead itr where itr.guestCard=?1) ")

    @Query("select itr from ItcsTagRead itr where itr.guestCard=?1 and itr.tagReadDatetime =(select max(itr.tagReadDatetime) from ItcsTagRead itr where itr.tagReadDatetime between '2010-04-01 00:00:00' and current_timestamp and itr.guestCard=?1) ")
    ItcsTagRead findGuestCardHistory(Integer guestCardId);


}
