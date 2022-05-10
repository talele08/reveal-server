package com.revealprecision.revealserver.api.v1.dto.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColumnData implements Serializable {

  private Double value;
  private Boolean isPercentage;
}
