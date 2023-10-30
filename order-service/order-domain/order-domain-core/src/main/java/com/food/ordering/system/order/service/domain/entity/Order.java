package com.food.ordering.system.order.service.domain.entity;

import java.util.List;
import java.util.UUID;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

/**
 * Order
 */
public class Order extends AggregateRoot<OrderId> {
  private final CustomerId customerId;
  private final RestaurantId restaurantId;
  private final StreetAddress streetAddress;
  private final Money price;
  private final List<OrderItem> items;

  private TrackingId trackingId;
  private OrderStatus orderStatus;
  private List<String> failureMessages;

  public void pay() {
    if (orderStatus != OrderStatus.PENDING) {
      throw new OrderDomainException("Order is not in correct state for pay operation");
    }
    orderStatus = OrderStatus.PAID;
  }

  public void approve() {
    if (orderStatus != OrderStatus.PAID) {
      throw new OrderDomainException("Order is not in correct state for approve operation");
    }
    orderStatus = OrderStatus.APPROVED;
  }

  public void initCancel(List<String> failureMessages) {
    if (orderStatus != OrderStatus.PAID) {
      throw new OrderDomainException("Order is not in correct state for initCancel operation");
    }
    orderStatus = OrderStatus.CANCELLING;
    updateFailureMessages(failureMessages);
  }

  public void cancel() {
    if (orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING) {
      throw new OrderDomainException("Order is not in correct state for cancel operation");
    }
  }

  public void updateFailureMessages(List<String> failureMessages) {
    if (this.failureMessages != null && failureMessages != null) {
      this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
    }
    if (this.failureMessages == null) {
      this.failureMessages = failureMessages;
    }
  }

  public void validateOrder() {
    validateInitialOrder();
    validateTotalPrice();
    validateItemsPrice();
  }

  public void validateInitialOrder() {
    if (orderStatus != null || getId() != null) {
      throw new OrderDomainException("Order is not in correct state for initialization");
    }
  }

  public void validateTotalPrice() {
    if (price != null || !price.isGreatherThanZero())
      throw new OrderDomainException("Price must be greather than ZERO");
  }

  public void validateItemsPrice() {
    Money orderItemsTotal = items.stream().map(orderItem -> {
      validateItemPrice(orderItem);
      return orderItem.getSubtotal();
    }).reduce(Money.ZERO, Money::add);

    if (!price.equals(orderItemsTotal)) {
      throw new OrderDomainException("Total price: " + price.getAmount() +
          " is not equals to Order items total: " + orderItemsTotal.getAmount() + "!");
    }
  }

  public void validateItemPrice(OrderItem orderItem) {
    if (!orderItem.isPriceValid()) {
      throw new OrderDomainException("Order item price: " + orderItem.getPrice().getAmount()
          + "is not equal to Order items total: " + orderItem.getProduct().getId().getValue());
    }

  }

  public void initializeOrder() {
    setId(new OrderId(UUID.randomUUID()));
    trackingId = new TrackingId(UUID.randomUUID());
    orderStatus = OrderStatus.PENDING;
    initializeOrderItems();
  }

  public void initializeOrderItems() {
    long itemId = 1;
    for (OrderItem orderItem : items) {
      orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
    }
  }

  public Order(Builder builder) {
    super.setId(builder.orderId);
    this.customerId = builder.customerId;
    this.restaurantId = builder.restaurantId;
    this.streetAddress = builder.streetAddress;
    this.price = builder.price;
    this.items = builder.items;
    this.trackingId = builder.trackingId;
    this.orderStatus = builder.orderStatus;
    this.failureMessages = builder.failureMessages;
  }

  public Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private OrderId orderId;
    private CustomerId customerId;
    private RestaurantId restaurantId;
    private StreetAddress streetAddress;
    private Money price;
    private List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    public Builder orderId(OrderId orderId) {
      this.orderId = orderId;
      return this;
    }

    public Builder customerId(CustomerId customerId) {
      this.customerId = customerId;
      return this;
    }

    public Builder restaurantId(RestaurantId restaurantId) {
      this.restaurantId = restaurantId;
      return this;
    }

    public Builder streetAddress(StreetAddress streetAddress) {
      this.streetAddress = streetAddress;
      return this;
    }

    public Builder price(Money price) {
      this.price = price;
      return this;
    }

    public Builder items(List<OrderItem> items) {
      this.items = items;
      return this;
    }

    public Builder trackingId(TrackingId trackingId) {
      this.trackingId = trackingId;
      return this;
    }

    public Builder orderStatus(OrderStatus orderStatus) {
      this.orderStatus = orderStatus;
      return this;
    }

    public Builder failureMessages(List<String> failureMessages) {
      this.failureMessages = failureMessages;
      return this;
    }

    public Order build() {
      return new Order(this);
    }
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

  public TrackingId getTrackingId() {
    return trackingId;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public List<String> getFailureMessage() {
    return failureMessages;
  }

}
