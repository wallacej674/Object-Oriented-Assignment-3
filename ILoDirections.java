public interface ILoDirections {
   // public ILoRoadTripChunk splitUpTripHelper(int switchMiles,int miles, String  driver1, String driver2, ILoDirections dir);

   public ILoDirections splitUpDirections(int switchMiles,int miles, String  driver, String switchDriver, ILoDirections dir);

    public ILoRoadTripChunk splitUpTripHelper(int switchMiles, int miles, String driver1, String driver2, ILoDirections dir);


    public RoadTripChunk makeChunks();
}

class ConsLoDirections implements ILoDirections {
    Direction first;

    ILoDirections rest;

    ConsLoDirections(Direction first, ILoDirections rest) {

        first = this.first;

        rest = this.rest;
    }


    @Override
    public ILoDirections splitUpDirections(int switchMiles, int miles, String driver, String switchDriver, ILoDirections dir) {
       int x = switchMiles - miles;

        if (miles == switchMiles){
           return new ConsLoDirections(new Direction ("switch with " + switchDriver, 0),
                    splitUpDirections (switchMiles, 0, switchDriver, driver, dir ));
        }
        else if (x < this.first.getMiles()){
            return new ConsLoDirections(new Direction ("switch with " + switchDriver, x),
                    splitUpDirections(switchMiles, 0 , switchDriver, driver,
                            new ConsLoDirections(new Direction(this.first.getDescription(),this.first.getMiles() - x), this.rest)));
        }
        else if (x - this.first.getMiles() >= 0 ){
            return new ConsLoDirections(new Direction(this.first.getDescription(), this.first.getMiles()),
                    splitUpDirections(switchMiles, x - this.first.getMiles(), driver, switchDriver, this.rest));
        }
    }




    @Override
    public ILoRoadTripChunk splitUpTripHelper(int switchMiles, int miles, String driver1, String driver2, ILoDirections dir) {
        if(miles == switchMiles && this.first.getMiles() != 0){

        }
    }

    @Override
    public RoadTripChunk makeChunks() {
        return null;
    }
}









    class MtLoDirections implements ILoDirections {


      @Override
        public ILoDirections splitUpDirections(int switchMiles, int miles, String driver, String switchDriver, ILoDirections dir) {
            return this;
        }

        @Override
        public ILoRoadTripChunk splitUpTripHelper(int switchMiles, int miles, String driver1, String driver2, ILoDirections dir) {
            return new MTRoadTripChunk();
        }

        @Override
        public RoadTripChunk makeChunks() {
            return null;
        }
    }
