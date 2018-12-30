package SKU;

public class Item {

    private int      id;
    private Category category;
    private String   name;
    private String   button_name;
    private double   price;
    private Item[]   addons;

    public int getId ( ) {

        return id;
    }

    public Item ( int id,
                  Category category,
                  String name,
                  String button_name,
                  double price,
                  Item[] addons ) {

        this.id = id;
        this.category = category;
        this.name = name;
        this.button_name = button_name;
        this.price = price;
        this.addons = addons;
    }
}

