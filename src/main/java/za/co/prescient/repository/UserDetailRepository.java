package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

    public UserDetail findByUserNameAndPassword(String userName, String password);

    public UserDetail findByUserName(String userName);

}

