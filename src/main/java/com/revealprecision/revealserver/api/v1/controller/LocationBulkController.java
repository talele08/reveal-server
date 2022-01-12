package com.revealprecision.revealserver.api.v1.controller;

import com.revealprecision.revealserver.api.v1.dto.response.IdentifierResponse;
import com.revealprecision.revealserver.batch.runner.LocationBatchRunner;
import com.revealprecision.revealserver.service.LocationBulkService;
import com.revealprecision.revealserver.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/location/bulk")
@CrossOrigin(originPatterns = "*", origins = "*")
public class LocationBulkController {

  private final LocationBulkService locationBulkService;
  private final LocationBatchRunner locationBatchRunner;
  private final StorageService storageService;


  @Operation(summary = "Import Locations from JSON file",
      description = "Import Locations from JSON file",
      tags = {"Location"}
  )
  @PostMapping()
  public ResponseEntity<IdentifierResponse> importLocations(
      @RequestParam("file") MultipartFile file)
      throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

    String path = storageService.saveJSON(file);
    UUID identifier = locationBulkService.saveBulk(file.getOriginalFilename());
    locationBatchRunner.run(identifier.toString(), path);
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(IdentifierResponse.builder().identifier(identifier).build());
  }
}
