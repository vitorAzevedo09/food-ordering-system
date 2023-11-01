package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * CustomerId
 */
public class CustomerId extends BaseId<UUID> {

  CustomerId(UUID value) {
    super(value);
  }

}
