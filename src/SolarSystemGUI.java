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

public class SolarSystemGUI extends Application
{

  public static void main(String[] args) {
    launch(args);
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

		draw3d.setBackgroundColor(Color.WHITE);

	}

}
