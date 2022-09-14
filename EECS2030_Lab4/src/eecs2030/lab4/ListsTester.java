package eecs2030.lab4;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListsTester {
	private static Random rng = new Random();

	@Test
	public void test01_defaultSort() {
		List <Integer> list = createRandomList(100);

		Lists.defaultSort(list);

		String error = "defaultSort failed (list not sorted)";
		checkSortedOrder (error, list);
	}

	@Test
	public void test02_selectionSortRecursive() {
		List <Integer> list = createRandomList(100);

		Lists.selectionSortRecursive(list);

		String error = "defaultSort failed (list not sorted)";
		checkSortedOrder (error, list);
	}

	@Test
	public void test03_selectionSortIterative() {
		List <Integer> list = createRandomList(100);

		Lists.selectionSortIterative(list);

		String error = "defaultSort failed (list not sorted)";
		checkSortedOrder (error, list);
	}

	private static List <Integer> createRandomList (int size){
		rng.setSeed(8);
		List <Integer> list = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			list.add(rng.nextInt());
		}
		return list;
	}

	private static void checkSortedOrder (String error, List <Integer> list){
		int prev = list.get(0);
		for (int item:list){
			assertTrue(error, item >= prev);
			prev = item;
		}
	}

}
