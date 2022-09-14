package eecs2030.lab3;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

public class Vector3Test {

	@Test
	public void testVectorConstrGetElements1() {
		double v [] = {1.0, 2.0, 3.0};
		Vector3 newVector3 = new Vector3 (v);
		String error = String.format("Vector3 Constructor(%s) or getElements failed ", Arrays.toString(v));
		assertFalse(error, newVector3.getElements() == v);
		assertTrue(error, Arrays.equals(newVector3.getElements(), v));
	}

	@Test
	public void testVectorConstrGetElements2() {
		double v [] = {1.0, 2.0, 3.0};
		Vector3 newVector3 = new Vector3 (v[0], v[1], v[2]);
		String error = String.format("Vector3 Constructor(%f, %f, %f) or getElements failed ", v[0], v[1], v[2]);
		assertFalse(error, newVector3.getElements() == v);
		assertTrue(error, Arrays.equals(newVector3.getElements(), v));
	}

	@Test
	public void testVectorConstrGetElements3() {
		double v [] = {1.0, 2.0, 3.0};
		Vector3 v1 = new Vector3 (v);
		Vector3 v2 = new Vector3 (v1);
		String error = String.format("Vector3 Constructor(Vector3) or getElements failed ");
		assertFalse(error, v1.getElements() == v2.getElements());
		assertTrue(error, Arrays.equals(v2.getElements(), v));
	}

	@Test
	public void testVectorHashCode() {
		double v [] = {1.0, 2.0, 3.0};
		Vector3 vector1 = new Vector3 (v);
		Vector3 vector2 = new Vector3 (v);
		String error = String.format("Vector3 hashCode() failed");
		assertFalse(error, vector1 == vector2);
		assertTrue(error, vector1.equals(vector2));
		assertTrue(error, vector1.hashCode() == vector2.hashCode());
	}

	@Test
	public void testVectorEquals() {
		double v1 [] = {1.0, 2.0, 3.0};
		double v2 [] = {1.0, 2.0, 4.0};
		Vector3 vector1 = new Vector3 (v1);
		Vector3 vector2 = new Vector3 (v2);
		Vector3 vector3 = new Vector3 (v2);
		String error = String.format("Vector3 equals() failed");
		assertFalse(error, vector1.equals(null));
		assertTrue(error, vector1.equals(vector1));
		assertFalse(error, vector1.equals(vector2));
		assertTrue(error, vector2.equals(vector3));
		assertFalse(error, vector1.equals(vector3));
	}

	@Test
	public void testVectorCompareTo() {
		double v1 [] = {1.0, 2.0, 3.0};
		double v2 [] = {1.0, 2.0, 4.0};
		Vector3 vector1 = new Vector3 (v1);
		Vector3 vector2 = new Vector3 (v2);
		Vector3 vector3 = new Vector3 (v2);
		String error = String.format("Vector3 compareTo() failed");
		assertTrue(error, vector1.compareTo(vector2) < 0);
		assertTrue(error, vector1.compareTo(vector3) < 0);
		assertTrue(error, vector3.compareTo(vector1) > 0);
		assertTrue(error, vector2.compareTo(vector2) == 0);
	}

}
