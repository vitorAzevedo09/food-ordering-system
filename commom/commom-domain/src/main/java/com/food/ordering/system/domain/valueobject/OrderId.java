package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * OrderId
 */
public class OrderId extends BaseId<UUID> {

  OrderId(UUID value) {
    super(value);
  }

}
