import javax.swing.JOptionPane;

public class Finance {
    private double totalPaid;
    private double tuitionFee;
    private double hostelFee;
    private double serviceFee;
    private double summonsFee;
    private Student student;

    public Finance(Student student, double tuitionFee, double hostelFee, double serviceFee, double summonsFee) {
        this.student = student;
        this.tuitionFee = tuitionFee;
        this.hostelFee = hostelFee;
        this.serviceFee = serviceFee;
        this.summonsFee = summonsFee;
        this.totalPaid = 0; 
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public double getHostelFee() {
        return hostelFee;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public double getSummonsFee() {
        return summonsFee;
    }

    public String getStudent() {
        return student.getName();
    }

    // Method to calculate total fee to pay
    public double getTotalToPay() {
        return tuitionFee + hostelFee + serviceFee + summonsFee;
    }

    // Method to display finance details
    public static void showFinanceDetails(User user, Finance finance, boolean returnToMain) {
        double tuitionFee = finance.getTuitionFee();
        double hostelFee = finance.getHostelFee();
        double serviceFee = finance.getServiceFee();
        double summonsFee = finance.getSummonsFee();
        double totalToPay = finance.getTotalToPay();

        StringBuilder message = new StringBuilder();
        message.append("Welcome to the Finance Module, ").append(user.getStudent()).append("!\n");
        message.append("\nDetails of Fees:\n");
        message.append("- Tuition Fee: $").append(tuitionFee).append("\n");
        message.append("- Hostel Fee: $").append(hostelFee).append("\n");
        message.append("- Service Fee: $").append(serviceFee).append("\n");
        message.append("- Summons Fee: $").append(summonsFee).append("\n");
        message.append("\nTotal Amount to Pay: $").append(totalToPay);

        JOptionPane.showMessageDialog(null, message.toString(), "Finance Details", JOptionPane.INFORMATION_MESSAGE);

        if (returnToMain) {
            LoginSystem.showMainMenu(user);
        }
    }
}
