import java.io.*;
import javax.swing.JOptionPane;

public abstract class Student {
    protected String matricNo;
    protected String name;
    protected Address address;
    protected String email;
    protected String faculty;
    protected String filePath;
    
    public Student(String filePath) {
        this.filePath = filePath;
        loadFromFile(filePath);
    }

    private void loadFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            this.matricNo = br.readLine();
            this.name = br.readLine();
            String street = br.readLine();
            String city = br.readLine();
            String state = br.readLine();
            String postalCode = br.readLine();
            this.address = new Address(street, city, state, postalCode);
            this.email = br.readLine();
            this.faculty = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(matricNo + "\n");
            fw.write(name + "\n");
            fw.write(address.getStreet() + "\n");
            fw.write(address.getCity() + "\n");
            fw.write(address.getState() + "\n");
            fw.write(address.getPostalCode() + "\n");
            fw.write(email + "\n");
            fw.write(faculty + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Getters for Student attributes
    public String getStud_id() {
        return matricNo;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFilePath() {
        return filePath;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    // Method to get all student information
    public String getStudentInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(matricNo).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Faculty: ").append(faculty).append("\n");
        return sb.toString();
    }

    public void viewResult() {
        // Placeholder: Extend to show actual results
        JOptionPane.showMessageDialog(null, "Results shown here.");
    }
}
