package opgave02.udenThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestFlettesortering {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        Random r = new Random();
        for (int i = 0; i < 10_000_000; i++) {
            list.add(Math.abs(r.nextInt() % 10000));
        }
        System.out.println(list);
        FletteSortering sort = new FletteSortering();
        long l1, l2;
        l1 = System.nanoTime();
        sort.mergesort(list, 0, list.size() - 1);
        l2 = System.nanoTime();
        System.out.println();
        System.out.println("Koeretiden var " + (l2 - l1) / 1000000);
        System.out.println();
        //System.out.println(list);

        System.out.println("Koeretiden var " + (l2 - l1) / 1000000);
    }
}