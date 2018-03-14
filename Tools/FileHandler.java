package Tools;

import java.io.File;
import java.io.IOException;


// This will be to create a new folder, to save all of the pictures created from
// recording the screen and then will use them to load up into a linkedlist
// and build the gif with them
public class FileHandler {

    private String directory;

    // here we know this is just temporary, but you can work more on it later
    public File createFolder(String folderName) throws IOException{
        File tempdir = new File(folderName);
        tempdir.mkdir();
        return tempdir;
    }

    // This is for creating the temporary directory to load the files before
    // creating the gif, keeping data in the heap will break the program
    public void deleteFolder(String folderName) throws IOException{
        File dir  = new File(folderName);

        // no point since it will be the created directory
        if (!dir.isDirectory()){
            System.out.println("Folder is not a directory");
        }

        File[] listFiles = dir.listFiles();
        for(File file:listFiles){
            System.out.println("Deleting: " + file.getName());
            file.delete();
        }
        System.out.println("Deleting directory. Success = " + dir.delete());
    }



}
