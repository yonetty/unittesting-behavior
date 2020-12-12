/**
 * 顧客.
 */
public class Customer {

    private final String firstName;
    private final String lastName;
    private final Address address;
    private final CustomerRank rank;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public CustomerRank getRank() {
        return rank;
    }

    public Customer(String firstName, String lastName, Address address, CustomerRank rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.rank = rank;
    }
}
