package com.revealprecision.revealserver.persistence.es;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revealprecision.revealserver.messaging.message.MetaDataEvent;
import com.revealprecision.revealserver.util.ElasticModelUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityMetadataElastic {

  private String type;
  private String tag;
  private Object value;
  private MetadataElastic meta;
  private boolean isActive = true;

  public EntityMetadataElastic(MetaDataEvent metadataObj) {
    this.type = metadataObj.getType();
    this.tag = metadataObj.getTag();
    this.isActive = metadataObj.isActive();
    this.meta = MetadataElastic.builder()
        .planId(metadataObj.getTagData().getMeta().getPlanId().toString())
        .userId(metadataObj.getTagData().getMeta().getUserId())
        .createDateTime(ElasticModelUtil.toDateFromLocalDateTime(metadataObj.getTagData().getMeta().getCreateDateTime()))
        .updateDateTime(ElasticModelUtil.toDateFromLocalDateTime(metadataObj.getTagData().getMeta().getUpdateDateTime()))
        .taskId(metadataObj.getTagData().getMeta().getTaskId()==null?null:metadataObj.getTagData().getMeta().getTaskId().toString())
        .taskType(metadataObj.getTagData().getMeta().getTaskType())
        .build();
    switch (metadataObj.getDataType()){
      case "string":
        this.value = metadataObj.getTagData().getValue().getValueString();
        break;
      case "integer":
        this.value = metadataObj.getTagData().getValue().getValueInteger();
        break;
      case "double":
        this.value = metadataObj.getTagData().getValue().getValueDouble();
        break;
      case "date":
        this.value = metadataObj.getTagData().getValue().getValueDate();
        break;
      case "boolean":
        this.value = metadataObj.getTagData().getValue().getValueBoolean();
        break;
    }
  }
}
