/**
 * 住所
 */
public class Address {
    private final String postalCode;
    private final String prefecture;
    private final String city;
    private final String address;

    public String getPostalCode() {
        return postalCode;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Address(String postalCode, String prefecture, String city, String address) {
        this.postalCode = postalCode;
        this.prefecture = prefecture;
        this.city = city;
        this.address = address;
    }
}
