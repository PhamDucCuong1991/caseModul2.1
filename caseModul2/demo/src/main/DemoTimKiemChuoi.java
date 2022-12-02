package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DemoTimKiemChuoi {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String input, spCanTim;
        Scanner scanner = new Scanner(System.in);
        //nhap list sp
        for (int i = 0; i<5; i++) {
            input = scanner.nextLine();
            list.add(input);
        }

        List<String> ketQuaTK = new ArrayList<>();
        while (true) {
            //nhap sp can tim
            System.out.println("Nhap sp can tim: ");
            spCanTim = scanner.nextLine();
            // tim kiem
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(spCanTim)) {
                    System.out.println(list.get(i));
                }
            }
        }
        //
    }
}
