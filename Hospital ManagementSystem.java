import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Patient {
    private String name;
    private int age;
    private String gender;

    public Patient(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }
}

public class PatientRecordApp {
    private List<Patient> patientList;
    private DefaultListModel<String> listModel;
    private JList<String> patientJList;
    private JTextField nameTextField, ageTextField, genderTextField;

    public PatientRecordApp() {
        patientList = new ArrayList<>();
        listModel = new DefaultListModel<>();
        patientJList = new JList<>(listModel);

        JFrame frame = new JFrame("Patient Record Management System");
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageTextField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
        genderTextField = new JTextField();

        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(ageLabel);
        inputPanel.add(ageTextField);
        inputPanel.add(genderLabel);
        inputPanel.add(genderTextField);
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(patientJList), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void addPatient() {
        String name = nameTextField.getText();
        String ageStr = ageTextField.getText();
        String gender = genderTextField.getText();

        if (name.isEmpty() || ageStr.isEmpty() || gender.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            Patient patient = new Patient(name, age, gender);
            patientList.add(patient);
            listModel.addElement(patient.toString());

            // Clear input fields
            nameTextField.setText("");
            ageTextField.setText("");
            genderTextField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PatientRecordApp();
            }
        });
    }
}