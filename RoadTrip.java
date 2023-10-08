interface IRoadTrip {}

class Drivers implements IRoadTrip {
  String driver1;
  String driver2;
  ILoDirections directions;
  Drivers(String driver1, String driver2, ILoDirections directions) {
    this.driver1 = driver1;
    this.driver2 = driver2;
    this.directions = directions;
  }
}

class Direction implements IRoadTrip {
  String description;
  int miles;
  Direction(String description, int miles) {
    this.description = description;
    this.miles = miles;
  }
}