package com.food.ordering.system.order.service.domain.entity;

import java.util.List;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;

/**
 * Order
 */
public class Order extends AggregateRoot<OrderId> {
  private final CustomerId customerId;
  private final RestaurantId restaurantId;
  private final StreetAddress streetAddress;
  private final Money price;
  private final List<OrderItem> items;

  public Order(CustomerId customerId,
      RestaurantId restaurantId,
      StreetAddress streetAddress,
      Money price,
      List<OrderItem> items) {
    this.customerId = customerId;
    this.restaurantId = restaurantId;
    this.streetAddress = streetAddress;
    this.price = price;
    this.items = items;
  }

  public CustomerId getCustomerId() {
    return customerId;
  }

  public RestaurantId getRestaurantId() {
    return restaurantId;
  }

  public StreetAddress getStreetAddress() {
    return streetAddress;
  }

  public Money getPrice() {
    return price;
  }

  public List<OrderItem> getItems() {
    return items;
  }
}
