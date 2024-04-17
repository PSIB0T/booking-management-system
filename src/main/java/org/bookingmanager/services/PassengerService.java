package org.bookingmanager.services;

import org.bookingmanager.enums.PassengerType;
import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IPassenger;
import org.bookingmanager.repository.PassengerRepository;
import org.bookingmanager.repository.impl.PassengerRepositoryImpl;


/**
 * The `PassengerService` class is responsible for managing the passengers of the travel agency.
 * It interacts with the `PassengerRepository` to perform various operations related to passengers.
 */
public class PassengerService {


  /**
   * Constructs a new `PassengerService` instance and initializes the `PassengerRepository`.
   */
  private final PassengerRepository passengerRepository;

  public PassengerService() {
    this.passengerRepository = new PassengerRepositoryImpl();
  }


  /**
   * Creates a new passenger and saves it in the `PassengerRepository`.
   *
   * @param iPassenger the passenger to be created
   */
  public void createPassenger(IPassenger iPassenger) {
    passengerRepository.save(iPassenger);
  }

  /**
   * Prints the detailed information of the passenger with the specified ID.
   * The information includes the passenger's name, ID, balance (if applicable), and the list of activities they have signed up for.
   *
   * @param passengerId the ID of the passenger whose information should be printed
   */
  public void printPassengerInfo(int passengerId) {
    IPassenger passenger = passengerRepository.findPassengerById(passengerId);
    System.out.println("Passenger info for passenger " + passengerId);
    System.out.println("Name: " + passenger.getName());
    System.out.println("Id: " + passenger.getId());
    if (!passenger.getPassengerType().equals(PassengerType.PREMIUM)) {
      System.out.println("Balance: " + passenger.getBalance());
    }
    if (passenger.getActivities().size() > 0) {
      System.out.println("ACTIVITIES: ");
      for (IActivity activity : passenger.getActivities()) {
        System.out.println("\t -NAME: " + activity.getName());
        System.out.println("\t -DESTINATION: " + activity.getDestination().getName());
        System.out.println("\t -PRICE PAID: " + activity.getCost() * (1 - passenger.getDiscount()));
        System.out.println();
      }
    }
  }

}
