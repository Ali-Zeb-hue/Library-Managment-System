import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibraryManagementUI extends JFrame implements ActionListener {
    private JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7;
    private JButton addButton, viewButton, editButton, deleteButton, clearButton, exitButton;
    private Library library = new Library();

    public LibraryManagementUI() {
        setTitle("Library Management System");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(10, 2));
        panel.add(new JLabel("Book ID")); textField1 = new JTextField(10); panel.add(textField1);
        panel.add(new JLabel("Book Title")); textField2 = new JTextField(20); panel.add(textField2);
        panel.add(new JLabel("Author")); textField3 = new JTextField(20); panel.add(textField3);
        panel.add(new JLabel("Publisher")); textField4 = new JTextField(20); panel.add(textField4);
        panel.add(new JLabel("Year of Publication")); textField5 = new JTextField(10); panel.add(textField5);
        panel.add(new JLabel("ISBN")); textField6 = new JTextField(20); panel.add(textField6);
        panel.add(new JLabel("Number of Copies")); textField7 = new JTextField(10); panel.add(textField7);

        addButton = new JButton("Add"); viewButton = new JButton("View");
        editButton = new JButton("Edit"); deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear"); exitButton = new JButton("Exit");

        addButton.addActionListener(this); viewButton.addActionListener(this);
        editButton.addActionListener(this); deleteButton.addActionListener(this);
        clearButton.addActionListener(this); exitButton.addActionListener(this);

        panel.add(addButton); panel.add(viewButton);
        panel.add(editButton); panel.add(deleteButton);
        panel.add(clearButton); panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            Book book = new Book(textField1.getText(), textField2.getText(), textField3.getText(),
                    textField4.getText(), textField5.getText(), textField6.getText(), textField7.getText());
            library.addBook(book);
            JOptionPane.showMessageDialog(this, "Book added successfully");
            clearFields();
        } else if (e.getSource() == viewButton) {
            String[] columns = {"Book ID", "Book Title", "Author", "Publisher", "Year", "ISBN", "Copies"};
            Object[][] data = new Object[library.getBooks().size()][7];

            for (int i = 0; i < library.getBooks().size(); i++) {
                data[i] = library.getBooks().get(i).getBookDetails();
            }

            JTable table = new JTable(data, columns);
            JScrollPane scrollPane = new JScrollPane(table);
            JFrame frame = new JFrame("View Books");
            frame.add(scrollPane);
            frame.setSize(800, 400);
            frame.setVisible(true);
        }
    }

    private void clearFields() {
        textField1.setText(""); textField2.setText(""); textField3.setText("");
        textField4.setText(""); textField5.setText(""); textField6.setText(""); textField7.setText("");
    }
}