package daa;

class RBI {
    public static final float minInt = 0.04f;

    public float getRateOfInterest() {
        return minInt;
    }
}

// Customer class to store customer details
class Customer {
    private String name;
    private String customerId;

    public Customer(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }
}

// Account class to store account-related info
class Account {
    private Customer customer;
    private float balance;
    private RBI bank;

    public Account(Customer customer, float balance, RBI bank) {
        this.customer = customer;
        this.balance = balance;
        this.bank = bank;
    }

    public void showAccountDetails() {
        System.out.println("Customer: " + customer.getName());
        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Balance: ₹" + balance);
        System.out.println("Bank: " + bank.getClass().getSimpleName());
        System.out.println("Interest Rate: " + bank.getRateOfInterest() * 100 + "%");
        System.out.println("Estimated Yearly Interest: ₹" + calculateInterest());
    }

    public float calculateInterest() {
        return balance * bank.getRateOfInterest();
    }
}

// ICICI bank overriding interest rate
class ICICI extends RBI {
    @Override
    public float getRateOfInterest() {
        return 0.065f; // 6.5%
    }
}

// AXIS bank overriding interest rate
class AXIS extends RBI {
    @Override
    public float getRateOfInterest() {
        return 0.0575f; // 5.75%
    }
}

public class P3_Banks {
    public static void main(String[] args) {
        Customer c1 = new Customer("Ananya", "C001");
        RBI icici = new ICICI();
        Account a1 = new Account(c1, 100000f, icici); // ₹1,00,000

        Customer c2 = new Customer("Rahul", "C002");
        RBI axis = new AXIS();
        Account a2 = new Account(c2, 50000f, axis); // ₹50,000

        System.out.println("=== Account Details ===");
        a1.showAccountDetails();
        System.out.println("---------------------------");
        a2.showAccountDetails();
    }
}
