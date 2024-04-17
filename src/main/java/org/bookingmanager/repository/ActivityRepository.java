package org.bookingmanager.repository;

import java.util.List;

import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IDestination;

/**
 * The `ActivityRepository` interface provides a contract for a repository that manages activities.
 */
public interface ActivityRepository {

  List<IActivity> findAll();

  IActivity findActivityById(int id);

  List<IActivity> findByDestinationId(int id);

  void save(IActivity activity, IDestination destination);

}
