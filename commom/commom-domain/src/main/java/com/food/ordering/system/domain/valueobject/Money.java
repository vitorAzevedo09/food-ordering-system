package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Money
 */
public class Money {
  private final BigDecimal amount;

  Money(BigDecimal amount) {
    this.amount = amount;
  }

  public boolean isGreatherThanZero() {
    return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
  }

  public boolean isGreatherThan(Money money) {
    return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
  }

  public Money add(Money money) {
    return new Money(setScale(this.amount.add(money.getAmount())));
  }

  public Money subtract(Money money) {
    return new Money(setScale(this.amount.subtract(money.getAmount())));
  }

  public Money multiply(int multiplier) {
    return new Money(setScale(this.amount.multiply(new BigDecimal(multiplier))));
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Money that = (Money) obj;
    return amount.equals(that.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount);
  }

  private BigDecimal setScale(BigDecimal input) {
    return input.setScale(2, RoundingMode.HALF_EVEN);
  }
}
