package luisrrleal.com.classicmodelstarea.models;

public class Client {
    private int customer_number;
    private String customer_name;
    private String contact_last_name;
    private String getContact_first_name;
    private String phone;
    private String address_line_1;
    private String address_line_2;
    private String city;
    private String state;
    private String postal_code;
    private String country;
    private int sales_rep_employee_number;
    private double credit_limit;

    public Client() {
    }

    public Client(int customer_number, String customer_name, String contact_last_name, String getContact_first_name, String phone, String address_line_1, String address_line_2, String city, String state, String postal_code, String country, int sales_rep_employee_number, double credit_limit) {
        this.customer_number = customer_number;
        this.customer_name = customer_name;
        this.contact_last_name = contact_last_name;
        this.getContact_first_name = getContact_first_name;
        this.phone = phone;
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.country = country;
        this.sales_rep_employee_number = sales_rep_employee_number;
        this.credit_limit = credit_limit;
    }

    public int getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(int customer_number) {
        this.customer_number = customer_number;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getContact_last_name() {
        return contact_last_name;
    }

    public void setContact_last_name(String contact_last_name) {
        this.contact_last_name = contact_last_name;
    }

    public String getContact_first_name() {
        return getContact_first_name;
    }

    public void setContact_first_name(String getContact_first_name) {
        this.getContact_first_name = getContact_first_name;
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

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSales_rep_employee_number() {
        return sales_rep_employee_number;
    }

    public void setSales_rep_employee_number(int sales_rep_employee_number) {
        this.sales_rep_employee_number = sales_rep_employee_number;
    }

    public double getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(double credit_limit) {
        this.credit_limit = credit_limit;
    }
}
