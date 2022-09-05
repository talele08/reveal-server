package com.revealprecision.revealserver.persistence.repository;

import com.revealprecision.revealserver.persistence.domain.CountryCampaign;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryCampaignRepository extends JpaRepository<CountryCampaign, UUID> {

  @Query(value = "select c from CountryCampaign c where c.identifier in :identifiers")
  List<CountryCampaign> getAllByIdentifiers(Set<UUID> identifiers);
}
