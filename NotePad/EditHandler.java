package NotePad;

public class EditHandler {
    GUI gui;
    
    public EditHandler(GUI gui){
        this.gui = gui;
    }
    
    public void undo(){
        if (gui.Um.canUndo()) {
            gui.Um.undo();
        }
    }

    public void redo(){
        if (gui.Um.canRedo()) {
            gui.Um.redo();
        }
    }
}