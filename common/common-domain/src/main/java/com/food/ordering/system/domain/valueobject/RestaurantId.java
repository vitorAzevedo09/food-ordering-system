package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * RestaurantId
 */
public class RestaurantId extends BaseId<UUID> {

  RestaurantId(UUID value) {
    super(value);
  }

}
