package pl.coderslab.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.domain.model.Donation;
import pl.coderslab.charity.utils.SQLConstants;

public interface DonationRepository extends JpaRepository<Donation,Long> {
    @Query(nativeQuery = true, value = "SELECT SUM(quantity) FROM " +  SQLConstants.DONATION)
    int sumDonatedBags();

//    @Query(nativeQuery = true, value = "SELECT count(*) FROM " +  SQLConstants.DONATION)
    int countDonationsBy();
}
