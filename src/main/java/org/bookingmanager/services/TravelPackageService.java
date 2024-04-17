package org.bookingmanager.services;

import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IDestination;
import org.bookingmanager.interfaces.classes.IPassenger;
import org.bookingmanager.interfaces.classes.ITravelPackage;
import org.bookingmanager.repository.DestinationRepository;
import org.bookingmanager.repository.PassengerRepository;
import org.bookingmanager.repository.TravelPackageRepository;
import org.bookingmanager.repository.impl.DestinationRepositoryImpl;
import org.bookingmanager.repository.impl.PassengerRepositoryImpl;
import org.bookingmanager.repository.impl.TravelPackageRepositoryImpl;


/**
 * The `TravelPackageService` class is responsible for managing the travel packages offered by the travel agency.
 * It interacts with the `TravelPackageRepository`, `DestinationRepository`, and `PassengerRepository` to perform various operations related to travel packages.
 */
public class TravelPackageService {

  private final TravelPackageRepository travelPackageRepository;
  private final DestinationRepository destinationRepository;
  private final PassengerRepository passengerRepository;

  public TravelPackageService() {
    this.travelPackageRepository = new TravelPackageRepositoryImpl();
    this.passengerRepository = new PassengerRepositoryImpl();
    this.destinationRepository = new DestinationRepositoryImpl();
  }

  /**
   * Creates a new travel package and saves it in the `TravelPackageRepository`.
   *
   * @param iTravelPackage the travel package to be created
   */
  public void createPackage(ITravelPackage iTravelPackage) {
    travelPackageRepository.save(iTravelPackage);
  }


  /**
   * Assigns a destination to the specified travel package.
   *
   * @param packageId    the ID of the travel package to assign the destination to
   * @param destinationId the ID of the destination to be assigned
   */
  public void assignDestinationToPackage(int packageId, int destinationId) {
    ITravelPackage travelPackage = travelPackageRepository.findTravelPackageById(packageId);
    IDestination destination = destinationRepository.findDestinationById(destinationId);

    travelPackage.addDestination(destination);
    travelPackageRepository.save(travelPackage);

  }

  /**
   * Assigns a passenger to the specified travel package.
   *
   * @param packageId   the ID of the travel package to assign the passenger to
   * @param passengerId the ID of the passenger to be assigned
   */
  public void assignPassengerToPackage(int packageId, int passengerId) {
    ITravelPackage travelPackage = travelPackageRepository.findTravelPackageById(packageId);
    IPassenger passenger = passengerRepository.findPassengerById(passengerId);

    if (travelPackage.getPassengers().size() >= travelPackage.getPassengerCapacity()) {
      System.out.println(
          "Travel package is full " + packageId + " Reached max capacity " + travelPackage.getPassengerCapacity());
      return;
    }

    travelPackage.addPassenger(passenger);
    travelPackageRepository.save(travelPackage);

  }

  /**
   * Prints the detailed itinerary of the travel package with the specified ID.
   * The itinerary includes the package name, destinations, and activities.
   *
   * @param packageId the ID of the travel package whose itinerary should be printed
   */
  public void printItinerary(int packageId) {
    ITravelPackage travelPackage = travelPackageRepository.findTravelPackageById(packageId);
    System.out.println("Itinerary for package " + packageId);
    System.out.println("Package name: " + travelPackage.getName());
    System.out.println("Destinations: ");
    for (IDestination destination : travelPackage.getDestinations()) {
      System.out.println("\t- " + destination.getName());
      if (destination.getActivities().size() == 0) {
        continue;
      }
      System.out.println("\tACTIVITIES: ");
      for (IActivity activity : destination.getActivities()) {
        System.out.println("\t\t- Name: " + activity.getName());
      }
    }
  }

  /**
   * Prints the detailed information of the passengers enrolled in the travel package with the specified ID.
   * The information includes the passenger's name and ID.
   *
   * @param packageId the ID of the travel package whose passenger information should be printed
   */
  public void printPassengerInfo(int packageId) {
    ITravelPackage travelPackage = travelPackageRepository.findTravelPackageById(packageId);
    System.out.println("Passenger info for package " + packageId);
    System.out.println("Package name: " + travelPackage.getName());
    System.out.println("Passenger capacity: " + travelPackage.getPassengerCapacity());
    System.out.println("Passenger currently enrolled: " + travelPackage.getPassengers().size());
    if (travelPackage.getPassengers().size() == 0) {
      return;
    }
    System.out.println("Passengers: ");
    for (IPassenger passenger : travelPackage.getPassengers()) {
      System.out.println("\t- Name: " + passenger.getName());
      System.out.println("\t- Id: " + passenger.getId());
    }
  }

}
