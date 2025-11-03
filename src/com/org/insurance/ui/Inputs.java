package com.org.insurance.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class Inputs {
    private Inputs() {}

    public static String nextLine(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public static int nextInt(Scanner sc, String prompt, Integer min, Integer max) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = sc.nextInt();
                sc.nextLine();
                if (min != null && v < min) { System.out.println("Min: " + min); continue; }
                if (max != null && v > max) { System.out.println("Max: " + max); continue; }
                return v;
            } catch (InputMismatchException e) {
                sc.nextLine(); // чистимо буфер
                System.out.println("Введіть ціле число.");
            }
        }
    }

    public static double nextDouble(Scanner sc, String prompt, Double min, Double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double v = sc.nextDouble();
                sc.nextLine();
                if (min != null && v < min) { System.out.println("Min: " + min); continue; }
                if (max != null && v > max) { System.out.println("Max: " + max); continue; }
                return v;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Введіть число.");
            }
        }
    }

    public static Double nextDoubleOrSkip(Scanner sc, String prompt) {
        double v = nextDouble(sc, prompt + " (-1 = пропустити): ", null, null);
        return v < 0 ? null : Double.valueOf(v);
    }

    public static boolean nextBoolean(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt + " (true/false): ");
            String s = sc.next();
            sc.nextLine(); // з'їдаємо \n
            if ("true".equalsIgnoreCase(s) || "t".equalsIgnoreCase(s) || "yes".equalsIgnoreCase(s) || "y".equalsIgnoreCase(s)) return true;
            if ("false".equalsIgnoreCase(s) || "f".equalsIgnoreCase(s) || "no".equalsIgnoreCase(s) || "n".equalsIgnoreCase(s)) return false;
            System.out.println("Введіть true або false.");
        }
    }
}
