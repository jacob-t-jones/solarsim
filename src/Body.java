// Physical bodies for the simulation
import org.edisonwj.draw3d.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.stage.StageStyle;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javafx.scene.image.Image;

import java.util.*;


public class Body {
  int id;
  // Universal Constants
  // final static long INVERSE_G = 14983338500;
  // final static double G         = 1.0 / INVERSE_G;
  // final static double PI        = Math.PI;

  // Coordinates
  double x, y, z;
  double newX, newY, newZ;

  // Physical dimensions
  double mass, radius;
  Color color;

  // Orbit Information
  double period, e, parallaxAngle, vel;


  public Body(int id, double x, double y, double z, double radius, double mass, Color c) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.z = z;
    this.mass = mass;
    this.radius = radius;
    this.color = c;
    this.newX = this.x;
    this.newY = this.y;
    this.newZ = this.z;
  }

  //returns a Sphere3D object with the same coords as this body for drawing in the scene
  public Sphere3D createSphere() {
    finalizePosition();
    PhongMaterial material = new PhongMaterial(color);
    return new Sphere3D(x, y, z, radius, material);
  }

  //update the x, y, z, for the body based on the gravitational forces from the other bodies
  public void updatePosition(ArrayList<Body> bodies) {
    for (Body b: bodies) {
      if (b.id != this.id) { //Check not the same body
        //Position calculation

      }
    }
  }

  //Finalize the new position
  public void finalizePosition() {
    x = newX;
    y = newY;
    z = newZ;
  }



  //Utility methods
  public double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
    return Math.sqrt(Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0) + Math.pow(z2 - z1, 2.0));
  }
}
