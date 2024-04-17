package org.bookingmanager.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bookingmanager.exception.NotFoundException;
import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IPassenger;
import org.bookingmanager.interfaces.classes.impl.PassengerImpl;
import org.bookingmanager.repository.PassengerRepository;
import org.bookingmanager.repository.entry.PassengerEntry;

/**
 * The `PassengerRepositoryImpl` class implements the `PassengerRepository` interface.
 */
public class PassengerRepositoryImpl implements PassengerRepository {


  @Override
  public IPassenger findPassengerById(int id) {

    return Data.passengers
        .stream()
        .filter(x -> x.getId() == id)
        .map(x -> PassengerImpl
            .builder()
            .passengerEntry(x)
            .id(x.getId())
            .name(x.getName())
            .passengerType(x.getPassengerType())
            .balance(x.getBalance())
            .build())
        .findFirst()
        .orElseThrow(NotFoundException::new);
  }

  @Override
  public List<IPassenger> findByActivityId(int id) {

    return Data.passengers
        .stream()
        .filter(x -> Objects.nonNull(x.getActivities()) && x.getActivities().contains(id))
        .map(x -> PassengerImpl
            .builder()
            .passengerEntry(x)
            .id(x.getId())
            .name(x.getName())
            .passengerType(x.getPassengerType())
            .balance(x.getBalance())
            .build())
        .map(x -> (IPassenger) x)
        .toList();
  }

  @Override
  public void save(IPassenger entry) {

    List<Integer> activityIds =
        Optional.ofNullable(entry.getActivities()).orElse(new ArrayList<>()).stream().map(IActivity::getId).toList();
    Optional.ofNullable(entry.getId())
        .flatMap(x -> Data.passengers.stream().filter(y -> y.getId() == x).findFirst())
        .ifPresentOrElse(x -> {
          x.setName(entry.getName());
          x.setBalance(entry.getBalance());
          x.setPassengerType(entry.getPassengerType());
          x.setActivities(activityIds);
        }, () -> {
          Data.passengers.add(
              new PassengerEntry(entry.getName(), entry.getId(), entry.getBalance(), entry.getPassengerType(),
                  activityIds));
        });

  }
}
