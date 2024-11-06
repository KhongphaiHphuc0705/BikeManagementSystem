package ui;

import utilities.MyUtils;
import java.util.ArrayList;

public class Menu extends ArrayList<String> implements IMenu {
    private String title;

    public Menu(String title) {
        this.title = title;
    }
    
    @Override
    public void addNewOption(String newOption) {
        this.add(newOption);
    }
    
    @Override
    public void printMenu() {
        System.out.println("----------------------------------------------------");
        System.out.println("Welcome to " + title);
        int count = 0;
        for (String x : this) {
            count++;
            System.out.println(count + ". " + x);
        }
    }
    
    @Override
    public int getChoice() {
        return MyUtils.getInteger("Choose 1 - " + this.size() + ": "
                , "Invalid. Please input choice again. ", 1, this.size());
    }
}
