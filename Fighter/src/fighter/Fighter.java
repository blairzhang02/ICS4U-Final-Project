/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fighter;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author blzha5634
 */
public class Fighter extends JFrame {

    public Fighter() {
        //create the User interface
        initUI();
    }

    private void initUI() {
        //set title of the JFrame
        setTitle("Fighter");
        //add a custom JPanel to draw on
        add(new DrawingSurface());
        //set the size of the window
        setSize(1000, 539);
       
        //tell the JFrame what to do when closed
        //this is important if our application has multiple windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                //instantiate the main window
                Fighter windowFrame = new Fighter();
                //make sure it can be seen
                windowFrame.setVisible(true);
            }
        });
    }

}
