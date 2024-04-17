package org.bookingmanager.interfaces.classes;

import java.util.List;

/**
 * The `IActivity` interface represents an activity that can be booked by a passenger.
 */
public interface IActivity {

  int getId();

  String getName();

  String getDescription();

  double getCost();

  int getMaxCapacity();

  IDestination getDestination();

  List<IPassenger> getPassengers();

}
