public class Transaction 
{
    private String type;
    private double amount;
    private String date;
    @Override
    public String toString() {
        return "Transaction{" +
            "type='" + type + '\'' +
            ", amount=" + amount +
            ", date='" + date + '\'' +
            '}';
    }


    public Transaction(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Getters and toString method
}
