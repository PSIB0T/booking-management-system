package org.bookingmanager.repository.impl;

import java.util.List;

import org.bookingmanager.exception.NotFoundException;
import org.bookingmanager.interfaces.classes.IActivity;
import org.bookingmanager.interfaces.classes.IDestination;
import org.bookingmanager.interfaces.classes.impl.ActivityImpl;
import org.bookingmanager.repository.ActivityRepository;
import org.bookingmanager.repository.entry.ActivityEntry;

/**
 * The `ActivityRepositoryImpl` class implements the `ActivityRepository` interface.
 */
public class ActivityRepositoryImpl implements ActivityRepository {


  @Override
  public List<IActivity> findAll() {
    return Data.activityData
        .stream()
        .map(x -> ActivityImpl
            .builder().activityEntry(x)
            .id(x.getId())
            .name(x.getName()).cost(x.getCost()).description(x.getDescription()).maxCapacity(x.getMaxCapacity())
            .build())
        .map(x -> (IActivity) x)
        .toList();
  }

  @Override
  public IActivity findActivityById(int id) {
    return Data.activityData
        .stream()
        .filter(x -> x.getId() == id)
        .map(x -> ActivityImpl
            .builder().activityEntry(x)
            .id(x.getId())
            .name(x.getName()).cost(x.getCost()).description(x.getDescription()).maxCapacity(x.getMaxCapacity())
            .build())
        .findFirst()
        .orElseThrow(NotFoundException::new);
  }

  @Override
  public List<IActivity> findByDestinationId(int id) {

    return Data.activityData
        .stream()
        .filter(x -> x.getDestinationId() == id)
        .map(x -> ActivityImpl
            .builder().activityEntry(x)
            .id(x.getId())
            .name(x.getName()).cost(x.getCost()).description(x.getDescription()).maxCapacity(x.getMaxCapacity())
            .build())
        .map(x -> (IActivity) x)
        .toList();
  }

  @Override
  public void save(IActivity activity, IDestination destination) {
    int id = Data.activityData.size() + 1;
    ActivityEntry entry = ActivityEntry
        .builder()
        .id(id)
        .destinationId(destination.getId())
        .name(activity.getName())
        .cost(activity.getCost())
        .description(activity.getDescription())
        .maxCapacity(activity.getMaxCapacity())
        .build();

    Data.activityData.add(entry);

  }
}
