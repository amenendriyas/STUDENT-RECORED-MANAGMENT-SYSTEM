package NotePad;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileHandler {
    GUI gui;
    String fileName;
    String FileAddress;

    public FileHandler(GUI gui) {
        this.gui = gui;
    }

    public void newFile() {
        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        FileAddress = null;
    }

    public void Open() {
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            FileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        
        if (FileAddress != null && fileName != null) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(FileAddress + fileName));
                gui.textArea.setText("");

                String line = null;
                while ((line = br.readLine()) != null) {
                    gui.textArea.append(line + "\n");
                }
                br.close();

            } catch (Exception e) {
                System.out.println("File not opened");
            }
        }
    }

    public void Save() {
        if (fileName == null) {
            SaveAs();
        } else {
            try {
                FileWriter fw = new FileWriter(FileAddress + fileName);
                fw.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fw.close();
            } catch (Exception e) {
                System.out.println("Something wrong");
            }
        }
    }

    public void SaveAs() {
        FileDialog fd = new FileDialog(gui.window, "save", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            fileName = fd.getFile();
            FileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
            
            try {
                FileWriter fw = new FileWriter(FileAddress + fileName);
                fw.write(gui.textArea.getText());
                fw.close();
            } catch (Exception e) {
                System.out.println("Something wrong");
            }
        }
    }

    public void exit() {
        System.exit(0);
    }
}