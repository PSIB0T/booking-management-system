package org.bookingmanager.repository;

import java.util.List;

import org.bookingmanager.interfaces.classes.ITravelPackage;

/**
 * The `TravelPackageRepository` interface provides a contract for a repository that manages travel packages.
 */
public interface TravelPackageRepository {

  ITravelPackage findTravelPackageById(int id);

  List<ITravelPackage> findByDestinationId(int id);

  void save(ITravelPackage iTravelPackage);

}
