package com.revealprecision.revealserver.api.v1.dto.request;

import com.revealprecision.revealserver.enums.SignEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SearchValue {

  private Object value;
  private SignEntity sign;
}
