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
  //final static long INVERSE_G = 14983338500;
  //final static double G         = 1.0 / INVERSE_G;
  final static double G = 6.674*Math.pow(10,-11);
  final static double PI        = Math.PI;

  String name;

  // Coordinates
  double x, y, z;
  double newX, newY, newZ;
  double orbitRadius;

  // Physical properties
  double mass; //kg
  double radius;
  Color color;

  // Orbit Information
  double period;
  double e;
  double parallaxAngle;
  double velX; //km/s
  double velY;
  double delT = 0.1;


  public Body(int id, double x, double y, double z, double radius, double orbitRadius, double mass, double vx, double vy, Color c, String name) {
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
    this.name = name;
    this.velX = vx; //km/s
    this.velY = vy;
    this.orbitRadius = orbitRadius;
  }

  //returns a Sphere3D object with the same coords as this body for drawing in the scene
  public Sphere3D createSphere() {
    finalizePosition();
    PhongMaterial material = new PhongMaterial(color);
    return new Sphere3D(x, y, z, radius, material);
  }

  //update the x, y, z, for the body based on the gravitational forces from the other bodies
  public void updatePosition(int iteration, ArrayList<Body> bodies) {
    if (id != 0) {
      double sunMass = bodies.get(0).mass;
      double theta = getTheta();

      double fx = (Math.pow(10.0, 12.0) * (-1.0) * G * sunMass * mass * Math.cos(theta)) / Math.pow(distance(0.0, 0.0, 0.0, x, y, z), 2.0);
      double fy = (Math.pow(10.0, 12.0) * (-1.0) * G * sunMass * mass * Math.sin(theta)) / Math.pow(distance(0.0, 0.0, 0.0, x, y, z), 2.0);

      double ax = fx / mass;
      double ay = fy / mass;

      velX += ax * delT;
      velY += ay * delT;

      if ((iteration % 5 == 0) && (id == 3)) {
        // System.out.println("FX: " + fx);
        // System.out.println("ax: " + ax);
        // System.out.println("velX: " + velX);
        // System.out.println("x: " + x);
        // System.out.println("newX: " + newX);
        // System.out.println("newX(AU): " + mToAu(newX));
        // System.out.println("orbit radius: " + orbitRadius);
        // System.out.println("distance: " + distance(x, y, z, 0.0, 0.0, 0.0));
        System.out.println("theta: " + theta);
      }

      newX += velX * delT;
      newY += velY * delT;

    }
  }

  //Finalize the new position
  public void finalizePosition() {
    this.x = this.newX;
    this.y = this.newY;
    this.z = this.newZ;
  }



  //Utility methods
  public double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
    return Math.sqrt(Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0) + Math.pow(z2 - z1, 2.0));
  }

  public double getTheta() {
    //return Math.asin(y / distance(0.0, 0.0, 0.0, x, y, z));
    return Math.acos(y / distance(0.0, 0.0, 0.0, x, y, z));
  }

	public double mToAu(double km) {
		return km * (6.6846 * Math.pow(10, -12));
	}
}
