public class BagelShop
{
    /** The name of the BagelShop */
    private String name;

    /** The earned profit so far */
    private int profit;

    /** How many bagels are in inventory */
    private int inventory;

    /** The price of each bagel (in dollars) */
    private int bagelPrice;

    /** The Bank where the BagelShop has a vendor deposit account */
    private Bank vendorBank;

    /** Creates a new BagelShop
     @param name  The name of the BagelShop
     @param inventory  How many bagels in starting inventory
     @param bagelPrice  How much (in dollars) each bagel costs
     @param vendorBank  The bank the BagelShop is using for depositing profits
     */
    public BagelShop(String name, int inventory, int bagelPrice, Bank vendorBank)
    {
        profit = 0;
        this.name = name;
        this.inventory = inventory;
        this.bagelPrice = bagelPrice;
        this.vendorBank = vendorBank;
    }

    /** First checks to see if the provided cardPIN matches the PIN number of the
     credit card; if not,do nothing, and immediately return false (purchase was not successful)

     If cardPIN number matches the credit card's PIN, calculate the total price of the purchase
     (bagel's price x quantity purchased), charge the credit card for that amount, add that
     amount to the bagel shop's profit, and reduce quantity.  Return true (bagel purchase was successful).

     @param card  The CreditCard used to pay for the bagels
     @param int  How many bagels are being purchasd
     @param cardPIN  The PIN number provided by the customer

     @return  Return true if the purchase was successful, false if the purchase was unsuccessful
     */
    public boolean payForBagels(CreditCard card, int quantity, String cardPIN)
    {
        int purchasePrice = (this.bagelPrice * quantity);

        if (card.checkPIN(cardPIN)) {
            card.chargeCard(purchasePrice);
            this.profit += purchasePrice;
            this.inventory -= quantity;
            return true;
        } else return false;
    }

    /** First checks to see if the provided cardPIN matches the PIN number of the
     credit card; if not,do nothing, and immediately return false (purchase was not successful)

     If cardPIN number matches the credit card's PIN, calculate the total price of the return
     (bagel's price x quantity purchased), reduce the balance on the credit card for that amount, reduce
     the bagel shop's profit by that amount; do NOT add returned bagels back to inventory (yuck!).
     Return true (the bagel return was successful).

     @param card  The CreditCard used to pay for the bagels
     @param quantity  How many bagels are being purchasd
     @param cardPIN  The PIN number provided by the customer

     @return  Return true if the purchase was successful, false if the purchase was unsuccessful
     */
    public boolean returnBagels(CreditCard card, int quantity, String cardPIN)
    {
        int returnAmount = this.bagelPrice * quantity;

        if (card.checkPIN(cardPIN)) {
            card.reduceBalance(returnAmount);
            this.profit -= returnAmount;
            return true;
        } else return false;
    }

    /** Deposits all current profits in the vendorBank
     *  and sets profits back to 0.
     */
    public void depositProfits()
    {
        vendorBank.vendorDeposit(this.profit);
        this.profit = 0;
    }

    public String toString()
    {
        String str = "Vendor: " + name;
        str += "\nProfit: " + profit;
        str += "\nInventory: " + inventory;
        str += "\nItem price: " + bagelPrice;

        // omitting the bank instance variable from the String
        return str;
    }
}
