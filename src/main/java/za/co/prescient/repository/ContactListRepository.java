package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.ContactList;

import java.util.List;


@Repository
public interface ContactListRepository extends JpaRepository<ContactList,Long> {
    @Query("select cl from ContactList cl where cl.owner.id=?1")
    List<ContactList> findContactsByOwner(Long userId);
}
