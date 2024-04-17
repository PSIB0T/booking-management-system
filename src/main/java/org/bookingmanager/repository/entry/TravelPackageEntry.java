package org.bookingmanager.repository.entry;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelPackageEntry {

  private int id;
  private String name;
  private int passengerCapacity;
  @Builder.Default
  private List<Integer> destinations = new ArrayList<>();
  @Builder.Default
  private List<Integer> passengers = new ArrayList<>();

}
