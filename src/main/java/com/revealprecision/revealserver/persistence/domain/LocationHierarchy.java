package com.revealprecision.revealserver.persistence.domain;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.UniqueElements;

@FieldNameConstants
@Entity
@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/*@TypeDef(
    name = "list-array",
    typeClass = ListArrayType.class
)*/
@TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
)
@SQLDelete(sql = "UPDATE location_hierarchy SET entity_status = 'DELETED' where identifier=?")
@Where(clause = "entity_status='ACTIVE'")
public class LocationHierarchy extends AbstractAuditableEntity {

  @Id
  @GeneratedValue
  private UUID identifier;

  @NotBlank(message = "location_hierarchy name is required and must not be blank")
  private String name;

  @NotEmpty(message = "node order list  is required and must not be empty")
  @UniqueElements(message = "duplicate nodes in hierarchy")
  //@Type(type = "list-array")
  @Type(type = "string-array")
  @Column(
          name = "node-order",
          columnDefinition = "varchar[]"
  )
  private List<String> nodeOrder;

  @OneToMany(mappedBy = "locationHierarchy")
  private List<LocationRelationship> locationRelationships;

  @OneToMany(mappedBy = "locationHierarchy", cascade = CascadeType.REMOVE)
  private Set<Plan> plans;
}
