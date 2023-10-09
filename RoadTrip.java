import tester.*;
public class RoadTrip {
  String driver1;
  String driver2;
  ILoDirections directions;
  RoadTrip(String driver1, String driver2, ILoDirections directions) {
    this.driver1 = driver1;
    this.driver2 = driver2;
    this.directions = directions;
  }
}

class Direction {
  String description;
  int miles;
  Direction(String description, int miles) {
    this.description = description;
    this.miles = miles;
  }
}

class RoadTripChunk {
  String driver;
  ILoDirections direction;
  RoadTripChunk(String driver, ILoDirections direction){
    driver = this.driver;
    direction = this.direction;
  }
}

class ExamplesRoadTrip {
  RoadTrip Example = new RoadTrip ("Hazel", "Henry", new ConsLoDirections (new Direction ("Make a right at the fork", 2),
    new ConsLoDirections (new Direction ("Make a left at the next fork", 3),
    new ConsLoDirections (new Direction ("Take the overpass", 45),
      new ConsLoDirections (new Direction ("Destination on left", 12),
        new MtLoDirections()))))
    );
}