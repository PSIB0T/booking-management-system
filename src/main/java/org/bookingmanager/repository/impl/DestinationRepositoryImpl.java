package org.bookingmanager.repository.impl;

import org.bookingmanager.exception.NotFoundException;
import org.bookingmanager.interfaces.classes.IDestination;
import org.bookingmanager.interfaces.classes.impl.DestinationImpl;
import org.bookingmanager.repository.DestinationRepository;
import org.bookingmanager.repository.entry.ActivityEntry;
import org.bookingmanager.repository.entry.DestinationEntry;

/**
 * The `DestinationRepositoryImpl` class implements the `DestinationRepository` interface.
 */
public class DestinationRepositoryImpl implements DestinationRepository {


  @Override
  public IDestination findDestinationById(int id) {

    return Data.destinations
        .stream()
        .filter(x -> x.getId() == id)
        .map(x -> DestinationImpl
            .builder().destinationEntry(x).id(x.getId()).name(x.getName()).build())
        .findFirst()
        .orElseThrow(NotFoundException::new);
  }

  @Override
  public void save(IDestination destination) {
    int id = Data.destinations.size() + 1;
    DestinationEntry entry = DestinationEntry
        .builder().id(id).name(destination.getName()).build();

    Data.destinations.add(entry);
  }
}
