package serverapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputMap;

import za.ac.tut.lecturethread.LectureThread;
import za.ac.tut.studentmanager.StudentManager;

public class ServerApplication {


    public static void main(String[] args) {
        String dburl = "jdbc:mysql://localhost:3306/studentdb";
        String userName = "root";
        String password = "root";

        ServerSocket getConnection;
        try {
            getConnection = new ServerSocket(8989);
        } catch (IOException ex) {
            System.err.println("Failed to establish connection/Create socket." + ex.getMessage());
            return;
        }
        //while (true) {
            Socket listeningPort;
            try {
                listeningPort = getConnection.accept();
            } catch (IOException ex) {
                System.err.println("Failed to listen to the client request.");
                //break;
                return;
            }

           // while (true) {
                //Writing to the client
//                DataOutputStream writeToClient;
//
//                try {
//                    writeToClient = new DataOutputStream(listeningPort.getOutputStream());
//
//                    //writing to the client code
//                } catch (IOException ex) {
//                    System.err.println("Failed to write to the client.");
//                    return;
//                }
//                //Reading from the client
//                DataInputStream readFromClient;
//
//                try {
//                    readFromClient = new DataInputStream(listeningPort.getInputStream());
//                    //Reading the client input
//                } catch (IOException ex) {
//                    System.err.println("Failed to read from the client.");
//                    return;
//                }
//                int option;
//                try {
//                    writeToClient.writeUTF("hey client please enter 1 to continue: ");
//                    option = Integer.parseInt(readFromClient.readUTF());
//
//                    System.out.println("option: " + option);

                    Thread t1;
//                    
                        LectureThread lecture;
                      
                        try {
                            lecture = new LectureThread(new StudentManager(dburl, userName, password),listeningPort);

                        } catch (ClassNotFoundException ex) {
                            System.err.println("Failed to get the required class");
                            return;
                        } catch (SQLException ex) {
                            System.err.println("Failed to connect to the database"+ex.getMessage());
                            return;
                        } catch (IOException ex) {
                    Logger.getLogger(ServerApplication.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
                        
                        
                        
                        t1 = new Thread(lecture, "Lecture");
                        t1.start();
                    
                //} catch (IOException ex) {
               // }
               
            //}

        }
    }

