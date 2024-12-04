import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type your message (type 'exit' to quit):");
        while (true) {
            String message = scanner.nextLine();
            if ("exit".equalsIgnoreCase(message)) break;

            DatagramPacket request = new DatagramPacket(
                message.getBytes(),
                message.length(),
                serverAddress,
                5000
            );
            socket.send(request);

            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            String serverResponse = new String(response.getData(), 0, response.getLength());
            System.out.println("Server: " + serverResponse);
        }
        socket.close();
    }
}


/*
   // 1st Terminal:
   javac UDPClient.java UDPServer.java
   java UDPServer
   Server is running...
   Client: hi

   // 2nd Terminal:
   java UDPClient
   Type your message (type 'exit' to quit):
   hi
   Server: Received: hi
*/
