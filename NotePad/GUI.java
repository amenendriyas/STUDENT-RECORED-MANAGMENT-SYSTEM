package NotePad;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;
    JMenuBar MenuBar;
    JMenu MenuFile, MenuEdit, MenuFormat, MenuColor;
    JMenuItem iNew, iOpen, iSave, iSaveas, iExit;
    JMenuItem iUndo, iRedo;
    JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28, iFontSize30;
    JMenu menuFont, menuFontsize;
    JMenuItem iColor1, iColor2, iColor3;

    FileHandler file = new FileHandler(this);
    FormatHandler format = new FormatHandler(this);
    ColorHandler color = new ColorHandler(this);
    EditHandler edit = new EditHandler(this);

    UndoManager Um = new UndoManager();

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createfileMenu();
        CreateEditMenu();
        createFormatMenu();
        createColorMenu();
        
        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();
        color.changeColor("White");
        window.setVisible(true);
    }

    public void createWindow() {
        window = new JFrame("Æ's Notepad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea() {
        textArea = new JTextArea();
        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent e) {
                        Um.addEdit(e.getEdit());
                    }
                });
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    public void createMenuBar() {
        MenuBar = new JMenuBar();
        window.setJMenuBar(MenuBar);
        MenuFile = new JMenu("File");
        MenuBar.add(MenuFile);
        MenuEdit = new JMenu("Edit");
        MenuBar.add(MenuEdit);
        MenuFormat = new JMenu("Format");
        MenuBar.add(MenuFormat);
        MenuColor = new JMenu("Color");
        MenuBar.add(MenuColor);
    }

    public void createfileMenu() {
        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        MenuFile.add(iNew);
        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        MenuFile.add(iOpen);
        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        MenuFile.add(iSave);
        iSaveas = new JMenuItem("Save as");
        iSaveas.addActionListener(this);
        iSaveas.setActionCommand("SaveAs");
        MenuFile.add(iSaveas);
        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        MenuFile.add(iExit);
    }

    public void CreateEditMenu() {
        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        MenuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo"); 
        MenuEdit.add(iRedo);
    }

    public void createFormatMenu() {
        iWrap = new JMenuItem("Word Wrap: off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        MenuFormat.add(iWrap);
        
        menuFont = new JMenu("Font");
        MenuFormat.add(menuFont);
        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);
        iFontCSMS = new JMenuItem("Comic Sans Ms");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans Ms");
        menuFont.add(iFontCSMS);
        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);
        
        menuFontsize = new JMenu("Font size");
        MenuFormat.add(menuFontsize);
        
        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("Size8");
        menuFontsize.add(iFontSize8);

        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("Size12");
        menuFontsize.add(iFontSize12);

        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("Size16");
        menuFontsize.add(iFontSize16);

        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("Size20");
        menuFontsize.add(iFontSize20);

        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("Size24");
        menuFontsize.add(iFontSize24);

        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("Size28");
        menuFontsize.add(iFontSize28);

        iFontSize30 = new JMenuItem("30");
        iFontSize30.addActionListener(this);
        iFontSize30.setActionCommand("Size30");
        menuFontsize.add(iFontSize30);
    }

    public void createColorMenu() {
        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        MenuColor.add(iColor1);

        iColor2 = new JMenuItem("Black");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Black");
        MenuColor.add(iColor2);

        iColor3 = new JMenuItem("Blue");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Blue");
        MenuColor.add(iColor3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New": file.newFile(); break;
            case "Open": file.Open(); break;
            case "Save": file.Save(); break;
            case "SaveAs": file.SaveAs(); break;
            case "Exit": file.exit(); break;
            case "Undo": edit.undo(); break;
            case "Redo": edit.redo(); break; 
            case "Word Wrap": format.wordWrap(); break;
            case "Arial": format.setFont(command); break;
            case "Comic Sans Ms": format.setFont(command); break;
            case "Times New Roman": format.setFont(command); break;
            case "Size8": format.createFont(8); break;     
            case "Size12": format.createFont(12); break;
            case "Size16": format.createFont(16); break;
            case "Size20": format.createFont(20); break;
            case "Size24": format.createFont(24); break;
            case "Size28": format.createFont(28); break;
            case "Size30": format.createFont(30); break;
            case "White": color.changeColor(command); break;
            case "Black": color.changeColor(command); break;
            case "Blue": color.changeColor(command); break;
        }
    }
}