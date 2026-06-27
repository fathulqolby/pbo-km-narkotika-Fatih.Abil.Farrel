package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    // Fungsi untuk mengamankan input angka bulat (Integer)
    public int getIntInput(String pesan) {
        int angka = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(pesan);
                angka = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("[Error] Input tidak valid! Harap masukkan angka.");
                scanner.nextLine(); // Membersihkan sisa input yang salah
            }
        }
        scanner.nextLine(); // Mengkonsumsi karakter newline (Enter) agar tidak lompat
        return angka;
    }
}