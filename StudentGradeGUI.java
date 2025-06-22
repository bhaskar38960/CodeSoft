import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGradeGUI extends JFrame {
    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JTextArea resultArea;
    private int numSubjects = 6; // You can modify this to ask dynamically

    public StudentGradeGUI() {
        setTitle("🎓 Student Grade Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(numSubjects + 1, 2, 10, 10));
        subjectFields = new JTextField[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            inputPanel.add(new JLabel("Marks for Subject " + (i + 1) + ":"));
            subjectFields[i] = new JTextField();
            inputPanel.add(subjectFields[i]);
        }

        calculateButton = new JButton("Calculate Grade");
        inputPanel.add(calculateButton);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateGrades();
            }
        });
    }

    private void calculateGrades() {
        int total = 0;
        boolean validInput = true;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numSubjects; i++) {
            String input = subjectFields[i].getText().trim();
            int marks;
            try {
                marks = Integer.parseInt(input);
                if (marks < 0 || marks > 100) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid marks (0–100) for all subjects.");
                validInput = false;
                break;
            }

            result.append("Subject ").append(i + 1)
                  .append(": ").append(marks)
                  .append(" - ").append(marks >= 40 ? "Pass" : "Fail").append("\n");
            total += marks;
        }

        if (validInput) {
            double average = (double) total / numSubjects;
            String grade;

            if (average >= 90)
                grade = "A+";
            else if (average >= 80)
                grade = "A";
            else if (average >= 70)
                grade = "B";
            else if (average >= 60)
                grade = "C";
            else if (average >= 50)
                grade = "D";
            else
                grade = "F";

            result.append("\nTotal Marks: ").append(total)
                  .append(" / ").append(numSubjects * 100);
            result.append("\nAverage: ").append(String.format("%.2f", average)).append("%");
            result.append("\nFinal Grade: ").append(grade);

            resultArea.setText(result.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGradeGUI().setVisible(true);
        });
    }
}
