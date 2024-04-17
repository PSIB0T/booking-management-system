package org.bookingmanager.interfaces.classes.impl;

import java.util.List;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IDestination;
import org.bookingmanager.interfaces.classes.ITravelPackage;
import org.bookingmanager.repository.entry.DestinationEntry;

@Setter
@Builder
public class DestinationImpl extends BaseClass implements IDestination {

  private int id;
  private String name;
  private List<IActivity> activities;
  private List<ITravelPackage> travelPackages;

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private DestinationEntry destinationEntry;


  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<IActivity> getActivities() {
    activities = Optional.ofNullable(activities).orElseGet(() -> activityRepository.findByDestinationId(id));
    return activities;
  }

  @Override
  public List<ITravelPackage> getTravelPackages() {
    travelPackages =
        Optional.ofNullable(travelPackages).orElseGet(() -> travelPackageRepository.findByDestinationId(id));
    return travelPackages;
  }
}
