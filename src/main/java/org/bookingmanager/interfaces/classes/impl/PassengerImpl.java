package org.bookingmanager.interfaces.classes.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bookingmanager.enums.PassengerType;
import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IPassenger;
import org.bookingmanager.repository.entry.PassengerEntry;

@Builder
@Getter
public class PassengerImpl extends BaseClass implements IPassenger {

  private String name;
  private int id;
  private double balance;
  private PassengerType passengerType;
  private List<IActivity> activities;

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private PassengerEntry passengerEntry;


  @Override
  public double getDiscount() {
    return passengerType.discountRate();
  }

  @Override
  public List<IActivity> getActivities() {
    this.activities = Optional
        .ofNullable(activities)
        .orElseGet(() -> this.load(passengerEntry.getActivities(), IActivity.class));

    return this.activities;
  }

  @Override
  public void assignActivity(IActivity activity) {

    this.activities =
        new ArrayList<>(Optional.ofNullable(this.getActivities()).orElse(Collections.emptyList()));
    this.activities.add(activity);
  }


  @Override

  public void deductBalance(double amount) {
    this.balance -= amount;
  }

}
