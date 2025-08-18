package DIS01.opgave01;

import java.util.Scanner;

public class ReadThread extends Thread{


    @Override
    public void run() {
        Scanner input = new Scanner(System.in);

        while(true){
            MyStringClass.setString(input.next());
        }
    }
}
