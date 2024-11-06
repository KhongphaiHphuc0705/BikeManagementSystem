package main;

import data.Product;
import DAO.ProductDAO;
import ui.Menu;
import utilities.MyUtils;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDAO productList = new ProductDAO();
        productList.loadFromFile(".\\Product.txt");
        Menu mainMenu = new Menu("Bike Stores Management System");
        mainMenu.addNewOption("Add a product");
        mainMenu.addNewOption("Search product by product name");
        mainMenu.addNewOption("Update product");
        mainMenu.addNewOption("Delete product");
        mainMenu.addNewOption("Save products to file");
        mainMenu.addNewOption("Print list products from the file");
        mainMenu.addNewOption("Find and update price by model year");
        mainMenu.addNewOption("Quit");
        
        boolean saved = true;
        while (true) {
            mainMenu.printMenu();
            switch(mainMenu.getChoice()) {
                case 1:
                    productList.add();
                    saved = false;
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 2:
                    productList.search();
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 3:
                    productList.update();
                    saved = false;
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 4:
                    productList.delete();
                    saved = false;
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 5:
                    productList.saveToFile(".\\Product.txt");
                    System.out.println("Saved successfully.");
                    saved = true;
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 6:
                    productList.print();
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                case 7:
                    double x = MyUtils.getDouble("Input updated price: ", "Invalid, please input again.", 0);
                    int y = MyUtils.getInteger("For model year: ", "Invalid, please input again.", 0);
                    productList.find(x, y);
                    saved = false;
                    System.out.println("Press Enter to go back to the main menu.");
                    sc.nextLine();
                    break;
                default:
                    if(saved) {
                        System.out.println("Thanks for stopping by. See you again!");
                        return;
                    }
                    String confirm = MyUtils.getString("Are you sure you want to exit without saving? (Y/N) ", "Invalid, please input again. ", "[Yy|Nn]");
                    if (confirm.equalsIgnoreCase("Y")) {
                        System.out.println("Thanks for stopping by. See you again!");
                        return;
                    }
                    break;
            }
        }
    }    
}
