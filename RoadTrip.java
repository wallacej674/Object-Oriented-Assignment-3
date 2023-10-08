


public class RoadTrip {

    String driver1;
    String driver2;

    ILoDirections dir;

    RoadTrip(String driver1, String driver2, ILoDirections dir){

        driver1 = this.driver1;

        driver2 = this.driver2;

        dir = this.dir;


    }


}


class Direction{

    String dirName;

    int mile;

    Direction(String dirName, int mile){

        dirName = this.dirName;

        mile = this.mile;

    }
}









interface ILoDirections{

}

class ConsLoDirections implements ILoDirections{

    Direction first;

    ILoDirections rest;


    ConsLoDirections(Direction first, ILoDirections rest){

        first = this.first;

        rest = this.rest;
    }

}


class MTLoDirections implements ILoDirections{


}


class RoadTripChunk{

    String driver;

    ILoDirections dir;

    RoadTripChunk(String driver, ILoDirections dir){

        driver = this.driver;

        dir = this.dir;
    }

}