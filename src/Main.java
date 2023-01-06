//libraries
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.List;
import java.io.File;

public class Main {
    public static boolean restartApp() {
        //library
        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to restart the program?: ");
        String res = sc.next();
        if (res.equals("no") || res.equals("n")) {
            return false;
        }
        return true;
    }

    public static void welcomeHeader() {
        String art = "";
        File welcomeArt = new File("welcome_art.txt");
        try {
            List<String> lines = Files.readAllLines(welcomeArt.toPath());
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.println("Err: " + ex.getMessage());
        }
        System.out.println("\nThe program will take your annual interest rate, principal (loan amount),\nand loan duration and output by line a yearly breakdown of payments\nand finally a summary of your total loan.\n");
    }


    public static void main(String[] args) {
        // initialize vars
        int term = 0; // years
        double r = 0; // int rate
        double balance = 0; // principal balance
        boolean continueApp = true;

        while (continueApp) {
            welcomeHeader();
            System.out.println("Enter loan term (years)");
            term = sc.nextInt();
            if (!restartApp()) {
                continueApp = false;
            }
        }
        System.out.println("Thank you for using the amortization schedule app");
    }
}