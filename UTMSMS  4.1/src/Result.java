import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Result {
    private Student student;
    private List<Course> coursesTaken = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();

    public void addCourseGrade(Course course, Grade grade) {
        coursesTaken.add(course);
        grades.add(grade);
    }

    public double getGPA() {
        double sum = 0;
        for (Grade grade : grades) {
            sum += grade.getNumericGrade();
        }
        return sum / grades.size();
    }

    public List<Grade> viewResults() {
        return grades;
    }

    public static void main(User user, boolean returnToMain) {
        StringBuilder result = new StringBuilder();
        result.append("Results for ").append(user.getStudent()).append(":\n");

        List<Grade> gradeList = user.getGrades();
        for (Grade grade : gradeList) {
            String courseCode = grade.getCourse().getCode();
            String courseName = grade.getCourse().getName();
            String gradeValue = grade.getGrade();
            double numericGrade = grade.getNumericGrade();
            result.append(courseCode).append(" - ").append(courseName).append(": ").append(gradeValue).append(" (").append(numericGrade).append(")\n");
        }

        double gpa = calculateGPA(user);
        result.append("\nGPA: ").append(gpa);

        JOptionPane.showMessageDialog(null, result.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);

        if (returnToMain) {
            LoginSystem.showMainMenu(user);
        }
    }

    private static double calculateGPA(User user) {
        List<Grade> grades = user.getGrades();
        double totalGradePoints = 0;
        int totalCourses = grades.size();

        for (Grade grade : grades) {
            totalGradePoints += grade.getNumericGrade();
        }

        return totalGradePoints / totalCourses;
    }
}
