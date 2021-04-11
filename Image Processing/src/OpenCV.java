import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

public class OpenCV
{
    public static void main( String[] args )
    {
        // Load the native OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat input = new Mat();
        Mat dest = new Mat();

        Imgproc.cvtColor(input, dest, 5);
        Imgproc.blur(input, dest, new Size(3, 3));

//        Imgproc.Canny(detectedEdges, detectedEdges, this.threshold.getValue(), this.threshold.getValue() * 3, 3, false);


        Core.add(dest, Scalar.all(0), dest);


        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
        System.out.println( "mat = " + mat.dump() );
    }
}