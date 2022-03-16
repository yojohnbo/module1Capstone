package com.techelevator;

import com.techelevator.view.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;




public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT,MAIN_MENU_OPTION_SALES_REPORT  };

	private static final String PURCHASE_MENU_OPTIONS_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTIONS_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTIONS_FEED_MONEY, PURCHASE_MENU_OPTIONS_SELECT_PRODUCT, PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION};

	private Menu menu;
	private Customer customer = new Customer();
	private SalesReport salesReport ;
	// constructor
	public VendingMachineCLI(Menu menu) throws FileNotFoundException {
		this.menu = menu;
	}

	public void run() {
		Inventory inventory = new Inventory();
		Map<String, Product> inventoryMap = inventory.readFile();


		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			CoinBank coinBank = new CoinBank();

			String filePath = "Log.txt";
			File logFile = new File(filePath);

			double amountToDeposit = 0;

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				inventory.displayItems(inventoryMap);

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				Scanner userInput = new Scanner(System.in);
				Product product = new Product();
				while (true) {
					String purchaseChoice = (String) menu.getChoiceFromPurchaseMenuOptions(PURCHASE_MENU_OPTIONS, customer);
					if (purchaseChoice.equals(PURCHASE_MENU_OPTIONS_FEED_MONEY)) {
						System.out.println("Please deposit money in whole dollar amounts: ");

						try{
							amountToDeposit = Double.parseDouble(userInput.nextLine());
						}catch(NumberFormatException e){
							System.out.println("Oops, please enter again.");
						}
						customer.feedMoney(amountToDeposit);

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTIONS_SELECT_PRODUCT)) {
						//Select product method
						product = customer.selectProduct(inventoryMap, customer.getMoneyProvided(), userInput,customer);

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION)) {
							//receive change, money provided to zero, update coinbank balance
						customer.finishTransaction(product,customer,coinBank);
						writeDataToFile(logFile, customer);
						// return to main menu
						break;
					}
				}

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				break;
			}else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)){
				// print salesReport
				salesReport = new SalesReport(inventoryMap);
				salesReport.printReport();

			}

		}
	}


	// create method for audit
	public void writeDataToFile(File logFile, Customer customer){
		try(PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))){
			for (AuditLog log : customer.auditLogs){
				writer.println(log.printInLog());
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}





	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();


	}
}
