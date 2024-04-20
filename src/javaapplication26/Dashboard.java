
package deliveryvendor;
import javax.swing.*;
import javax.swing.JPanel;

public class Dashboard {
    
    public Dashboard(){
        
    }
    
    public void openPopup(JFrame Main,JPanel popupForm) {
        // Create a new JDialog for the pop-up
        JDialog popupDialog = new JDialog(Main, "Popup Form", true);
        popupDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popupDialog.setSize(645, 375);

        

        // Add the form panel to the dialog
        popupDialog.add(popupForm);

        // Display the pop-up
        popupDialog.setVisible(true);
        
        
    }
    
    
}
