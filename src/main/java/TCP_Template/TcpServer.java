package TCP_Template;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(1234); //ServerSocket gør at vi lyter på 1234.

            while (true){
                socket = serverSocket.accept();//serSocker.accpept betyder at vi venter på at nogle kommer ind på porten
                System.out.println("New client joined - ip: " + socket.getInetAddress());

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true){
                    String msgFromClient = bufferedReader.readLine();

                    System.out.println("Client: " + msgFromClient);

                    bufferedWriter.write("MSG Received.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (msgFromClient.equalsIgnoreCase("BYE")){
                        break;
                    }
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                System.out.println("Client exit");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
