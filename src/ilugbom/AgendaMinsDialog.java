package ilugbom;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class AgendaMinsDialog extends JDialog {
	  public AgendaMinsDialog( JFrame parent ) {
	    super( parent );
	    this.setTitle( "Roll Number Details..." );
	    this.setLocationRelativeTo( null );
	    this.setModal( true );
	    this.setResizable( false );
	    this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
	    this.getContentPane().setLayout( new BorderLayout() );

	    JTable shortcutKeysTable = getShortcutKeysTable();
	  //  this.add( shortcutKeysTable, BorderLayout.CENTER );
	    JScrollPane tpane=new JScrollPane(shortcutKeysTable);
	    tpane.setPreferredSize(new Dimension( 800, 200) );
	    this.add(tpane, BorderLayout.CENTER );
       
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setVisible( true );
	    
	  }
	  

	  private JTable getShortcutKeysTable() {
	    JTable shortcutKeysTable;

	    Object rowData[][] = { 
	    		              { "", "" }, 
	    		              { "", "" }, 
	    		              { "", "" },
	    		              { "", "" },
	    		              { "", "" }, 
	    		              { "", "" }, 
	    		              { "", "" },
	    		              { "", "" }
	    		              
	                         };
	    Object columnNames[] = { "AGENDA", "MINUTES" };

	    shortcutKeysTable = new JTable(rowData, columnNames);


	 //   this.add(scrollPane, BorderLayout.CENTER);
	  //  this.setSize(500, 550);
	    
	    return shortcutKeysTable;
	  }

	}