
public class Course {
    private String code;
    private String name;
    private Faculty faculty;
    private Lecturer lecturer;
    private String startTime;
    private String endTime;
    private String day;
    private String venue;

    public Course(String code, String name, Faculty faculty, Lecturer lecturer, String startTime, String endTime, String day, String venue) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.lecturer = lecturer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.venue = venue;
    }
    public String getLecturer() {
        return "Lecturer for " + name + "\n" + lecturer.toString() + "\n";
    }

    public String getSchedule() {
        return "Schedule for " + name + "\n" + "Day: " + day + "\n" + "Time: " + startTime + " - " + endTime + "\n" + "Venue: " + venue + "\n";
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
    
}
