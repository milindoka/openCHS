package ilugbom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private Model model;
    private View view;
    private ActionListener actionListener,printMCMbuttonListner;
    
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
       // String path;
     
	     System.out.println(model.getJarPath()); ///set JAR path in model variable path;

                          
    }
    
    public void contol()
    {        
        actionListener = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent) {                  
                  linkBtnAndLabel();
              }
        };                
        view.getButton().addActionListener(actionListener);
        
        
        printMCMbuttonListner = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent)
              {                  
                  printMCM();
              }
        };                
        view.getMCMprintButton().addActionListener(printMCMbuttonListner);
        
        
        
    }
    
    private void linkBtnAndLabel()
    {
        model.incX();                
        view.setText(Integer.toString(model.getX()));
        
        
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