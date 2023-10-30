package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

/**
 * OrderItem
 */
public class OrderItem extends BaseEntity<OrderItemId> {

  private OrderId orderId;
  private final Product product;
  private final int quantity;
  private final Money price;
  private final Money subtotal;

  public OrderItem(Builder builder) {
    super.setId(builder.orderItemId);
    this.product = builder.product;
    this.quantity = builder.quantity;
    this.price = builder.price;
    this.subtotal = builder.subtotal;
  }

  public boolean isPriceValid() {
    return price.isGreatherThanZero() &&
        price.equals(product.getPrice()) &&
        price.multiply(quantity).equals(subtotal);
  }

  public void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
    this.orderId = orderId;
    super.setId(orderItemId);
  }

  public static class Builder {
    private OrderItemId orderItemId;
    private Product product;
    private int quantity;
    private Money price;
    private Money subtotal;

    public Builder orderItemId(OrderItemId orderItemId) {
      this.orderItemId = orderItemId;
      return this;
    }

    public Builder product(Product product) {
      this.product = product;
      return this;
    }

    public Builder quantity(int quantity) {
      this.quantity = quantity;
      return this;
    }

    public Builder price(Money price) {
      this.price = price;
      return this;
    }

    public Builder subtotal(Money subtotal) {
      this.subtotal = subtotal;
      return this;
    }

    public OrderItem build() {
      return new OrderItem(this);
    }
  }

  public OrderId getOrderId() {
    return orderId;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public Money getPrice() {
    return price;
  }

  public Money getSubtotal() {
    return subtotal;
  }

  public Builder builder() {
    return new Builder();
  }

}
