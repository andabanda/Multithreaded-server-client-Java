import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.*;
public class password_server {

/**
 * @author Ibrahim Noor Mohammed with Dr. Qusay Mahmoud's starter code
 * 10/3/2020
 */
   ServerSocket hi;
   Socket client;
   DataInputStream br;
   DataOutputStream dos;
//My three methods of varying password generating difficulty
   public static String weak(int n) {
	   String password = "abcedfghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   StringBuilder sb = new StringBuilder(n);
	   for (int i = 0; i < n; i++) {
		   int index = (int)(password.length() * Math.random());
		   sb.append(password.charAt(index));
	   }
	   return sb.toString(); 
   }
   
   public static String medium(int n) {
	   String password = "abcedfghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ + 1234567890";
	   StringBuilder sb = new StringBuilder(n);
	   for (int i = 0; i < n; i++) {
		   int index = (int)(password.length() * Math.random());
		   sb.append(password.charAt(index));
	   }
	   return sb.toString(); 
   }
   
   public static String strong(int n) {
	   String password = "abcedfghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ + 1234567890 +  @#&!()^=+/:.;,$%*_-<>?{}[]";
	   StringBuilder sb = new StringBuilder(n);
	   for (int i = 0; i < n; i++) {
		   int index = (int)(password.length() * Math.random());
		   sb.append(password.charAt(index));
	   }
	   return sb.toString();
   }
   
   public static void main(String argv[]) throws Exception {
     new MathServer();
   }

   public password_server() throws Exception {
     hi = new ServerSocket(3500);
     System.out.println("Server Listening on port 3500....");
     try {
    	 while(true) {
    		 client = hi.accept();
    		 System.out.println("New Client connected");
    		 ClientHandler clientSock = new ClientHandler(client);
    		 new Thread(clientSock).start();
    	 }
    	}
      catch (Exception e){
    	 e.printStackTrace();
     } finally {
    	 if (hi != null) {
    		 try {
    			 hi.close();
    		 } catch (IOException e) {
    			 e.printStackTrace();
    		 }
    	 }
     }
   }
   
 class Connection extends Thread{
	   Socket clientSocket;
	   DataInputStream br;
	   DataOutputStream dos;
	   
	   public Connection(Socket sock){
		   clientSocket = sock;
		   try {
			   br = new DataInputStream(client.getInputStream());
			   dos = new DataOutputStream(client.getOutputStream());
		   }
		   catch (IOException e) {
			   try {
				   client.close();
			   }
			   catch(IOException ex) {
				   System.out.println("could not get socket stream"+ex);
			   }
			   return;
		   }
		   this.start();
	   }
   }
 
 public class ClientHandler implements Runnable{
	 private final Socket clientSocket;
	 public ClientHandler(Socket socket) {
		 this.clientSocket = socket;
	 }
	 
	@Override
   	public void run() {
	  DataInputStream br = null;
	  DataOutputStream dos = null;
	  try {
	  br = new DataInputStream(client.getInputStream());
	  dos = new DataOutputStream(client.getOutputStream());
	  int a = br.readInt();
	  System.out.println("I recieved option:" + a);
	  //if loop that receives input and calls password generator method
	  if (a == 1) {
		int x = br.readInt();
		   System.out.println("I got type: "+x);
		   int y = br.readInt();
		   System.out.println("I got length: "+y);
		   int n = y;
		   
	  if(x == 1) {   
	   dos.writeBytes(MathServer.weak(n));
		}
		else if (x == 2){
			dos.writeBytes(MathServer.medium(n));
		}
		
		else if (x ==3) {
			dos.writeBytes(MathServer.strong(n));
			
		}
	}
	else if(a == 2){
		//I made checks to see if the connection was working between server and client
		System.out.println("WAITING FOR UTF");
		String passwordHash = br.readUTF();
		System.out.println("UTF RECEIEVED");
		String userPass = passwordHash;
		System.out.println("USERPASS: " + userPass);
		String generatedPassword = null;
		
		//MD5 encryption is within this try loop
		try {
			MessageDigest msg = MessageDigest.getInstance("MD5");
			msg.update(userPass.getBytes());
			byte[] bytes = msg.digest();
			
			StringBuilder build = new StringBuilder();
			for(int i=0;i<bytes.length; i++) {
				build.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = build.toString();
			dos.writeBytes(generatedPassword);
			clientSocket.close();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	} 
   } catch (IOException e) {
      e.printStackTrace();
   }
	   finally {
		   try {
			   if (br != null) {
				   dos.close();
			   }
			   if (br != null) 
				   br.close();
				   clientSocket.close();
			   } catch (IOException e) {
				   e.printStackTrace();
			   }
		   }
	   }
 }
 }



