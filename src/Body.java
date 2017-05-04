// Physical bodies for the simulation

import java.util.*;

public class Body {
        // Coordinates
        double x, y, z;

        // Physical dimensions
        double mass, radius;
        
        // Orbit Information
        double period, e, parallaxAngle;

        // Universal Constants
        final static double INVERSE_G = 14983338500;
        final static double G         = 1 / INVERSE_G;
        final static double PI        = Math.PI;


}
