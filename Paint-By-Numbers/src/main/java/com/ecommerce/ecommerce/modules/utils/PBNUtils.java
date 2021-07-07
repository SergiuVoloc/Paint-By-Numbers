package com.ecommerce.ecommerce.modules.utils;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Sergiu Voloc
 * @version 1.0
 */


@Component
public class PBNUtils {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public List<File> process(String filename) throws IOException {
        List<File> files = new ArrayList<>();
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
        Mat img = Imgcodecs.imread(filename);
        int k = 10;
        String uuid = UUID.randomUUID().toString();
        List<Mat> clusters = cluster(img, k);
        Imgcodecs.imwrite(uploadDir + "\\"+uuid+".png", clusters.get(0));
        Mat t = drawEdges(clusters, uploadDir + "\\"+uuid+"_C.png");
        files.add(new File(uploadDir + "\\"+uuid+".png"));
        files.add(new File(uploadDir + "\\"+uuid+"_C.png"));
        return files;
    }

    /**
     * Method helping to identify clusters centers and writing them in centers.jpg
     *
     * @param cutout input image
     * @param k represents number of clusters
     * @return image, labels and it's centers passed as parameters to showClusters method
     */
    public static List<Mat> cluster(Mat cutout, int k) {

        // Blurring the image using a Gaussian filter
        Mat blurred = new Mat();
        Imgproc.GaussianBlur(cutout, blurred, new Size(1,1),0);
        Mat samplesLAB = blurred;

        // applying morphological transformations using an erosion and dilation as basic operations
        Imgproc.morphologyEx(samplesLAB, samplesLAB, Imgproc.MORPH_OPEN, Mat.ones(5,5, CvType.CV_32F));
        Mat samples = samplesLAB.reshape(1, samplesLAB.cols() * samplesLAB.rows());

        Mat samples32f = new Mat();
        samples.convertTo(samples32f, CvType.CV_32F);

        // Setting the termination criteria for iterative algorithm
        TermCriteria criteria = new TermCriteria(TermCriteria.COUNT, 100, 1);

        // Applying KMeans Algorithm that finds centers of clusters
        Mat labels = new Mat();
        Mat centers = new Mat();
        Core.kmeans(samples32f, k, labels, criteria, 3, Core.KMEANS_PP_CENTERS, centers);
//        Imgcodecs.imwrite("/static/images/ProcessingIMG/output/centers.jpg", centers);
//        System.out.println(labels.toString());
        return showClusters(samplesLAB, labels, centers);
    }



    /**
     *
     * @param cutout represents input image processed by KMeans
     * @param labels input/output integer array that stores the cluster indices for image
     * @param centers clusters centers of the image
     * @return
     */
    private static List<Mat> showClusters (Mat cutout, Mat labels, Mat centers) {

        List<Mat> rel = new ArrayList<Mat>();
        Mat result = new Mat(cutout.size(),CvType.CV_8UC3);
        Mat labels2 = labels.reshape(1, cutout.rows());
//        System.out.println("labels "+labels2.toString()+" cols "+labels2.size());
        for(int i = 0; i < cutout.rows();i++){
            for(int j = 0; j < cutout.cols();j++){
                int label = (int) labels2.get(i, j)[0];
                double color0 = centers.get(label, 0)[0];
                double color1 = centers.get(label, 1)[0];
                double color2 = centers.get(label, 2)[0];
                double[] v = new double[]{color0,color1,color2};
                result.put(i, j, v);

            }
        }
        rel.add(result);

        for(int i= 0; i< centers.rows();i++){
            Mat part = Mat.zeros(cutout.size(),CvType.CV_8UC1);

            for(int x = 0; x < cutout.rows();x++){
                for(int y = 0; y < cutout.cols();y++){
                    int label = (int) labels2.get(x, y)[0];
                    if(label==i){
                        part.put(x, y, 255.0);//array
                    }
                }
            }
            rel.add(part);

        }
        rel.add(centers);

        return rel;
    }



    /**
     * Identifying the edges of each cluster and inserting labels to each color
     *
     * @param clusters the clustered image processed by K-Means
     * @return the image with each cluster identified by the outline and a number inside
     */
    public static Mat drawEdges(List<Mat> clusters, String fileName){

        // identifying the image size
        Mat re = Mat.zeros(clusters.get(0).size(),CvType.CV_8UC1);

        // applying morphological transformations using an erosion and dilation as basic operations
        Imgproc.morphologyEx(re, re, Imgproc.MORPH_CLOSE, Mat.ones(5,5, CvType.CV_32F));

        for(int i =1; i< clusters.size()-1; i++){
            Mat c = clusters.get(i);
            List<MatOfPoint> points = new ArrayList<MatOfPoint>();
            Mat hierarchy = new Mat();

            // finding contours of the binary image using the algorithm CITE: Suzuki85
            Imgproc.findContours(c, points, hierarchy, Imgproc.RETR_EXTERNAL , Imgproc.CHAIN_APPROX_SIMPLE);
            //note! image border will be clipped

            List<MatOfPoint> filteredPoints = new ArrayList<MatOfPoint>();

            //System.out.println("pointslength:"+points.size()+" hierarchy:"+hierarchy.cols()+" " +hierarchy.get(0, 0)[3]);
            for(int k =0; k<points.size();k++){
                MatOfPoint contour = points.get(k);
                if (Imgproc.contourArea(contour) > 32) {
                    filteredPoints.add(contour);
                    if(hierarchy.get(0, k)[3]<0)
                        Imgproc.putText(
                                re,
                                ""+i,
                                new Point (contour.get(0,0)[0],contour.get(0,0)[1]+15),
                                Imgproc.FONT_HERSHEY_PLAIN,
                                1,
                                new Scalar(100,100,100)
                        );
                }
            }

            Mat cont = Mat.zeros(clusters.get(0).size(),CvType.CV_8UC1);
            Imgproc.drawContours(cont, filteredPoints, -1, new Scalar(255.0));
            Imgproc.drawContours(re, filteredPoints, -1, new Scalar(55.0));


//	        Iterator<MatOfPoint> each2 = filteredPoints.iterator();
//	        while (each2.hasNext()) {
//	            MatOfPoint contour2 = each2.next();
//                Imgproc.putText(re, ""+i, new Point (contour2.get(0,0)[0],contour2.get(0,0)[1]), Imgproc.FONT_HERSHEY_PLAIN, 1, new Scalar(75,75,75));
//                Imgproc.putText(cont, ""+i, new Point (contour2.get(0,0)[0],contour2.get(0,0)[1]), Imgproc.FONT_HERSHEY_PLAIN, 1, new Scalar(255));
//	        }

//            Imgcodecs.imwrite("/static/images/ProcessingIMG/output/cont_"+i+".png", cont);
        }

        Mat allwhite = Mat.ones(clusters.get(0).size(),CvType.CV_8UC1);
        allwhite.setTo(new Scalar(255.));
        Mat reInv = new Mat();
        Core.subtract(allwhite,re,reInv);
        Mat bott = Mat.zeros(new Size(reInv.cols(),50),CvType.CV_8UC3);

        Mat centers = clusters.get(clusters.size()-1);
        int swidth = (int) (bott.cols()/centers.rows());
        int rectwidth = swidth - 20;
        for (int l = 0; l< centers.rows();l++){
            double col1 = centers.get(l, 0)[0];
            double col2 = centers.get(l, 1)[0];
            double col3 = centers.get(l, 2)[0];
            Imgproc.putText(bott, l+1+":", new Point(10+l*swidth,40),
                    Imgproc.FONT_HERSHEY_SIMPLEX, 1.5, new Scalar(255,255,255),2);
            Imgproc.rectangle(bott, new Point(58+l*swidth,5), new Point(rectwidth+l*swidth,45), new Scalar(255,255,255));
            Imgproc.rectangle(bott, new Point(59+l*swidth,6), new Point(rectwidth-1+l*swidth,44), new Scalar(col1,col2,col3),-1);
        }
        Mat reInv2 = new Mat(new Size(reInv.cols(),reInv.rows()),CvType.CV_8UC3);
        Imgproc.cvtColor(reInv, reInv2, Imgproc.COLOR_GRAY2BGR);
//        System.out.println(reInv);
//        System.out.println(reInv2);
//        System.out.println(bott);
        reInv2.push_back(bott);
        Imgcodecs.imwrite(fileName, reInv2);

        return reInv;
    }


}