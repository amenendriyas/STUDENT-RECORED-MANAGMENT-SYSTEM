package NotePad;

import java.awt.*;

public class FormatHandler {
    GUI gui;
    Font arial, comicSansMS, timesNewRoman;
    String selectedFont;

    public FormatHandler(GUI gui){
        this.gui = gui;
    }

    public void wordWrap(){
        if(gui.wordWrapOn == false){
            gui.wordWrapOn = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.iWrap.setText("Word Wrap: on");
        }
        else { 
            gui.wordWrapOn = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.iWrap.setText("Word Wrap: off");
        }
    }
    
    public void createFont(int fontSize){
        arial = new Font("Arial", Font.PLAIN, fontSize);
        comicSansMS = new Font("Comic Sans MS", Font.PLAIN, fontSize);
        timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);
        setFont(selectedFont);
    }

    public void setFont(String font){
        selectedFont = font;
        
        int currentSize = (gui.textArea.getFont() != null) ? gui.textArea.getFont().getSize() : 16;
        if (arial == null) {
            createFont(currentSize);
        }

        switch(selectedFont){
            case "Arial":
                gui.textArea.setFont(arial);
                break;
            case "Comic Sans Ms":
                gui.textArea.setFont(comicSansMS);
                break;
            case "Times New Roman":
                gui.textArea.setFont(timesNewRoman);
                break;
        }
    }
}