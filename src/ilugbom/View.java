package ilugbom;


import javax.swing.*;
import java.awt.BorderLayout;

public class View {
      
    private JFrame frame;
    private JLabel label;
    private JButton button;
    private JButton MCMprint;
    
    public View(String text){
        frame = new JFrame("View");                                    
        frame.getContentPane().setLayout(new BorderLayout());                                          
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
        frame.setSize(200,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        label = new JLabel(text);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        
        button = new JButton("Button");        
        frame.getContentPane().add(button, BorderLayout.SOUTH);    
        
        MCMprint = new JButton("MCM Print");        
        frame.getContentPane().add(MCMprint, BorderLayout.NORTH);    
        
        
        
    }
        
    public JButton getButton(){
        return button;
    }
    
    public void setText(String text){
        label.setText(text);
    }
    
    
}