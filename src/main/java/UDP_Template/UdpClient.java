package UDP_Template;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpClient {
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer;

    public UdpClient(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        UdpClient udpClient = new UdpClient(datagramSocket, inetAddress);
        udpClient.sendThenReceive();
    }

    public void sendThenReceive(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            try {
                String messageToSend = scanner.nextLine();
                buffer = messageToSend.getBytes(StandardCharsets.UTF_8);
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                datagramSocket.receive(datagramPacket);
                String messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("Server echo: " + messageFromServer);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
