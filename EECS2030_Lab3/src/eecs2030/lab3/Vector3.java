package eecs2030.lab3;

/**
 * An immutable class implementing a 3D vector 
 * @author Andriy
 * EECS 2030 Lab 3 SU2022
 *
 */
public class Vector3 implements Comparable <Vector3>{
	//TODO
	//consider how to ensure the objects are immutable

	/**
	 * Creates a 3D vector from 3 numeric scalar components
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 */
	public Vector3(double x, double y, double z) {
		//TODO
	}

	/**
	 * Creates a 3D vector from an array
	 * @param v array containing 3 components of the desired vector 
	 * TODO HERE, determine and specify the preconditions, and take an appropriate action
	 */
	public Vector3(double[] v) {
		//TODO
		//consider how to ensure the objects are immutable
	}

	/**
	 * Clones an existing vector
	 * @param old an existing Vector3 object 
	 * TODO HERE, determine and specify the preconditions, and take an appropriate action
	 */
	public Vector3(Vector3 old) {
		//TODO
		//consider how to ensure the objects are immutable
	}

	/**
	 * Returns a vector component at a specified index
	 * @param index the index of the vector component
	 * @return vector component as a scalar
	 * TODO HERE, determine and specify the preconditions, and take an appropriate action
	 */
	public double getElement (int index){
		//TODO
		return 0d;
	}

	/**
	 * Returns vector components as an array
	 * @return vector components, first element at index [0], and so on
	 */
	public double[] getElements (){
		//TODO
		//consider how to ensure the objects are immutable
		return null;
	}

	@Override
	public int hashCode() {
		//TODO
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		//TODO
		return true;
	}

	/**
	 * Compares vectors by their magnitudes
	 * @return -1 if o is greater than this, 0 if equal, 1 otherwise
	 */
	@Override
	public int compareTo(Vector3 o) {
		//TODO
		return 0;
	}
	
	
}
