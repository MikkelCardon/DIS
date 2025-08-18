package DIS01.opgave02;

import DIS01.opgave02.udenThread.FletteSortering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestFlettesortering {

    private static final int NUMBER_OF_INTS = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<Integer>();
        Random r = new Random();
        for (int i = 0; i < NUMBER_OF_INTS; i++) {
            list.add(Math.abs(r.nextInt() % 10000));
        }
        //System.out.println(list);


        DIS01.opgave02.FletteSortering tråd1 = new DIS01.opgave02.FletteSortering(list, 0, list.size()/2);
        DIS01.opgave02.FletteSortering tråd2 = new DIS01.opgave02.FletteSortering(list, (list.size()/2)+1, list.size()-1);


        long l1, l2;
        l1 = System.nanoTime();


        tråd1.start();
        tråd2.start();

        tråd1.join();
        tråd2.join();

        FletteSortering.merge(list,0, list.size() / 2, list.size()-1);
        System.out.println(list.size());

        l2 = System.nanoTime();
        System.out.println();
        System.out.println("Koeretiden var " + (l2 - l1) / 1000000);
        System.out.println();
        //System.out.println(list);

        System.out.println("Koeretiden var " + (l2 - l1) / 1000000);
    }

}