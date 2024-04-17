package org.bookingmanager.interfaces.classes;

import java.util.List;

/**
 * The `IDestination` interface represents a destination that can be visited by a passenger.
 */
public interface IDestination {

  int getId();

  String getName();

  List<ITravelPackage> getTravelPackages();

  List<IActivity> getActivities();

}
