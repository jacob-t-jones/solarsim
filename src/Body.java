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

        //final static double G = (6.674 * Math.pow(10, -11)) / (Math.pow(1.496 * Math.pow(10, 11), 3));

        String name;

        // Coordinates
        double x, y, z;
        double newX, newY, newZ;
        double orbitRadius;
        double orbitX, orbitY, orbitZ, orbitMass;

        // Physical properties
        double mass; //kg
        double radius;
        //Color color;
        Image img;
        boolean isRinged;

        // Orbit Information
        double period;
        double e;
        double parallaxAngle;
        double velX; //km/s
        double velY;
        double delT = 0.1;
        double theta = 0.0;


        public Body(int id, double x, double y, double z, Body orbitBody, double radius, double mass, Image img, String name, boolean isRinged) {
                this.id = id;
                this.x = x;
                this.y = y;
                this.z = z;
                this.mass = mass;
                this.radius = radius;
                this.img = img;
                this.newX = this.x;
                this.newY = this.y;
                this.newZ = this.z;
                this.name = name;
                this.isRinged = isRinged;
                if (orbitBody != null) {
                        this.orbitX = orbitBody.x;
                        this.orbitY = orbitBody.y;
                        this.orbitZ = orbitBody.z;
                        this.orbitMass = orbitBody.mass;
                }

        }

        //returns a Sphere3D object with the same coords as this body for drawing in the scene
        // public Sphere3D createSphere() {
        //   finalizePosition();
        //   PhongMaterial material = new PhongMaterial(color);
        //   return new Sphere3D(x, y, z, radius, material);
        // }

        //update the x, y, z, for the body based on the gravitational forces from the other bodies
        public void updatePosition(int iteration, ArrayList<Body> bodies) {
                if (id != 0) {
                        //double sunMass = bodies.get(0).mass;
                        //double theta = getTheta();

                        //Method 1
                        // double fx = (Math.pow(10.0, 11.0) * (1.0) * G * sunMass * mass * Math.cos(theta)) / Math.pow(distance(0.0, 0.0, x, y), 2.0);
                        // double fy = (Math.pow(10.0, 11.0) * (-1.0) * G * sunMass * mass * Math.sin(theta)) / Math.pow(distance(0.0, 0.0, x, y), 2.0);

                        // double ax = fx / mass;
                        // double ay = fy / mass;

                        //Method 2
                        // double mult = -1.0;
                        // // if ((x < 0) && (y < 0)) mult = 1.0;
                        //
                        // double ax = Math.pow(10, 5) * ((mult) * G * sunMass * Math.cos(theta)) / Math.pow(distance(0.0, 0.0, 0.0, x, y, z), 2.0);
                        // double ay = Math.pow(10, 5) * ((mult) * G * sunMass * Math.sin(theta)) / Math.pow(distance(0.0, 0.0, 0.0, x, y, z), 2.0);
                        //
                        // velX += ax;
                        // velY += ay;
                        //
                        // newX += (Math.pow(10.0, 5.0)) * velX;
                        // newY += (Math.pow(10.0, 5.0)) * velY;
                        //
                        // //update theta
                        // double v = Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
                        // double angV = Math.pow(10, 5) * v / distance(0.0, 0.0, 0.0, newX, newY, newZ);
                        // theta += angV;


                        //Method 3
                        // double velocity = Math.pow(10.0, 8) * Math.pow((G * sunMass) / orbitRadius, 0.5);
                        //
                        // double angVelocity = (velocity / orbitRadius);
                        // double vX = velocity * Math.cos(theta);
                        // double vY = velocity * Math.sin(theta);
                        //
                        // theta += angVelocity * delT;
                        // newX += vX * delT;
                        // newY += vY * delT;

                        //Method 4
                        double velocity = Math.pow(10.0, 7) * Math.pow((G * orbitMass) / distance(orbitX, orbitY, orbitZ, x, y, z), 0.5);
                        double angVelocity = (velocity / distance(orbitX, orbitY, orbitZ, x, y, z));

                        theta += angVelocity * delT;

                        newX = distance(orbitX, orbitY, orbitZ, x, y, z) * Math.cos(theta);
                        newY = distance(orbitX, orbitY, orbitZ, x, y, z) * Math.sin(theta);


                        if ((iteration % 5 == 0) && (id == 3)) {
                                // System.out.println("FX: " + fx);
                                // System.out.println("ax: " + ax);

                                // System.out.println("x: " + x);
                                // System.out.println("newX: " + newX);
                                // System.out.println("newX(AU): " + mToAu(newX));
                                // System.out.println("orbit radius: " + orbitRadius);
                                // System.out.println("distance: " + distance(x, y, z, 0.0, 0.0, 0.0));
                                // System.out.println("ax: " + ax);
                                // System.out.println("ay: " + ay);
                                System.out.println("velX: " + velX);
                                System.out.println("velY: " + velY);
                                System.out.println("X: " + mToAu(x));
                                System.out.println("Y: " + mToAu(y));
                                System.out.println("theta: " + theta);
                                System.out.println("y/x: " +  y / x);
                        }

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

        public double distance(double x1, double y1, double x2, double y2) {
                return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        }

        public double getTheta() {
                // double angle = Math.atan(y / x);
                // // if (angle < 0.0) {
                // //   angle += PI;
                // // }
                // return angle;
                return Math.acos(x / distance(0.0, 0.0, 0.0, x, y, z));
        }

        public double mToAu(double km) {
                return km * (6.6846 * Math.pow(10, -12));
        }
}
