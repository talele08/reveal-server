package com.revealprecision.revealserver.api.v1.dto.request;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elasticsearch.search.SearchHit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataFilterRequest {

  @NotNull
  private UUID hierarchyIdentifier;
  private UUID locationIdentifier;
  private List<EntityFilterRequest> entityFilters;
  private SearchHit lastHit;
  private List<String> filterGeographicLevelList;
  private List<String> inactiveGeographicLevelList;
  private boolean includeInactive;

}
