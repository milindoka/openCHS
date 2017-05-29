package ilugbom;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class MCMprint implements Printable
{	
	public  ArrayList<String> fileArray = new ArrayList<String>();
	
	private String   MCMYEAR;
	 int linespacing=15;
	  Font LS15=new Font("Liberation Serif", Font.PLAIN, 15);
	  Font LS8=new Font("Liberation Serif", Font.PLAIN, 8);
	  private String[] NoticeDate;//=new String[12];
	  private String[] MeetingDate;//=new String[12];
	  private String[] Agenda;//=new String[12];
	  private String[] Minute;//=new String[12];
	  private String[] Signatures;//=new String[12];
	 
	
	  private String SocietyName="ADITYA PARK BLDG. NO. 4 CO-OP. HOUSING SOCIETY LTD";
	  private String SocietyRegNo="{ Regd. # MUM/W-T/HSG/T.C.-9309/ Dt. 01/10/2007 }";

	
	
	int TotalMarklists=0;
	int CurrentMarklist=0;
	String CollegeName1;
	String CollegeName2;
	String CollegeName3;
    String Clas;
	String Division;// = new String[TL];
	String Stream;// = new String[TL];
	String Subject;// = new String[TL];
	String Examination;// = new String[TL];
	String Examiner;
	String MaxMarks;// = new String[TL];
	String Date;// = new String[TL];
	String FilePath;//= new String[TL];
	String FileTitle;// = new String[TL];
	int TotalSets;
	
	boolean bModified;
	boolean bSaveDirect;
	boolean bFileLoaded;
	String FirstRoll;
	String LastRoll;
	String[] Set = new String[100];
	String[] Key = new String[100];
	
	  public void show(String msg) ///for debugging
		{JOptionPane.showMessageDialog(null, msg);}
	  
	  
	  public void PrintFormattedPara(String para,int topleftx,int toplefty,int width,Font font,Graphics gr)
		{  	FontMetrics metrics = gr.getFontMetrics(font);
		    int defaultspacing=3;
		  	String[] result = para.split("\\s");
		  	int totalwords=result.length;
		  	//int NonSpaceExtent=0;
		  	ArrayList<String> line = new ArrayList<String>();
		 // 	mylist.add(mystring); //this adds an element to the list.
		    int extent=0;
		    String templine="";
		  	for (int x=0; x<totalwords; x++)
		  	    { extent = extent + metrics.stringWidth(result[x]);
		  	      if(extent>width) { line.add(templine); templine=result[x] + ' '; extent=0; continue;}
		  	      if((extent+defaultspacing)>width) { line.add(templine); templine=result[x]+' '; extent=0; continue;}
		  	      templine=templine+result[x]+' ';
		  	      extent=extent+defaultspacing;
		  	    }
		  	line.add(templine);
		  	int LineTotal=line.size();
		  	
		  	int linespacing=15;
		  	for(int i=0;i<LineTotal-1;i++)
		  	    { String tempstr=line.get(i);
		  	      tempstr.trim();
		  	      PrintPara(tempstr,topleftx,toplefty,width,font,gr);
		  	      toplefty=toplefty+linespacing;
		  	    }
		    gr.drawString(line.get(LineTotal-1),topleftx,toplefty);
		}  


public void PrintPara(String oneline,int topleftx,int toplefty,int width,Font font,Graphics gr)
{   
    //int defaultspacing=14;   ////default word spacing
	FontMetrics metrics = gr.getFontMetrics(font);
  	String[] result = oneline.split("\\s");
  	int totalwords=result.length;
  	int NonSpaceExtent=0;
   for (int x=0; x<totalwords; x++)
          NonSpaceExtent=NonSpaceExtent+ metrics.stringWidth(result[x]);
    int spaceleft=width-NonSpaceExtent;
    int averagegap=spaceleft/(totalwords-1);
    int remainingpixels=spaceleft%(totalwords-1);
    
    
    for (int x=0; x<totalwords; x++)
   	   { gr.drawString(result[x],topleftx,toplefty);
   	     topleftx=topleftx+metrics.stringWidth(result[x])+averagegap;
   	     if(remainingpixels>0) { topleftx++; remainingpixels--; }
   	   }
    
}

	  
	  
	  
	  public void PrintHeader(Graphics gr,int month)
		{ int x=70,y=70;
		  gr.setFont(LS15);
		  Centre(SocietyName,470,x,y,gr);
		  gr.setFont(LS8);
		  y=y+linespacing;
		  Centre(SocietyRegNo,470,x,y,gr);
		  y=y+linespacing;
		  gr.drawString("YEAR : "+MCMYEAR, x+424, y-4);
		  gr.drawLine(x,y,x+470,y);
		  y=y+linespacing-4;
		  String tt=String.format("Ref. No : %02d-MCMND-%s",month+1,NoticeDate[month]);
		  gr.drawString(tt,x,y);
		  gr.drawString("Date : "+NoticeDate[month],x+421,y);
		  y=y+linespacing;y=y+linespacing;
		  Centre("NOTICE OF THE MANAGING COMMITTEE MEETING",470,x,y,gr);
		  y=y+linespacing;y=y+linespacing;

	String para="It is to inform to all the members of the "; 
	para+=SocietyName;
	para+=" that the Managing Committee Meeting of the ";
	para+=SocietyName;
	para+=" shall be held on ";
	para+=MeetingDate[month];
	para+=" at ";
	para+="9.00 p.m.";                  ///MeetingTime[1];
	para+=" ";
	para+="at Society premises/office, to transact the business as per following agenda.";
		   
	PrintFormattedPara(para,x,y,470,LS8,gr);
		   y=y+linespacing;y=y+linespacing;y=y+linespacing;y=y+linespacing;
		 //  gr.drawString"(iterator, x, y)All are requested to attend the meeting punctually.
		   Centre("AGENDA",470,x,y,gr);
		   y=y+linespacing;y=y+linespacing;
		    String line="1]  To read and confirm the minutes of the last Managing Committee Meeting held on ";
		   if(month>0) line+=MeetingDate[month-1]; else line+=" ____/____/___";
		   line+=".";
		   gr.drawString(line,x,y);
		   y=y+linespacing;
		  
		   gr.drawString("2]  To approve the expenses incurred till last managing committee meeting.", x, y);
		   
		   y=y+linespacing;y=y+linespacing;
		   Centre("-----x-----",470,x,y,gr);
		   y=y+linespacing;y=y+linespacing;
		   Centre("MINUTES OF THE MANAGING COMMITTEE MEETING",470,x,y,gr);
		   y=y+linespacing;y=y+linespacing;
		   line="1]  Minutes of the last Managing Committee Meeting held on ";
		   if(month>0) line+=MeetingDate[month-1]; else line+=" ____/___/___";
		   line+="  were read out, approved & confirmed by all without any changes";
		   gr.drawString(line,x,y);
		   y=y+linespacing;
		   gr.drawString("2]  The expenses incurred in the last month have been discussed and the same have been approved.",x,y);
		   
		   PrintSignatories(month,gr);
		}

	    
	  private void PrintSignatories(int month,Graphics gr)
		{
		  String[] Part = Signatures[month].split("#");
		  gr.drawString(Part[1],70, 700);
		  gr.drawString("(Secretary)",70,715);
		  gr.drawString("Other Members :",70,730);
		}
	  
	
    public void printSelected(ArrayList <String> Array)
    {
    	 for(int x = 0; x <Array.size() ; x++) show(Array.get(x));
    }
	
	int listfiles(String path)
    { 
  	  FilenameFilter mrkFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".mrk")) {
					return true;
				} else {
					return false;
				}
			}
		};
  	  
  	  fileArray.removeAll(fileArray);
  	  File folder = new File(path);
  	  File[] listOfFiles = folder.listFiles(mrkFilter);
  	      for (int i = 0; i < listOfFiles.length; i++) {
  	        if (listOfFiles[i].isFile()) 
  	        {  fileArray.add(listOfFiles[i].getAbsolutePath());
  	         } 
  	      }
  	    TotalMarklists=fileArray.size();
  	    return TotalMarklists;
    }
 	
	
	
	///// Here the whole  JAVA Printing Mechanism Starts 
	/////  Note 'implements Printable above', It includes the Print Mechanism to our Program
	/////
	  public void PrintAllMarklists(String printername)
              {
		  
		  TotalMarklists=fileArray.size();
		  PrintService ps = findPrintService(printername);
		  if(ps==null) ps = PrintServiceLookup.lookupDefaultPrintService(); 
		  if(ps==null) return;
		   
		   if(TotalMarklists==0)
		    CurrentMarklist=0;
		   	  
	        	  
		  
		  
	         PrinterJob job = PrinterJob.getPrinterJob();
	         try {
				job.setPrintService(ps);
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         job.setPrintable(this);
	        
	         ////Widening the print AREA.
	         //Java Keeps preset Margins of about 1 inch on all corners
                 //Top Left Corner is  (0,0), then width and height
	         HashPrintRequestAttributeSet pattribs=new HashPrintRequestAttributeSet();
	         pattribs.add(new MediaPrintableArea(0, 0, 210, 297, MediaPrintableArea.MM));
	         // 210 x 297  A4 size paper in Millimiters.
	         
	  
	         boolean ok = true; ///job.printDialog();
	         if (ok) 
	             try {
	                  job.print(pattribs);
	             } catch (PrinterException ex) {}
	             
	          
              
	         }
	
	  public void PrintHeader(Graphics pg,int px,int py)
	  {Centre(CollegeName1,450,px,py,pg);
	  pg.drawString("Class & Div : "+Clas+" "+Division,px, py+17);
	  pg.drawString("Subject :"+Subject,px+200, py+17);
	  pg.drawString("Examiner : "+(Examiner.length()>9 ? Examiner.substring(0, 8) : Examiner),px+400,py+17);
      pg.drawString("Examination : "+Examination,px, py+34);
      pg.drawString("Total Marks : "+MaxMarks,px+200,py+34);
      pg.drawString("Date : "+Date,px+400, py+34);
		  
	  }
	  
	  
	public int print(Graphics pg, PageFormat pf, int pageno)
			throws PrinterException
	{
		
		
		 if (pageno>4)             // We have only one page, and 'page no' is zero-based
		    {  return NO_SUCH_PAGE;  // After NO_SUCH_PAGE, printer will stop printing.
	        }
		 
		 
		 Font MyFont = new Font("Liberation Serif", Font.PLAIN,10);
		 pg.setFont(MyFont); 
         
		 
		 pg.drawString("Hello",200, 200);
         
		return PAGE_EXISTS;
	 }
///// Here the JAVA Printing Mechanism Ends
/////////////////////////////////////////////////
///////////////////////////////////////////////
	
	
	/////Supporting Functions for Prining //////////////////
	
	
	public PrintService findPrintService(String printerName)
    {
        for (PrintService service : PrinterJob.lookupPrintServices())
        {
            if (service.getName().equalsIgnoreCase(printerName))
                return service;
        }

        return null;
    }

	
	
	
	
	 private void Centre(String s, int width, int XPos, int YPos,Graphics g2d){  
	        int stringLen = (int)  g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();  
	        int start = width/2 - stringLen/2;  
	        g2d.drawString(s, start + XPos, YPos);  
	 }  
	    
	    private void RightJustify(String s, int xPos, int YPos,Graphics g2d){  
	        int stringLen = (int)  g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();  
	        g2d.drawString(s, xPos-stringLen, YPos);  
	 }  
	    
	    private int atoi(String number)
	    {  String input = number.replaceAll("\\D+","");
	    
	    	if(input.length()==0) return 0;
	    	  try {
	    	    return Integer.parseInt(input.replaceAll("[^0-9.]",""));
	    	  } catch (NumberFormatException e)
	    	  {
	    	    return 0;
	    	  }
	    }

		
	public void PrintGrid(int px,int py, int height, int width,int rows,int cols,Graphics gr)
	 {  
		
		
		
		 
	     int cc=2;  
	     
	    
	    // String pt=String.format("Page Total : %d", pagetotal);
	    // gr.drawString(pt,px, py+680);
	     RightJustify("Examiner's Sign : ____________",px+492, py+680,gr);
	     
	 }
	    
	
/*
	
	public  void ReadFromDisk(String fnem)
    {   strArray.removeAll(strArray);
    	BufferedReader reader=null;
		try {
			reader = new BufferedReader(new FileReader(fnem));
		} catch (FileNotFoundException e1) 
		{
		
			e1.printStackTrace();
		}
 				
		String line = null;
    	try { while ((line = reader.readLine()) != null) 
			{
			 
			 strArray.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
     }
	
	
	
	
	
	
	
	
	*/
	
	
	
}