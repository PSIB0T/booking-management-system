package org.bookingmanager.interfaces.classes;

import java.util.List;

import org.bookingmanager.enums.PassengerType;

/**
 * The `IPassenger` interface represents a passenger that can book activities.
 */
public interface IPassenger {

  String getName();

  int getId();

  double getBalance();

  double getDiscount();

  PassengerType getPassengerType();

  List<IActivity> getActivities();

  void assignActivity(IActivity activity);


  void deductBalance(double amount);
}
