import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Postgraduate extends Student {
    private String thesisTitle;

    public Postgraduate(String filePath) {
        super(filePath);
        loadAdditionalInfo(filePath);
    }

    private void loadAdditionalInfo(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < 8; i++) {
                br.readLine(); // Skip first 8 lines
            }
            this.thesisTitle = br.readLine(); // Read the thesis title
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    @Override
    public String getStudentInfo() {
        return "Postgraduate Student at UTM\n" + super.getStudentInfo() + "Thesis Title: " + thesisTitle + "\n";
    }

    @Override
    public void saveToFile(String filePath) {
        super.saveToFile(filePath);
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(thesisTitle + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
