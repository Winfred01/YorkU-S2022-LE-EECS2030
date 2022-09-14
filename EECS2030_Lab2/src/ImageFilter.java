import java.util.Arrays;

/**
 * @author Andriy Pavlovych
 * The class is meant to illustrate a couple of image-processing algorithms:
 * Gaussian blurring and simple edge detection
 */
public class ImageFilter{
	//TODO prevent object creation, as this is a utility class
	private ImageFilter(){
		
	}
	
	/**
	 * Method implements Gaussian blurring
	 * @param imageData array of image pixels
	 * @param width image width
	 * @param sigma parameter of the Gaussian distribution
	 */
	public static void blur (int[] imageData, int width, double sigma){
		//TODO your task is to replace this stub code with the proper implementation of the method
		//create a temporary array to store the result
		final int MAX_KERNEL_SIZE = 15;
		double kernel [] = new double [MAX_KERNEL_SIZE];
		
		for (int i = 0; i <= MAX_KERNEL_SIZE / 2 ; i++){
			kernel [MAX_KERNEL_SIZE / 2 + i] = Math.exp(-0.5 * i * i / sigma / sigma);
			kernel [MAX_KERNEL_SIZE / 2 - i] = Math.exp(-0.5 * i * i / sigma / sigma);
		}
		double kernelSum = 0;
		for (int i = 0; i < MAX_KERNEL_SIZE; i++) kernelSum += kernel [i]; //compute the sum
		for (int i = 0; i < MAX_KERNEL_SIZE; i++) kernel [i] /= kernelSum; //normalize by that sum
		System.out.println(Arrays.toString(kernel));
		
		int [] resultImageDatax = new int[imageData.length]; //TODO NO, it should not be null!
		
		//TODO apply convolution in one dimension
		for(int i = 0; i < imageData.length/width; i++) {
			for(int j = 0; j < width; j++) {
				for(int k = 0; k < MAX_KERNEL_SIZE; k++) {
					
					int imageColumnIndex = j + k - MAX_KERNEL_SIZE/2;
					
					if (imageColumnIndex < 0) {
						imageColumnIndex = 0;
					}else if(imageColumnIndex >= width) {
						imageColumnIndex = width - 1;
					}
					
					int image1DIndex = i * width + imageColumnIndex;
					
					int red, green, blue;
					red 	= (int)(kernel[k] * ((imageData[image1DIndex] & 0x00FF0000)>>16));
					green 	= (int)(kernel[k] * ((imageData[image1DIndex] & 0x0000FF00)>>8));
					blue 	= (int)(kernel[k] * (imageData[image1DIndex] & 0x000000FF));
					
					resultImageDatax[i * width + j] += red << 16 | green << 8 | blue;
					
				}
				
			}
		}
		System.arraycopy(resultImageDatax, 0, imageData, 0, imageData.length);
		
		//TODO repeat for the other dimension
		int [] resultImageDatay = new int[imageData.length];
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < imageData.length/width; j++) {
				for(int k = 0; k < MAX_KERNEL_SIZE; k++) {
					
					int imageRowIndex = j + k - MAX_KERNEL_SIZE/2;
					
					if (imageRowIndex < 0) {
						imageRowIndex = 0;
					}else if (imageRowIndex >= imageData.length/width) {
						imageRowIndex = (imageData.length/width) - 1;
					}
					
					int image1DIndex = imageRowIndex * width + i;
					
					int red, green, blue;
					red 	= (int)(kernel[k] * ((imageData[image1DIndex] & 0x00FF0000)>>16));
					green 	= (int)(kernel[k] * ((imageData[image1DIndex] & 0x0000FF00)>>8));
					blue 	= (int)(kernel[k] * (imageData[image1DIndex] & 0x000000FF));
					
					resultImageDatay[j * width + i] += red << 16 | green << 8 | blue;
					
				}
				
			}
		}
		//TODO store the result back in the original imageData array
		//one way to store the result back 
		System.arraycopy(resultImageDatay, 0, imageData, 0, imageData.length);
	}

	/**
	 * Method implements simple edge detection
	 * @param imageData imageData array of image pixels
	 * @param width image width
	 */
	public static void edgeDetection(int[] imageData, int width) {
		//TODO your task is to replace this stub code with the proper implementation of the method 
		//The code below merely demonstrates how to extract RGB pixel values from the image and how to write them them back
		/*for (int i=0; i<imageData.length; i++){
			int red, green, blue;
			red 	= (imageData[i] & 0x00FF0000)>>16; //try 0.0 * (imageData[i] & 0x00FF0000)>>16 here
			green 	= (imageData[i] & 0x0000FF00)>>8;
			blue 	= (imageData[i] & 0x000000FF);

		//one way to store the result back
		imageData[i] = red<<16 | green <<8 | blue;
		}*/
		
		double kernel [][] = {
				{-1, 0, 1},
				{-2, 0, 2},
				{-1, 0, 1}
		};
		//System.out.println("imageData.length: " + imageData.length);
		//System.out.println("width: " + width);
		
		int[] resultImageData = new int[imageData.length];
		
		for (int x = 0; x < imageData.length/width; x++) {
			for(int y = 0; y < width; y++) {
				
				int red = 0, green = 0, blue = 0;
				
				for(int i = 0; i < kernel.length; i++) {
					for(int j = 0; j < kernel[i].length; j++) {
						
						int imageColumnIndex = y + j - kernel.length/2;
						
						if( imageColumnIndex < 0) {
							imageColumnIndex = 0;
						}else if( imageColumnIndex >= width) {
							imageColumnIndex = width - 1;
						}
						
						int imageRowIndex = x + i - kernel.length/2;
						
						if( imageRowIndex < 0) {
							imageRowIndex = 0;
						}
						if( imageRowIndex >= imageData.length/width) {
							imageRowIndex = imageData.length/width - 1;
							//System.out.println("imageRowIndex: " + imageRowIndex +", imageColumnIndex: "+imageColumnIndex);
						}
						
						int image1DIndex = imageRowIndex * width + imageColumnIndex;
						//System.out.println(image1DIndex);
						red 	+= (int)(kernel[i][j] * ((imageData[image1DIndex] & 0x00FF0000)>>16));
						green   += (int)(kernel[i][j] * ((imageData[image1DIndex] & 0x0000FF00)>>8));
						blue 	+= (int)(kernel[i][j] * (imageData[image1DIndex] & 0x000000FF));
					}
				}
				if(red < 0) red = 0;
				if(green < 0) green = 0;
				if(blue < 0) blue = 0;
				
				if(red > 255) red = 255;
				if(green > 255) green = 255;
				if(blue > 255) blue = 255;
				
				resultImageData[x * width + y] = red << 16 | green << 8 | blue;
			}
		}
		
		System.arraycopy(resultImageData, 0, imageData, 0, imageData.length);
	}


}
