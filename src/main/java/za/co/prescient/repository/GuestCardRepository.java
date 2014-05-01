package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.GuestCard;

@Repository
public interface GuestCardRepository extends JpaRepository<GuestCard, Long> {

    @Deprecated
    @Query("select gc from GuestCard gc where gc.magStripeNo = ?1")
    GuestCard findAGuestCard(String magStripeNo);

    GuestCard findByMagStripeNo(String magStripeNo);

}
