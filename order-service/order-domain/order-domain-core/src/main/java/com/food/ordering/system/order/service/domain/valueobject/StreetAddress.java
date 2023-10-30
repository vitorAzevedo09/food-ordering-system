package com.food.ordering.system.order.service.domain.valueobject;

import java.util.UUID;

/**
 * StreetAddress
 */
public class StreetAddress {

  private final UUID id;
  private final String street;
  private final String postalCode;
  private final String city;

  public StreetAddress(UUID id, String street, String postalCode, String city) {
    this.id = id;
    this.street = street;
    this.postalCode = postalCode;
    this.city = city;
  }

  public UUID getId() {
    return id;
  }

  public String getStreet() {
    return street;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCity() {
    return city;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    StreetAddress that = (StreetAddress) obj;
    return street.equals(that.getStreet()) && postalCode.equals(that.getPostalCode()) && city.equals(that.getCity());
  }

}
