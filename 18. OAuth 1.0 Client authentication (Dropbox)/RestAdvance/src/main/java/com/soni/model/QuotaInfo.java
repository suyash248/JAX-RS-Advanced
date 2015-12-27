package com.soni.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.ToString;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) @ToString
@JsonPropertyOrder({ "shared", "quota", "normal" }) 
public class QuotaInfo { 
 
 @JsonProperty("shared") 
 private Double shared; 
 @JsonProperty("quota") 
 private Double quota; 
 @JsonProperty("normal") 
 private Double normal; 
 private Map<String, Object> additionalProperties = new HashMap<String, Object>(); 
 
 @JsonProperty("shared") 
 public Double getShared() { 
  return shared; 
 } 
 
 @JsonProperty("shared") 
 public void setShared(Double shared) { 
  this.shared = shared; 
 } 
 
 @JsonProperty("quota") 
 public Double getQuota() { 
  return quota; 
 } 
 
 @JsonProperty("quota") 
 public void setQuota(Double quota) { 
  this.quota = quota; 
 } 
 
 @JsonProperty("normal") 
 public Double getNormal() { 
  return normal; 
 } 
 
 @JsonProperty("normal") 
 public void setNormal(Double normal) { 
  this.normal = normal; 
 } 
 
 @JsonAnyGetter 
 public Map<String, Object> getAdditionalProperties() { 
  return this.additionalProperties; 
 } 
 
 @JsonAnySetter 
 public void setAdditionalProperties(String name, Object value) { 
  this.additionalProperties.put(name, value); 
 } 
 
}
