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
		sun = new Body(0, 0.0, 0.0, 0.0, 0.1, 500.0, Color.YELLOW);
		mercury = new Body(1, 0.387, 0.0, 0.0, 0.1, 500, Color.GREY);
		venus = new Body(2, 0.723, 0.0, 0.0, 0.1, 500.0, Color.ORANGE);
		earth = new Body(3, 1.0, 0.0, 0.0, 0.1, 500.0, Color.BLUE);
		mars = new Body(4, 1.524, 0.0, 0.0, 0.075, 500.0, Color.RED);
		jupiter = new Body(5, 5.203, 0.0, 0.0, 2, 500.0, Color.ORANGE);
		saturn = new Body(6, 9.537, 0.0, 0.0, 1.5, 500.0, Color.YELLOW);
		uranus = new Body(7, 19.191, 0.0, 0.0, 1.25, 500.0, Color.BLUE);
		neptune = new Body(8, 30.069, 0.0, 0.0, 1.25, 500.0, Color.GREEN);

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
		Object[] sphereBodies = new Object[bodies.size()];
		for (int i = 0; i < sphereBodies.length; i++) {
			sphereBodies[i] = bodies.get(i).createSphere();
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
