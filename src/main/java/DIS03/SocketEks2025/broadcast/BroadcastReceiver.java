package DIS03.SocketEks2025.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class BroadcastReceiver {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(9995)) { // bind to port 9995
            socket.setBroadcast(true);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Listening for broadcasts on port 9995...");

            while (true) {
                socket.receive(packet); // blocking call
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + message +
                        " from " + packet.getAddress().getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
