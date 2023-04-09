package luisrrleal.com.classicmodelstarea.models;

public class Office {
    private String office_code;
    private String city;
    private String phone;
    private String address_line_1;
    private String address_line_2;
    private String state;
    private String country;
    private String postal_code;
    private String territory;

    public Office() {
    }

    public Office(String office_code, String city, String phone, String address_line_1, String getAddress_line_2, String state, String country, String postal_code, String territory) {
        this.office_code = office_code;
        this.city = city;
        this.phone = phone;
        this.address_line_1 = address_line_1;
        this.address_line_2 = getAddress_line_2;
        this.state = state;
        this.country = country;
        this.postal_code = postal_code;
        this.territory = territory;
    }

    public String getOffice_code() {
        return office_code;
    }

    public void setOffice_code(String office_code) {
        this.office_code = office_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress_line_1() {
        return address_line_1;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public void setAddress_line_2(String getAddress_line_2) {
        this.address_line_2 = getAddress_line_2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
}
