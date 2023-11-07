package clientapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientApplication {

    public static void main(String[] args) {
        Socket connectToServer;

        try {
            connectToServer = new Socket("127.0.0.1", 8989);

        } catch (IOException ex) {
            System.err.println("Failed to connect to the server." + ex.getMessage());
            return;
        }

        DataOutputStream output;

        try {
            output = new DataOutputStream(connectToServer.getOutputStream());
        } catch (IOException ex) {
            System.err.println("Failed to create the output channel.");
            return;
        }

        DataInputStream input;
        String readedInput;
        try {
            input = new DataInputStream(connectToServer.getInputStream());
            readedInput = input.readUTF();

        } catch (IOException ex) {
            System.err.println("Failed to get the input channel.");
            return;
        }
       
        String options;
        int option;
        //if (readedInput.contains("1")) {
        //while(Integer.parseInt(option) != 6)  
        do{
        try {
            System.out.print(readedInput);
            //System.out.print("Please enter 1 to continue: ");
            options = new Scanner(System.in).next();
            System.out.println("\nYour option is: "+options);
            output.writeUTF(options);
            output.flush();
           
             option = Integer.parseInt(options);  
            
            if (option == 1) {
                String readMenu = input.readUTF();
                System.out.print(readMenu);
                String menuOption = new Scanner(System.in).next();
                output.writeUTF(menuOption);
                output.flush();
                //Getting name promt and sending input
                String nameInPut = input.readUTF();
                System.out.println(nameInPut); 
                String name = new Scanner(System.in).next();
                output.writeUTF(name);
                output.flush();
                
                //Getting formative prompt
                String formInput = input.readUTF();
                System.out.println(formInput);
                String formative = new Scanner(System.in).next();
                output.writeUTF(formative);
                output.flush();
                
                //Getting summative prompt
                String summaPrompt = input.readUTF();
                System.out.println(summaPrompt);
                String summative = new Scanner(System.in).next();
                output.writeUTF(summative);
                output.flush();
                
            }else if(option == 2){
                String studNumPrompt = input.readUTF();
                System.out.print(studNumPrompt);
                String studNum = new Scanner(System.in).next();
                output.writeUTF(studNum);
                output.flush();
                String student = input.readUTF();
                System.out.println(student);
                
            }else if(option == 3)
            {
                String studNumIn = input.readUTF();
                System.out.print(studNumIn);
                String studNum = new Scanner(System.in).next();
                output.writeUTF(studNum);
                
                String markIn = input.readUTF();
                System.out.print(markIn);
                String mark = new Scanner(System.in).next();
                output.writeUTF(mark);
                System.out.println(input.readUTF()+"\n");
                
            }else if(option == 4)
            {
            
                
            }else if(option == 5)
            {
                String listInput = "";
                listInput = listInput +  input.readUTF();
                System.out.println(listInput);
            }else if(option == 6){
                System.out.println("GoodBye");
            }
           
        } catch (IOException ex) {
            System.err.println("The server is interupted!");
            break;
        }

        }while(option != 6);  
    }

}
