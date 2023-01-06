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
        double payment = this.getPayment();
        double startingBalance = this.balance;
        double interestPayment = 0;
        double principalPayment = 0;
        double endingBal = 0;

        String result = this.term + " Years Amortization Schedule\n";
        result += "Month | Starting Bal | Interest Paid | Principal Paid | Ending Balance \n";
        for (int i = 1; i <= this.term*12; i++) { // todo change this later to actual months
            interestPayment = this.rate/100/12*startingBalance;
            startingBalance -= principalPayment;
            principalPayment = payment-interestPayment;
            endingBal = startingBalance - principalPayment;
            result += i + " | " + Math.round(startingBalance*Math.pow(10,2))/Math.pow(10,2) + " | " + Math.round(interestPayment*Math.pow(10,2))/Math.pow(10,2) + " | " + Math.round(principalPayment*Math.pow(10,2))/Math.pow(10,2) + " | " + Math.round(endingBal*Math.pow(10,2))/Math.pow(10,2) +"\n";
        }
        return result;
    }

    public String getSummary(){
        return "Summary";
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
