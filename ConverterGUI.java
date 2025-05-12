import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConverterGUI {
    private static File selectedFile;
    private static final String OUTPUT_DIR = "C:\\Users\\dell\\Desktop\\java_project-main\\output_files";

    public static void main(String[] args) {
        JFrame frame = new JFrame("File Converter");
        frame.setSize(600, 450); // زيادة حجم النافذة لاستيعاب الخيارات الجديدة
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel selectedLabel = new JLabel("No file selected");
        JButton chooseButton = new JButton("Browse File");
        chooseButton.setFont(new Font("Arial", Font.PLAIN, 13));

        chooseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                selectedLabel.setText("Selected: " + selectedFile.getName());
            }
        });

        topPanel.add(chooseButton);
        topPanel.add(selectedLabel);


        JPanel formatPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JCheckBox htmlBox = new JCheckBox("HTML");
        JCheckBox docxBox = new JCheckBox("Salt");
        JCheckBox txtBox = new JCheckBox("TXT");
        JCheckBox mdBox = new JCheckBox("MD");
        JCheckBox cbcBox = new JCheckBox("CBC");
        JCheckBox dacBox = new JCheckBox("DAC");

        Font checkboxFont = new Font("Segoe UI", Font.PLAIN, 14);
        htmlBox.setFont(checkboxFont);
        docxBox.setFont(checkboxFont);
        txtBox.setFont(checkboxFont);
        mdBox.setFont(checkboxFont);
        cbcBox.setFont(checkboxFont);
        dacBox.setFont(checkboxFont);

        formatPanel.add(htmlBox);
        formatPanel.add(docxBox);
        formatPanel.add(txtBox);
        formatPanel.add(mdBox);
        formatPanel.add(cbcBox);
        formatPanel.add(dacBox);


        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton convertButton = new JButton("Convert Selected");
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(convertButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formatPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);


        convertButton.addActionListener(e -> {
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(frame, "Please select a file first.");
                return;
            }

            ArrayList<String> selectedFormats = new ArrayList<>();
            if (htmlBox.isSelected()) selectedFormats.add("html");
            if (docxBox.isSelected()) selectedFormats.add("Salt");
            if (txtBox.isSelected()) selectedFormats.add("txt");
            if (mdBox.isSelected()) selectedFormats.add("md");
            if (cbcBox.isSelected()) selectedFormats.add("cbc");
            if (dacBox.isSelected()) selectedFormats.add("dac");

            if (selectedFormats.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please select at least one format.");
                return;
            }

            convertFile(selectedFormats);
        });
        JPanel openPanel = new JPanel(new GridLayout(2, 3, 10, 10)); // تغيير التنسيق لاستيعاب الأزرار الجديدة

        JButton openHtmlBtn = new JButton("Open HTML");
        JButton openDocxBtn = new JButton("Open Salt");
        JButton openTxtBtn = new JButton("Open TXT");
        JButton openMdBtn = new JButton("Open MD");
        JButton openCbcBtn = new JButton("Open CBC");
        JButton openDacBtn = new JButton("Open DAC");

        JButton[] openButtons = {openHtmlBtn, openDocxBtn, openTxtBtn, openMdBtn, openCbcBtn, openDacBtn};
        for (JButton btn : openButtons) {
            btn.setFont(new Font("Arial", Font.PLAIN, 12));
            openPanel.add(btn);
        }

        openHtmlBtn.addActionListener(e -> openConvertedFile("html"));
        openDocxBtn.addActionListener(e -> openConvertedFile("Salt"));
        openTxtBtn.addActionListener(e -> openConvertedFile("txt"));
        openMdBtn.addActionListener(e -> openConvertedFile("md"));
        openCbcBtn.addActionListener(e -> openConvertedFile("cbc"));
        openDacBtn.addActionListener(e -> openConvertedFile("dac"));


        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(openPanel, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }

    private static void convertFile(ArrayList<String> formats) {
        ConversionManager manager = new ConversionManager();
        manager.startConversion(selectedFile.getAbsolutePath(), formats.toArray(new String[0]));
        JOptionPane.showMessageDialog(null, "Conversion completed successfully!");
    }

    private static void openConvertedFile(String format) {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(null, "Please select a file first.");
            return;
        }

        String baseName = selectedFile.getName().replaceAll("\\.[^.]+$", "");
        File outputFile = new File(OUTPUT_DIR, baseName + "." + format);

        if (!outputFile.exists()) {
            JOptionPane.showMessageDialog(null, "The file does not exist:\n" + outputFile.getAbsolutePath());
            return;
        }

        try {
            Desktop.getDesktop().open(outputFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Unable to open file: " + outputFile.getAbsolutePath());
            ex.printStackTrace();
        }
    }
}