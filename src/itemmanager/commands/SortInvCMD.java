package itemmanager.commands;

import java.util.Map;
import java.util.List;

import java.util.Collections;
import java.util.ArrayList;
import item.Item;
import item.ItemComparator;

public class SortInvCMD {
    Map<String, Item> inventory;

    public SortInvCMD(Map<String, Item> inventory){
        this.inventory = inventory;
    }

    public List<Item> getItemList(String[] args){
        String field = ItemComparator.ID;
        int type = 0;

        if(args.length > 1){
          field = args[1];
        }

        if(args.length > 2){
            if(args[2].equals("asc")){
                type = 0;
            }
            if(args[2].equals("des")){
                type = 1;
            }
        }

        return sortInventory(inventory, field, type);
        
    }

    public static List<Item> sortInventory(Map<String, Item> map, String field, int type){
        // type 0 for asc, type 1 for des
        List<Item> items = new ArrayList<Item>();

        for(String id : map.keySet()){
            items.add(map.get(id));
        }
        Collections.sort(items, Item.getComparator(field));

        if(type == 1){
            Collections.reverse(items);
        }
        return items;
    }


}

