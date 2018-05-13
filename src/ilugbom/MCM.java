package ilugbom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MCM
{
	
	private String   MCMYEAR="16-17";
	private String Signatures="Mr. Jayesh Kelkar#Mr. Vasudeo Gavande#Mr#Mr. Kamlesh Patil#Mr. Milind Oka";
	public String[] NoticeDate=new String[12];
	public  String[] MeetingDate=new String[12];
	public String[] LastMeetingDate= new String[12];
	public String[] Agenda=new String[12];
	public String[] Minute=new String[12];
	


public void CreateYearPack(String det)
{  	
	String yy=det.substring(6);
	String zz=IncrementDate(det,0,0,-1);
    MCMYEAR=zz.substring(6)+"-"+yy;
    System.out.println(MCMYEAR);
    System.out.println("04/04/"+zz.substring(6));
    
    NoticeDate[0]="04/04/"+zz.substring(6);
    for(int i=1;i<12;i++)  NoticeDate[i]=IncrementDate(NoticeDate[i-1],0,1,0);
    for(int i=0;i<12;i++)  MeetingDate[i]=IncrementDate(NoticeDate[i],8,0,0);
    
    LastMeetingDate[0]="12/03/"+zz.substring(6);
    for(int i=1;i<12;i++) LastMeetingDate[i]=MeetingDate[i-1];
 
}


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








}
