package org.bookingmanager.services;

import java.util.List;
import java.util.Optional;

import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IDestination;
import org.bookingmanager.interfaces.classes.IPassenger;
import org.bookingmanager.repository.ActivityRepository;
import org.bookingmanager.repository.DestinationRepository;
import org.bookingmanager.repository.PassengerRepository;
import org.bookingmanager.repository.impl.ActivityRepositoryImpl;
import org.bookingmanager.repository.impl.DestinationRepositoryImpl;
import org.bookingmanager.repository.impl.PassengerRepositoryImpl;


/**
 * The `ActivityService` class is responsible for managing the activities offered by the travel agency.
 */

public class ActivityService {

  private final ActivityRepository activityRepository;
  private final DestinationRepository destinationRepository;
  private final PassengerRepository passengerRepository;

  public ActivityService() {
    this.activityRepository = new ActivityRepositoryImpl();
    this.destinationRepository = new DestinationRepositoryImpl();
    this.passengerRepository = new PassengerRepositoryImpl();
  }


  /**
   * Creates a new activity and associates it with the specified destination.
   *
   * @param iActivity      the activity to be created
   * @param destinationId the ID of the destination to associate the activity with
   */
  public void createActivity(IActivity iActivity, int destinationId) {
    IDestination destination = destinationRepository.findDestinationById(destinationId);
    activityRepository.save(iActivity, destination);
  }

  /**
   * Assigns a passenger to the specified activity.
   *
   * @param activityId   the ID of the activity to assign the passenger to
   * @param passengerId  the ID of the passenger to be assigned
   */
  public void assignPassengerToActivity(int activityId, int passengerId) {
    IActivity activity = activityRepository.findActivityById(activityId);
    IPassenger passenger = passengerRepository.findPassengerById(passengerId);

    Optional<Integer> isPassengerPresent = activity
        .getDestination()
        .getTravelPackages()
        .stream()
        .flatMap(x -> x.getPassengers().stream())
        .map(x -> x.getId())
        .filter(x -> x == passengerId)
        .findFirst();

    if (!isPassengerPresent.isPresent()) {
      System.out.println(
          "Not assigning passenger " + passenger.getId() + " because the passenger is not part of the travel package");
      return;
    }

    if (activity.getPassengers().size() >= activity.getMaxCapacity()) {
      System.out.println("Not assigning passenger " + passenger.getId() + " activity because of full capacity");
      return;
    }

    if (passenger.getBalance() < activity.getCost() * (1 - passenger.getDiscount())) {
      System.out.println("Not assigning passenger " + passenger.getId() + " activity  because of low balance");
      return;
    }

    passenger.assignActivity(activity);
    passenger.deductBalance(activity.getCost() * (1 - passenger.getDiscount()));
    passengerRepository.save(passenger);

  }


  /**
   * Prints the details of all the activities that still have available spaces.
   */
  public void printAvailableActivities() {
    List<IActivity> activities = activityRepository.findAll();
    System.out.println("ALL AVAILABLE ACTIVITIES: ");
    activities.stream().filter(x -> x.getPassengers().size() < x.getMaxCapacity()).forEach(x -> {
      System.out.println("\t NAME: " + x.getName());
      System.out.println("\t DESCRIPTION: " + x.getDescription());
      System.out.println("\t AVAILABLE SPACES: " + (x.getMaxCapacity() - x.getPassengers().size()));
      System.out.println();
    });
  }

}
