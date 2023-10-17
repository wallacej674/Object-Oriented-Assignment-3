import javalib.worldcanvas.WorldCanvas;
import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import java.awt.Color;

interface ITree {
  WorldImage draw();
  WorldImage drawRight();
  WorldImage drawLeft();
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
    return (new CircleImage(this.size, OutlineMode.SOLID, this.color));
  }
  public WorldImage drawRight() {
    return null;
  }
  public WorldImage drawLeft() {
    return null;
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
    return
      (new OverlayImage
        (new RotateImage
          (new VisiblePinholeImage
            (new LineImage
              (new Posn(0, this.length), Color.BLUE)).movePinhole(0, ((double) this.length / 2)), (this.theta * 8)),
          this.tree.draw()));
  }
  public WorldImage drawRight() {
    return null;
  }
  public WorldImage drawLeft() {
    return null;
  }

  public boolean isDrooping() {
    return !((this.theta > 0) && (this.theta < 180));
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
    return
      (new OverlayImage
        (this.drawLeft(),
          this.drawRight()));
  }
  // ((Math.cos(leftTheta) * leftLength) + (Math.cos(rightTheta) * rightLength))
  public WorldImage drawRight() {
    return
      (new VisiblePinholeImage
        // WRITE OVERLAY FIRST AND THEN USE ROTATE???
       (new RotateImage
          (new OverlayImage
              (new LineImage
                (new Posn(0, this.rightLength), Color.BLUE).movePinhole(0, -((double) this.rightLength / 2)),
                this.right.draw())
            .movePinhole(0, this.rightLength),
            (90 - this.rightTheta))
            ));
        //.movePinhole((Math.cos (this.rightTheta) * this.rightLength), (Math.sin(this.rightTheta) * this.rightLength));
  }
  public WorldImage drawLeft() {
    return
      new VisiblePinholeImage
      (new RotateImage
        (new OverlayImage
          (new LineImage
              (new Posn(0, this.leftLength), Color.RED).movePinhole(0, -((double) this.leftLength / 2))
            ,this.left.draw()).movePinhole(0, this.leftLength),
            (90 - this.leftTheta)
        ));
  }

  public boolean isDrooping() {
    return !(((this.leftTheta > 90) && (this.leftTheta < 180)) && ((this.rightTheta > 0) && (this.rightTheta < 90)));
  }
}

class ExampleTrees {
  Leaf LeafExample1 = new Leaf(20, Color.BLUE);
  WorldImage Leaf1 = LeafExample1.draw();
  Stem StemExample1 = new Stem (50, 30.0, LeafExample1);
  Stem StemExample2 = new Stem(50, 150.0, LeafExample1);
  WorldImage Stem1 = StemExample1.draw();
  WorldImage Stem2 = StemExample2.draw();
  Branch BranchExample1 = new Branch(50, 70, 145.0, 35.0, LeafExample1, LeafExample1);
  Branch BranchExample2 = new Branch(70, 50, 120.0, 330.0, LeafExample1, LeafExample1);
  WorldImage Branch1 = BranchExample1.draw();
  WorldImage Branch2 = BranchExample2.draw();
  // WorldImage Tree1 = new BesideImage(this.LeafExample.draw(), this.StemExample.draw());
  boolean testDrawTree(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    return c.drawScene(s.placeImageXY(Branch2, 250, 250))
      && c.show();
  }
  /*
  boolean testImages(Tester t) {
    return t.checkExpect(this.LeafExample1.draw(), new CircleImage(20, OutlineMode.OUTLINE, Color.BLUE));
  }

  boolean testImages2(Tester t) {
    return t.checkExpect(this.StemExample1.draw(), new LineImage(new Posn(0, 20), Color.BLUE));
  }

  boolean testImages3(Tester t) {
    return t.checkExpect(this.BranchExample1.draw(), new BesideImage(new LineImage(new Posn(0, 20), Color.RED),
      new LineImage(new Posn(1, 30), Color.YELLOW)));
  }


  boolean testDroop(Tester t) {
    return t.checkExpect(this.StemExample1.isDrooping(), false);
  }

  boolean testDroop2(Tester t) {
    return t.checkExpect(this.BranchExample1.isDrooping(), false);
  }
  boolean testDroop3(Tester t) {
    return t.checkExpect(this.StemExample2.isDrooping(), true);
  }
  boolean testDroop4(Tester t) {
    return t.checkExpect(this.BranchExample2.isDrooping(), true);
  }

   */
}