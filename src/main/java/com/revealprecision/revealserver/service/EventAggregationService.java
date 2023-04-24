package com.revealprecision.revealserver.service;

import com.revealprecision.revealserver.api.v1.dto.response.EntityTagResponse;
import com.revealprecision.revealserver.api.v1.dto.response.LookupEntityTypeResponse;
import com.revealprecision.revealserver.constants.EntityTagDataTypes;
import com.revealprecision.revealserver.persistence.projection.EventAggregationNumericTagProjection;
import com.revealprecision.revealserver.persistence.repository.EventAggregationRepository;
import com.revealprecision.revealserver.props.EventAggregationProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventAggregationService {

  private final EventAggregationRepository eventAggregationRepository;
  private final EventAggregationProperties eventAggregationProperties;

  List<EventAggregationNumericTagProjection> uniqueTagsFromEventAggregationNumeric = new ArrayList<>();
  List<String> uniqueTagsFromEventAggregationStringCount = new ArrayList<>();

  public List<EntityTagResponse> getEventBasedTags(UUID entityTypeIdentifier) {

    List<EntityTagResponse> entityTagResponses = uniqueTagsFromEventAggregationNumeric.stream()

        .flatMap(
            eventAggregationNumericTagProjection ->
                Stream.of(getEntityTagResponse(
                        eventAggregationNumericTagProjection.getEventTagSum(), entityTypeIdentifier,
                        EntityTagDataTypes.DOUBLE),
                    getEntityTagResponse(
                        eventAggregationNumericTagProjection.getEventTagAverage(),
                        entityTypeIdentifier,
                        EntityTagDataTypes.DOUBLE),
                    getEntityTagResponse(
                        eventAggregationNumericTagProjection.getEventTagMedian(),
                        entityTypeIdentifier,
                        EntityTagDataTypes.DOUBLE))
        )

        .collect(Collectors.toList());
    entityTagResponses.addAll(uniqueTagsFromEventAggregationStringCount.stream().map(s ->
        getEntityTagResponse(
            s, entityTypeIdentifier,
            EntityTagDataTypes.DOUBLE)
    ).collect(Collectors.toList()));

    return entityTagResponses.stream()
        .filter(entityTagResponse -> !entityTagResponse.getTag()
            .matches(eventAggregationProperties.getExclusionListRegex()))
        .collect(Collectors.toList());

  }


  @Async
  public void syncTags() {
    uniqueTagsFromEventAggregationNumeric = eventAggregationRepository.getUniqueTagsFromEventAggregationNumeric();
    uniqueTagsFromEventAggregationStringCount = eventAggregationRepository.getUniqueTagsFromEventAggregationStringCount();
  }


  private EntityTagResponse getEntityTagResponse(
      String tagName, UUID entityTypeIdentifier, String dataTypes) {
    return EntityTagResponse.builder()
        .simulationDisplay(false)
        .isAggregate(true)
        .fieldType("tag")
        .addToMetadata(true)
        .isResultLiteral(true)
        .isGenerated(false)
        .resultExpression(null)
        .generationFormula(null)
        .lookupEntityType(
            LookupEntityTypeResponse.builder().identifier(entityTypeIdentifier).code("Location")
                .tableName("location").build())
        .valueType(dataTypes)
        .referenceFields(null)
        .identifier(UUID.randomUUID())
        .tag(tagName)
        .build();
  }
}
