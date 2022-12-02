package main;

import account.Account;

import java.util.Scanner;

public class DemoRegex {
    public static void main(String[] args) {
        String rex = "^[!@#$%^&*][A-Za-z0-9]{6,10}", input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            input = scanner.nextLine();
            System.out.println(input.matches(rex));
        }

    }
}
