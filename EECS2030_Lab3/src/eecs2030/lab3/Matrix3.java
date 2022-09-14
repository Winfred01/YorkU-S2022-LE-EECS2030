package eecs2030.lab3;

/**
 * An immutable class implementing a 3x3 matrix 
 * @author Andriy
 * EECS 2030 Lab 3 SU2022
 */
public class Matrix3 {
	//TODO
	//consider how to ensure the objects are immutable
	private Matrix3() {
		
	}
	/**
	 * Creates a 3x3 matrix from an 2D array
	 * @param array array containing the elements of the desired matrix
	 * TODO HERE, determine and specify the preconditions, and take an appropriate action
	 */
	public Matrix3(double[][] array) {
		//TODO
		//HERE, consider how to ensure the objects are immutable
	}
	
	/**
	 * Clones an existing matrix
	 * @param old an existing Matrix3 object 
	 * TODO HERE, determine and specify the preconditions, and take an appropriate action
	 */
	public Matrix3(Matrix3 old) {
		//TODO
		//consider how to ensure the objects are immutable
	}

	/**
	 * Returns a matrix element at a row, column
	 * @param row row
	 * @param column column
	 * @return matrix element as a scalar
	 * TODO HERE, determine and specify the preconditions, and take an appropriate action
	 */
	public double getElement (int row, int column){
		//TODO
		return 0d;
	}
	
	/**
	 * Returns matrix elements as a 2D array
	 * @return 2D array containing 9 matrix elements
	 */
	public double[][] getElements (){
		//TODO
		//HERE, consider how to ensure the objects are immutable
		return null;
	}

}
