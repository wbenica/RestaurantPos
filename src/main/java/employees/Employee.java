package employees;

import java.util.Date;

public class Employee {

    private final int     id;
    private final String  firstName;
    private final String  lastName;
    private final Date    hireDate;
    private       String  position;
    private       Date    terminationDate;
    private       boolean isSalaried;
    private       double  payRate;

    public Employee ( int id,
                      String firstName,
                      String lastName,
                      String position,
                      Date hireDate,
                      Date terminationDate,
                      boolean isSalaried,
                      double payRate ) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.hireDate = hireDate;
        this.terminationDate = terminationDate;
        this.isSalaried = isSalaried;
        this.payRate = payRate;
    }

    public Date getHireDate ( ) {

        return hireDate;
    }

    public Date getTerminationDate ( ) {

        return terminationDate;
    }

    public boolean isSalaried ( ) {

        return isSalaried;
    }

    public double getPayRate ( ) {

        return payRate;
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
