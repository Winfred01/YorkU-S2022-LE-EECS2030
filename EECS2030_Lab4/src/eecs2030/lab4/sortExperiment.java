package eecs2030.lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class sortExperiment {
	private static Random rng = new Random();

	public static void main(String[] args) {
		List <Integer> list;
		long start, end;

		//You might need to change the 1M value to something smaller for other sorts
		for (int i = 100; i < 20000; i *= 2){ 
			list = createRandomList(i); 
			start = System.nanoTime();
			Lists.defaultSort(list);
			end = System.nanoTime();
			System.out.println("N=" + i + "\nDefault Sort, Time spent: " + (end - start)/1e6 + " ms");
			
			start = System.nanoTime(); // 记录初始时间
			Lists.selectionSortRecursive(list); // 跑一下List.java里面的selectionSortRecursive
			end = System.nanoTime();  // 记录结束时间
			System.out.println("Selection-Recursive, Time spent: " + (end - start)/1e6 + " ms");
			
			start = System.nanoTime();// 记录初始时间
			Lists.selectionSortIterative(list);// 跑一下List.java里面的selectionSortIterative
			end = System.nanoTime();// 记录结束时间
			System.out.println("Selection-Iterative, Time spent: " + (end - start)/1e6 + " ms");

			System.out.println("");
		}
	}

	private static List <Integer> createRandomList (int size){
		rng.setSeed(8);
		List <Integer> list = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			list.add(rng.nextInt());
		}
		return list;
	}


}
