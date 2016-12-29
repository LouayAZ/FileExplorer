package MyClasses;
import java.io.*;
import java.util.*;
/**
 * Created by Louay on 12/7/2016.
 */
public class Drive {
    private String driveName;
    private double totalSpace;
    private double usedSpace;
    private List<Files> fileList;
    private List<Folder> folderList;
    private File drive;

    public Drive(File f){
      //  if(f.exists()) {
            //driveName = f.getName();
            fileList = new LinkedList<Files>();
            folderList = new LinkedList<Folder>();
            File[] tmp = f.listFiles();


            for (File i : f.listFiles()) {

                if (i.isFile())
                    fileList.add(new Files(i));
                else if(!i.isHidden())
                    folderList.add(new Folder(new File(i.getPath())));

            }

            totalSpace = f.getTotalSpace()/(1024*1024);
            usedSpace = f.getUsableSpace()/(1024*1024);
            drive = f;
       // }
        //else
          //  System.out.println("not found");
    }

    public String getPath(){
        return drive.getPath();
    }


    public List<Folder> getFolderList(){return  folderList;}
    public List<Files> getFileList(){return fileList;}
    public File getDrive(){return drive;}
    public String getName(){
        return driveName;
    }
    public void printData(){
        System.out.println("\n" + drive.getPath() + " : Total Space : " + totalSpace + " Used Space : " + usedSpace +" , contains : ");
        if(fileList.size() > 0 || folderList.size() > 0 ) {

            for (Folder i : folderList) {
                System.out.println("\t" + i.getName() + " " + i.getSize());
                // i.printData();
            }

            for (Files i : fileList) { System.out.print("\t"); i.printData(); }
        }
        else
            System.out.println("Drive " + driveName + " is empty");
    }

    public void addFile(String fileName , String txt ){
        Files newFile = new Files(new File(drive.getPath() + "\\" + fileName));
        newFile.writeToFile(txt);
        fileList.add(newFile);
    }

    public void  addFolder(String name){
        folderList.add(new Folder(new File(drive.getPath()+"\\" + name)));
    }


}
