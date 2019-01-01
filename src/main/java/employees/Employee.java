package employees;

public class Employee {

    int    id;

    public Employee ( int id,
                      String firstName,
                      String lastName ) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId ( ) {

        return id;
    }

    public String getFirstName ( ) {

        return firstName;
    }

    String firstName;
    String lastName;

    public String getLastName ( ) {

        return lastName;
    }
}
