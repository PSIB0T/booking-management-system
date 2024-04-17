package org.bookingmanager.interfaces.classes.impl;

import java.util.List;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IDestination;
import org.bookingmanager.interfaces.classes.IPassenger;
import org.bookingmanager.repository.entry.ActivityEntry;


@Builder
@Getter
public class ActivityImpl extends BaseClass implements IActivity {

  private int id;
  private String name;
  private String description;
  private double cost;
  private int maxCapacity;
  private IDestination destination;
  private List<IPassenger> passengers;

  @Getter(AccessLevel.NONE)
  private ActivityEntry activityEntry;

  @Override
  public IDestination getDestination() {
    this.destination = Optional
        .ofNullable(destination)
        .orElseGet(() -> Optional
            .ofNullable(this.load(List.of(activityEntry.getDestinationId()), IDestination.class))
            .map(x -> x.get(0))
            .orElse(null));
    return destination;
  }

  @Override
  public List<IPassenger> getPassengers() {
    this.passengers = Optional
        .ofNullable(passengers)
        .orElseGet(() -> passengerRepository.findByActivityId(id));
    return passengers;
  }

}
