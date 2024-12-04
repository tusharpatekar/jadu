import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5000);
        System.out.println("Server is running...");
        byte[] buffer = new byte[1024];

        while (true) {
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);

            String clientMessage = new String(request.getData(), 0, request.getLength());
            System.out.println("Client: " + clientMessage);

            String response = "Received: " + clientMessage;
            DatagramPacket reply = new DatagramPacket(
                response.getBytes(),
                response.length(),
                request.getAddress(),
                request.getPort()
            );
            socket.send(reply);
        }
    }
}
