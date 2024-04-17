package org.bookingmanager.services;

import org.bookingmanager.enums.PassengerType;
import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IDestination;
import org.bookingmanager.interfaces.classes.IPassenger;
import org.bookingmanager.repository.DestinationRepository;
import org.bookingmanager.repository.PassengerRepository;
import org.bookingmanager.repository.impl.DestinationRepositoryImpl;
import org.bookingmanager.repository.impl.PassengerRepositoryImpl;


/**
 * The `DestinationService` class is responsible for managing the destinations offered by the travel agency.
 * It interacts with the `DestinationRepository` to mainly create destinations
 */
public class DestinationService {

  private final DestinationRepository destinationRepository;

  public DestinationService() {
    this.destinationRepository = new DestinationRepositoryImpl();
  }

  /**
   * Creates a new destination and saves it in the `DestinationRepository`.
   *
   * @param destination the destination to be created
   */
  public void createDestination(IDestination destination) {
    destinationRepository.save(destination);
  }

}
