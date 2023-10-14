import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;

interface ITree {
  WorldImage draw();
  boolean isDrooping();
  // ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree otherTree);
}

class Leaf implements ITree {
  int size; // represents the radius of the leaf
  Color color; // the color to draw it
  // CONSTRUCTOR
  Leaf(int size, Color color) {
    this.size = size;
    this.color = color;
  }
  // Methods
  public WorldImage draw() {
    return new CircleImage(this.size, OutlineMode.OUTLINE, this.color);
  }
  public boolean isDrooping() {
    return false;
  }
  // public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree otherTree) {
    //return
  // }
}

class Stem implements ITree {
  // How long this stick is
  int length;
  // The angle (in degrees) of this stem, relative to the +x axis
  double theta;
  // The rest of the tree
  ITree tree;

  // CONSTRUCTOR
  Stem(int length, double theta, ITree tree) {
    this.length = length;
    this.theta = theta;
    this.tree = tree;
  }

  // Methods
  public WorldImage draw() {
    return new LineImage(new Posn(0, this.length), Color.BLUE);
  }

  public boolean isDrooping() {
    return !(this.theta > 0) && !(this.theta < 180);
  }
}

class Branch implements ITree {
  // How long the left and right branches are
  int leftLength;
  int rightLength;
  // The angle (in degrees) of the two branches, relative to the +x-axis,
  double leftTheta;
  double rightTheta;
  // The remaining parts of the tree
  ITree left;
  ITree right;

  // CONSTRUCTOR
  Branch(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree left, ITree right) {
    this.leftLength = leftLength;
    this.rightLength = rightLength;
    this.leftTheta = leftTheta;
    this.rightTheta = rightTheta;
    this.left = left;
    this.right = right;
  }

  // Methods
  public WorldImage draw() {
    return new BesideImage(new LineImage(new Posn(0, this.leftLength), Color.RED),
      new LineImage(new Posn(1, this.rightLength), Color.YELLOW));
  }

  public boolean isDrooping() {
    //return !((this.leftTheta > 90.0) && (this.leftTheta < 180.0)) || !((this.rightTheta > 0.0) && (this.rightTheta < 90.0));
    return !((this.leftTheta > 90) || (this.leftTheta < 180)) && !((this.rightTheta > 0) || (this.rightTheta < 90));
  }
}

class ExampleTrees {
  // WorldScene Background = new WorldScene(100, 100);
  Leaf LeafExample = new Leaf(20, Color.BLUE);
  Stem StemExample = new Stem(20, 30.0, LeafExample);
  Branch BranchExample = new Branch(20, 30, 145.0, 35.0, LeafExample, LeafExample);
  // WorldImage Tree1 = new BesideImage(this.LeafExample.draw(), this.StemExample.draw());

  boolean testImages(Tester t) {
    return t.checkExpect(this.LeafExample.draw(), new CircleImage(20, OutlineMode.OUTLINE, Color.BLUE));
  }

  boolean testImages2(Tester t) {
    return t.checkExpect(this.StemExample.draw(), new LineImage(new Posn(0, 20), Color.BLUE));
  }

  boolean testImages3(Tester t) {
    return t.checkExpect(this.BranchExample.draw(), new BesideImage(new LineImage(new Posn(0, 20), Color.RED),
      new LineImage(new Posn(1, 30), Color.YELLOW)));
  }

  boolean testDroop(Tester t) {
    return t.checkExpect(this.StemExample.isDrooping(), false);
  }

  boolean testDroop2(Tester t) {
    return t.checkExpect(this.BranchExample.isDrooping(), false);
  }
}