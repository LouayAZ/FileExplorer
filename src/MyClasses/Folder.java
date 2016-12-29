package MyClasses;

import java.io.*;
import java.util.*;


/**
 * Created by Louay on 12/6/2016.
 */

public class Folder implements Comparable<Folder>{

   private String folderName;
   private double size;
   private List<Files> fileList;
   private List<Folder> folderList;
   private File folder;
   private String parent;

    public Folder(File f){
        if(!f.exists())
            f.mkdir();
        folderName = f.getName();
        fileList = new LinkedList<Files>();
        folderList = new LinkedList<Folder>();
        File[] tmp = f.listFiles();
        parent  = f.getParent();
        try{
            for(File i : tmp )
            {
                if(i.isDirectory())
                    folderList.add(new Folder(i));
                else
                    fileList.add(new Files(i));

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        folder = f;
    }

  private void refresh(){
       File[] tmp = folder.listFiles();
       folderList.clear();
       fileList.clear();
       for(int i = 0 ; i < tmp.length ; ++i)
       {
           if(tmp[i].isDirectory())
               folderList.add(new Folder(tmp[i]));
           else
               fileList.add(new Files(tmp[i]));
       }
   }

   public List<Folder> getFolderList(){return  folderList;}
   public List<Files> getFileList(){return fileList;}
   public File getFolder(){return folder;}
   public String getParent(){
       return parent;
   }
   public String getName(){
       return folderName;
   }
   public void printData(){

       if(fileList.size() > 0 || folderList.size() > 0 ) {
           System.out.println("\n" + folderName + " contains : ");
           for (Folder i : folderList) {
               System.out.println(i.getName() + " ");
               // i.printData();
           }

           for (Files i : fileList){ System.out.print("\t");  i.printData();}
       }
       else
           System.out.println("folder " + folderName + " is empty");
   }

   public void addFile(String fileName , String txt ){
       Files newFile = new Files(new File(folder.getPath() + "\\" + fileName));
       newFile.writeToFile(txt);
       fileList.add(newFile);
   }

   public int getSize(){
       return size(this.folder);
   }

   private int size(File file ){
       int res = 0;
       for(File i : file.listFiles()){
           if(i.isDirectory()){
               res = size(i);
           }
           else{
               res += i.length();
           }
       }
       return res;
   }

    public String getPath(){return folder.getPath();}
    public static Comparator<Folder> folCompareByName = new Comparator<Folder>() {
        @Override
        public int compare(Folder o1, Folder o2) {
            return (o1.folderName.compareTo(o2.folderName));
        }
    };

    @Override
    public int compareTo(Folder o) {
        return (int)(this.getSize() - o.getSize());
    }

    public void sortByName(){
        Collections.sort(folderList , Folder.folCompareByName);
        Collections.sort(fileList , Files.FileCompareByName);
    }

    public void sortBySize(){
        Collections.sort(folderList);
        Collections.sort(fileList);
    }



    private String StringFound;
    private String find(File f , String name){

        for(File i : f.listFiles())
        {
            if(name.equals(i.getName())) {
                return StringFound = i.getPath();
            }
            else if(i.isDirectory())
                find(i , name);
        }
        return StringFound;
    }

    public File search(String name){
        File foundFile = null;
        StringFound = null;
        String s = find(this.getFolder() , name);
        System.out.print(s);
        if(s!=null){
            foundFile = new File(s);

        }

        else
            System.out.println("file/folder not found");
        return foundFile;
    }



    private void deletess(File del , String name){

        if(del != null) {
            if(del.isFile())
            {
                del.delete();

            }
            else{
                if(del.listFiles().length != 0 ){
                    for(File i : del.listFiles())
                        deletess(i , name);
                }
                del.delete();
            }
        }

    }

    public void remove( String name){
        File del = search(name);
        if(del != null) {
            deletess(del, name);
        }
        refresh();
    }

    public void  addFolder(String name){
        folderList.add(new Folder(new File(getPath()+"\\" + name)));
    }






}
