package org.bookingmanager.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bookingmanager.enums.PassengerType;
import org.bookingmanager.repository.entry.ActivityEntry;
import org.bookingmanager.repository.entry.DestinationEntry;
import org.bookingmanager.repository.entry.PassengerEntry;
import org.bookingmanager.repository.entry.TravelPackageEntry;

/**
 * The `Data` class contains the initial data for the application.
 */
public class Data {

  public static List<ActivityEntry> activityData;
  public static List<DestinationEntry> destinations;
  public static List<PassengerEntry> passengers;
  public static List<TravelPackageEntry> travelPackages;

  static {
    activityData = new ArrayList<>();
    activityData.add(new ActivityEntry(1, "Hiking", "Enjoy a guided hike through scenic trails", 50.0, 20, 1));
    activityData.add(new ActivityEntry(2, "Sightseeing Tour", "Explore famous landmarks in the city", 80.0, 15, 1));
    activityData.add(new ActivityEntry(3, "Snorkeling Adventure", "Discover underwater marine life", 100.0, 10, 2));
    activityData.add(new ActivityEntry(4, "Helicopter Tour", "Breathtaking aerial views of the city", 200.0, 4, 2));
    activityData.add(new ActivityEntry(5, "Wine Tasting", "Visit local vineyards and sample wines", 75.0, 12, 3));

    passengers = new ArrayList<>();

    passengers.add(new PassengerEntry("Alice", 1, 100.0, PassengerType.STANDARD, Arrays.asList(1, 3, 5)));
    passengers.add(new PassengerEntry("Bob", 2, 200.0, PassengerType.GOLD, Arrays.asList(2, 4)));
    passengers.add(new PassengerEntry("Eve", 3, 0.0, PassengerType.PREMIUM, new ArrayList<>()));
    passengers.add(new PassengerEntry("John", 4, 150.0, PassengerType.STANDARD, Arrays.asList(2, 4)));
    passengers.add(new PassengerEntry("Sarah", 5, 300.0, PassengerType.GOLD, Arrays.asList(1, 3, 5)));
    passengers.add(new PassengerEntry("David", 6, 50.0, PassengerType.STANDARD, Arrays.asList(3)));
    passengers.add(new PassengerEntry("Sophia", 7, 500.0, PassengerType.PREMIUM, Arrays.asList(2, 4)));
    passengers.add(new PassengerEntry("Michael", 8, 80.0, PassengerType.STANDARD, Arrays.asList(1)));
    passengers.add(new PassengerEntry("Emma", 9, 400.0, PassengerType.GOLD, Arrays.asList(2, 4)));
    passengers.add(new PassengerEntry("Olivia", 10, 0.0, PassengerType.PREMIUM, new ArrayList<>()));

    travelPackages = new ArrayList<>();

    travelPackages.add(new TravelPackageEntry(1, "Hawaiian Paradise", 50, Arrays.asList(1, 2), Arrays.asList(1, 2)));
    travelPackages.add(new TravelPackageEntry(2, "European Adventure", 30, Arrays.asList(3, 4), Arrays.asList()));
    travelPackages.add(
        new TravelPackageEntry(3, "African Safari", 40, Arrays.asList(5, 6), Arrays.asList(1004, 5, 6, 7)));

    destinations = new ArrayList<>();

    destinations.add(new DestinationEntry(1, "Paris"));
    destinations.add(new DestinationEntry(2, "Tokyo"));
    destinations.add(new DestinationEntry(3, "New York"));
    destinations.add(new DestinationEntry(4, "London"));
    destinations.add(new DestinationEntry(5, "Rome"));
    destinations.add(new DestinationEntry(6, "Cape Town"));
  }

}
