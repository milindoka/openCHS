package ilugbom;


import javax.swing.*;
import java.awt.BorderLayout;

public class View {
      
    private JFrame frame;
    //private JLabel label;
    private JButton button;
    private JButton MCMprintButton;
    private JButton MCMEditButton;
    
    public View(String text){
        frame = new JFrame("View");                                    
        frame.getContentPane().setLayout(new BorderLayout());                                          
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
        frame.setSize(200,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
     //   label = new JLabel(text);
      //  frame.getContentPane().add(label, BorderLayout.CENTER);
        
        button = new JButton("Button");        
        frame.getContentPane().add(button, BorderLayout.SOUTH);    
        
        MCMprintButton = new JButton("MCM Print");        
        frame.getContentPane().add(MCMprintButton, BorderLayout.NORTH);    
        
        MCMEditButton = new JButton("Edit Agenda-Min");        
        frame.getContentPane().add(MCMEditButton, BorderLayout.CENTER);    
        
        
    }
        
    public JButton getButton()
    {
        return button;
    }
    
    public JButton getMCMprintButton()
    {
        return MCMprintButton;
    }
    
    public JButton getMCMEditButton()
    {
        return MCMEditButton;
    }
    
    
}