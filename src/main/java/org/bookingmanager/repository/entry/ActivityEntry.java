package org.bookingmanager.repository.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityEntry {

  private int id;
  private String name;
  private String description;
  private double cost;
  private int maxCapacity;
  private int destinationId;

}
