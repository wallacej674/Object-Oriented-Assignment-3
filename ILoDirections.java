
/*
* fields
*
* ILoDirections
* has:
*ConsLoDirections:
* ...first...
* ...rest...
*  ...MTLoDirections...
*
*
* methods:
* // returns the directions given switchMiles and a ILoDirections
* splitUp(int switchMiles,int miles, String  driver, String switchDriver, ILoDirections dir)
*
* // returns the ILoRoadTripChunks by making a recursive call to splitUp and unusedDirects and switching driver
* splitUpTripHelper (int switchMiles, String driver1, String driver2, ILoDirections dir)
*
* // returns the ILoDirections that have not been used in the ILoDirections.
* unusedDirects (int switchMiles, int miles, ILoDirections dir)
*
* */

public interface ILoDirections {
   // public ILoRoadTripChunk splitUpTripHelper(int switchMiles,int miles, String  driver1, String driver2, ILoDirections dir);



   public ILoDirections splitUp(int switchMiles,int miles, String  driver, String switchDriver, ILoDirections dir);

    public ILoRoadTripChunk splitUpTripHelper(int switchMiles, String driver1, String driver2, ILoDirections dir);


    public ILoDirections unusedDirects(int switchMiles, int miles, ILoDirections dir);

}

class ConsLoDirections implements ILoDirections {
    Direction first;

    ILoDirections rest;

    ConsLoDirections(Direction first, ILoDirections rest) {

        this.first = first;

        this.rest = rest;
    }

    public ILoDirections splitUp(int switchMiles, int miles, String driver, String switchDriver, ILoDirections dir) {
        int x = switchMiles - miles;

        if (miles >= switchMiles){
            return new ConsLoDirections(new Direction ("Switch with " + switchDriver, 0),
                new  MtLoDirections());
        }
        else if (x < this.first.getMiles()){
            return new ConsLoDirections(new Direction ("Switch with " + switchDriver, x),
                  new MtLoDirections());
        }
        else {
            return new ConsLoDirections(new Direction(this.first.getDescription(), this.first.getMiles()),
                    splitUp(switchMiles, miles + this.first.getMiles(), driver, switchDriver, this.rest));
        }

    }





    public ILoRoadTripChunk splitUpTripHelper(int switchMiles, String driver1, String driver2, ILoDirections dir) {
        if (dir instanceof MtLoDirections) {
            return new MTRoadTripChunk();
        }
        else{
        return new ConsRoadTripChunk(new RoadTripChunk(driver1, splitUp(switchMiles, 0, driver1, driver2, dir)),splitUpTripHelper(switchMiles, driver2, driver1,unusedDirects(switchMiles,0,dir)));
    }
    }


    public ILoDirections unusedDirects(int switchMiles, int miles, ILoDirections dir) {
        int x = switchMiles - miles;

        if (x == 0){
            return dir;
        }
        else if (x < this.first.getMiles()){
            return new ConsLoDirections(new Direction(this.first.getDescription(),this.first.getMiles() - x), this.rest);
        }
        else{
            return  unusedDirects(switchMiles, miles + this.first.getMiles(), this.rest);
        }
    }


}









    class MtLoDirections implements ILoDirections {



        public ILoDirections splitUp(int switchMiles, int miles, String driver, String switchDriver, ILoDirections dir) {
            return new MtLoDirections();
        }


        public ILoRoadTripChunk splitUpTripHelper(int switchMiles, String driver1, String driver2, ILoDirections dir) {
            return new MTRoadTripChunk();
        }


        public ILoDirections unusedDirects(int switchMiles, int miles, ILoDirections dir) {
            return new MtLoDirections();
        }




    }
