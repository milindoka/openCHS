package ilugbom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MCM
{
	
	private String   MCMYEAR="16-17";
	private String Signatures="Mr. Pramod Dhyani#Mr. Jayesh Kelkar#Mr. Dharmesh Panchal#Mr. Kamlesh Patil#Mr. Milind Oka";

public class month
{  String NoticeDate,MeetingDate,Agenda,Minute,Signatures,LastMeetingDate;

};
	
month Month[]=new month[12];


public void CreateYearPack(String det)
{  	
	String yy=det.substring(6);
	String zz=IncrementDate(det,0,0,-1);
    MCMYEAR=zz.substring(6)+"-"+yy;
    Month[0].NoticeDate="04/04/"+zz.substring(6);
    for(int i=1;i<12;i++)  Month[i].NoticeDate=IncrementDate(Month[i-1].NoticeDate,0,1,0);
    for(int i=0;i<12;i++)  Month[i].MeetingDate=IncrementDate(Month[i].NoticeDate,8,0,0);
    Month[0].LastMeetingDate="12/03/"+zz.substring(6);
    for(int i=1;i<12;i++)  Month[i].LastMeetingDate=Month[i-1].MeetingDate;
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
