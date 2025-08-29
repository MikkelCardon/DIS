package UDP_Template;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer {
    private DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];

    public UdpServer(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(1234);
        UdpServer server = new UdpServer(datagramSocket);
        server.receiveThenSend();
    }

    public void receiveThenSend(){
        while (true){
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket); //gør at vi venter på at modtage et datagram fra klienten. Det datagram vi modtager bliver så sat ind i vores Datagrampacket var

                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();

                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("Message from client: " + messageFromClient);

                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                datagramSocket.send(datagramPacket);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
