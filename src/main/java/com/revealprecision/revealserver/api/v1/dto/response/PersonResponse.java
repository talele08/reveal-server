package com.revealprecision.revealserver.api.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.revealprecision.revealserver.api.v1.dto.request.PersonRequest.Gender;
import com.revealprecision.revealserver.api.v1.dto.request.PersonRequest.Name;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponse {
  UUID identifier;

  Boolean active;
  Name name;
  Gender gender;
  LocalDate birthDate;
  Set<Group> groups;
  Long count;
}
