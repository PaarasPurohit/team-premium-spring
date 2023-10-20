package com.nighthawk.spring_portfolio.mvc.astronomy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CelestialDataRepository extends CrudRepository<CelestialData, Long> {

    Optional<CelestialData> findById(String id);
    Iterable<CelestialData> findAll();
    List<CelestialData> findByDatesFromBetween(Date startDate, Date endDate);

}
