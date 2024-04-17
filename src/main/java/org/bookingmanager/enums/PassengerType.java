package org.bookingmanager.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The `PassengerType` enum represents the type of passenger.
 */
public enum PassengerType {

  STANDARD {
    @Override
    public double discountRate() {
      return 0;
    }
  },
  GOLD {
    @Override
    public double discountRate() {
      return 0.1;
    }
  },
  PREMIUM {
    @Override
    public double discountRate() {
      return 1;
    }
  },
  _unknown {
    @Override
    public double discountRate() {
      return 0;
    }
  };

  private static final Map<String, PassengerType> ENUM_MAPPING =
      Arrays.stream(PassengerType.values()).collect(Collectors.toMap(Enum::name, x -> x));

  @JsonCreator
  public static PassengerType forValue(String val) {
    return ENUM_MAPPING.getOrDefault(val, PassengerType._unknown);
  }

  public abstract double discountRate();
}
