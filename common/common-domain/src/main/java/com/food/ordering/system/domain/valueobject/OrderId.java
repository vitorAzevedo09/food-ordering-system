package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * OrderId
 */
public class OrderId extends BaseId<UUID> {
  
  public OrderId(UUID value) {
    super(value);
  }

}
