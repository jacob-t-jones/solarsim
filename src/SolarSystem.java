// Simulation happens here
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

public class SolarSystem implements Algorithm {

        Body sun;
        Body mercury, venus, earth, mars, jupiter, saturn, uranus, neptune, pluto;

        ArrayList<Body> bodies;

        public SolarSystem() {

                //Initialize planets
                initPlanets();
        }


        public void initPlanets() {
                //load images
                Image sunImg     = new Image("images/sunmap.jpg");
                Image mercuryImg = new Image("images/mercurymap.jpg");
                Image venusImg   = new Image("images/venusmap.jpg");
                Image earthImg   = new Image("images/earthmap1k.jpg");
                Image marsImg    = new Image("images/mars_1k_color.jpg");
                Image jupiterImg = new Image("images/jupitermap.jpg");
                Image saturnImg  = new Image("images/saturnmap.jpg");
                Image uranusImg  = new Image("images/uranusmap.jpg");
                Image neptuneImg = new Image("images/neptunemap.jpg");
		Image plutoImg   = new Image("images/plutomap1k.jpg");


                sun     = new Body(0, 0.0,                      0.0, 0.0, null, 0.0,                      1.989 * Math.pow(10, 30), sunImg,     "Sun",     false);
                mercury = new Body(1, 5.790 * Math.pow(10, 10), 0.0, 0.0, sun,  2.4400 * Math.pow(10, 6), 3.302 * Math.pow(10, 23), mercuryImg, "Mercury", false);
                venus   = new Body(2, 1.082 * Math.pow(10, 11), 0.0, 0.0, sun,  6.0540 * Math.pow(10, 6), 4.869 * Math.pow(10, 24), venusImg,   "Venus",   false);
                earth   = new Body(3, 1.496 * Math.pow(10, 11), 0.0, 0.0, sun,  6.3780 * Math.pow(10, 6), 5.974 * Math.pow(10, 24), earthImg,   "Earth",   false);
                mars    = new Body(4, 2.279 * Math.pow(10, 11), 0.0, 0.0, sun,  3.3960 * Math.pow(10, 6), 6.419 * Math.pow(10, 23), marsImg,    "Mars",    false);
                jupiter = new Body(5, 7.783 * Math.pow(10, 11), 0.0, 0.0, sun,  7.1492 * Math.pow(10, 7), 1.899 * Math.pow(10, 27), jupiterImg, "Jupiter", false);
                saturn  = new Body(6, 1.427 * Math.pow(10, 12), 0.0, 0.0, sun,  6.0286 * Math.pow(10, 7), 5.685 * Math.pow(10, 26), saturnImg,  "Saturn",  true);
                uranus  = new Body(7, 2.871 * Math.pow(10, 12), 0.0, 0.0, sun,  2.5557 * Math.pow(10, 7), 8.685 * Math.pow(10, 25), uranusImg,  "Uranus",  false);
                neptune = new Body(8, 4.497 * Math.pow(10, 12), 0.0, 0.0, sun,  2.4766 * Math.pow(10, 7), 1.024 * Math.pow(10, 26), neptuneImg, "Neptune", false);
                pluto   = new Body(9, 5.906 * Math.pow(10, 12), 0.0, 0.0, sun,  1.1850 * Math.pow(10, 6), 1.460 * Math.pow(10, 22), plutoImg,   "Pluto",   false);
                // moon    = new Body(10,


                bodies = new ArrayList<Body>(); //an array to hold the bodies
                bodies.add(sun);
                bodies.add(mercury);
                bodies.add(venus);
                bodies.add(earth);
                bodies.add(mars);
                bodies.add(jupiter);
                bodies.add(saturn);
                bodies.add(uranus);
                bodies.add(neptune);
                bodies.add(pluto);

        }

        //Interface methods
        //This is the main animation function - it is called at specific intervals by the GUI
        //We just make it return the array ob objects we want to be drawn
        public Object processAlgorithm(int iteration) {
                //System.out.println(iteration);
                //update body positions
                for (Body b: bodies) {
                        b.updatePosition(iteration, bodies);
                        b.finalizePosition();
                }

                //finalize positions and create spheres that can be drawn for the bodies
                //we shift the x position for the planets by the sun radius to get better visual product
                ArrayList<Object> drawBodies = new ArrayList<Object>();
                for (int i = 0; i < bodies.size(); i++) {
                        //get body
                        Body b = bodies.get(i);
                        double offset = 0.0;

                        // scale objects
                        double scaledRadius = Math.log10(10000 * mToAu(b.radius));
                        double distance = Math.log10(100 * distance(b.x, b.y, b.z, b.orbitX, b.orbitY, b.orbitZ));
                        double pX = distance * Math.cos(b.theta);
                        double pY = distance * Math.sin(b.theta);
                        double pZ = 0;

                        //make label
                        Text3D label = new Text3D(pX, pY, pZ + (scaledRadius * 1.1), 0.0, 180.0, 0.0, b.name);
                        label.setColor(Color.BLACK);

                        //make sphere
                        PhongMaterial material = new PhongMaterial();
                        material.setDiffuseMap(b.img);
                        Sphere3D sphere = new Sphere3D(pX, pY, pZ, scaledRadius, material);
                        drawBodies.add(sphere);
                        drawBodies.add(label);

                        if (b.isRinged) {
                                PhongMaterial mat = new PhongMaterial();
                                //System.out.println("Adding rings");
                                Image i1 = new Image("images/saturn_ring.png");
                                mat.setDiffuseMap(i1);
                                Image i2 = new Image("images/saturnmap.jpg");
                                Cylinder3D ring = new Cylinder3D(pX, pY, pZ, 2.0 * b.radius, 0.05, mat);
                                drawBodies.add(ring);
                        }



                }

                Object[] drawBodyArray = new Object[drawBodies.size()];
                drawBodyArray = drawBodies.toArray();

                return drawBodyArray;
        }

        public PhongMaterial getMaterial(int iteration) {
                return new PhongMaterial();
        }

        public boolean isDrone() {
                return false;
        }

        public boolean doClear() {
                return true;
        }

        public long getDelay() {
                return 100;
        }

        public int getIterations() {
                return 5000;
        }

        public double[] getInfo() {
                double[] a = new double[10];
                return a;
        }

        public void setInfo(double[] info) {
                System.out.println("The info method stuff:");
                System.out.println(info);
        }

        public void setId(int id) {
                System.out.println("Id: " + id);
        }

        public int getId() {
                return 1;
        }

        //Utility methods
        public double mToAu(double m) {
                //return km * (6.6846 * Math.pow(10, -12));
                return m / (1.496 * Math.pow(10, 11));
        }

        public double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
                return Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0) + (z1 - z0) * (z1 - z0));
        }

}
