package com.revealprecision.revealserver.messaging.message;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocationMetadataUnpackedEvent extends Message {

  private UUID identifier;
  private UUID entityId;
  private UUID ancestorNode;
  private UUID hierarchyIdentifier;
  private MetaDataEvent metaDataEvent;
}
