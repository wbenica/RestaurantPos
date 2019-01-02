package employees;

public class Employee {

    int    id;
    String firstName;
    String lastName;
    String position;

    public Employee ( int id,
                      String firstName,
                      String lastName,
                      String position ) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;

    }

    public String getPosition ( ) {

        return position;
    }

    public int getId ( ) {

        return id;
    }

    public String getFirstName ( ) {

        return firstName;
    }

    public String getLastName ( ) {

        return lastName;
    }
}
