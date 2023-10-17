import tester.*;

/*
* fields
*
* RoadTrip
* ...driver1...
* ...driver2...
* ...ILoDirections directions...
*
* methods:
*
*
*
* */


public class RoadTrip {
  String driver1;
  String driver2;
  ILoDirections directions;
  RoadTrip(String driver1, String driver2, ILoDirections directions) {
    this.driver1 = driver1;
    this.driver2 = driver2;
    this.directions = directions;

  }
  public ILoRoadTripChunk splitUpTrip(int switchMiles) {
    return   this.directions.splitUpTripHelper(switchMiles, this.driver1, this.driver2, this.directions);
  }
}

class Direction {
  String description;
  int miles;
  Direction(String description, int miles) {
    this.description = description;
    this.miles = miles;
  }



  public String getDescription(){
    return description;
  }


  public int getMiles(){
    return miles;
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

interface ILoRoadTripChunk{



}

class ConsRoadTripChunk implements ILoRoadTripChunk{

  RoadTripChunk first;

  ILoRoadTripChunk rest;

  ConsRoadTripChunk(RoadTripChunk first, ILoRoadTripChunk rest){

    this.first = first;

    this.rest = rest;
  }

}




class MTRoadTripChunk implements ILoRoadTripChunk{


}


class ExamplesRoadTrip {
  RoadTrip Example = new RoadTrip ("Hazel", "Henry", new ConsLoDirections (new Direction ("Make a right at the fork", 2),
    new ConsLoDirections (new Direction ("Make a left at the next fork", 3),
    new ConsLoDirections (new Direction ("Take the overpass", 45),
      new ConsLoDirections (new Direction ("Destination on left", 12),
        new MtLoDirections()))))
    );
}