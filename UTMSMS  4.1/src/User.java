import java.io.*;
import java.util.List;

public class User {
    private String username;
    private List<Course> enrolledCourses;
    private List<Grade> grades;
    private Student student;
    private String filePath;

    public User(String username, List<Course> enrolledCourses, List<Grade> grades, Student student, String filePath) {
        this.username = username;
        this.enrolledCourses = enrolledCourses;
        this.grades = grades;
        this.student = student;
        this.filePath = filePath;
        readCoursesFromFile();
    }

    public String getUsername() {
        return username;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            writeCoursesToFile(); // Write to file when a course is added
        }
    }

    public void unenrollCourse(Course course) {
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
            writeCoursesToFile(); // Write to file when a course is removed
        }
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public String getStudent() {
        return student.getName();
    }

    public Student getStu() {
        return student;
    }

    private void readCoursesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String courseCode;
            while ((courseCode = reader.readLine()) != null) {
                Course course = Registration.findCourseByCode(courseCode);
                if (course != null) {
                    enrolledCourses.add(course);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCoursesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Course course : enrolledCourses) {
                writer.write(course.getCode());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
