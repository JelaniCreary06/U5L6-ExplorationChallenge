import java.util.Scanner;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean programDone = false;

        BankApp bankApp = new BankApp();

        do {
            bankApp.displayOptionMenu();

            switch (scanner.nextInt()) {
                case 1:
                    bankApp.makePayment();
                    break;
                case 2:
                    bankApp.addNewCreditCard();
                    break;
                case 3:
                    bankApp.compareCreditCards();
                    break;
                case 4:
                    bankApp.bagelShopMethods();
                    break;
                case 5:
                    programDone = true;
            }
        } while (!programDone);
    }
}
