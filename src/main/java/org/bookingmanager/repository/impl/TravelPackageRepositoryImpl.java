package org.bookingmanager.repository.impl;

import static org.bookingmanager.repository.impl.Data.travelPackages;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.bookingmanager.exception.NotFoundException;
import org.bookingmanager.interfaces.classes.IDestination;
import org.bookingmanager.interfaces.classes.IPassenger;
import org.bookingmanager.interfaces.classes.ITravelPackage;
import org.bookingmanager.interfaces.classes.impl.TravelPackageImpl;
import org.bookingmanager.repository.TravelPackageRepository;
import org.bookingmanager.repository.entry.TravelPackageEntry;

/**
 * The `TravelPackageRepositoryImpl` class implements the `TravelPackageRepository` interface.
 */
public class TravelPackageRepositoryImpl implements TravelPackageRepository {


  @Override
  public ITravelPackage findTravelPackageById(int id) {

    return travelPackages
        .stream()
        .filter(x -> x.getId() == id)
        .map(x -> TravelPackageImpl
            .builder()
            .travelPackageEntry(x)
            .id(x.getId())
            .passengerCapacity(x.getPassengerCapacity())
            .name(x.getName()).build())
        .findFirst()
        .orElseThrow(NotFoundException::new);
  }

  @Override
  public List<ITravelPackage> findByDestinationId(int id) {
    return travelPackages
        .stream()
        .filter(x -> x.getDestinations().contains(id))
        .map(x -> TravelPackageImpl
            .builder()
            .travelPackageEntry(x)
            .id(x.getId())
            .passengerCapacity(x.getPassengerCapacity())
            .name(x.getName()).build())
        .map(x -> (ITravelPackage) x)
        .toList();
  }

  @Override
  public void save(ITravelPackage entry) {

    List<Integer> destinations =
        Optional
            .ofNullable(entry.getDestinations())
            .orElse(Collections.emptyList())
            .stream()
            .map(IDestination::getId)
            .toList();
    List<Integer> passengers =
        Optional
            .ofNullable(entry.getPassengers())
            .orElse(Collections.emptyList())
            .stream()
            .map(IPassenger::getId)
            .toList();

    Optional.ofNullable(entry.getId())
        .flatMap(x -> travelPackages.stream().filter(y -> y.getId() == x).findFirst())
        .ifPresentOrElse(x -> {
          x.setName(entry.getName());
          x.setDestinations(destinations);
          x.setPassengers(passengers);
          x.setPassengerCapacity(entry.getPassengerCapacity());
        }, () -> {
          travelPackages.add(
              new TravelPackageEntry(entry.getId(), entry.getName(), entry.getPassengerCapacity(), destinations,
                  passengers));
        });
  }
}
