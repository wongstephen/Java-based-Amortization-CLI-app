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
        double rate = 0; // int rate
        double balance = 0; // principal balance
        boolean continueApp = true;
        //library
        Scanner sc = new Scanner(System.in);

        while (continueApp) {
            welcomeHeader();
            System.out.println("Enter loan length in years (term): ");
            Calcs loan = new Calcs(term, rate, balance);

            while (term <= 0){
                double input = sc.nextDouble();
                if(input%1 == 0){ // test for if the user enters a whole and non-negative int
                    term = (int) input;
                    loan.setTerm(term);
                } else {
                    System.out.println("Whole integers are required. Please enter number again: ");
                }
            }

            System.out.println("Enter loan amount (principal): ");
            while (balance <= 0 ){
                double input = sc.nextDouble();
                if(input > 0){ // test for if the user enters a non-negative double
                    balance = input;
                    loan.setBalance(input);
                } else {
                    System.out.println("Non-negative and non-zero numbers are required. Please enter number again: ");
                }
            }

            System.out.println("Enter interest rate (percentage): ");
            while (rate <= 0){
                double input = sc.nextDouble();
                if(input > 0){ // test for if the user enters a non-negative double
                    rate = input;
                    loan.setRate(input);
                } else {
                    System.out.println("Non-negative and non-zero numbers are required. Please enter number again: ");
                }
            }


            System.out.println("\n" + loan.toString() + "\n");
            System.out.println(loan.getSched());
            System.out.println(loan.getSummary());
            System.out.println(loan.getPayment());
            // End of app input from user to restart.
            if (!restartApp()) {
                continueApp = false;
            }
        }
        System.out.println("Thank you for using the amortization schedule app");
    }
}