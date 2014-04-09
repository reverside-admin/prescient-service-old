package za.co.prescient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.prescient.model.GuestCardAllocation;

@Repository
public interface GuestCardAllocationRepository extends JpaRepository<GuestCardAllocation, Long> {
}
