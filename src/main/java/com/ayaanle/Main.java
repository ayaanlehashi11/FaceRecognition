import org.opencv.core.*;

// Importing date class of sql package
import javax.swing.*;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Importing date class of sql package
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// Class - Swing Class
public class Main extends JFrame {
    // Camera screen
    private JLabel cameraScreen;

    // Button for image capture
    private JButton btnCapture;

    // Start camera
    public VideoCapture capture;

    // Store image as 2D matrix
    public Mat image;

    private boolean clicked = false;
    public Main()
    {

        // Designing UI
        setLayout(null);

        cameraScreen = new JLabel();
        cameraScreen.setBounds(0, 0, 640, 480);
        add(cameraScreen);

        btnCapture = new JButton("capture");
        btnCapture.setBounds(300, 480, 80, 40);
        add(btnCapture);

        btnCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                clicked = true;
            }
        });

        setSize(new Dimension(640, 560));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    // Main driver method

    public void startCamera()
    {
        capture = new VideoCapture(0);
        image = new Mat();
        byte[] imageData;
        ImageIcon icon;
        while (true) {
            // read image to matrix
            capture.read(image);
            if (image == null) {
                System.out.println("Sawirka lama ama waa madow ... ");
            }
            CascadeClassifier cc = new CascadeClassifier();
            cc.load("C:\\Users\\pc\\IdeaProjects\\CVApp\\data\\haarcascade_frontalface_default.xml");
            //Mat dest = new Mat();
            //Imgproc.cvtColor(source, dest, Imgproc.COLOR_RGB2GRAY);
            if (capture.read(image)) {
                MatOfRect mor = new MatOfRect();
                cc.detectMultiScale(image, mor);
                System.out.println(String.format("Detected %s faces",
                        mor.toArray().length));
                //Rect x ,  y , w , h;
                Rect[] face = mor.toArray();
                for (Rect rect : face) {
                    Imgproc.rectangle(
                            image, new Point(rect.x, rect.y),
                            new Point(rect.x + rect.width,
                                    rect.y + rect.height),
                            new Scalar(0, 0, 255));
                }
                final MatOfByte buf = new MatOfByte();
                Imgcodecs.imencode(".jpg", image, buf);
                HighGui.imshow("Ayaanle" , image);
                imageData = buf.toArray();
                icon = new ImageIcon(imageData);
                cameraScreen.setIcon(icon);
                HighGui.waitKey(0);
            }
            // convert matrix to byte

        }
    }

    public static void main(String[] args)
    {
        // Designing UI
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        EventQueue.invokeLater(new Runnable() {
            // Overriding existing run() method
            @Override public void run()
            {
                final Main camera = new Main();

                // Start camera in thread
                new Thread(new Runnable() {
                    @Override public void run()
                    {
                        camera.startCamera();
                    }
                }).start();
            }
        });
            // convert matrix to byte
    }

        //HighGui.imshow("Ayaanle", source);

        // Overriding existing run() method


}
