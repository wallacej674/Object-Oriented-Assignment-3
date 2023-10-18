import tester.*;

/*
* fields
*
* RoadTrip
* ...driver1...
* ...driver2...
* ...ILoDirections directions...
*
*Direction:
*
* ...description...
* ...miles...
*
* RoadTripChunk:
*
* ...String driver...
* ...ILoDirections directions...
*
* ILoRoadTripChunk:
*
* ...first...
* ...rest...
*
* two classes in interface
*
* ConsRoadTripChunk
* MTLoRoadTripChunks
*
* methods for RoadTrip:
*
* // returns the trip split up into Road Trip Chunks
* splitUpTrip(int switchMiles)
*
*
*
* methods for Direction:
*
* // return the int miles
* getMiles();
*
* // returns the string associated with the direction
* getDescription();
*
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
    this.driver = driver;
    this.direction = direction;
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


  ILoDirections tripToStopAndShop = new ConsLoDirections(new Direction("Make a left on ridgeview Dr", 10),
          new ConsLoDirections(new Direction("Make a left on Ora Manor", 2),
                  new ConsLoDirections(new Direction("Your destination will be on the right", 1),new MtLoDirections())));

  RoadTrip roadTripToStopAndShop = new RoadTrip("Jonathan", "Chris",tripToStopAndShop);



 ILoRoadTripChunk exampleRoadTripAns = new ConsRoadTripChunk(new RoadTripChunk("Hazel",new ConsLoDirections(new Direction("Make a left at Alberquerque",13),
        new ConsLoDirections(new Direction("Make a right at the fork",2),new ConsLoDirections(new Direction("Switch with Henry",0),new MtLoDirections())))),
            new ConsRoadTripChunk(new RoadTripChunk("Henry",new ConsLoDirections(new Direction("Make a left at the next fork",3),new ConsLoDirections(new Direction("Switch with Hazel",12), new MtLoDirections()))),
                new ConsRoadTripChunk(new RoadTripChunk("Hazel", new ConsLoDirections(new Direction("Switch with Henry", 15),new MtLoDirections())),
                        new ConsRoadTripChunk(new RoadTripChunk("Henry", new ConsLoDirections(new Direction("Switch with Hazel", 15), new MtLoDirections())), new ConsRoadTripChunk(new RoadTripChunk("Hazel",new ConsLoDirections(new Direction("Take the overpass",3),new ConsLoDirections(new Direction("Destination on left", 12),new MtLoDirections()))), new MTRoadTripChunk())))));

 ILoRoadTripChunk RoadTripToStopAndShopDriversans3 = new ConsRoadTripChunk(new RoadTripChunk("Jonathan", new ConsLoDirections(new Direction("Switch with Chris", 3), new MtLoDirections())),
         new ConsRoadTripChunk(new RoadTripChunk("Chris", new ConsLoDirections(new Direction("Switch with Jonathan",3), new MtLoDirections())),
                 new ConsRoadTripChunk(new RoadTripChunk("Jonathan", new ConsLoDirections(new Direction("Switch with Chris", 3), new MtLoDirections())),
                         new ConsRoadTripChunk(new RoadTripChunk("Chris", new ConsLoDirections(new Direction("Make a left on ridgeview Dr",1), new ConsLoDirections(new Direction("make a left on Ora Manor", 2),new ConsLoDirections(new Direction("Switch with Jonathan",0),new MtLoDirections())))),
                                 new ConsRoadTripChunk(new RoadTripChunk("Jonathan", new ConsLoDirections(new Direction("Your destination will be on the right",1),new MtLoDirections())), new MTRoadTripChunk())))));


  boolean testRoadTripExample (Tester t) {
    return t.checkExpect(this.Example.splitUpTrip(15), exampleRoadTripAns);
  }
  boolean testRoadTripToStopAndShop (Tester t) {
    return t.checkExpect(this.roadTripToStopAndShop.splitUpTrip(3), RoadTripToStopAndShopDriversans3);
  }



}