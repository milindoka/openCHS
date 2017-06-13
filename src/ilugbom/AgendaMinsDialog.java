package ilugbom;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class AgendaMinsDialog extends JDialog 
{
	
	 private ActionListener btnoklistner,btncancellistner;
	
	  public AgendaMinsDialog( JFrame parent ) 
	  {
	    super(parent);
	    this.setTitle( "Add Agenda and Minutes Details..." );
	    this.setLocationRelativeTo( null );
	    this.setModal( true );
	    this.setResizable( false );
	    this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
	    this.getContentPane().setLayout( new BorderLayout() );
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
	    
	    
	    
	    btnpanel.add(okbutton);btnpanel.add(cancelbutton);
	    JTable amTable = getamTable();
	    JScrollPane tpane=new JScrollPane(amTable);
	    tpane.setPreferredSize(new Dimension( 800, 200) );
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
			    	for(int i=0;i<20;i++)
			        dtm.addRow(new Object[]{"", ""});	
				 table.setFont(LS16);
				 table.setRowHeight(20);
	    return table;
	  }

	  
	  private void onokclicked()
	  {
		  dispose();
		  
	  }
	  

	  private void oncancelclicked()
	  { 
		 dispose();
	
	  }

	  
	  
	}