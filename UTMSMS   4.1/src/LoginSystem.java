import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LoginSystem {
    private static final User[] users;
    private static final Finance[] finances;

    static {
        // Initialize Course objects
        Faculty computing = new Faculty("Faculty of Computing");
        Faculty engineering = new Faculty("Faculty of Engineering");
        Faculty arts = new Faculty("Faculty of Arts");

        // Last Semester Lecturer
        Lecturer drAhmadNajmi = new Lecturer("Dr. Ahmad Najmi bin Amerhaider Nuar", "ahmadnajmi@utm.my", "Room 101");
        Lecturer drMohdFoad = new Lecturer("Dr. Mohd Fo’ad bin Rohani", "mohdfoad@utm.my", "Room 102");
        Lecturer drMuhalim = new Lecturer("Dr. Muhalim bin Mohamed Amin", "muhalim@utm.my", "Room 103");
        Lecturer drNoorfaHaszlinna = new Lecturer("Dr. Noorfa Haszlinna binti Mustaffa", "noorfa@utm.my", "Room 104");
        Lecturer drNiesHuiWen = new Lecturer("Dr. Nies Hui Wen", "nieshuiwen@utm.my", "Room 105");
        
        // Last Semester Courses
        Course PSDA = new Course("SECJ2013", "Data Structure", computing, drAhmadNajmi, "", "", "", "");
        Course DATA = new Course("SECP2523", "Database", computing, drMohdFoad, "", "", "", "");
        Course SAD = new Course("SECP2613", "System Analysis and Design", computing, drMuhalim, "", "", "", "");
        Course SDT = new Course("SECP3723", "Data Development Technology", computing, drNoorfaHaszlinna, "", "", "", "");
        Course NC = new Course("SECR1213", "Network Communication", computing, drNiesHuiWen, "", "", "", "");


        // Initialize Student objects
        Postgraduate student1 = new Postgraduate("UTMSMS  4.1/Info1.txt");
        Undergraduate student2 = new Undergraduate("UTMSMS  4.1/Info2.txt");
        Undergraduate student3 = new Undergraduate("UTMSMS  4.1/Info3.txt");

        // Initialize Grade objects for each student
        List<Grade> gradesUser1 = new ArrayList<>();
        gradesUser1.add(new Grade(student1, PSDA, "A"));
        gradesUser1.add(new Grade(student1, DATA, "A"));
        gradesUser1.add(new Grade(student1, SAD, "B"));
        gradesUser1.add(new Grade(student1, SDT, "A"));
        gradesUser1.add(new Grade(student1, NC, "C"));

        List<Grade> gradesUser2 = new ArrayList<>();
        gradesUser2.add(new Grade(student2, PSDA, "A"));
        gradesUser2.add(new Grade(student2, DATA, "B"));
        gradesUser2.add(new Grade(student2, SAD, "B"));
        gradesUser2.add(new Grade(student2, SDT, "C"));
        gradesUser2.add(new Grade(student2, NC, "A"));

        List<Grade> gradesUser3 = new ArrayList<>();
        gradesUser3.add(new Grade(student3, PSDA, "C"));
        gradesUser3.add(new Grade(student3, DATA, "A"));
        gradesUser3.add(new Grade(student3, SAD, "C"));
        gradesUser3.add(new Grade(student3, SDT, "A"));
        gradesUser3.add(new Grade(student3, NC, "A"));

        // Initialize Course lists for each user
        List<Course> coursesUser1 = new ArrayList<>();
        List<Course> coursesUser2 = new ArrayList<>();
        List<Course> coursesUser3 = new ArrayList<>();

        // Initialize User array
        users = new User[]{
                new User("A22EC0193", coursesUser1, gradesUser1, student1, "UTMSMS  4.1/User1.txt"),
                new User("A22EC0042", coursesUser2, gradesUser2, student2, "UTMSMS  4.1/User2.txt"),
                new User("A22EC0059", coursesUser3, gradesUser3, student3, "UTMSMS  4.1/User3.txt")
        };

        // Initialize Finance array
        finances = new Finance[]{
                new Finance(student1, 800.00, 500.00, 300.00, 50.00),
                new Finance(student2, 750.00, 450.00, 290.00, 0.00),
                new Finance(student3, 744.00, 524.00, 304.00, 25.00)
        };
    }

    public static void main(String[] args) {
        String enteredUsername = JOptionPane.showInputDialog(null, "Enter Matric No.:", "UTM Student Management System", JOptionPane.PLAIN_MESSAGE);
        if (enteredUsername == null) { // User clicked Cancel
            JOptionPane.showMessageDialog(null, "Application Closed", "UTM Student Management System", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        for (User user : users) {
            if (user.getUsername().equals(enteredUsername)) {
                JOptionPane.showMessageDialog(null, "Login Successful", "UTM Student Management System", JOptionPane.INFORMATION_MESSAGE);
                showMainMenu(user);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Login Failed", "UTM Student Management System", JOptionPane.ERROR_MESSAGE);
    }

    public static void showMainMenu(User user) {
        Object[] options = {"Course Registration", "Result", "Finance", "Student Profile", "Logout"}; 
        int choice = JOptionPane.showOptionDialog(null, "Hello, " + user.getStu().getName() + "! \nSelect a module:", "Main Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                Registration.main(user, true); 
                break;
            case 1:
                Result.main(user, true); 
                break;
            case 2:
                Finance selectedFinance = findUserFinance(user); 
                Finance.showFinanceDetails(user, selectedFinance, true); 
                break;
            case 3:
                showStudentProfile(user);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Logout Successful", "UTM Student Management System", JOptionPane.INFORMATION_MESSAGE);
                main(new String[]{});
                break;
            default:
                JOptionPane.showMessageDialog(null, "No valid selection made, exiting application.", "UTM Student Management System", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
        }
    }

    private static Finance findUserFinance(User user) {
        for (Finance finance : finances) {
            if (finance.getStudent().equals(user.getStudent())) {
                return finance;
            }
        }
        return null; 
    }

    private static void showStudentProfile(User user) {
        Student student = user.getStu();
        Object[] options = {"Back", "Edit Profile"};
        int choice = JOptionPane.showOptionDialog(null, student.getStudentInfo(), "Student Profile", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

        if (choice == 1) {
            editStudentProfile(student, student.getFilePath(), user);
            showMainMenu(user); 
        } else {
            showMainMenu(user); 
        }
    }

    // Method to edit student profile
    private static void editStudentProfile(Student student, String filePath, User user) {
        String addressStreet = JOptionPane.showInputDialog(null, "Enter new street:", student.getAddress().getStreet());
        if (addressStreet == null) { 
            showStudentProfile(user); 
        }
        String addressCity = JOptionPane.showInputDialog(null, "Enter new city:", student.getAddress().getCity());
        if (addressCity == null) { 
            showStudentProfile(user); 
        }
        String addressState = JOptionPane.showInputDialog(null, "Enter new state:", student.getAddress().getState());
        if (addressState == null) { 
            showStudentProfile(user); 
        }
        String addressPostalCode = JOptionPane.showInputDialog(null, "Enter new postal code:", student.getAddress().getPostalCode());
        if (addressPostalCode == null) {
            showStudentProfile(user); 
        }
        String email = JOptionPane.showInputDialog(null, "Enter new email:", student.getEmail());
        if (email == null) { 
            showStudentProfile(user); 
        }
        String faculty = JOptionPane.showInputDialog(null, "Enter new faculty:", student.getFaculty());
        if (faculty == null) { 
            showStudentProfile(user);
        }
        Address newAddress = new Address(addressStreet, addressCity, addressState, addressPostalCode);
        student.setAddress(newAddress);
        student.setEmail(email);
        student.setFaculty(faculty);

        if (student instanceof Undergraduate) {
            String major = JOptionPane.showInputDialog(null, "Enter new major:", ((Undergraduate) student).getMajor());
            if (major == null) { 
                showStudentProfile(user); 
            }
            ((Undergraduate) student).setMajor(major);
        } else if (student instanceof Postgraduate) {
            String thesisTitle = JOptionPane.showInputDialog(null, "Enter new thesis title:", ((Postgraduate) student).getThesisTitle());
            if (thesisTitle == null) { 
                showStudentProfile(user); 
            }
            ((Postgraduate) student).setThesisTitle(thesisTitle);
        }

        student.saveToFile(filePath);
        JOptionPane.showMessageDialog(null, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
