package SKU;

public class Id {

    private static Id ourInstance = new Id ( );
    private static int current = 1;

    public static Id getInstance ( ) {

        return ourInstance;
    }

    public static int nextId() {
        return current++;
    }

    private Id ( ) {

    }
}
