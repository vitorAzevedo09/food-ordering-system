package com.food.ordering.system.order.service.domain.valueobject;

import java.util.UUID;

import com.food.ordering.system.domain.valueobject.BaseId;

/**
 * TrackingId
 */
public class TrackingId extends BaseId<UUID> {
  public TrackingId(UUID id) {
    super(id);
  }
}
