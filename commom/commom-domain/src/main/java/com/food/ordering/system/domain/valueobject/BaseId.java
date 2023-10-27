package com.food.ordering.system.domain.valueobject;

import java.util.Objects;

/**
 * BaseId
 */
public class BaseId<T> {
  private final T value;

  public T getValue() {
    return value;
  }

  BaseId(T value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    BaseId<?> that = (BaseId<?>) obj;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}
