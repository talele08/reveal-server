package com.revealprecision.revealserver.persistence.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Type;

@FieldNameConstants
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntityTag {

  @Id
  @GeneratedValue
  private UUID identifier;

  @Column(unique = true)
  private String tag;

  private String valueType;

  private String definition;

  @Type(type = "list-array")
  private List<String> aggregationMethod;

  private boolean isAggregate;

  private boolean simulationDisplay;

}
