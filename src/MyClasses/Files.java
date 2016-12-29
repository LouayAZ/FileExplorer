package MyClasses;
/**
 * Created by Louay on 12/6/2016.
 */

import java.util.*;
import java.io.*;

public class Files  implements Comparable<Files>{
    private File f;
    private String fileName;
    private double size;

    public Files(File f ){

        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.f = f;
        fileName = f.getName();
        size = f.length();
    }

    public double getsize(){
        return size;
    }
    public String getName(){
        return fileName;
    }
    public void printData(){
        System.out.println(fileName + ":\t" +  size);
    }
    public String getPath(){return f.getPath();}
    public File getFile(){
        return f;
    }
    public void writeToFile(String txt){
        BufferedWriter bfw = null;

        try {
            bfw = new BufferedWriter(new FileWriter(f));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bfw.write(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        size = f.length();
    }

    public void readFile(){
        BufferedReader bfr = null;
        try {
            bfr = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String tmp;
        try {
            while( (tmp = bfr.readLine()) != null )
                System.out.println(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Comparator<Files> FileCompareByName = new Comparator<Files>() {
        @Override
        public int compare(Files o1, Files o2) {
            return o1.fileName.compareTo(o2.fileName);
        }
    }  ;

    @Override
    public int compareTo(Files o) {
        return (int)(this.size - o.getsize());
    }
}
