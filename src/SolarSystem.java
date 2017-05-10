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

	//Defaults
	final double sunRadius = 3.0;

	//The planets
	Body sun;
	Body earth;
	Body mercury, venus, mars, jupiter, saturn, uranus, neptune;

	ArrayList<Body> bodies;


	public SolarSystem() {

		//Initialize planets
		initPlanets();
	}


	public void initPlanets() {
		sun = new Body(0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.989 * Math.pow(10, 30), 0.0, 0.0, Color.YELLOW, "Sun");
		mercury = new Body(1, 5.79 * Math.pow(10, 10), 0.0, 0.0, 0.1, 5.79 * Math.pow(10, 11), 3.302 * Math.pow(10, 23), 0.0, 47873, Color.GREY, "Mercury");
		venus = new Body(2, 1.082 * Math.pow(10, 11), 0.0, 0.0, 0.1, 1.082 * Math.pow(10, 11), 4.869 * Math.pow(10, 24), 0.0, 35021, Color.ORANGE, "Venus");
		earth = new Body(3, 1.496 * Math.pow(10, 11), 0.0, 0.0, 0.1, 1.496 * Math.pow(10, 11), 5.974 * Math.pow(10, 24), 0.0, 29786, Color.BLUE, "Earth");
		mars = new Body(4, 2.279 * Math.pow(10, 11), 0.0, 0.0, 0.075, 2.279 * Math.pow(10, 11), 6.419 * Math.pow(10, 23), 0.0, 24131, Color.RED, "Mars");
		jupiter = new Body(5, 7.783 * Math.pow(10, 11), 0.0, 0.0, 2, 7.783 * Math.pow(10, 11), 1.899 * Math.pow(10, 27), 0.0, 13070, Color.ORANGE, "Jupiter");
		saturn = new Body(6, 1.427 * Math.pow(10, 12), 0.0, 0.0, 1.5, 1.427 * Math.pow(10, 12), 5.685 * Math.pow(10, 26), 0.0, 9672, Color.YELLOW, "Saturn");
		uranus = new Body(7, 2.871 * Math.pow(10, 12), 0.0, 0.0, 1.25, 2.871 * Math.pow(10, 12), 8.685 * Math.pow(10, 25), 0.0, 6835, Color.BLUE, "Uranus");
		neptune = new Body(8, 4.497 * Math.pow(10, 12), 0.0, 0.0, 1.25, 4.497 * Math.pow(10, 12), 1.024 * Math.pow(10, 26), 0.0, 5478, Color.GREEN, "Neptune");

		//In AU
		// mercury = new Body(1, 0.387, 0.0, 0.0, 0.1, 0.387, 3.302 * Math.pow(10, 23), 0.0, mToAu(47873), Color.GREY, "Mercury");
		// venus = new Body(2, 0.723, 0.0, 0.0, 0.1, 0.723, 4.869 * Math.pow(10, 24), 0.0, mToAu(35021), Color.ORANGE, "Venus");
		// earth = new Body(3, 1.0, 0.0, 0.0, 0.1, 1.0, 5.974 * Math.pow(10, 24), 0.0, mToAu(29786), Color.BLUE, "Earth");

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
		Object[] sphereBodies = new Object[bodies.size() * 2];
		for (int i = 0; i < sphereBodies.length / 2; i++) {
			//get body
			Body b = bodies.get(i);
			double offset = 0.0;
			if (b.id != 0) offset = sunRadius;

			double pX = mToAu(b.x);
			double pY = mToAu(b.y);
			double pZ = mToAu(b.z);

			//make label
			Text3D label = new Text3D(pX, pY, pZ + (b.radius * 1.1), 0.0, 180.0, 0.0, b.name);
			label.setColor(Color.BLACK);

			//make sphere
			PhongMaterial material = new PhongMaterial(b.color);
			Sphere3D sphere = new Sphere3D(pX, pY, pZ, b.radius, material);
			sphereBodies[2 * i] = sphere;
			sphereBodies[2 * i + 1] = label;

		}

		return sphereBodies;
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

}
