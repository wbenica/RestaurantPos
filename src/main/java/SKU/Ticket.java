package SKU;

public class Ticket {

    private static int currentTicket = 0;

    Table table;
    int ticketNum;
    

    public static int getCurrentTicket ( ) {

        return currentTicket;
    }

    private static int getNextTicket ( ) {

        return currentTicket += 1;
    }

    private class Table {
        Character section;
        int tableNum;
    }
}
