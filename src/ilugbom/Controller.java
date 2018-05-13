package ilugbom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;


public class Controller {

    private Model model;
    private View view;
    private AgendaMinsDialog amd;
    private ActionListener EditAgendaButtonListner,printMCMbuttonListner;
    
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
       // String path;
       // 
     
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
    	MCM mcm=new MCM();
    	 String det = new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());
		  mcm.CreateYearPack(det);
		  for(int i=0;i<12;i++) System.out.println(mcm.MeetingDate[i]);
    	
    	
    	
    	
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
        if(ps.PrinterName==null) ps.SelectPrinter();
        MCMprint mcmp = new MCMprint();
        mcmp.PrintAllMarklists(ps.PrinterName);
    }
    
    
    
    
    
    
}