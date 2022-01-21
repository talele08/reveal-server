package com.revealprecision.revealserver.persistence.domain;

import com.revealprecision.revealserver.enums.PlanInterventionTypeEnum;
import com.revealprecision.revealserver.enums.PlanStatusEnum;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.engine.internal.Cascade;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
@SQLDelete(sql = "UPDATE plan SET entity_status = 'DELETED' where identifier=?")
@Where(clause = "entity_status='ACTIVE'")
public class Plan extends AbstractAuditableEntity {

  @Id
  @GeneratedValue
  private UUID identifier;
  private String name;
  private String title;
  private LocalDate date;
  private LocalDate effectivePeriodStart;
  private LocalDate effectivePeriodEnd;
  @Enumerated(EnumType.STRING)
  private PlanStatusEnum status;
  @Enumerated(EnumType.STRING)
  private PlanInterventionTypeEnum interventionType;

  @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
  private Set<Goal> goals;

  @OneToMany(mappedBy = "plan")
  private Set<Task> tasks;
}