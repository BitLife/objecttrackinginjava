import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class FilterImages {
	public Mat convertToHSV(Mat BGR){
		Mat HSV = new Mat();
		Imgproc.cvtColor(BGR, HSV, Imgproc.COLOR_RGB2HSV);
		return HSV;
	}
	
	public Mat filterHSV(Mat frame, boolean mode, SliderUIFrame slider) throws IOException{
		Mat filter1 = new Mat();
		Mat smoothImage = new Mat();
		Mat finalImage = new Mat();
		
		if(!mode){
			Core.inRange(frame, new Scalar(slider.hminValue(), slider.sminValue(), slider.vminValue()), new Scalar(slider.hmaxValue(), slider.smaxValue(), slider.vmaxValue()), filter1);
			Imgproc.blur(filter1, smoothImage, new Size(10, 10));
			Imgproc.threshold(smoothImage, finalImage, 150, 255, Imgproc.THRESH_BINARY);
			System.out.print(slider.hminValue() + ", ");
			System.out.print(slider.sminValue() + ", ");
			System.out.print(slider.vminValue() + ", ");
			System.out.print(slider.hmaxValue() + ", ");
			System.out.print(slider.smaxValue() + ", ");
			System.out.println(slider.vmaxValue());
		}
		
		else{
			Core.inRange(frame, new Scalar(102, 99, 177), new Scalar(116, 185, 258), filter1);
			Imgproc.blur(filter1, smoothImage, new Size(10, 10));
			Imgproc.threshold(smoothImage, finalImage, 150, 255, Imgproc.THRESH_BINARY);
		}
		
		return finalImage;
	}
	
	public double[] findPts(Mat img){
		double[] nums = new double[3];
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(img, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		double maxArea = 0;
		float[] radius = new float[1];
		Point center = new Point();
		for(int x = 0; x < contours.size(); x++){
			MatOfPoint c = contours.get(x);
			if(Imgproc.contourArea(c) > maxArea) {
				MatOfPoint2f c2f = new MatOfPoint2f(c.toArray());
				Imgproc.minEnclosingCircle(c2f, center, radius);
			}
		}
		nums[0] = center.x;
		nums[1] = center.y;
		nums[2] = radius[0];
		return nums;
	}
}