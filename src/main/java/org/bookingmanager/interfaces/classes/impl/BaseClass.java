package org.bookingmanager.interfaces.classes.impl;

import java.util.List;
import java.util.Objects;

import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IDestination;
import org.bookingmanager.interfaces.classes.IPassenger;
import org.bookingmanager.interfaces.classes.ITravelPackage;
import org.bookingmanager.repository.ActivityRepository;
import org.bookingmanager.repository.DestinationRepository;
import org.bookingmanager.repository.PassengerRepository;
import org.bookingmanager.repository.TravelPackageRepository;
import org.bookingmanager.repository.impl.ActivityRepositoryImpl;
import org.bookingmanager.repository.impl.DestinationRepositoryImpl;
import org.bookingmanager.repository.impl.PassengerRepositoryImpl;
import org.bookingmanager.repository.impl.TravelPackageRepositoryImpl;

public abstract class BaseClass {

  protected ActivityRepository activityRepository;
  protected PassengerRepository passengerRepository;
  protected TravelPackageRepository travelPackageRepository;
  private DestinationRepository destinationRepository;

  public BaseClass() {
    this.travelPackageRepository = new TravelPackageRepositoryImpl();
    this.destinationRepository = new DestinationRepositoryImpl();
    this.activityRepository = new ActivityRepositoryImpl();
    this.passengerRepository = new PassengerRepositoryImpl();
  }

  protected <T> List<T> load(List<Integer> input, Class<T> type) {
    if (Objects.isNull(input)) {
      return null;
    }

    return input.stream().map(x -> getValue(x, type)).toList();
  }

  private <T> T getValue(Integer input, Class<T> type) {
    if (type == ITravelPackage.class) {
      return (T) travelPackageRepository.findTravelPackageById(input);
    }

    if (type == IDestination.class) {
      return (T) destinationRepository.findDestinationById(input);
    }

    if (type == IActivity.class) {
      return (T) activityRepository.findActivityById(input);
    }

    if (type == IPassenger.class) {
      return (T) passengerRepository.findPassengerById(input);
    }

    return null;
  }


}
