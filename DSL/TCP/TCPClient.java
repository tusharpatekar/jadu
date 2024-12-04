import java.io.*;  
import java.net.*;  

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5000);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("Connected to server. Type 'exit' to quit.");

        String message;
        while (!(message = input.readLine()).equals("exit")) {
            out.println(message);
            System.out.println("Server: " + in.readLine());
        }

        socket.close();
    }
}

/*
   // 1st Terminal:
   javac TCPServer.java TCPClient.java 
   java TCPServer
   Server started, waiting for client...
   Client connected
   Client: Hi

   // 2nd Terminal:
   java TCPClient
   Connected to server. Type 'exit' to quit.
   Hi
   Server: Echo: Hi
*/
