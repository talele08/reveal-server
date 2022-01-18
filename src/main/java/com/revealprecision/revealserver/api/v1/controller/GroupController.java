package com.revealprecision.revealserver.api.v1.controller;

import com.revealprecision.revealserver.api.v1.dto.factory.GroupResponseFactory;
import com.revealprecision.revealserver.api.v1.dto.request.GroupRequest;
import com.revealprecision.revealserver.api.v1.dto.response.GroupResponse;
import com.revealprecision.revealserver.enums.SummaryEnum;
import com.revealprecision.revealserver.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

  private final GroupService groupService;

  @Autowired
  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  @Operation(summary = "Fetch all groups", description = "Fetch all Groups", tags = {"Group"})
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<GroupResponse>> getGroups(
      @Parameter(description = "Page number to return") @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
      @Parameter(description = "Number of records per page") @RequestParam(defaultValue = "50", required = false) Integer pageSize,
      @RequestParam(name = "search", required = false) String searchParam,
      @RequestParam(name = "location_name", required = false) String locationName,
      @RequestParam(name = "name", required = false) String groupName,
      @RequestParam(name = "_summary", defaultValue = "true") SummaryEnum summary) {
    return  ResponseEntity.status(HttpStatus.OK).body( new PageImpl<>(
        groupService.getGroups(searchParam, groupName, locationName, pageNumber, pageSize).stream()
            .map((group) -> GroupResponseFactory.fromEntity(group, summary))
            .collect(Collectors.toList())));
  }


  @Operation(summary = "Fetch a group by identfier", description = "Fetch a group by identfier", tags = {
      "Group"})

  @GetMapping(value = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GroupResponse> getGroupByIdentifier(
      @Parameter(description = "Group identifier") @PathVariable("identifier") UUID groupIdentifier,
      @Parameter(description = "Show summary or full") @RequestParam(name = "summary", required = false, defaultValue = "TRUE") SummaryEnum summary) {
    return  ResponseEntity.status(HttpStatus.OK).body(GroupResponseFactory.fromEntity(groupService.getGroupByIdentifier(groupIdentifier),
        summary));
  }

  @Operation(summary = "Create a group", description = "Create a Group", tags = {"Group"})
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GroupResponse> createGroup(@RequestBody GroupRequest groupRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(GroupResponseFactory.fromEntity(groupService.createGroup(groupRequest),
        SummaryEnum.TRUE));
  }

  @Operation(summary = "Delete a group by identifier", description = "Delete a group by identifier", tags = {
      "Group"})
  @DeleteMapping(value = "/{identifier}")
  public ResponseEntity<?> removeGroupByIdentifier(
      @Parameter(description = "Group identifier") @PathVariable("identifier") UUID groupIdentifier) {
    groupService.removeGroup(groupIdentifier);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Update a group by identifier", description = "Update a group by identifier", tags = {
      "Group"})
  @PutMapping(value = "/{identifier}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GroupResponse> updateGroupByIdentifier(
      @Parameter(description = "Group identifier") @PathVariable("identifier") UUID groupIdentifier,
      @RequestBody GroupRequest groupRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(GroupResponseFactory.fromEntity(groupService.updateGroup(groupIdentifier, groupRequest),
        SummaryEnum.TRUE));
  }

}