package ilugbom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class Controller {

    private Model model;
    private View view;
    private AgendaMinutesDialog amd;
    private ActionListener EditAgendaButtonListner,printMCMbuttonListner;
    
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
       // String path;
        amd = new AgendaMinutesDialog();
     
	     System.out.println(model.getJarPath()); ///set JAR path in model variable path;

                          
    }
    
    public void contol()
    {        
        EditAgendaButtonListner = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent) 
              {                  
                  ShowEditAgendaDialog();
              }
        };                
        view.getMCMEditButton().addActionListener(EditAgendaButtonListner);
        
        
        printMCMbuttonListner = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent)
              {                  
                  printMCM();
              }
        };                
        view.getMCMprintButton().addActionListener(printMCMbuttonListner);
        
        
        
    }
    
    private void ShowEditAgendaDialog()
    {
       System.out.println("test");
    //   String temp=amd.SetRollSubjects();
       JFrame frame=new JFrame();
    //   frame.setSize(600,300);
       AgendaMinsDialog amd=new AgendaMinsDialog(frame);
        
        
    }
    
    private void printMCM()
    {  // System.out.println("tt");
    	SetPrinter ps=new SetPrinter();
        ps.LoadPreferences();
        if(ps.PrinterName.length()==0) ps.SelectPrinter();
        MCMprint mcmp = new MCMprint();
        mcmp.PrintAllMarklists(ps.PrinterName);
    }
    
    
    
    
    
    
}