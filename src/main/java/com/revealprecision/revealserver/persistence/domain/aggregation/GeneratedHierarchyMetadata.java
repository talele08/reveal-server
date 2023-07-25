package com.revealprecision.revealserver.persistence.domain.aggregation;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.TypeDef;

@FieldNameConstants
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class GeneratedHierarchyMetadata {

  @Id
  @GeneratedValue
  private int id;

  private String locationIdentifier;
  private String tag;
  private Double value;

  private String fieldType;

  @ManyToOne
  private GeneratedHierarchy generatedHierarchy;

}
