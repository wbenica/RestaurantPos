package skus;

import employees.Employee;

public class Ticket {

    private static int currentTicket = 0;

    Table    table;
    int      ticketNum;
    Employee server;

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
