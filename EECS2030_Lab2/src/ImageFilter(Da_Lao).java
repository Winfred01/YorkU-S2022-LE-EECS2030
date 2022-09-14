/*
 * First Name: Cheng Tian
 * Last Name: Cui
 * Student Number: 218082305
 * EECS2030 A Lab 2
 */

import java.util.Arrays;

/**
 * @author Andriy Pavlovych
 * The class is meant to illustrate a couple of image-processing algorithms:
 * Gaussian blurring and simple edge detection
 */
public class ImageFilter{
	
	//Private constructor to prevent object creation
	private ImageFilter()
	{
		//Empty constructor
	}
	
	/**
	 * Method implements Gaussian blurring
	 * @param imageData array of image pixels
	 * @param width image width
	 * @param sigma parameter of the Gaussian distribution
	 */
	public static void blur (int[] imageData, int width, double sigma){
		final int MAX_KERNEL_SIZE = 15;
		double kernel [] = new double [MAX_KERNEL_SIZE];
		
		//Variable added to help manage loops
		int height = imageData.length / width;
		
		for (int i = 0; i <= MAX_KERNEL_SIZE / 2 ; i++){
			kernel [MAX_KERNEL_SIZE / 2 + i] = Math.exp(-0.5 * i * i / sigma / sigma);
			kernel [MAX_KERNEL_SIZE / 2 - i] = Math.exp(-0.5 * i * i / sigma / sigma);
		}
		double kernelSum = 0;
		for (int i = 0; i < MAX_KERNEL_SIZE; i++) kernelSum += kernel [i]; //compute the sum
		for (int i = 0; i < MAX_KERNEL_SIZE; i++) kernel [i] /= kernelSum; //normalize by that sum
		System.out.println(Arrays.toString(kernel));
		
		//Arrays for tracking pixels
		int [] xBlurredData = new int[imageData.length]; //X convolution intermediate array
		int [] yBlurredData = new int[imageData.length]; //Y convolution intermediate array
		
		//X-wise convolution
		for (int y = 0; y < height; y++) //Iterate through each row
		{
			for (int x = 0; x < width; x++) //Iterate through each column
			{
				//Declare and initialize accumulators
				int RSum = 0,
					GSum = 0,
					BSum = 0;
				
				for(int k = 0; k < MAX_KERNEL_SIZE; k++) //Iterate through nearby pixels on X axis
				{
					//Get pixel corresponding to kernel position, closest pixel in picture boundary if target is outside
					int targetX = Math.min(Math.max(0, x - MAX_KERNEL_SIZE / 2 + k), width - 1);
					
					//Get RGB channel values
					int RGB = getImageDataAt(imageData, width, targetX, y);
					int R = (RGB & 0x00FF0000) >>> 16;
					int G = (RGB & 0x0000FF00) >>> 8;
					int B = (RGB & 0x000000FF);
					
					//Add to RBG channel sums
					RSum += (int) (R * kernel[k]);
					GSum += (int) (G * kernel[k]);
					BSum += (int) (B * kernel[k]);
				}
				
				//Convert from RGB to int and then set value in corresponding pixel of intermediate array
				setImageDataAt(xBlurredData, width, x, y, (RSum << 16) | (GSum << 8) | BSum);
			}
		}
		
		//Y-wise convolution
		for (int y = 0; y < height; y++) //Iterate through each row
		{
			for (int x = 0; x < width; x++) //Iterate through each column
			{
				//Declare and initialize accumulators
				int RSum = 0,
					GSum = 0,
					BSum = 0;
				
				for(int k = 0; k < MAX_KERNEL_SIZE; k++) //Iterate through nearby pixels on X axis
				{
					//Get pixel corresponding to kernel position, closest pixel in picture boundary if target is outside
					int targetY = Math.min(Math.max(0, y - MAX_KERNEL_SIZE / 2 + k), height - 1);
					
					//Get RGB channel values
					int RGB = getImageDataAt(xBlurredData, width, x, targetY);
					int R = (RGB & 0x00FF0000) >>> 16;
					int G = (RGB & 0x0000FF00) >>> 8;
					int B = (RGB & 0x000000FF);
					
					//Add to RBG channel sums
					RSum += (int) (R * kernel[k]);
					GSum += (int) (G * kernel[k]);
					BSum += (int) (B * kernel[k]);
				}
				
				//Convert from RGB to int and then set value in corresponding pixel of intermediate array
				setImageDataAt(imageData, width, x, y, (RSum << 16) | (GSum << 8) | BSum);
			}
		}
	}
	
	//Gets the integer pixel color data at a specific coordinate
	private static int getImageDataAt(int[] imageData, int width , int x, int y)
	{
		//Coordinates of pixel can be expressed as (x, y) = (imageData.length % width, imageData.length / width)
		return imageData[x + width * y];
	}
	
	//Sets the integer pixel color data at a specific coordinate
	private static void setImageDataAt(int[] resultImageData, int width, int x, int y, int color)
	{
		//Coordinates of pixel can be expressed as (x, y) = (imageData.length % width, imageData.length / width)
		resultImageData[x + width * y] = color;
	}

	/**
	 * Method implements simple edge detection
	 * @param imageData imageData array of image pixels
	 * @param width image width
	 */
	public static void edgeDetection(int[] imageData, int width) {
		//Variable added to help manage loop
		int height = imageData.length / width;
		
		//Edge Detection Kernels
		//Prewitt
		//int[][] xOperator = {{-1, 0, 1}, {-1, 0, 1}, {-1, 0, 1}};
		//int[][] yOperator = {{-1, -1, -1}, {0, 0, 0}, {1, 1, 1}};
		//Sobel
		int[][] xOperator = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
		int[][] yOperator = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
		//Laplacian
		//int[][] xOperator = {{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};
		//int[][] yOperator = {{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};
		
		int opHeight = xOperator.length;
		int opWidth = xOperator[0].length;
		
		//Edges array & threshold
		int[] edges = new int[imageData.length];
		int edgeThreshold = 70;
		
		for (int y = 0; y < height; y++) //Iterate through each row
		{
			for (int x = 0; x < width; x++) //Iterate through each column
			{
				//Declare and initialize accumulators of color channels
				int xRAcc = 0;
				int xGAcc = 0;
				int xBAcc = 0;
				int yRAcc = 0;
				int yGAcc = 0;
				int yBAcc = 0;
				
				for (int m = 0; m < opHeight; m++) //Iterate through operator rows
				{
					for (int n = 0; n < opWidth; n++) //Iterate through operator columns
					{
						//Get pixel corresponding to kernel position, closest pixel in picture boundary if target is outside
						int targetX = Math.min(Math.max(0, x - opWidth / 2 + n), width - 1);
						int targetY = Math.min(Math.max(0, y - opHeight / 2 + m), height - 1);
						
						//Get the pixel color data and write to dedicated array for convolution
						int RGB = getImageDataAt(imageData, width, targetX, targetY);
						
						//Add RGB values to accumulators
						xRAcc += ((RGB & 0x00FF0000) >>> 16) * xOperator[m][n];
						xGAcc += ((RGB & 0x0000FF00) >>> 8) * xOperator[m][n];
						xBAcc +=  (RGB & 0x000000FF) * xOperator[m][n];
						yRAcc += ((RGB & 0x00FF0000) >>> 16) * yOperator[m][n];
						yGAcc += ((RGB & 0x0000FF00) >>> 8) * yOperator[m][n];
						yBAcc +=  (RGB & 0x000000FF) * yOperator[m][n];
					}
				}				
				
				//Restrict all RGB accumulator values to [0, 255]
				xRAcc = Math.min(255, Math.max(0, xRAcc));
				xGAcc = Math.min(255, Math.max(0, xGAcc));
				xBAcc = Math.min(255, Math.max(0, xBAcc));
				yRAcc = Math.min(255, Math.max(0, yRAcc));
				yGAcc = Math.min(255, Math.max(0, yGAcc));
				yBAcc = Math.min(255, Math.max(0, yBAcc));
				
				int R = (int)(Math.sqrt(xRAcc * xRAcc + yRAcc * yRAcc));
				int G = (int)(Math.sqrt(xGAcc * xGAcc + yGAcc * yGAcc));
				int B = (int)(Math.sqrt(xBAcc * xBAcc + yBAcc * yBAcc));
				setImageDataAt(edges, width, x, y, Math.max(Math.max(R, G), B));
			}
		}
		
		//Loop through all pixels in edges and imageData
		for(int i = 0; i < imageData.length; i++)
		{
			//If edge value meets threshold, replace pixel with white
			if(edges[i] >= edgeThreshold)
				imageData[i] = 0xFFFFFF;
			else //Otherwise pixel will be black
				imageData[i] = 0x000000;
		}
	}
}