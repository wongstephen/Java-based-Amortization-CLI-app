import java.text.DecimalFormat;

public class Calcs {
    private int term;
    private double rate;
    private double balance;

    public Calcs(int term, double rate, double balance) {
        this.term = term;
        this.rate = rate;
        this.balance = balance;
    }

    public void setTerm(int term){
        this.term = term;
    }

    public void setRate(double rate){
        this.rate = rate;
    }

    public void setBalance(double term){
        this.balance = term;
    }
    public String getSched(){
        DecimalFormat df = new DecimalFormat("###,###,###,###.00");
        double payment = this.getPayment();
        double startingBalance = this.balance;
        double interestPayment = 0;
        double principalPayment = 0;
        double endingBal = 0;

        String result = "";
        result += "=============================================================\n";
        result += this.term + " Years Amortization Schedule\n";
        result += "=============================================================\n\n";
        result += "Mo | Starting Bal | Interest | Principal | Ending Balance \n\n";
        for (int i = 1; i <= this.term*12; i++) { // todo change this later to actual months
            startingBalance -= principalPayment;
            interestPayment = this.rate/100/12*startingBalance;
            principalPayment = payment-interestPayment;
            endingBal = startingBalance - principalPayment;
            if (endingBal < 0){
                break;
            }
            result += String.format("%02d", i) + " | $"
                    + df.format(Math.round(startingBalance*Math.pow(10,2))/Math.pow(10,2)) + " | $"
                    + df.format(Math.round(interestPayment*Math.pow(10,2))/Math.pow(10,2)) + " | $"
                    + df.format(Math.round(principalPayment*Math.pow(10,2))/Math.pow(10,2)) + " | $"
                    + df.format(Math.round(endingBal*Math.pow(10,2))/Math.pow(10,2))
                    +"\n";
        }
        return result;
    }

    public String getSummary(){
        DecimalFormat df = new DecimalFormat("$###,###,###,###.00");

        double payment = this.getPayment();
        double startingBalance = this.balance;
        double interestPayment = 0;
        double principalPayment = 0;

        double totalInt = 0;
        double totalPrin = 0;
        int totalNumPayments = 0;

        for (int i = 1; i<=this.term*12; i++) {
            startingBalance -= principalPayment;
            interestPayment = this.rate/100/12*startingBalance;
            principalPayment = payment - interestPayment;

            totalInt += interestPayment;
            totalPrin += principalPayment;
            totalNumPayments++;
        }

        return  "================ SUMMARY ================ \n" +
                "Monthly Payment: " + df.format(this.getPayment()) +
                "\nTotal Principal Paid: " + df.format(totalPrin) +
                "\nTotal Interest Paid: " + df.format(totalInt) +
                "\nTotal Number of Payments: " +  totalNumPayments +
                "\n========================================= \n";
    }

    public double getPayment(){
        double monthRate = this.rate/100;
        double rate = monthRate*this.balance/(12*(1-(Math.pow(1+monthRate/12, -12*this.term))));
        double payment = Math.round(rate * Math.pow(10,2))/Math.pow(10,2);
        return payment;
    }

    @Override
    public String toString() {
        return  "Loan Term: " + term + " years" +
                ", Annual Interest Rate: " + rate + "%" +
                ", Principle: $" + balance;
    }
}
