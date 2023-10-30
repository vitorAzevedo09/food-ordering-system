package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.valueobject.BaseId;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

/**
 * OrderItem
 */
public class OrderItem extends BaseId<OrderItemId> {

  private OrderId orderId;

  public OrderId getOrderId() {
    return orderId;
  }

  public void setOrderId(OrderId orderId) {
    this.orderId = orderId;
  }

}
