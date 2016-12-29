package com.company;

import java.util.*;
import  java.io.*;

import MyClasses.Drive;
import MyClasses.Files;
import MyClasses.Folder;



public class Main {

    public static void main(String[] args) {
     
        Scanner in = new Scanner(System.in);
        Folder folder = null;
        Files fl;
        Drive dr;
        File[] drives = File.listRoots();
        System.out.println("Drivers:\n________");
        for(File i : drives)
            System.out.println("\t"+i.getPath() + " Total Space: " + i.getTotalSpace()/(1024*1024) + "MB , UsedSpace : "  + i.getUsableSpace()/(1024*1024) + "MB" );
        String choice;
        System.out.print("Choose a drive : ");
        choice = in.next();
        dr = new Drive(new File(choice));
        dr.printData();
        boolean ok = true;
        int c;
        System.out.print("Choose a Folder Name: ");
        while(ok) {
            choice = in.next();
            File tmp = new File(dr.getPath() + choice);
            System.out.print(tmp.getPath());
            if (tmp.isDirectory()) {
                folder = new Folder(tmp);
                folder.printData();
                ok = false;
            } else {
                System.out.print("Choose a  Folder please: ");

            }
        }
        ok = true;
while(ok) {
    folder.printData();
    System.out.println("\n1 - enter to a folder or file");
    System.out.println("2 - sort by name");
    System.out.println("3 - sort by size");
    System.out.println("4 - search");
    System.out.println("5 - remove");
    System.out.println("6 - add folder");
    System.out.println("7 - add file");
    System.out.println("8 - exit\nyour choice:");

    c = in.nextInt();

    switch (c) {
        case 1:
            System.out.print("Choose a File or Folder Name: ");
            choice = in.next();

            File tmp = new File(dr.getPath() + choice);
            System.out.print(tmp.getPath());
            if (tmp.isDirectory()) {
                folder = new Folder(tmp);
                folder.printData();
            } else {
                fl = new Files(tmp);
                System.out.println("Do you want to read this file or write to it ? (r/w)");
                choice = in.next();
                if (choice.equals("w") ) {
                    System.out.print("Enter your text :");
                    choice = in.next();
                    fl.writeToFile(choice);
                    System.out.print("Done Writting !");
                } else {
                    System.out.print("The file contains");
                    fl.readFile();

                }
                folder.printData();
            }
            break;

        case 2:
            folder.sortByName();
            folder.printData();
            break;
        case 3:
            folder.sortBySize();
            folder.printData();
            break;
        case 4:
            System.out.print("enter the name of folder or file you want to search for :");
            choice = in.next();
            System.out.print("the file is in :" + folder.search(choice).getPath());
            break;
        case 5:
            System.out.print("enter the name of folder or file you want to remove");
            choice = in.next();
            folder.remove(choice);
            break;
        case 6:
            System.out.print("enter the folder name : ");
            choice = in.next();
            folder.addFolder(choice);
            break;
        case 7:
            System.out.print("enter the file name : ");
            choice = in.next();
            System.out.print("enter the text to put in the file: ");
            String txt = in.next();
            folder.addFile(choice , txt );
            break;
        case 8 :
            ok = false;
    }
}





    }
}
