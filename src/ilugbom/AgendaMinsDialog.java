package ilugbom;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class AgendaMinsDialog extends JDialog 
{
	String ac="╬";
	JTextField noticeDatefield=new JTextField(8);
    JTextField mitingDatefield=new JTextField(8);
    int MaxPoints=20;
    int MonthIndex=0;
    JTable amTable = getamTable();
	
     MCM mcm=new MCM();	
	
	
	 private ActionListener btnoklistner,btncancellistner,btnnextmonthlistner,btnpreviousmonthlistner;
	
	  public AgendaMinsDialog( JFrame parent ) 
	  {
	    super(parent);
	    
	    
	    String det = new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());
		mcm.CreateYearPack(det);
        mcm.Agenda[0]="Painting work proposed╬Electrical Work Propsed";
        mcm.Minute[0]="Painting work proposed╬Electrical Work Propsed";
		
        mcm.Agenda[1]="Water work proposed╬Electrical Work decided";
        mcm.Minute[1]="Water work decided╬Electrical Work decided";
        
        
        UpdateFromBase();
	    this.setTitle( "Add Agenda and Minutes Details..." );
	    this.setLocationRelativeTo( null );
	    this.setModal( true );
	    this.setResizable( false );
	    this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
	    this.getContentPane().setLayout( new BorderLayout() );
	    
	    JPanel toppanel=new JPanel(new BorderLayout());
	    
	    
	    
	    JButton yearbutton=new JButton("Year : 2016-17");
	    yearbutton.setMaximumSize(new Dimension(8,8));
	    toppanel.add(noticeDatefield,BorderLayout.WEST);
	    
	    
	    JPanel topcenterpanel=new JPanel();
	    topcenterpanel.add(yearbutton);
	    toppanel.add(topcenterpanel,BorderLayout.CENTER);
	    toppanel.add(mitingDatefield,BorderLayout.EAST);
	    JPanel btnpanel=new JPanel();
	    
	    
	    JButton okbutton=new JButton("OK");
	    btnoklistner = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent) 
              {                  
                  onokclicked();
              }
        };
	    okbutton.addActionListener(btnoklistner);
	    
	    
	    
	    JButton cancelbutton=new JButton("Cancel");
	    btncancellistner = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent) 
              {                  
                  oncancelclicked();
              }
        };
	    cancelbutton.addActionListener(btncancellistner);
	       
	    JButton nextmonthbutton=new JButton("Next Month");
	    btnnextmonthlistner = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent) 
              {                  
                  onnextmonthclicked();
              }
        };
	    nextmonthbutton.addActionListener(btnnextmonthlistner);
	    
	    JButton previousmonthbutton=new JButton("Prev Month");
	    btnpreviousmonthlistner = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent) 
              {                  
                  onpreviousmonthclicked();
              }
        };
	    previousmonthbutton.addActionListener(btnpreviousmonthlistner);
	    
	    
	    btnpanel.add(previousmonthbutton);
	    btnpanel.add(okbutton);btnpanel.add(cancelbutton);btnpanel.add(nextmonthbutton);
	    
	    
	   
	    JScrollPane tpane=new JScrollPane(amTable);
	    tpane.setPreferredSize(new Dimension( 800, 200) );
	    this.add(toppanel,BorderLayout.NORTH);
	    this.add(tpane, BorderLayout.CENTER );
        this.add(btnpanel,BorderLayout.SOUTH);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setVisible( true );
	
	    
	    
	    
	    
	  }
	  

	  private JTable getamTable()
	  {
		  Font LS16=new Font("Liberation Serif", Font.PLAIN, 14);
			 Object rows[][] = {  { "", "" } };
			 Object cols[] = { "AGENDA", "MINUTES" };
			      DefaultTableModel model = new DefaultTableModel(rows, cols);
			    JTable table = new JTable(model);
			    	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			    	for(int i=0;i<MaxPoints;i++)
			        dtm.addRow(new Object[]{"", ""});	
				 table.setFont(LS16);
				 table.setRowHeight(20);
	    return table;
	  }

	  public void SetData(Object obj, JTable table, int row_index, int col_index)
	    {  table.getModel().setValueAt(obj,row_index,col_index);  }	
	  
	  private void onpreviousmonthclicked()
	  {
		if(MonthIndex>0) MonthIndex--;
		UpdateFromBase();
		  
	  }
	
	  
	  private void onokclicked()
	  {
		  dispose();
		  
	  }
	  

	  private void oncancelclicked()
	  { 
		 dispose();
	
	  }


	  
	  
	  private void onnextmonthclicked()
	  { 
			if(MonthIndex<11) MonthIndex++;
			UpdateFromBase();
	
	  }

	  
	  public void UpdateFromBase()
	    { 
		  
		  noticeDatefield.setText(mcm.NoticeDate[MonthIndex]);
	      mitingDatefield.setText(mcm.MeetingDate[MonthIndex]);
	  //    if(mcm.Agenda.length!=0) 
	    
	    String AgendaLines[],MinuteLines[];
	   
	    for(int i=0;i<MaxPoints;i++) { SetData("",amTable,i,0); SetData("",amTable,i,1);}
	    
	    AgendaLines=mcm.Agenda[MonthIndex].split("╬");
	    MinuteLines=mcm.Minute[MonthIndex].split("╬");
	    for(int i=0;i<AgendaLines.length;i++) SetData(AgendaLines[i],amTable,i,0); 
	    for(int i=0;i<MinuteLines.length;i++) SetData(MinuteLines[i],amTable,i,1);
	    //NoticeDateField.getDocument().addDocumentListener(documentListener);
	    //MeetingDateField.getDocument().addDocumentListener(documentListener);
	    
	    /*
	    table.getModel().addTableModelListener(new TableModelListener() {

	        public void tableChanged(TableModelEvent e) {        }
	      });
	      */
	    
	    
	    }
	  
	  
	  
	  
	  
	  
	  
	  
	}