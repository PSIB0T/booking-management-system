package org.bookingmanager.repository;

import org.bookingmanager.interfaces.classes.IDestination;

/**
 * The `DestinationRepository` interface provides a contract for a repository that manages destinations.
 */
public interface DestinationRepository {

  IDestination findDestinationById(int id);

  void save(IDestination destination);

}
