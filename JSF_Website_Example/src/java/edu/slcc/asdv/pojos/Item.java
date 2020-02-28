
package edu.slcc.asdv.pojos;
import java.util.Objects;

public class Item 
        implements Keyable<String>, Comparable<Item>, Categorable<String>
{   private String category;
    private String name;
    private String id;  
    private String description;
    private int qty;
    private double price;
    private String image;
    private String search;
    
    public Item(){}
    
    public Item(String id){
        this.id = id;
    }

    public Item(String id, String name, String description, int qty, double price, String image, String category, String search) {
        this.category = category;
        this.name = name;
        this.id = id;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.image = image;
        this.search = search;
    }
    
    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}
    public String getId(){return id;}
    public void setId(String id){this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getQty() {return qty;}
    public void setQty(int qty) {this.qty = qty;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}
    public String getSearch() {return search;}
    public void setSearch(String search) {this.search = search;}

    @Override public String getKey(){return getId();}
    @Override public void setKey(String key){ setKey(key);}

    @Override
    public String getCategory()
    {
        return category;
    }

    @Override
    public void setCategory(String t)
    {
         this.category = t;
    }

    @Override
    public int compareTo(Item o)
    {
        return this.id.compareTo(o.id);
    }

  

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
          {
            return true;
          }
        if (obj == null)
          {
            return false;
          }
        if (getClass() != obj.getClass())
          {
            return false;
          }
        final Item other = (Item) obj;
        if (!Objects.equals(this.id, other.id))
          {
            return false;
          }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "category=" + category + ", name=" + name + ", id=" + id + ", description=" + description + ", qty=" + qty + ", price=" + price + ", image=" + image + ", search=" + search + '}';
    }

    
}
