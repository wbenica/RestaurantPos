package skus;

import employees.Server;

public class Ticket {

    private static int currentTicket = 0;

    Table  table;
    int    ticketNum;
    Server server;

    public static int getCurrentTicket ( ) {

        return currentTicket;
    }

    private static int getNextTicket ( ) {

        currentTicket += 1;
        return currentTicket;
    }

    private class Table {

        Character section;
        int       tableNum;
    }
}
