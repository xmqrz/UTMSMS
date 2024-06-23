import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Undergraduate extends Student {
    private String major;

    public Undergraduate(String filePath) {
        super(filePath);
        loadAdditionalInfo(filePath);
    }

    private void loadAdditionalInfo(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < 8; i++) {
                br.readLine(); // Skip first 8 lines
            }
            this.major = br.readLine(); // Read the major
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String getStudentInfo() {
        return "Undergraduate Student at UTM\n" + super.getStudentInfo() + "Major: " + major + "\n";
    }

    @Override
    public void saveToFile(String filePath) {
        super.saveToFile(filePath);
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(major + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
