import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Registration {
    private static final List<Course> courseOfferingsList = new ArrayList<>();

    static {
        // Adding course offerings
        Faculty computing = new Faculty("Faculty of Computing");
        Faculty engineering = new Faculty("Faculty of Engineering");
        Faculty arts = new Faculty("Faculty of Arts");

        Lecturer drJohnDoe = new Lecturer("Dr. John Doe", "johndoe@university.edu", "Room 101");
        Lecturer drJaneDoe = new Lecturer("Dr. Jane Doe", "janedoe@university.edu", "Room 102");
        Lecturer drJamesDoe = new Lecturer("Dr. James Doe", "jamesdoe@university.edu", "Room 103");
        Lecturer drJanetDoe = new Lecturer("Dr. Janet Doe", "janetdoe@university.edu", "Room 104");
        Lecturer drJackDoe = new Lecturer("Dr. Jack Doe", "jackdoe@university.edu", "Room 105");
        Lecturer drJillDoe = new Lecturer("Dr. Jill Doe", "jilldoe@university.edu", "Room 106");

        courseOfferingsList.add(new Course("SECPROG", "Software Engineering", computing, drJohnDoe, "8:00 AM", "10:00 AM", "Monday", "BK1, N28a"));
        courseOfferingsList.add(new Course("SECDES", "Software Design", computing, drJaneDoe, "10:00 AM", "12:00 PM", "Tuesday", "MPK8, N28"));
        courseOfferingsList.add(new Course("SECENG", "Software Development", computing, drJamesDoe, "2:00 PM", "4:00 PM", "Wednesday", "BK4, N28"));
        courseOfferingsList.add(new Course("SECDAT", "Software Data Management", computing, drJanetDoe, "4:00 PM", "6:00 PM", "Thursday", "MP1, N28a"));
        courseOfferingsList.add(new Course("SECJAV", "Software Java Programming", computing, drJackDoe, "8:00 AM", "10:00 AM", "Friday", "BK3, N28"));
        courseOfferingsList.add(new Course("SECTEL", "Software Telecommunications", computing, drJillDoe, "10:00 AM", "12:00 PM", "Saturday", "MPK1, N28"));
    }

    public static void addCourseOffering(Course course) {
        courseOfferingsList.add(course);
    }

    public static void removeCourseOffering(Course course) {
        courseOfferingsList.remove(course);
    }

    public static void viewSchedule(User user) {
        StringBuilder schedule = new StringBuilder();
        for (Course course : user.getEnrolledCourses()) {
            schedule.append(course.getSchedule()).append("\n");
        }
        JOptionPane.showMessageDialog(null, schedule.toString(), "Course's Schedule", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void viewLecturer(User user) {
        StringBuilder lecturer = new StringBuilder();
        for (Course course : user.getEnrolledCourses()) {
            lecturer.append(course.getLecturer()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lecturer.toString(), "Course's Lecturer", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void registerCourse(User user, Course course) {
        if (!user.getEnrolledCourses().contains(course)) {
            user.enrollCourse(course);
            JOptionPane.showMessageDialog(null, "Course " + course.getName() + " registered successfully!", "Course Registration", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "You are already enrolled in this course.", "Course Registration", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void unregisterCourse(User user, Course course) {
        if (user.getEnrolledCourses().contains(course)) {
            user.unenrollCourse(course);
            JOptionPane.showMessageDialog(null, "Course " + course.getName() + " unregistered successfully!", "Course Unregistration", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "You are not enrolled in this course.", "Course Unregistration", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static Course findCourseByCode(String courseCode) {
        for (Course course : courseOfferingsList) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null; // Return null if course not found
    }

    public static Course findEnrolledCourseByCode(User user, String courseCode) {
        for (Course course : user.getEnrolledCourses()) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null; // Return null if enrolled course not found
    }

    public static void main(User user, boolean returnToMain) {
        while (true) {
            // Show the welcome message and handle closure
            String welcomeMessage = "Welcome to the Registration Module, " + user.getStudent() + "\n" +
                    "You are currently enrolled in the following courses:\n";
            for (Course course : user.getEnrolledCourses()) {
                welcomeMessage += course.getName() + "\n";
            }
            int welcomeResult = JOptionPane.showOptionDialog(null, welcomeMessage, "Course Registration",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if (welcomeResult == JOptionPane.CLOSED_OPTION) {
                // Handle case when the user closes the welcome dialog
                JOptionPane.showMessageDialog(null, "Exiting application.", "UTM Student Management System", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Proceed to show the main menu options
            Object[] options = {"Add Course", "Remove Course", "Course's Schedule", "Course's Lecturer", "Back to Main Menu"};
            int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Course Registration",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == JOptionPane.CLOSED_OPTION) {
                // Handle case when the user closes the options dialog
                JOptionPane.showMessageDialog(null, "Exiting application.", "UTM Student Management System", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            switch (choice) {
                case 0: // Add Course
                    StringBuilder addMenu = new StringBuilder("Select a course to register:\n");
                    for (Course course : courseOfferingsList) {
                        addMenu.append(course.getCode()).append(" - ").append(course.getName()).append("\n");
                    }
                    String addCourseCode = JOptionPane.showInputDialog(null, addMenu.toString(), "Course Registration", JOptionPane.PLAIN_MESSAGE);

                    if (addCourseCode == null) {
                        JOptionPane.showMessageDialog(null, "Operation canceled.", "Course Registration", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }

                    Course addCourse = findCourseByCode(addCourseCode);
                    if (addCourse != null) {
                        registerCourse(user, addCourse);
                    } else {
                        JOptionPane.showMessageDialog(null, "Course not found.", "Course Registration", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 1: // Remove Course
                    StringBuilder removeMenu = new StringBuilder("Select a course to unregister:\n");
                    for (Course course : user.getEnrolledCourses()) {
                        removeMenu.append(course.getCode()).append(" - ").append(course.getName()).append("\n");
                    }
                    String removeCourseCode = JOptionPane.showInputDialog(null, removeMenu.toString(), "Course Registration", JOptionPane.PLAIN_MESSAGE);

                    if (removeCourseCode == null) {
                        JOptionPane.showMessageDialog(null, "Operation canceled.", "Course Registration", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }

                    Course removeCourse = findEnrolledCourseByCode(user, removeCourseCode);
                    if (removeCourse != null) {
                        unregisterCourse(user, removeCourse);
                    } else {
                        JOptionPane.showMessageDialog(null, "Course not found.", "Course Registration", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 2: // Course's Details
                    viewSchedule(user);
                    break;
                case 3: //Course's Schedule
                    viewLecturer(user);
                    break;
                case 4: // Back to Main Menu
                if (returnToMain) {
                    LoginSystem.showMainMenu(user);
                }
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.", "Course Registration", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
