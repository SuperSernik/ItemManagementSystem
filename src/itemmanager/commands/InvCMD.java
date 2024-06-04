
package itemmanager.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import item.Item;

public class InvCMD {

    Map<String, Item> inventory;

    public InvCMD(Map<String, Item> inventory){
        this.inventory = inventory;
    }

    public List<Item> getItemList(String[] args){
        return hashMapToArrayList(this.inventory);
        
    }

    public static List<Item> hashMapToArrayList(Map<String, Item> hm){
        ArrayList<Item> items = new ArrayList<Item>();
        
        for(var key : hm.keySet()){
            items.add(hm.get(key));
        }
        return items;
    }

}