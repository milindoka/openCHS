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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MCMprint implements Printable
{	
	public  ArrayList<String> fileArray = new ArrayList<String>();
	
	private String   MCMYEAR="16-17";
	 int linespacing=15;
	  String LastYearMeetingDate;
	  Font LS15=new Font("Liberation Serif", Font.PLAIN, 15);
	  Font LS8=new Font("Liberation Serif", Font.PLAIN, 8);
	  private String[] NoticeDate=new String[12];
	  private String[] MeetingDate=new String[12];
	  private String[] Agenda=new String[12];
	  private String[] Minute=new String[12];
	  private String[] Signatures=new String[12];
	 
	
	  private String SocietyName="ADITYA PARK BLDG. NO. 4 CO-OP. HOUSING SOCIETY LTD";
	  private String SocietyRegNo="{ Regd. # MUM/W-T/HSG/T.C.-9309/ Dt. 01/10/2007 }";

	
	
	  public void show(String msg) ///for debugging
		{JOptionPane.showMessageDialog(null, msg);}
	
	  
	  
	  public  String IncrementDate(String det,int NofDays,int NofMonths,int NofYears) //Format dd/MM/yy
		 {
			    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
				 Calendar c = Calendar.getInstance();
				 try {
					c.setTime(sdf.parse(det));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 c.add(Calendar.DATE, NofDays);  // number of days to add
				 c.add(Calendar.MONTH, NofMonths);  // number of days to add
				 c.add(Calendar.YEAR, NofYears);  // number of days to add
				 det = sdf.format(c.getTime());  // dt is now the new date
				 return det;
		}
		
	  
	  

		public void CreateYearPack(String det)
		{
			
			
			String yy=det.substring(6);
			
			String zz=IncrementDate(det,0,0,-1);
		//	show(zz);
			
		  MCMYEAR=zz.substring(6)+"-"+yy;
		
		  
		NoticeDate[0]="04/04/"+zz.substring(6);
	    for(int i=1;i<12;i++) NoticeDate[i]=IncrementDate(NoticeDate[i-1],0,1,0);
	    for(int i=0;i<12;i++) MeetingDate[i]=IncrementDate(NoticeDate[i],8,0,0);
	    for(int i=0;i<12;i++) Signatures[i]="Mr. Pramod Dhyani#Mr.Mr. Jayesh Kelkar#Mr. Dharmesh Panchal#Mr. Kamlesh Patil#Mr. Milind Oka";
	    
	  //  NoticeDateField.setText(NoticeDate[MonthIndex]);
	  //  MeetingDateField.setText(MeetingDate[MonthIndex]);
	    
	 //  GiveSrNos();
	    
	    LastYearMeetingDate="12/03/"+zz.substring(6);
	    for(int i=0;i<12;i++) 
	    	{ String LastMeetingDate="";
	    	 if(i>0) LastMeetingDate=MeetingDate[i-1];
	    	 Agenda[i]="##########";   	 Minute[i]="###########";
	    	}
			
	  //  UpdateFromBase();
		}

	  
	  
	  
	  
	  
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
		   if(month>0) line+=MeetingDate[month-1];  else line+=LastYearMeetingDate;
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
		   if(month>0) line+=MeetingDate[month-1]; else line+=LastYearMeetingDate;
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
	  
	
	
	///// Here the whole  JAVA Printing Mechanism Starts 
	/////  Note 'implements Printable above', It includes the Print Mechanism to our Program
	/////
	  public void PrintAllMarklists(String printername)
              {
		
		  String det = new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime());
		  CreateYearPack(det);
		  PrintService ps = findPrintService(printername);
		  if(ps==null) ps = PrintServiceLookup.lookupDefaultPrintService(); 
		  if(ps==null) return;
		  
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
	  
	  
	public int print(Graphics pg, PageFormat pf, int pageno)
			throws PrinterException
	{
		
		
		 if (pageno>11)             // We have only one page, and 'page no' is zero-based
		    {  return NO_SUCH_PAGE;  // After NO_SUCH_PAGE, printer will stop printing.
	        }
		 
		 
		 Font MyFont = new Font("Liberation Serif", Font.PLAIN,10);
		 pg.setFont(MyFont); 
         
		 PrintHeader(pg,pageno);
		 //pg.drawString("Hello",200, 200);
         
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