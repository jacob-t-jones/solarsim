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
        final static double G = 6.674*Math.pow(10,-11);
        final static double PI        = Math.PI;

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
        Image ringImg;

        // Orbit Information
        double period;
        double e;
        double parallaxAngle;
        double velX; //km/s
        double velY;
        double delT = 0.1;
        double theta = 0.0;


        public Body(int id, double x, double y, double z, Body orbitBody, double radius, double mass, Image img, String name, boolean isRinged, Image ringImg) {
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
                this.ringImg = ringImg;
                if (orbitBody != null) {
                        this.orbitX = orbitBody.x;
                        this.orbitY = orbitBody.y;
                        this.orbitZ = orbitBody.z;
                        this.orbitMass = orbitBody.mass;
                }

        }

        //update the x, y, z, for the body based on the gravitational forces from the other bodies
        public void updatePosition(int iteration) {
                if (id != 0) {
                        double velocity = Math.pow(10.0, 7) * Math.pow((G * orbitMass) / distance(orbitX, orbitY, orbitZ, x, y, z), 0.5);
                        double angVelocity = (velocity / distance(orbitX, orbitY, orbitZ, x, y, z));

                        theta += angVelocity * delT;

                        newX = distance(orbitX, orbitY, orbitZ, x, y, z) * Math.cos(theta);
                        newY = distance(orbitX, orbitY, orbitZ, x, y, z) * Math.sin(theta);
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

        public double mToAu(double km) {
                return km * (6.6846 * Math.pow(10, -12));
        }
}
