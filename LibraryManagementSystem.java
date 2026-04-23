import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class LibraryManagementSystem extends JFrame {
    private DefaultTableModel tableModel;
    private JTextField idField, titleField, authorField;
    private JTable bookTable;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        inputPanel.add(new JLabel("Book ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        
        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);
        
        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        inputPanel.add(authorField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Book");
        JButton removeBtn = new JButton("Remove Selected");
        JButton clearBtn = new JButton("Clear Form");

        addBtn.addActionListener(e -> addBook());
        removeBtn.addActionListener(e -> removeBook());
        clearBtn.addActionListener(e -> clearForm());

        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(clearBtn);

        inputPanel.add(buttonPanel);

        // Create table
        String[] columns = {"ID", "Title", "Author"};
        tableModel = new DefaultTableModel(columns, 0);
        bookTable = new JTable(tableModel);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Add sample data
        addSampleBooks();

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addBook() {
        if (titleField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title is required!");
            return;
        }
        Vector<String> row = new Vector<>();
        row.add(idField.getText());
        row.add(titleField.getText());
        row.add(authorField.getText());
        tableModel.addRow(row);
        clearForm();
        JOptionPane.showMessageDialog(this, "Book added successfully!");
    }

    private void removeBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a book to remove!");
            return;
        }
        tableModel.removeRow(selectedRow);
        JOptionPane.showMessageDialog(this, "Book removed!");
    }

    private void clearForm() {
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
    }

    private void addSampleBooks() {
        tableModel.addRow(new Object[]{"1", "Java Programming", "James Gosling"});
        tableModel.addRow(new Object[]{"2", "Data Structures", "CLRS"});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new LibraryManagementSystem().setVisible(true);
        });
    }
}
