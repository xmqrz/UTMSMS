public class Lecturer {
    private String name;
    private String email;
    private String office;

    public Lecturer(String name, String email, String office) {
        this.name = name;
        this.email = email;
        this.office = office;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getOffice() {
        return office;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nEmail: " + email + "\nOffice: " + office;
    }
}
