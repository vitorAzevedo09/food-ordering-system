package com.food.ordering.system.order.service.domain.entity;

import java.util.List;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.RestaurantId;

/**
 * Restaurant
 */
public class Restaurant extends AggregateRoot<RestaurantId> {
  private List<Product> products;
  private boolean active;

  public List<Product> getProducts() {
    return products;
  }

  public boolean isActive() {
    return active;
  }

  private Restaurant(Builder builder) {
    super.setId(builder.restaurantId);
    this.products = builder.products;
    this.active = builder.active;
  }

  public Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private RestaurantId restaurantId;
    private List<Product> products;
    private boolean active;

    public Builder restaurantId(RestaurantId restaurantId) {
      this.restaurantId = restaurantId;
      return this;
    }

    public Builder products(List<Product> products) {
      this.products = products;
      return this;
    }

    public Builder active(boolean active) {
      this.active = active;
      return this;
    }

    public Restaurant build() {
      return new Restaurant(this);
    }
  }

}
