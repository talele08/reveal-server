package com.revealprecision.revealserver.api.v1.dto.factory;

import com.revealprecision.revealserver.api.v1.dto.response.LocationBulkDetailResponse;
import com.revealprecision.revealserver.enums.BulkEntryStatus;
import com.revealprecision.revealserver.enums.EntityStatus;
import com.revealprecision.revealserver.persistence.projection.LocationBulkProjection;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocationBulkDetailResponseFactory {

  public static LocationBulkDetailResponse fromProjection(LocationBulkProjection projection) {
    String name = null;
    BulkEntryStatus status;
    if (projection.getEntityStatus() == null) {
      status = BulkEntryStatus.ERROR;
    } else {
      if (projection.getEntityStatus() == EntityStatus.ACTIVE) {
        status = BulkEntryStatus.SUCCESSFUL;
        name = projection.getName();
      } else {
        status = BulkEntryStatus.ERROR;
      }
    }
    return LocationBulkDetailResponse.builder().name(name)
        .status(status)
        .build();
  }

  public static Page<LocationBulkDetailResponse> fromProjectionPage(
      Page<LocationBulkProjection> entries, Pageable pageable) {
    var response = entries.getContent()
        .stream()
        .map(LocationBulkDetailResponseFactory::fromProjection).collect(Collectors.toList());
    return new PageImpl<>(response, pageable, entries.getTotalElements());
  }
}
