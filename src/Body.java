import java.lang.Math;

public class Body {
	private double myXPos; //current x position
	private double myYPos; //current y position
	private double myXVel; //current velocity in x direction
	private double myYVel; //current velocity in y direction
	private double myMass; //mass of planet
	private String myFileName; //file name
	
	public Body(double xp, double yp, double xv, 
			double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	public Body(Body p) {
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
	}
	
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
	public double getXVel() {
		return myXVel;
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}

	public String getName() {
		return myFileName;
	}
	
	public double calcDistance(Body b) {
		double xvalue = b.myXPos - this.myXPos;
		double yvalue = b.myYPos - this.myYPos;
		return Math.sqrt(xvalue*xvalue + yvalue*yvalue);
	}
	
	public double calcForceExertedBy(Body p) {
		double grav = 6.67 * Math.pow(10, -11);
		double dis = this.calcDistance(p);
		double force = grav * this.myMass * p.myMass / Math.pow(dis, 2);
		return force;
	}
	
	public double calcForceExertedByX(Body p) {
		double xvalue = p.myXPos - this.myXPos;
		double force = this.calcForceExertedBy(p);
		double xforce = force * xvalue / this.calcDistance(p);
		return xforce;
	}
	
	public double calcForceExertedByY(Body p) {
		double yvalue = p.myYPos - this.myYPos;
		double force = this.calcForceExertedBy(p);
		double yforce = force * yvalue / this.calcDistance(p);
		return yforce;
	}	
	
	public double calcNetForceExertedByX(Body[] bodies) {
		double force = 0;
		for(int n = 0; n < bodies.length ; n++) {
			if (! bodies[n].equals(this)) {
				force = force + this.calcForceExertedByX(bodies[n]);
			}
		}
		return force;	
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		double force = 0;
		for(int n = 0; n < bodies.length ; n++) {
			if (! bodies[n].equals(this)) {
				force = force + this.calcForceExertedByY(bodies[n]);
			}
		}
		return force;	
	}
	
	public void update(double deltaT, double xforce, double yforce) {
		double xacc = xforce / this.myMass;
		double yacc = yforce / this.myMass;
		myXVel = this.myXVel + xacc*deltaT;
		myYVel = this.myYVel + yacc*deltaT;
		myXPos = this.myXPos + myXVel*deltaT;
		myYPos = this.myYPos + myYVel*deltaT;
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
	}
}

