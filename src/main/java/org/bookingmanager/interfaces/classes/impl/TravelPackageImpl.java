package org.bookingmanager.interfaces.classes.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.Builder;
import org.bookingmanager.interfaces.classes.IDestination;
import org.bookingmanager.interfaces.classes.IPassenger;
import org.bookingmanager.interfaces.classes.ITravelPackage;
import org.bookingmanager.repository.entry.TravelPackageEntry;

@Builder
public class TravelPackageImpl extends BaseClass implements ITravelPackage {

  private int id;
  private String name;
  private int passengerCapacity;
  private List<IDestination> destinations;
  private List<IPassenger> passengers;
  private TravelPackageEntry travelPackageEntry;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public int getPassengerCapacity() {
    return passengerCapacity;
  }

  @Override
  public List<IDestination> getDestinations() {
    this.destinations = Optional
        .ofNullable(destinations)
        .orElseGet(() -> this.load(travelPackageEntry.getDestinations(), IDestination.class));
    return destinations;
  }

  @Override
  public List<IPassenger> getPassengers() {
    this.passengers = Optional
        .ofNullable(passengers)
        .orElseGet(() -> this.load(travelPackageEntry.getPassengers(), IPassenger.class));

    return passengers;
  }

  @Override
  public void addPassenger(IPassenger passenger) {
    this.passengers =
        new ArrayList<>(Optional.ofNullable(this.getPassengers()).orElse(Collections.emptyList()));
    this.passengers.add(passenger);
  }

  @Override
  public void addDestination(IDestination destination) {
    this.destinations =
        new ArrayList<>(Optional.ofNullable(this.getDestinations()).orElse(Collections.emptyList()));
    this.destinations.add(destination);

  }

}
