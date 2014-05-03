package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.ContactList;


@Repository
public interface ContactListRepository extends JpaRepository<ContactList,Long> {
}
