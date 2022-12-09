import java.util.ArrayList;
import java.util.Scanner;
public class BankApp {
    private Scanner scanner = new Scanner(System.in);

    private Bank userBank; private BagelShop userBagelShop;
    private ArrayList<CreditCard> creditCardArrayList;

    private String userName;

    /**
     * Initialize a new BankApp object which initailzes everything
     */
    public BankApp() {
        System.out.println("Enter your name: ");
        this.userName = scanner.nextLine();

        userBank = new Bank();
        userBagelShop = new BagelShop(userName + "'s Bagel Shop", 20, 2, userBank);

        creditCardArrayList = new ArrayList<>();

        addNewCreditCard();
    }

    /**
     * Displays all available options
     */
    public void displayOptionMenu() {
        System.out.println(
                "1. Make a payment on the credit card."+
                        "\n2. Open another credit card"+
        "\n3. Compare credit card balances."+
        "\n4. Bagel Shop" + "\n5. Quit"
                + "Enter an option number: "
        );
    }

    /**
     * Adds a new credit card to the array
     */
    public void addNewCreditCard() {
        System.out.print("What should your credit card pin be: ");
        creditCardArrayList.add(new CreditCard(userName, scanner.nextLine()));
    }

    /**
     * @return Returns the credit card count
     */
    public int creditCardCount() {
        return this.creditCardArrayList.size();
    }

    /**
     * Makes a payment on the chosen card
     */
    public void makePayment() {
        int cc, amount;
        displayCreditCards();
        System.out.print("Credit Card Number: ");
        cc = scanner.nextInt();

        System.out.print("Amount to pay: ");
        amount = scanner.nextInt();

        CreditCard card = (CreditCard) (creditCardArrayList.get(cc - 1));
        card.reduceBalance(amount);
    }

    /**
     * Compares 2 credit cards
     */
    public void compareCreditCards() {
        CreditCard c1, c2;
        if (creditCardCount() <= 1) System.out.println("Not enough credit cards.");
        else {
            displayCreditCards();
            System.out.print("First Credit Card: ");
            c1 = creditCardArrayList.get(scanner.nextInt() - 1);

            System.out.print("Second Credit Card: ");
            c2 = creditCardArrayList.get(scanner.nextInt() - 1);

            String toPrint = "";

            if (c1.getBalanceOwed() < c2.getBalanceOwed()) toPrint += "The First Card Owes Less\n";
            else  toPrint += "The Second Card Owes Less\n";

            toPrint += "Card 1 Balance owed: " + c1.getBalanceOwed()
                    + "\nCard 2 Balance owed: " + c2.getBalanceOwed();
        }
    }

    /**
     * Display all credit cards
     */
    public void displayCreditCards() {
        int i = 1;
        for (CreditCard creditCard : creditCardArrayList) {
            System.out.println("Credit Card " + i
                    + "\nHolder: " + creditCard.getAccountHolder()
                    + "\nBalance Owed: " + creditCard.getBalanceOwed());
        }
    }

    /**
     * Displays and runs the bagel shop methods.
     */
    public void bagelShopMethods() {
        System.out.print(
                "Bagel Shop Options"
                + "\n1. Purchase a bagel."
                + "\n2. Return a bagel."
                + "\n3. Deposit profits in the bank (owner)"
                + "\n4. Check inventory."
                + "\n\nOption number: "
        );

        switch (scanner.nextInt()) {
            case 1:
                System.out.print("\nCredit Card Pin: ");
                userBagelShop.payForBagels(creditCardArrayList.get(1), 1, scanner.nextLine());
                break;
            case 2:
                System.out.print("\nCredit Card Pin: ");
                userBagelShop.returnBagels(creditCardArrayList.get(1), 1, scanner.nextLine());
                break;
            case 3:
                userBagelShop.depositProfits();
                break;
            case 4:
                userBagelShop.toString();
                break;
        }
    }
}
