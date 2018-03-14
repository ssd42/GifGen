package Tools;
import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;
import javax.swing.*;
import java.awt.*;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.swing.*;

import java.lang.RuntimeException;

public class Recorder extends JFrame{
/*
This needs to be remade, right now im creating all
    of the pictures and saving them in the heap, after a shot amount of time
    the space in the heap runs out and the progam crashes, better to
    just create a temporary folder, load a bunch of pictures into it,
    create the gif from the pictures and then delete the folder.
*/

    private static int timeframe =  400;
    private static boolean alwaysloop = true;

    private static final long seralVersionUID = 1L;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
    private static int counter = 0;


    // temp for creation and creation only
    public void Recordit(String directoryName, int framerate, int timeTorecord){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);
        String fileName;
        BufferedImage screenFullImage;

        // create a picture and save it to the temporary directory

        for(int i = 0; i<timeTorecord; i+=framerate) {
            try {
                Robot robot = new Robot();
                fileName = directoryName + String.format("screenpic%d.jpeg", ++counter);
                screenFullImage = robot.createScreenCapture(screenRect);
                ImageIO.write(screenFullImage, "jpg", new File(fileName));
                Thread.sleep(framerate);

            } catch (IOException | InterruptedException | AWTException err) { //IOException |
                System.err.println(err);
            }
        }
    }

    // loading it directly messes it up since I use up all of the heap space
    // now recording each to a folder
    public void Record(int framerate, int timerecorded){
        LinkedList<BufferedImage> theGif = new LinkedList<>();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);

        String fileName;
        Recorder r;
        BufferedImage screenFullImage;

        for(int i = 0; i<timerecorded; i+=framerate) {
            try {
                Robot robot = new Robot();
//                r = new Recorder();
                fileName = String.format("C://Users//Steven//Desktop//JavaGif//screenpic%d.jpeg", ++counter);
                screenFullImage = robot.createScreenCapture(screenRect);
                ImageIO.write(screenFullImage, "jpg", new File(fileName));
                theGif.add(screenFullImage);
                Thread.sleep(framerate);

            } catch (IOException | InterruptedException | AWTException err) { //IOException |
                System.err.println(err);
            }
        }

        createGif(theGif, "C://Users//Steven//Desktop//JavaGif//MyGif.gif", 150);
    }

    // create a gif based on a linked list
    private static void createGif(LinkedList<BufferedImage> picList, String fileName, int delay){
        AnimatedGifEncoder e = new AnimatedGifEncoder();

        e.start(fileName);
        e.setDelay(delay);

        for(int i=0; i<picList.size();i++){
            e.addFrame(picList.get(i));
        }
        e.finish();
    }

    private static void createGif(String saveDirectoy, String fileName, int delay){
        File theDir;
        // load up the gif library
        AnimatedGifEncoder e = new AnimatedGifEncoder();
        e.start(fileName);
        e.setDelay(delay);
        e.setQuality(5); // will significantly slow down, 1 is the max, 10 isdeafult, over 20 cant notice difference

        // load the pictures from the files
        theDir = new File(saveDirectoy);

        //if invalid
        if (!theDir.isDirectory()){
            System.out.println("Invalid file path");
            throw new RuntimeException("Unable to find ");
        }

        BufferedImage frame;
        File[] listFiles = theDir.listFiles();
        for(File pic:listFiles) {
            try {
                frame = ImageIO.read(pic);
                e.addFrame(frame);
            }
            catch (IOException io){
                System.out.print("Unable to load in file");
            }
        }
        e.finish();



//        for(int i=0; i<picList.size();i++){
//            e.addFrame(picList.get(i));
//        }
//        e.finish();
    }

    private void captureScreenshot(String directory){
        System.out.println(sdf);
        Recorder r = new Recorder();
        String defaultName = String.format("screnpic-%s.jbeg", sdf);

        // need to specify it is a dimension object otherwise it throws an error
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);

        try {
//            Thread.sleep(2000);
            Robot robot = new Robot();
            String fileName = directory + defaultName;
//            String fileName = "C://Users//Steven//Desktop//JavaGif//screenpic.jpeg";


            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, "jpg", new File(fileName));

            r.setLocation(500,500);
            JLabel text = new JLabel("A full screenshot saved!");
            r.add(text);
            r.setSize(200,100);
            r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            r.getContentPane().setLayout(new FlowLayout());
            r.setVisible(true);
        }
        catch (AWTException | IOException ex){
            System.err.println(ex);
        }
    }

    //default
    public void takeScreeshot(){
        takeScreenshot(0);
    }

    public void takeScreenshot(int delay){
        try{
            Thread.sleep(delay);
            captureScreenshot("C://Users//Steven//Desktop//JavaGif//");

        }catch (InterruptedException err){
            System.err.println(err);
        }
    }
}
