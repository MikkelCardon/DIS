package opgave01;

public class PrintThread extends Thread{



    public PrintThread() {

    }

    @Override
    public void run() {

        while(true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(MyStringClass.getString());
        }

    }
}
