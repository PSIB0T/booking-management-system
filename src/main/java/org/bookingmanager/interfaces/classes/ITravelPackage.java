package org.bookingmanager.interfaces.classes;

import java.util.List;

/**
 * The `ITravelPackage` interface represents a travel package that can be booked by a passenger.
 */
public interface ITravelPackage {

  String getName();

  int getId();

  int getPassengerCapacity();

  List<IDestination> getDestinations();

  List<IPassenger> getPassengers();

  void addPassenger(IPassenger passenger);

  void addDestination(IDestination destination);
}
