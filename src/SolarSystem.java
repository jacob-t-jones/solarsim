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

	double x = - 10.0;
	double y = 0.0;

	public SolarSystem() {

		//Initialize planets
		initPlanets();
	}


	public void initPlanets() {
		sun = new Body(0, 0.0, 0.0, 0.0, sunRadius, 500.0, Color.YELLOW, "Sun");
		mercury = new Body(1, 0.387, 0.0, 0.0, 0.1, 500, Color.GREY, "Mercury");
		venus = new Body(2, 0.723, 0.0, 0.0, 0.1, 500.0, Color.ORANGE, "Venus");
		earth = new Body(3, 1.0, 0.0, 0.0, 0.1, 500.0, Color.BLUE, "Earth");
		mars = new Body(4, 1.524, 0.0, 0.0, 0.075, 500.0, Color.RED, "Mars");
		jupiter = new Body(5, 5.203, 0.0, 0.0, 2, 500.0, Color.ORANGE, "Jupiter");
		saturn = new Body(6, 9.537, 0.0, 0.0, 1.5, 500.0, Color.YELLOW, "Saturn");
		uranus = new Body(7, 19.191, 0.0, 0.0, 1.25, 500.0, Color.BLUE, "Uranus");
		neptune = new Body(8, 30.069, 0.0, 0.0, 1.25, 500.0, Color.GREEN, "Neptune");

		bodies = new ArrayList<Body>(); //an array to hold the bodies
		bodies.add(sun);
		bodies.add(earth);
		bodies.add(mercury);
		bodies.add(venus);
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

		//update body positions
		for (Body b: bodies) {
			b.updatePosition(bodies);
		}

		//finalize positions and create spheres that can be drawn for the bodies
		//we shift the x position for the planets by the sun radius to get better visual product
		Object[] sphereBodies = new Object[bodies.size() * 2];
		for (int i = 0; i < sphereBodies.length / 2; i++) {
			Body b = bodies.get(i);
			if (b.id != 0) {
				//make label
				Text3D label = new Text3D(b.x + sunRadius, b.y, b.z + (b.radius * 1.1), 0.0, 180.0, 0.0, b.name);
				label.setColor(Color.BLACK);

				//make sphere
				PhongMaterial material = new PhongMaterial(b.color);
				Sphere3D sphere = new Sphere3D(b.x + sunRadius, b.y, b.z, b.radius, material);
				sphereBodies[2 * i] = sphere;
				sphereBodies[2 * i + 1] = label;
			} else {
				//make label
				Text3D label = new Text3D(b.x, b.y, b.z + (b.radius * 1.1), 0.0, 180.0, 0.0, b.name);
				label.setColor(Color.BLACK);

				//make sphere
				PhongMaterial material = new PhongMaterial(b.color);
				Sphere3D sphere = new Sphere3D(b.x, b.y, b.z, b.radius, material);
				sphereBodies[2 * i] = sphere;
				sphereBodies[2 * i + 1] = label;
			}

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
		return 1000;
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

}
