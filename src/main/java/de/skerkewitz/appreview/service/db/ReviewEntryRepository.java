package de.skerkewitz.appreview.service.db;

import de.skerkewitz.appreview.service.AppStoreCC;
import de.skerkewitz.appreview.service.ReviewEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Data access object for the review entry table.
 *
 * @author skerkewitz, 2016-03-21
 */
@Repository
@Transactional
public interface ReviewEntryRepository extends CrudRepository<ReviewEntry, Long> {

//    @Query("select first r from ReviewEntry r where r.appStoreId = :appStoreId and r.date = (select max(r.date) group by r.countryCode")
//    List<ReviewEntry> getNewestByCountryCode(@Param("appStoreId") Integer appStoreId);

    ReviewEntry findFirstByAppStoreIdAndCountryCodeOrderByDateDesc(@Param("appStoreId") Integer appStoreId, @Param("countryCode") AppStoreCC countryCode);
}
