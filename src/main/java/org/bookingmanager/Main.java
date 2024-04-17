package org.bookingmanager;

import org.bookingmanager.services.ActivityService;

public class Main {

  public static void main(String[] args) {

    ActivityService activityService = new ActivityService();
    activityService.printAvailableActivities();
    System.out.println("Hello world!");
  }
}