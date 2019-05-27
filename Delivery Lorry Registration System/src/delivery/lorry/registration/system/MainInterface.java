package delivery.lorry.registration.system;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MainInterface {

    ArrayList<Lorry> lorry;
    DefaultTableModel model = new DefaultTableModel();
    int selectedRow;
    JTable t = new JTable();
    
    public MainInterface() {
        lorry = new ArrayList<>();
        //declare elements to use
        JFrame f = new JFrame("Delivery Lorry Registration System");
        JLabel l = new JLabel("Register Number", SwingConstants.RIGHT);
        JLabel l1 = new JLabel("Model", SwingConstants.RIGHT);
        JLabel l2 = new JLabel("Size(Ton)", SwingConstants.RIGHT);
        JLabel l3 = new JLabel("Search:");
        JTextField tf = new JTextField();
        JTextField tf1 = new JTextField();
        JTextField tf2 = new JTextField();      
        JTextField tf3 = new JTextField();
        JButton b = new JButton("Add");
        JButton b1 = new JButton("Delete");
        JButton b2 = new JButton("Update");
        JScrollPane sp = new JScrollPane(t);
        
        //set table elements
        Object tableHeader[] = {"Register Number", "Model", "Size(Ton)"}; //set table header
        model.setColumnIdentifiers(tableHeader); //add header to model
        t.setModel(model); //set model to table
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(t.getModel()); //declare row sorter
        t.setRowSorter(rowSorter); //add row sorter to table
        t.setDefaultEditor(Object.class, null); //make table cant be edit
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //make the
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);                     //word in
        for(int i = 0; i  < tableHeader.length; i++){                             //table allign
            t.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);      //to CENTER
        }        
        
        //set elements bounds
        l.setBounds(20, 20, 100, 30);
        l1.setBounds(20, 60, 100, 30);
        l2.setBounds(20, 100, 100, 30);
        l3.setBounds(20, 180, 100, 30);
        tf.setBounds(150, 20, 420, 30);
        tf1.setBounds(150, 60, 420, 30);
        tf2.setBounds(150, 100, 420, 30);
        tf3.setBounds(80, 183, 490, 25);
        b.setBounds(150, 140, 100, 30);
        b1.setBounds(310, 140, 100, 30);
        b2.setBounds(470, 140, 100, 30);
        sp.setBounds(20, 220, 550, 520);

        //add function to button
        //add button
        b.addActionListener((ActionEvent e) -> {
            String regNum = tf.getText();
            String lorryModel = tf1.getText();
            String ton = tf2.getText();
            if(!regNum.equals("") && !lorryModel.equals("") && !ton.equals("")){
            lorry.add(new Lorry(regNum, lorryModel, ton));
            String data[] = {regNum, lorryModel, ton};
            model.addRow(data);            
            addData(); //call method
            JOptionPane.showMessageDialog(null, "Save Successful");
            tf.setText(null);
            tf1.setText(null);
            tf2.setText(null);
            } 
            else{
                JOptionPane.showMessageDialog(null, "Please fill in all the blanks");
            }
        });
        //delete button
        b1.addActionListener((ActionEvent e) -> {
            if(tf3.getText().equals("")){
                if (selectedRow >= 0) {
                    selectedRow = t.getSelectedRow();
                    model.removeRow(selectedRow);
                    deleteData(); //call method
                    JOptionPane.showMessageDialog(null, "Delete Successful");
                    tf.setText(null);
                    tf1.setText(null);
                    tf2.setText(null);           
                }
                else{
                    JOptionPane.showMessageDialog(null, "Delete Error");
                }
            }
            else{
                selectedRow = t.convertRowIndexToModel(selectedRow);
                model.removeRow(selectedRow);
                deleteData(); //call method
                JOptionPane.showMessageDialog(null, "Delete Successful");
                tf.setText(null);
                tf1.setText(null);
                tf2.setText(null);
            }
            selectedRow = -1;
        });
        //update button
        b2.addActionListener((ActionEvent e) -> {
            if(tf3.getText().equals("")){
                selectedRow = t.getSelectedRow();
                String line = tf.getText();
                String line1 = tf1.getText();
                String line2 = tf2.getText();
                if (selectedRow >= 0 && !line.equals("") && !line1.equals("") && !line2.equals("")) {
                    model.setValueAt(line, selectedRow, 0);
                    model.setValueAt(line1, selectedRow, 1);
                    model.setValueAt(line2, selectedRow, 2);
                    updateData(tf.getText(), tf1.getText(), tf2.getText()); //call method
                    JOptionPane.showMessageDialog(null, "Update Successful");
                    tf.setText(null);
                    tf1.setText(null);
                    tf2.setText(null);
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Update Failed");
                }
            }
            else{
                selectedRow = t.convertRowIndexToModel(selectedRow);
                String line = tf.getText();
                String line1 = tf1.getText();
                String line2 = tf2.getText();
                if (selectedRow >= 0 && !line.equals("") && !line1.equals("") && !line2.equals("")) {
                    model.setValueAt(line, selectedRow, 0);
                    model.setValueAt(line1, selectedRow, 1);
                    model.setValueAt(line2, selectedRow, 2);
                    updateData(tf.getText(), tf1.getText(), tf2.getText()); //call method
                    JOptionPane.showMessageDialog(null, "Update Successful");
                    tf.setText(null);
                    tf1.setText(null);
                    tf2.setText(null);
                } 
                else {
                    JOptionPane.showMessageDialog(null, "Update Failed");
                }
            }
            selectedRow = -1;
        });
        
        //display value to text field
        t.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                selectedRow = t.getSelectedRow();               
                tf.setText(t.getValueAt(selectedRow, 0).toString());
                tf1.setText(t.getValueAt(selectedRow, 1).toString());
                tf2.setText(t.getValueAt(selectedRow, 2).toString());
            }
        });
        
        //table sorter
        tf3.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent de) {
               String text = tf3.getText();
               if(text.trim().length() == 0){
                   rowSorter.setRowFilter(null);
               }
               else{
                   rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
               }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                 String text = tf3.getText();
               if(text.trim().length() == 0){
                   rowSorter.setRowFilter(null);
               }
               else{
                   rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
               }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });        
        
        //add elements to frame
        f.add(l);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(tf);
        f.add(tf1);
        f.add(tf2);
        f.add(tf3);
        f.add(b);
        f.add(b1);
        f.add(b2);
        f.add(sp);
        f.setSize(600, 800);
        f.setLayout(null);
        f.setResizable(false);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loadData(); //call method
    }
    //store data to file
    private void addData() { 
        try {
            File outFile = new File("Lorry.txt");
            FileWriter outFileStream = new FileWriter(outFile);
            try (PrintWriter outStream = new PrintWriter(outFileStream)) {
                for (int i = 0; i < lorry.size(); i++) {
                    outStream.println(lorry.get(i).getRegNum() + "," + lorry.get(i).getModel() + "," + lorry.get(i).getTon());
                }
            }
        } catch (IOException ex) {}
    }
    //load data from stored file
    private void loadData() {
        try {
            File inFile = new File("Lorry.txt");
            Scanner scan = new Scanner(inFile);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                String lorryArray[] = data.split(",");
                lorry.add(new Lorry(lorryArray[0], lorryArray[1], lorryArray[2]));
                model.addRow(lorryArray);
            }
        } catch (FileNotFoundException ex) {}
    }
    //delete data from file
    private void deleteData() {
        lorry.remove(selectedRow);
        addData(); //overwrite the deleted row
    }
    //update data to file
    private void updateData(String r, String m, String t) {
        String newData[] = {r, m, t};
        lorry.remove(selectedRow);
        lorry.add(selectedRow, new Lorry(newData[0], newData[1], newData[2]));
        addData(); //overwrite the updated data
    }
    
} 