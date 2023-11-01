package com.food.ordering.system.order.service.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * OrderDomainService
 */
@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

  @Override
  public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
    validateRestaurant(restaurant);
    setOrderProductInformation(order, restaurant);
    order.validateOrder();
    order.initializeOrder();
    return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
  }

  @Override
  public OrderPaidEvent payOrder(Order order) {
    return null;
  }

  @Override
  public void approveOrder(Order order) {
  }

  @Override
  public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
  }

  @Override
  public void cancelOrder(Order order, List<String> failureMessages) {
  }

  public void validateRestaurant(Restaurant restaurant) {

  }

  public void setOrderProductInformation(Order order, Restaurant restaurant) {

  }

}
