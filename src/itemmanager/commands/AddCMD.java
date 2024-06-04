package itemmanager.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Scanner;

import item.Item;
import item.ItemCategory;
import ui.ANSICodes;

public class AddCMD {
    
    Map<String, Item> inventory;
    Scanner scanner;

    Map<String, ArrayList<Item>> nameInv;
    Map<String, ArrayList<Item>> brandInv;
    Map<String, ArrayList<Item>> modelInv;
    Map<String, ArrayList<Item>> categoryInv;

    public AddCMD(Map<String, Item> inventory,
                Scanner scanner,
                Map<String, ArrayList<Item>> nameInv, Map<String,       ArrayList<Item>> brandInv,
                Map<String, ArrayList<Item>> modelInv, Map<String,      ArrayList<Item>> categoryInv){

        this.inventory = inventory;
        this.scanner = scanner;

        this.nameInv = nameInv;
        this.brandInv = brandInv;
        this.modelInv = modelInv;
        this.categoryInv = categoryInv;
    }

    public Item getItem(){


        
        System.out.print("Item Name " + ANSICodes.YELLOW_HI + "$> " + ANSICodes.RESET);
        String name = scanner.next();
        
        System.out.print("Item Brand " + ANSICodes.YELLOW_HI + "$> " + ANSICodes.RESET);
        String brand = scanner.next();
        
        System.out.print("Item Model " + ANSICodes.YELLOW_HI + "$> " + ANSICodes.RESET);
        String model = scanner.next();
        
        System.out.print("Item Categories:\n1 Electronics\n2 Food\n3 Health\n4 Sanitary\nChoice " + ANSICodes.YELLOW_HI + "$> " + ANSICodes.RESET);
        int choice = scanner.nextInt();
        String category = getCategoryByInt(choice);

        
        System.out.print("Item Price " + ANSICodes.YELLOW_HI + "$> " + ANSICodes.RESET);
        int price = scanner.nextInt();

        System.out.println("Does this look right? (y/n)");

        System.out.print(ANSICodes.YELLOW_HI + "$> " + ANSICodes.RESET);
        String ans = scanner.next();

        if(!ans.equals("y")){
            System.out.println("Aborted!");
            return null;
        }


        Item item = new Item(createId(), name, brand, model, category, price);

        return item;
    }

    public String createId(){
        Set<String> keys = inventory.keySet();
        Set<Integer> intKeys = keys.stream()
                                        .map(s -> Integer.parseInt(s)) 
                                        .collect(Collectors.toSet());
        Integer max =  Collections.max(intKeys);
        max ++;
        return String.valueOf(max);
    }

    public String getCategoryByInt(int x){
        switch (x) {

            case 1:
                return ItemCategory.ELECTRONICS;
            
            case 2:
                return ItemCategory.FOOD;
            
            case 3:
                return ItemCategory.HEALTH;
            
            case 4:
                return ItemCategory.SANITARY;
                
        
            default:
                return "X";
        }
    }
}
