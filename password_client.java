import java.io.*;
import java.net.*;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.*;
public class password_client {

/**
 * @author Ibrahim Noor Mohammed with Dr. Qusay Mahmoud's starter code
 * 10/3/2020
 */



//Quick note: I recycled code used from my lab submission
   public static void main(String argv[]) throws Exception {
	 int x;
	 int y;
	 int a,b;
     Socket echo;
     DataInputStream br;
     DataOutputStream dos;
     String userInput = null;
     byte[] userPass;
     
     Scanner scan = new Scanner(System.in);
    
     echo = new Socket("localhost", 3500);
     br = new DataInputStream(echo.getInputStream());
     dos = new DataOutputStream(echo.getOutputStream());
     
     System.out.println("For the quick password generator feature, please enter 1. For the password encrypter, press 2.");
     a = scan.nextInt();
     dos.writeInt(a);
     dos.flush();
     scan.nextLine();
     //if statement to allow decision between the two features
     if(a == 2) {
    	System.out.println("Please insert password to be encrypted:");
    	 while((userInput = scan.nextLine()) != null) { 
           userPass = userInput.getBytes("UTF8");
           dos.writeUTF(userInput);
           dos.flush();
           String str = br.readLine();
           System.out.println("Your encrypted password is:" + str);
           break;
    	 }
    	 
     }else if (a == 1) {
    	 System.out.println("Enter password type(1(weak), 2(medium), or 3(strong): ");
    	 x = scan.nextInt();
    	 System.out.println("What length?: ");
    	 y = scan.nextInt();
    	 dos.writeInt(x);
    	 dos.flush();
    	 dos.writeInt(y);
    	 dos.flush();
    	 String str = br.readLine();
    	 System.out.println("Your password is:" + str);
    	 FileOutputStream fos = new FileOutputStream("list.txt");
    	 DataOutputStream out = new DataOutputStream(new BufferedOutputStream(fos));
    	 out.writeChars(str);
    	 out.close();
     }
   }
}

