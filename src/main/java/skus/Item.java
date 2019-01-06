package skus;

public class Item {

    private final int      id;
    private final Category category;
    private final String   name;
    private       String   buttonName;
    private       double   price;
    private       Item[]   addons;

    public int getId ( ) {

        return id;
    }

    public Item ( int id,
                  Category category,
                  String name,
                  String buttonName,
                  double price,
                  Item[] addons ) {

        this.id = id;
        this.category = category;
        this.name = name;
        this.buttonName = buttonName;
        this.price = price;
        this.addons = addons;
    }
}

