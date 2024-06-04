package itemmanager.commands;

import java.util.Map;
import java.util.List;

import java.util.Collections;
import item.Item;
import item.ItemComparator;

public class SortCMD {
    
    Map<String, Item> inventory;

    public SortCMD(Map<String, Item> inventory){
        this.inventory = inventory;
    }

    public List<Item> getItemList(String[] args, List<Item> itemList){

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

        return sortItemList(itemList, field, type);
    }

    public static List<Item> sortItemList(List<Item> items, String field, int type){

        Collections.sort(items, Item.getComparator(field));

        if(type == 1){
            Collections.reverse(items);
        }
        return items;
    }

}
