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
//import SliderPackage.SliderUIFrame;

public class FilterImages {
	public Mat convertToHSV(Mat BGR){
		Mat HSV = new Mat();
		Imgproc.cvtColor(BGR, HSV, Imgproc.COLOR_RGB2HSV);
		return HSV;
	}
	public Mat filterHSV(Mat frame) throws IOException{
		SliderUIFrame slider = new SliderUIFrame();
		Mat filter1 = new Mat();
		Mat filter2 = new Mat();
		Mat combineFilters = new Mat();
		Mat smoothImage = new Mat();
		Mat finalImage = new Mat();
		//Core.inRange(frame, new Scalar(97, 212, 94), new Scalar(164, 288, 212), filter1);
		//Core.inRange(frame, new Scalar(86, 70, 102), new Scalar(129, 277,253), filter2);
		//Core.inRange(frame, new Scalar(slider.hminValue(), slider.sminValue(), slider.vminValue()), new Scalar(slider.hmaxValue(), slider.smaxValue(), slider.vmaxValue()), filter1);
		//Core.bitwise_and(filter1, filter2, combineFilters);
		Core.inRange(frame, new Scalar(51, 43, 126), new Scalar(126, 263, 258), filter1);
		Core.inRange(frame, new Scalar(81, 110, 156), new Scalar(121, 258, 234), filter2);
		Core.bitwise_and(filter1, filter2, combineFilters);
		Imgproc.blur(filter2, smoothImage, new Size(10, 10));
		Imgproc.threshold(smoothImage, finalImage, 150, 255, Imgproc.THRESH_BINARY);
		/*System.out.println(slider.hminValue());
		System.out.println(slider.hmaxValue());
		System.out.println(slider.sminValue());
		System.out.println(slider.smaxValue());
		System.out.println(slider.vminValue());
		System.out.println(slider.vmaxValue());*/
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