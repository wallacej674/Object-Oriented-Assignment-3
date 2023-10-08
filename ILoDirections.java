public interface ILoDirections { }

class ConsLoDirections implements ILoDirections {
  Direction first;

  ILoDirections rest;

  ConsLoDirections(Direction first, ILoDirections rest) {

    first = this.first;

    rest = this.rest;
  }
}

class MtLoDirections implements ILoDirections { }