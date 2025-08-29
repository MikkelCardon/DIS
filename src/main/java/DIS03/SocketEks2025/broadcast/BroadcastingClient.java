package DIS03.SocketEks2025.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastingClient {

	public static void main(String[] args) {
		try {
			sendBroadcast("Hello", 9995);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendBroadcast(String message, int port) throws IOException {
		try (DatagramSocket socket = new DatagramSocket()) {
			socket.setBroadcast(true);

			byte[] buffer = message.getBytes();
			InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");

			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcastAddress, port);
			int i = 0;
			while(i < 3){
				socket.send(packet);
				System.out.println("Broadcast message sent: " + message + " on port " + port);
				socket.setSoTimeout(3000);
				i++;
			}
        }
	}
}
