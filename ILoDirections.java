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

        first = this.first;

        rest = this.rest;
    }

    public ILoDirections splitUp(int switchMiles, int miles, String driver, String switchDriver, ILoDirections dir) {
        int x = switchMiles - miles;

        if (miles == switchMiles){
            return new ConsLoDirections(new Direction ("switch with " + switchDriver, 0),
                new  MtLoDirections());
        }
        else if (x < this.first.getMiles()){
            return new ConsLoDirections(new Direction ("switch with " + switchDriver, x),
                  new MtLoDirections() );
        }
        else{
            return new ConsLoDirections(new Direction(this.first.getDescription(), this.first.getMiles()),
                    splitUp(switchMiles, x - this.first.getMiles(), driver, switchDriver, this.rest));
        }
    }





    public ILoRoadTripChunk splitUpTripHelper(int switchMiles, String driver1, String driver2, ILoDirections dir) {
        return new ConsRoadTripChunk(new RoadTripChunk(driver1, splitUp(switchMiles, 0, driver1, driver2, dir)),splitUpTripHelper(switchMiles, driver2, driver1,unusedDirects(switchMiles,0,dir)));
    }


    public ILoDirections unusedDirects(int switchMiles, int miles, ILoDirections dir) {
        int x = switchMiles - miles;

        if (miles == switchMiles){
            return dir;
        }
        else if (x < this.first.getMiles()){
            return new ConsLoDirections(new Direction(this.first.getDescription(),this.first.getMiles() - x), this.rest);
        }
        else{
            return new ConsLoDirections(new Direction(this.first.getDescription(), this.first.getMiles()),
                    unusedDirects(switchMiles, x - this.first.getMiles(), this.rest));
        }
    }


}









    class MtLoDirections implements ILoDirections {



        public ILoDirections splitUp(int switchMiles, int miles, String driver, String switchDriver, ILoDirections dir) {
            return this;
        }


        public ILoRoadTripChunk splitUpTripHelper(int switchMiles, String driver1, String driver2, ILoDirections dir) {
            return new MTRoadTripChunk();
        }


        public ILoDirections unusedDirects(int switchMiles, int miles, ILoDirections dir) {
            return this;
        }

    }
