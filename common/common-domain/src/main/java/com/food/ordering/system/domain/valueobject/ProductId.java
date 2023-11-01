package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * ProductId
 */
public class ProductId extends BaseId<UUID> {

  ProductId(UUID value) {
    super(value);
  }

}
