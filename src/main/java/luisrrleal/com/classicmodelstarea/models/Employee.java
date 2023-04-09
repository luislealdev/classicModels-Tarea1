package luisrrleal.com.classicmodelstarea.models;

public class Employee {
    private int employee_number;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String office_code;
    private int reports_to;
    private String job_title;

    public Employee() {
    }

    public Employee(int employee_number, String lastName, String firstName, String extension, String email, String office_code, int reports_to, String job_title) {
        this.employee_number = employee_number;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.office_code = office_code;
        this.reports_to = reports_to;
        this.job_title = job_title;
    }

    public int getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(int employee_number) {
        this.employee_number = employee_number;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOffice_code() {
        return office_code;
    }

    public void setOffice_code(String office_code) {
        this.office_code = office_code;
    }

    public int getReports_to() {
        return reports_to;
    }

    public void setReports_to(int reports_to) {
        this.reports_to = reports_to;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }
}
