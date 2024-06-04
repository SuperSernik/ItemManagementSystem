package item;

public interface IItem {
    
    public String getId();
    public String getName();
    public String getBrand();
    public String getModel();
    public String getCategory();
    public double getPrice();

    public void setId(String id);
    public void setName(String name);
    public void setBrand(String brand);
    public void setModel(String model);
    public void setCategory(String category);
    public void setPrice(double price);

}
