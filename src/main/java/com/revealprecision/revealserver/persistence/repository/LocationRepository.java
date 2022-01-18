package com.revealprecision.revealserver.persistence.repository;

import com.revealprecision.revealserver.persistence.domain.Location;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

  List<Location> findByGeographicLevelIdentifier(UUID geographicLevelId);

  @Query(value =
      "SELECT l FROM Location l WHERE (lower(l.name) like lower(concat('%', :param, '%'))) AND l.entityStatus='ACTIVE'")
  Page<Location> findAlLByCriteria(@Param("param") String param, Pageable pageable);

  @Query(value = "SELECT COUNT(l) FROM Location l WHERE (lower(l.name) like lower(concat('%', :param, '%'))) AND l.entityStatus='ACTIVE'")
  long findAllCountByCriteria(@Param("param") String param);
}
