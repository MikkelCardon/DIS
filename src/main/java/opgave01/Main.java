package opgave01;

public class Main {
    public static void main(String[] args) {
        ReadThread readThread = new ReadThread();
        PrintThread printThread = new PrintThread();

        readThread.start();
        printThread.start();


    }
}
