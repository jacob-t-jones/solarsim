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

public class SolarSystem extends Application implements Algorithm {

	//The planets
	static Body sun;
	static Body earth;

	static ArrayList<Body> bodies;

	public static void main(String[] args) {

		//Initialize planets
		initPlanets();

		launch(args);

	}


	public static void initPlanets() {
		sun = new Body(0.0, 0.0, 0.0, 3.0, 500.0, Color.YELLOW);
		earth = new Body(20.0, 0.0, 0.0, 1.0, 500.0, Color.BLUE);

		bodies = new ArrayList<Body>(); //an array to hold the bodies
		bodies.add(sun);
		bodies.add(earth);
	}


	@Override //Required to start the window and scene
	public void start(Stage stage) {

		Draw3D draw3d = new Draw3D();

		stage.setScene(draw3d.buildScene());

		try {
			draw3d.start(stage);
		} catch (Exception e) {
			System.out.println("Error ocurred while trying to start stage");
			e.printStackTrace();
		}

		draw3d.setBackgroundColor(Color.BLACK);

	}

	//Interface methods
	//This is the main animation function - it is called at specific intervals by the GUI
	//We just make it return the array ob objects we want to be drawn
	public Object processAlgorithm(int iteration) {

		//update body positions

		//create spheres that can be drawn for the bodies
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
		return 100;
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
