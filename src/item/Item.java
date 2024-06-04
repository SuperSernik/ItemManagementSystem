package item;

import java.util.Comparator;



public class Item implements IItem {
    
    // Essential Attributes
    String id;
    String name;
    String brand;
    String model;
    String category;
    double price;

    // Non Essential Attributes
    String description;


    public Item(String id, String name, String brand, String model, String category, double price){
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
    }


    public static Comparator<Item> getComparator(String field){
        Comparator<Item> comp;

        switch(field){
            case ItemComparator.ID:
                comp = Comparator.comparing(Item::getId);
                break;
            case ItemComparator.NAME:
            comp = Comparator.comparing(Item::getName);
            break;
            case ItemComparator.BRAND:
                comp = Comparator.comparing(Item::getBrand);
                break;
            case ItemComparator.MODEL:
                comp = Comparator.comparing(Item::getModel);
                break;
            case ItemComparator.CATEGORY:
                comp = Comparator.comparing(Item::getCategory);
                break;
            case ItemComparator.PRICE:
                comp = Comparator.comparing(Item::getPrice);
                break;

            default:
                throw new IllegalArgumentException("Field was not a viable field!");
        }
        return comp;
        
    }


    public String toString(){
        String str = "[";
        str += "ID:" + this.id + " ";
        str += "NAME:" + this.name + " ";
        str += "BRAND:" + this.brand + " ";
        str += "MODEL:" + this.model + " ";
        str += "CAT:" +this.category + " ";
        str += "]";

        return str;
    }

    public String getDataEntry(){
        String str = "< ";
        str += this.id + "\t";
        str += this.name + "\t";
        str += this.brand + "\t";
        str += this.model + "\t";
        str += this.category + "\t";
        str += this.price;
        str += "\t>";

        return str;
    }



    // Getters and Setters

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCategory() {
        return this.category;
    }



    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public double getPrice() {
        return this.price;
    }


    @Override
    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String getModel() {
        return this.model;
    }


    @Override
    public void setModel(String model) {
        this.model = model;
    }


    @Override
    public String getBrand() {
        return this.brand;
    }


    @Override
    public void setId(String id) {
        this.id = id;
    }


    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }




    

    

}
