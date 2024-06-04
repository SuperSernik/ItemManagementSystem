package itemmanager.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import item.Item;

public class FindCMD {
    Map<String, Item> inventory;

    Map<String, ArrayList<Item>> nameInv;
    Map<String, ArrayList<Item>> brandInv;
    Map<String, ArrayList<Item>> modelInv;
    Map<String, ArrayList<Item>> categoryInv;

    public FindCMD(Map<String, Item> inventory, Map<String, 
                ArrayList<Item>> nameInv, Map<String, ArrayList<Item>> brandInv,
                Map<String, ArrayList<Item>> modelInv, Map<String, ArrayList<Item>> categoryInv){
                    
        this.inventory = inventory;

        this.nameInv = nameInv;
        this.brandInv = brandInv;
        this.modelInv = modelInv;
        this.categoryInv = categoryInv;
    }

    public List<Item> getItemList(String[] args){
        
        switch (args[1]) {
            case "-n":
                return nameInv.get(args[2]);

            case "-b":
                return brandInv.get(args[2]);

            case "-m":
                return modelInv.get(args[2]);
            
            case "-c":
                return categoryInv.get(args[2]);

        }

        return new ArrayList<Item>() {};
    }

}
