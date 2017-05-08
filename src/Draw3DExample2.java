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
import javafx.geometry.Point3D;


/**
*
* @author William Edison
* @version 1.00 July 2015
*
* Draws a series of boxes around the x-axis
* as the angle of rotation is varied from 0 to 2 PI.
* Uses x-axis transformation matrix.
*
*/
public class Draw3DExample2 implements Algorithm {

	private int id;
	private double[] info;
	private int iterations = 32;
	private long delay = 50L;
	private final boolean clear = false;
	private final boolean drone = false;
	private PhongMaterial vMaterial;

	public Draw3DExample2 ()
	{
		System.out.println("Loaded: " + this.getClass().getName());
		vMaterial = new PhongMaterial();
		vMaterial.setDiffuseColor(Color.SALMON);
		vMaterial.setSpecularColor(Color.LIGHTSALMON);
	}

	public Object processAlgorithm(int n)
	{
		double depth = 2.0;
		double height = 3.0;
		double width = 2.0;

		double a = n * 2.0 * Math.PI / iterations;
		double[] v = {5.0, 5.0, 5.0};

		double[][] mx = {{    1.0,        0.0,       0.0      },
		{    0.0,    Math.cos(a), Math.sin(a)},
		{    0.0,   -Math.sin(a), Math.cos(a)}};

		double[] t = matrixMultiply(v, mx);

		Box3D vt = new Box3D(new Point3D(t[0], t[1], t[2]), depth, height, width, vMaterial);

		return vt;

	}

	private double[] matrixMultiply(double[] v, double[][] m)
	{
		double[] t = new double[3];
		t[0] = v[0]*m[0][0] + v[1]*m[1][0] + v[2]*m[2][0];
		t[1] = v[0]*m[0][1] + v[1]*m[1][1] + v[2]*m[2][1];
		t[2] = v[0]*m[0][2] + v[1]*m[1][2] + v[2]*m[2][2];
		return t;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public double[] getInfo()
	{
		return info;
	}

	public void setInfo(double[] info)
	{
		this.info = info.clone();
	}

	public int getIterations()
	{
		return iterations;
	}

	public long getDelay()
	{
		return delay;
	}

	public boolean doClear()
	{
		return clear;
	}

	public boolean isDrone()
	{
		return drone;
	}

	public PhongMaterial getMaterial(int n)
	{
		return vMaterial;
	}

}
