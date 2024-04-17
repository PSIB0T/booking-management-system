package org.bookingmanager.repository.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookingmanager.enums.PassengerType;

@Data
@NoArgsConstructor
@Builder
public class PassengerEntry {

  private String name;
  private int id;
  private double balance;
  private PassengerType passengerType;
  @Builder.Default
  private List<Integer> activities = new ArrayList<>();

  public PassengerEntry(String name, int id, double balance, PassengerType passengerType, List<Integer> activities) {
    this.name = name;
    this.id = id;
    this.setBalance(balance);
    this.passengerType = passengerType;
    this.setActivities(activities);
  }

  public void setBalance(double balance) {
    if (balance < 0) {
      throw new IllegalArgumentException("Balance cannot be negative");
    }
    this.balance = balance;
  }

  public void setActivities(List<Integer> activities) {
    this.activities = Optional.ofNullable(activities).orElseGet(() -> new ArrayList<>());
  }

}
