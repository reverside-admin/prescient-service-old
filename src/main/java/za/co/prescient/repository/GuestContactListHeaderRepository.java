package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.GuestContactListHeader;


@Repository
public interface GuestContactListHeaderRepository extends JpaRepository<GuestContactListHeader,Long> {
}
