package imageProcessing.faceDetection;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetection {

    public FaceDetection() {
    }

    public void faceDetector(String imageInputPath, String imageOutPath) {
        try {
            loadLibrary();
            CascadeClassifier detector = new CascadeClassifier();
            System.out.println("face detecting started...");
            final String fullFace = "resources/haarcascades/haarcascade_frontalface_alt.xml";
            detector.load(fullFace);
            Mat image = Imgcodecs.imread(imageInputPath);
            MatOfRect faceDetector = new MatOfRect();
            detector.detectMultiScale(image, faceDetector);
            for (Rect rect : faceDetector.toArray()) {
                Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 5);
            }
            Imgcodecs.imwrite(imageOutPath, image);
            System.out.println("face detecting ended...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
