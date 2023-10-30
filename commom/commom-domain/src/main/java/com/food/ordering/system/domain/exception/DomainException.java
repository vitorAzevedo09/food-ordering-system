package com.food.ordering.system.domain.exception;

/**
 * DomainException
 */
public class DomainException extends RuntimeException {

  public DomainException(String message) {
    super(message);
  }

  public DomainException(String message, Throwable exception) {
    super(message, exception);
  }

}
