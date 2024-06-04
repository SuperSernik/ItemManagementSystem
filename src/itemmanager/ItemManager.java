package itemmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import item.ItemCategory;
import itemmanager.commands.*;
import item.Item;
import ui.ANSICodes;



public class ItemManager {

    // Commands
    final String INV = "inv";
    final String SORTINV = "sortinv";
    final String SORT = "sort";
    final String FIND = "find";
    final String ADD = "add";
    final String EXIT = "exit";
    final String HELP = "help";

    boolean running;

    // Reads Commands
    Scanner cmdSC;

    // Stores all item in the inventory by id as key
    Map<String, Item> inventory;

    // Store all items in a sorted list to display to 
    // output console
    List<Item> itemList;

    // Specialised dictionaries for faster searching
    Map<String, ArrayList<Item>> nameInv;
    Map<String, ArrayList<Item>> brandInv;
    Map<String, ArrayList<Item>> modelInv;
    Map<String, ArrayList<Item>> categoryInv;


    public ItemManager(){
        
        inventory = new HashMap<String, Item>();
        itemList = new ArrayList<Item>();
        
        nameInv = new HashMap<String, ArrayList<Item>>();
        brandInv = new HashMap<String, ArrayList<Item>>();
        modelInv = new HashMap<String, ArrayList<Item>>();
        categoryInv = new HashMap<String, ArrayList<Item>>();


        running = true;
    }
    
    public void initialise(){
        Item a = new Item("1", "Laptop", "DELL", "XPS", ItemCategory.ELECTRONICS, 1000);
        Item b = new Item("2", "Laptop", "DELL", "INSP", ItemCategory.ELECTRONICS, 500);
        Item c = new Item("3", "Laptop", "HP", "ENVY", ItemCategory.ELECTRONICS, 100);
        Item d = new Item("4", "Laptop", "MAC", "PRO", ItemCategory.ELECTRONICS, 600);

        Item e = new Item("5", "Butter", "Lurpak", "Salted", ItemCategory.FOOD, 10);
        Item f = new Item("6", "Butter", "Flora", "Unsalted", ItemCategory.FOOD, 5);

        inventory.put(a.getId(), a);
        inventory.put(b.getId(), b);
        inventory.put(c.getId(), c);
        inventory.put(d.getId(), d);
        inventory.put(e.getId(), e);
        inventory.put(f.getId(), f);


        // Init. itemList to display all items in inventory
        itemList = hashMapToArrayList(inventory);

        initNameInv();
        initBrandInv();
        initModelInv();
        initCategoryInv();

        System.out.println("BRANDINV: " + brandInv);



    }

    public void run() throws Exception{
        System.out.println(ANSICodes.PURPLE + "Item Management System [v1]" + ANSICodes.RESET);
        
        menu();

    }

    public void menu() throws Exception{
        cmdSC = new Scanner(System.in);
        
        while (running) {
            
            System.out.print(ANSICodes.YELLOW_HI + "$> " + ANSICodes.RESET);
            Thread.sleep(1000);
            String command = cmdSC.nextLine();
            

            String[] args = command.split(" ");

            switch (args[0]) {
                case INV:
                    InvCMD invcmd = new InvCMD(inventory);
                    itemList = invcmd.getItemList(args);
                    printItemList();
                    break;

                case SORT:
                    SortCMD sortcmd = new SortCMD(inventory);
                    itemList = sortcmd.getItemList(args, itemList);
                    printItemList();
                    break;
            
                case SORTINV:
                    SortInvCMD sortinvCMD = new SortInvCMD(inventory);
                    itemList = sortinvCMD.getItemList(args);
                    printItemList();
                    break;

                case FIND:
                    FindCMD findCMD = new FindCMD(inventory, nameInv, brandInv, modelInv, categoryInv);
                    itemList = findCMD.getItemList(args);
                    printItemList();
                    break;

                case HELP:
                    HelpCMD helpcmd = new HelpCMD();
                    String helpStr = helpcmd.getHelpString();
                    System.out.println(helpStr);
                    break; 

                case ADD:
                    AddCMD addcmd = new AddCMD(inventory, cmdSC, nameInv, brandInv, modelInv, categoryInv);
                    Item newItem = addcmd.getItem();
                    addItem(newItem);
                    break;

                case EXIT:
                    running = false;
                    cmdSC.close();
                    break;

                default:
                    System.out.println("The command: " + command + " does not exist!");;
                    break;
            }
        }


    }



    public void addItem(Item item){

        if(item != null){
            inventory.put(item.getId(), item);
    
            addToNameInv(item);
            addToBrandInv(item);
            addToModelInv(item);
            addToCategoryInv(item);
            System.out.println("Item Added!");

        }
    }

    public void addToNameInv(Item item){

        if(nameInv.keySet().contains(item.getName())){
            ArrayList<Item> workingList = nameInv.get(item.getName());
            workingList.add(item);
            nameInv.put(item.getName(), workingList);
        }
        
        else{
            ArrayList<Item> newList = new ArrayList<Item>();
            newList.add(item);
            nameInv.put(item.getName(), newList);
        }
    }
    
    public void addToBrandInv(Item item){
        if(brandInv.keySet().contains(item.getName())){
            ArrayList<Item> workingList = brandInv.get(item.getBrand());
            workingList.add(item);
            brandInv.put(item.getBrand(), workingList);
        }
        
        else{
            ArrayList<Item> newList = new ArrayList<Item>();
            newList.add(item);
            brandInv.put(item.getName(), newList);
        }
    }

    public void addToModelInv(Item item){
        if(modelInv.keySet().contains(item.getModel())){
            ArrayList<Item> workingList = modelInv.get(item.getModel());
            workingList.add(item);
            modelInv.put(item.getModel(), workingList);
        }
        
        else{
            ArrayList<Item> newList = new ArrayList<Item>();
            newList.add(item);
            modelInv.put(item.getModel(), newList);
        }
    }

    public void addToCategoryInv(Item item){
        if(categoryInv.keySet().contains(item.getCategory())){
            ArrayList<Item> workingList = categoryInv.get(item.getCategory());
            workingList.add(item);
            categoryInv.put(item.getCategory(), workingList);
        }
        
        else{
            ArrayList<Item> newList = new ArrayList<Item>();
            newList.add(item);
            categoryInv.put(item.getCategory(), newList);
        }
    }

    public void printItemList(){
        String str = ANSICodes.CYAN + "  ID\tName\tBrand\tModel\tCategory\tPrice\n" + ANSICodes.RESET;

        for(var x : this.itemList){
            str += x + "\n";
        }
        System.out.println(str);
    }


    public static List<Item> hashMapToArrayList(Map<String, Item> hm){
        ArrayList<Item> items = new ArrayList<Item>();
        
        for(var key : hm.keySet()){
            items.add(hm.get(key));
        }
        return items;
    }


    public void initNameInv(){
        HashSet<String> listOfKeys = new HashSet<String>();

        for(var id : inventory.keySet()){
            String nameKey = inventory.get(id).getName();
            
            if(listOfKeys.contains(nameKey)){
                ArrayList<Item> workingList = nameInv.get(nameKey);
                workingList.add(inventory.get(id));
                nameInv.put(nameKey, workingList);
            }
            
            else{
                ArrayList<Item> newList = new ArrayList<Item>();
                newList.add(inventory.get(id));
                nameInv.put(nameKey, newList);
                listOfKeys.add(nameKey);
            }
            
        }
    }

    public void initBrandInv(){
        HashSet<String> listOfKeys = new HashSet<String>();

        for(var id : inventory.keySet()){
            String brandKey = inventory.get(id).getBrand();
            
            if(listOfKeys.contains(brandKey)){
                ArrayList<Item> workingList = brandInv.get(brandKey);
                workingList.add(inventory.get(id));
                brandInv.put(brandKey, workingList);
            }
            
            else{
                ArrayList<Item> newList = new ArrayList<Item>();
                newList.add(inventory.get(id));
                brandInv.put(brandKey, newList);
                listOfKeys.add(brandKey);
            }
            
        }

    }

    public void initModelInv(){
        HashSet<String> listOfKeys = new HashSet<String>();

        for(var id : inventory.keySet()){
            String modelKey = inventory.get(id).getModel();
            
            if(listOfKeys.contains(modelKey)){
                ArrayList<Item> workingList = modelInv.get(modelKey);
                workingList.add(inventory.get(id));
                modelInv.put(modelKey, workingList);
            }
            
            else{
                ArrayList<Item> newList = new ArrayList<Item>();
                newList.add(inventory.get(id));
                modelInv.put(modelKey, newList);
                listOfKeys.add(modelKey);
            }
            
        }

    }

    public void initCategoryInv(){
        HashSet<String> listOfKeys = new HashSet<String>();

        for(var id : inventory.keySet()){
            String catKey = inventory.get(id).getCategory();
            
            if(listOfKeys.contains(catKey)){
                ArrayList<Item> workingList = categoryInv.get(catKey);
                workingList.add(inventory.get(id));
                categoryInv.put(catKey, workingList);
            }
            
            else{
                ArrayList<Item> newList = new ArrayList<Item>();
                newList.add(inventory.get(id));
                categoryInv.put(catKey, newList);
                listOfKeys.add(catKey);
            }
            
        }

    }
    
}