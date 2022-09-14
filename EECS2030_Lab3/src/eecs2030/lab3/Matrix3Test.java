package eecs2030.lab3;

import static org.junit.Assert.*;
import org.junit.Test;

public class Matrix3Test {

	@Test
	public void testMatrixConstr() {
		double m [] [] = {{1.0, 2.0, 3.0},
						  {4.0, 5.0, 6.0},
		                  {7.0, 8.0, 9.0}};
		Matrix3 m1= new Matrix3 (m);
		String error = String.format("Matrix3 Constructor(array) or getElements failed ");
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				assertEquals(error + " test 1", m[i][j], m1.getElements()[i][j], 1e-6);
				assertEquals(error + " test 2", m1.getElement(i, j), m1.getElements()[i][j], 1e-6);
			}
		}
		m[0][0] = 0;
		assertNotEquals(error + " test 3", m[0][0], m1.getElements()[0][0], 1e-6);
		assertTrue(error + " test 4", m1.getElement(0, 0) == m1.getElements()[0][0]);
	}

}
