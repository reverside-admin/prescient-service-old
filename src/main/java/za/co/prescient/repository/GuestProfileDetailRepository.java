package za.co.prescient.repository;

        import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.GuestProfileDetail;

        import java.util.List;

@Repository
public interface GuestProfileDetailRepository extends JpaRepository<GuestProfileDetail, Long>{

    public List<GuestProfileDetail> findGuestProfileDetailByHotelId(Long id);
}
