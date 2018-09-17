	

/**
 * @author YOUR NAME THE STUDENT IN 201
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		
		// TODO: read values at beginning of file to
		// find the radius
		double value = 0;
		
		Scanner scan = new Scanner(new File(fname));
		scan.nextInt();
		value = scan.nextDouble();
			
		scan.close();
		
		
		// TODO: return radius read
		return value;	
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
			
			// TODO: read # bodies, create array, ignore radius
			int nb = 0; // # bodies to be read
			nb = s.nextInt();
			Body[] bodies = new Body[nb];
			s.nextDouble();
			
			for(int k=0; k < nb; k++) {
				// TODO: read data for each body
				// construct new body object and add to array
				double x = s.nextDouble();
				double y = s.nextDouble();
				double z = s.nextDouble();
				double p = s.nextDouble();
				double q = s.nextDouble();
				String pass = s.next();
				
				bodies[k] = new Body(x, y, z, p, q, pass);
			}
			s.close();
			
			// TODO: return array of body objects read
			return bodies;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 157788000.0;
		double dt = 25000.0;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname);
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {
			
			// TODO: create double arrays xforces and yforces
			// to hold forces on each body
			double [] xForces = new double [5];
			double [] yForces = new double [5];
			
			// TODO: loop over all bodies, calculate
			// net forces and store in xforces and yforces
			//for (int i = 0; i <planets.length; i++) {
			//int i = 0;
			//for (Body body : bodies) {
			//	xForces[i] = body.calcNetForceExertedByX(bodies);
			//	yForces[i] = body.calcNetForceExertedByY(bodies);
			//	i += 1;
			//}
			for (int i = 0; i <bodies.length; i++) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			
			// TODO: loop over all bodies and call update
			// with dt and corresponding xforces, yforces values
			
			//for (Body body: bodies) {
			//	body.update(dt, xForces[i], yForces[i]);
			//	i += 1;
			//}
			for (int i = 0; i <bodies.length; i++) {
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			// TODO: loop over all bodies and call draw on each one
			for (Body body: bodies) {
				body.draw();
			}
			StdDraw.show(10);
		}
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
