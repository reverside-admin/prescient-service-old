package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
