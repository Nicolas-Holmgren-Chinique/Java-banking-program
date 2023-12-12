// Auhtor: Nicolas Holmgren
//date: 12/06/2023
// this is the final project for intro to java class.
//professor: Trzos

// NOTE TO SELF: THERE ARE SOME ERRORS GOING ON STILL, MOSTLY WITH THE PROGRAM ASKING TWICE FOR THE AMOUNT TO WITHDRAW, in the functions insufficentFunds and remove

// The purpose of this program is to create a simple banking system. We will
// have a class that allows a user to create a bank account.

// we will have a method that will allow the users to add and withdrawl money from their account

//Input, the user answers if they want to create an account with the bank, if yes then they will be asked to provide their name, and set a password for their account, they will 

//proccess: Determine how the user answered the questions and weather the methods for setting the bank account and establishing the amount of money in the account

// importing the Scanner class from the util package
import java.util.Scanner;

//this import formats the big numbers so that they have commas inbetween
import java.text.DecimalFormat;

// this package allows us to manipulte if numbers will get truncated, which we don't want as we are a bank and need precise dollar amounts.
import java.math.RoundingMode;
//create the java BankAccount Class

public class BankAccount {

    // private DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
    Scanner input = new Scanner(System.in);

    // this shuts off the rounding in our decimal formatting

    // here we are declaring the instance variables

    private DecimalFormat decimalFormat;

    private String NAME;
    private String password;
    private double moneyInAccount = 0.0;
    private double withdrawlMoney = 0.0;
    // private double enterMoneyAmount;

    // here is the class constructor
    public BankAccount(String NAME, String password, double enterMoneyAmount) {
        this.NAME = NAME;
        this.password = password;
        // this.enterMoneyAmount = enterMoneyAmount;
        decimalFormat = new DecimalFormat("#,###,###");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
    }

    // this is our setter for setting the name of the account
    public void setAccountName(String NAME) {
        this.NAME = NAME;
    }

    // this is for getting the name of the account that we set. allowing us to
    // return the value of the name
    public String getAccountName() {
        return "Welcome " + this.NAME;
    }

    // setting the password
    public void setPassword(String password) {
        this.password = password;
    }

    // this method is for getting/returinging the value of password, although for
    // the most part
    // we won't want to return password to the user or really anyone as it should be
    // secret
    public String getPassword() {
        return this.password;
    }

    // setting the amount of money
    public void setMoneyInAccount(double moneyInAccount) {

        // this checks if the amount of money being entered to the account is greater
        // than 0
        // then += add/set that amount to moneyInAccount
        if (moneyInAccount > 0) {
            this.moneyInAccount += moneyInAccount;
        }
    }

    public double getMoneyInAccount() {
        return this.moneyInAccount - getWithdrawlAmount(); // here we subtract the amount being withdrawn from the total
        // amount of money in the account
    }

    // here we are setting the amount of money to witdrawl from the account,
    // we are implmenting a try catch so that if the user trys to witdrawl more
    // money than they actually have in the account
    // an error will arise telling the user that they don't have sufficent funds.
    public void setWithdrawl(double withdrawlMoney) {
        // try {
        if (getMoneyInAccount() >= withdrawlMoney) {
            this.withdrawlMoney = withdrawlMoney;
        }

        // } catch (Exception e) {
        // System.out.printf("You don't have sufficent funds, you have %.2f, you were
        // trying to withdrawl %2.f",
        // getMoneyInAccount(), getWithdrawlAmount());
        // }

    }

    public double getWithdrawlAmount() {
        return this.withdrawlMoney;
    }

    // this method is asking the user weahter they want to create an account, if
    // they say yes, then they will be asked to set their name and password.

    public void createAccount() {

        // we ask the user if they want to create an account, if yes then we ask them to
        // enter their name and we set that name to the method setAccountName
        System.out.println(
                "Would you like to create a bank account with us? Type in yes if you want to create an account ");

        String createAnAccount = input.nextLine();

        if (createAnAccount.equals("yes")) {
            System.out.println("Awsome we are glad you want to join us " + "please enter your full name: ");
            String createAccountName = input.nextLine();

            setAccountName(createAccountName);

            getAccountName();

            System.out.println("Now we will have you create your password");

            String createAccountPassword = input.nextLine();
            setPassword(createAccountPassword);

            // now that they have set their account we want to thank the user
            System.out.println("Thank you " + createAccountName + " for creating an account with our bank");
            // now we have to ask the user if they would like to add meney to their account
        }
    }

    // this method asks the user to enter the amount of money they want to add to
    // their account, we then print to the screen the amount of money they added to
    // their account and the amount of
    // money they have in their account total
    public void addMoneyToAccount() {

        // here we implement a while loop so that if the user wants to add more money
        // they can do that until they say no or something other than yes.
        boolean continueAdding = true;

        while (continueAdding) {
            System.out.print("How much money do you want to add to your account $");

            double addMoney = input.nextDouble();
            input.nextLine();

            setMoneyInAccount(addMoney);

            String formattedAddMoney = decimalFormat.format(addMoney); // this adds comma spacing between numbers
            System.out.println("You added $" + formattedAddMoney + " to your account");

            String formattedTotalMoney = decimalFormat.format(getMoneyInAccount());
            System.out.println("You have a total of $" + formattedTotalMoney);

            System.out.println("Do you want to add more money? Type 'yes' to continue or 'no' to exit");
            String userInput = input.nextLine();

            if (!userInput.equalsIgnoreCase("yes")) {
                continueAdding = false;
            }
        }
    }

    // we are now creating the function for withdrawing money from the account, the
    // user will be asked if they want to
    // withdraw money if so then a we will call the setter functions and the getter
    // functions for establsihing the amount of money to withdraw.

    public void withdrawlMoneyFromAccount() {

        // problem with this code is that, the program is allowing a withdrawl no and
        // the error is not displayed, the withdrawl gets set to 0.0 which is what it
        // has been set to as an instance variable
        boolean withDraw = true;

        while (withDraw == true) { // this while statement will keep asking the user the withdrawl amount until it
            // <= to the amount in the bank account
            System.out.printf("%n %s, would you like to withdraw any money from your account today? ",
                    getAccountName());
            String userInput = input.nextLine();

            // this array stores all the possible responses the user might give
            String[] strArray = { "yes", "yeah", "yea", "sure", "alright", "ok" };

            boolean isMatch = false; // so this is giving a minor error, but doesn't cause in runtime errors, the
                                     // program still works

            // this is a hack that we are doing so that, isMatch doesn't complain about not
            // being used, we'll have to figure out a better solution

            for (String str : strArray) {
                if (userInput.equalsIgnoreCase(str)) {
                    isMatch = true;

                    try {

                        double withdrawalAmount = inputForWithdrawl();

                        // this checks if the money getting withdrawn is less than money in account,
                        // total money avialbe - money being removed
                        // we also check that the amount being entered is greater than or equal to 0
                        if (withdrawalAmount < getMoneyInAccount() && withdrawalAmount >= 0) {
                            withDrawFunction(withdrawalAmount); // if all those cases procces right than
                                                                // withDrawFunction() is called
                            // and
                        }
                        // this checks if user is removing all money from their account, if the withdraw
                        // amount is the same as the as monies in account
                        // then removingAllMoney() is called and the method proccesses a fee for
                        // removing all monies
                        else if (withdrawalAmount == getMoneyInAccount()) {
                            removingAllMoney(withdrawalAmount);
                        }

                        else if (withdrawalAmount > getMoneyInAccount()) {
                            throw new Exception();
                        }

                    } catch (Exception e) { // if they try to take more funds than they have an error will arise

                        double withDrawalAmount = inputForWithdrawl();

                        insufficentFundsMethod(withDrawalAmount);

                        // System.out.printf(
                        // "You don't have sufficent funds, you have %.2f, you were trying to withdrawl
                        // %2.f",
                        // getMoneyInAccount(), getWithdrawlAmount());
                    } finally {
                        input.nextLine();
                    }
                }

                else {
                    withDraw = false;
                }
            }
        }
    }

    public double inputForWithdrawl() {
        System.out.print("Great! Enter the amount of money you would like to withdraw $");

        return input.nextDouble();
    }

    // this method is checking how much money the user wants to remove from their
    // account
    public void withDrawFunction(double withdrawalAmount) {
        // inputForWithdrawl();

        // here we are checking to see if the amount being withdrawled is greater than =
        // to the amount money in the account
        // if (enterMoneyAmount < getMoneyInAccount() && enterMoneyAmount > 0) {
        // setWithdrawl(enterMoneyAmount);
        // here we are setting the amount of money being taken out of
        // the
        // account

        getWithdrawlAmount(); // here we are calling the method that will show how much is getting
                              // removed
                              // from the account

        // there is a problem here, with amount being withdrawn
        double totalInAccount = getMoneyInAccount() - withdrawalAmount;
        String fomrattedMoneyWithdrawn = decimalFormat.format(withdrawalAmount);
        String fomrattedMoneyInAccount = decimalFormat.format(totalInAccount);
        System.out.printf("You are withdrawing $%s, you have $%s left in your account ",
                fomrattedMoneyWithdrawn, fomrattedMoneyInAccount);

        // }

    }

    // this method is for checking if the user is trying to withdrawl all their
    // moeny from their account, in which if they do they will incure a fee of .05
    // or 5%
    public void removingAllMoney(double withdrawalAmount) {

        // double withdrawalAmount = inputForWithdrawl();

        if (withdrawalAmount == getMoneyInAccount()) { // this if checks if all money

            // the point of this variable is to hold the value of the moneyInAccount
            double moneyInAccount = getMoneyInAccount();
            // is getting
            // withdrawn, which applies a fee
            final double ACCOUNTCLOSERFEE = .05;
            double feeApplied = withdrawalAmount * ACCOUNTCLOSERFEE; // this gets the amount of money should
                                                                     // be removed from the account based on
                                                                     // 5% of the money that was in the
                                                                     // account
            double amountDeductedFromAccount = withdrawalAmount - feeApplied; // this is the amount removed
                                                                              // from the account, so if 100
                                                                              // is being taken out $5 is
                                                                              // subtracted from the account
            setWithdrawl(amountDeductedFromAccount); // for withdrawing all money in the account, the user
                                                     // will
                                                     // have to pay a fee of 5% which will get removed when
                                                     // setWithdrawl is called, passing the variable that
                                                     // does the calculation, amountDeductedFromAccount

            // these 2 lines below, format the numbers of the the getWithdrawlAmount and the
            // amount of money deducted from the account
            String formattedWithDrawlAmount = decimalFormat.format(moneyInAccount);
            String formattedDecutionAmount = decimalFormat.format(amountDeductedFromAccount);

            System.out.printf(
                    "You are withdrawing all the money in your account which is $%s, you will be charged a .05 fee for removing all cash, "
                            + "after fees you have $%s ",
                    formattedWithDrawlAmount, formattedDecutionAmount);
        }
    }

    // this method checks if the there is sufficent funds in the account, so when
    // the user enters an amount to withdrawl from their account if the amount if
    // more than what is avaibale then an error message would arise. this method
    // would get called in the catch block
    public void insufficentFundsMethod(double withDrawalAmount) {

        // double withdrawalAmount = inputForWithdrawl();

        // if (enterMoneyAmount > getMoneyInAccount()) {

        String formattedMoneyInAccount = decimalFormat.format(getMoneyInAccount());
        String formattedWithdrawlAmount = decimalFormat.format(withDrawalAmount);
        System.out.printf( // this print excutes when the user trys taking out more money than they have
                           // in the account
                "You don't have sufficient funds. You have $%s in your account, but you were trying to withdraw $%s\n",
                formattedMoneyInAccount, formattedWithdrawlAmount); // the 1st variable being casted is
                                                                    // formated and returns amount$ in
                                                                    // the account the 2nd returns the
                                                                    // amount being attempted to
                                                                    // withdraw

        System.out.printf("you have %.2f in your account ", getMoneyInAccount());
        // }
    }
}

// Thank you professor Trzos for a great two semesters, I have really enjoyed
// your classes and I have learend and grown a lot since last spring starting at
// Grossmont.
// I am sad that I won't have you as my professor for any of my future classes,
// I wish you the best and thank you for being a great professor!