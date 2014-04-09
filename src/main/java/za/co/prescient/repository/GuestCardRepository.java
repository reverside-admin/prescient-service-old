package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.GuestCard;

@Repository
public interface GuestCardRepository extends JpaRepository<GuestCard, Long> {

}
