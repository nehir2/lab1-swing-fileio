
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import javax.swing.JTextField;
import java.io.IOException;

public class ProjectFormPanel extends JPanel {
    private JTextField projectNameField;
    private JTextField teamLeaderField;

    private JComboBox<String> teamSizeComboBox;
    private JComboBox<String> projectTypeComboBox;

    private JTextField startDateField;

    private JButton saveButton;
    private JButton clearButton;


    public ProjectFormPanel() {


setLayout(new GridLayout(6,2,5,5));
setBackground(Color.gray);

JLabel lblNewLabel = new JLabel("Project Name:");
add(lblNewLabel);
projectNameField = new JTextField();
add(projectNameField);

JLabel lblNewLabel_2 = new JLabel("Team Leader:");
add(lblNewLabel_2);
teamLeaderField = new JTextField();
add(teamLeaderField);

JLabel lblTeamSize = new JLabel("Team Size:");
add(lblTeamSize);
String[] teamSizeOptions = {"1-3","4-5","7-10","10+"};
teamSizeComboBox = new JComboBox<>(teamSizeOptions);
add(teamSizeComboBox);

JLabel lblProjectType = new JLabel("Project:");
add(lblProjectType);
String[] projectTypeOptions = {"Web","Mobile","Desktop","API"};
projectTypeComboBox=new JComboBox<>(projectTypeOptions);
add(projectTypeComboBox);

JLabel lblStartDate = new JLabel("Start Date:");
add(lblStartDate);
startDateField=new JTextField();
add(startDateField);

saveButton=new JButton("Save.");
add(saveButton);

clearButton=new JButton("Clear.");
add(clearButton);


saveButton.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        String projectName=projectNameField.getText();
        String teamLeader=teamLeaderField.getText();
        String StartDate=startDateField.getText();

        if(projectName.isEmpty()||teamLeader.isEmpty()||StartDate.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill all the fields!");
        return;
        }


String teamSize=(String)teamSizeComboBox.getSelectedItem();
String projectType=(String)projectTypeComboBox.getSelectedItem();
String recordTime=  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


try(FileWriter writer = new FileWriter("projects.txt", true)) {
    writer.write("=== Project Entry ===\n");
    writer.write("Project Name : " + projectName + "\n");
    writer.write("Team Leader  : " + teamLeader + "\n");
    writer.write("Team Size    : " + teamSize + "\n");
    writer.write("Project Type : " + projectType + "\n");
    writer.write("Start Date   : " + StartDate + "\n");
    writer.write("Record Time  : " + recordTime + "\n");
    writer.write("======\n");

    JOptionPane.showMessageDialog(ProjectFormPanel.this, "Project saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
} catch (IOException ex) {
    JOptionPane.showMessageDialog(ProjectFormPanel.this, "Error writing to file.", "File Error", JOptionPane.ERROR_MESSAGE);
}


    }
});

clearButton.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        projectNameField.setText("");
        teamLeaderField.setText("");
        startDateField.setText("");
        teamSizeComboBox.setSelectedIndex(0);
        projectTypeComboBox.setSelectedIndex(0);
    }
});


    }
}