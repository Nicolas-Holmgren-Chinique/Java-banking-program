public class BankAccountTest {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("", "", 0.0);

        account.createAccount();

        account.addMoneyToAccount();

        // PROBLEM TO RESOLVE:
        // the problem is that the withdrawl function code will excute right when the
        // addMoney in account function asks the user if
        // they want to add more money to their account !!!

        account.withdrawlMoneyFromAccount();
    }
}
