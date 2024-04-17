package org.bookingmanager.repository;

import java.util.List;

import org.bookingmanager.interfaces.classes.IPassenger;

/**
 * The `PassengerRepository` interface provides a contract for a repository that manages passengers.
 */
public interface PassengerRepository {

  IPassenger findPassengerById(int id);

  List<IPassenger> findByActivityId(int id);

  void save(IPassenger passenger);

}
