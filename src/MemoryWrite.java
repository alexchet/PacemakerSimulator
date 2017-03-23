import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemoryWrite {	
	final static long memorySize = 1024;
	
	public MemoryWrite(HeartRate heartRate) {		
		
	}
	
	public static void continuousWrite(ArrayList<Point> ratesList, boolean clear){
		String fileName = "continuousStorage.csv";
		File file = new File(fileName);
		try {
			Date d = new Date();
			boolean overwirte = false;
			if (file.length() < memorySize) {
				overwirte = true;
			}
			
			if(ratesList!=null){
				FileWriter csvWrite = new FileWriter(fileName, overwirte);
				for(int i=0; i<ratesList.size(); i++){
					String f = ratesList.get(i).getY()+"";
					csvWrite.append(f);
					csvWrite.append(',');
				}
				SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy h:mm:ss a");
				String formattedDate = dataFormat.format(d);
				csvWrite.append(formattedDate);	
				csvWrite.append(',');			
				csvWrite.flush();
				csvWrite.close();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	public static void writeFixed(ArrayList<Point> ratesList, boolean clear){
		String fileName = "fixedStorage.csv";		
		File file = new File(fileName);
		try {
			Date d = new Date();
			if (clear) {
				FileWriter csvWrite = new FileWriter(fileName, false);
				SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy h:mm:ss a");
				String formattedDate = dataFormat.format(d);
				csvWrite.write(formattedDate);
				csvWrite.append(',');				
				csvWrite.flush();				
				csvWrite.close();
			}

			if (file.length() < memorySize) {
				if(ratesList!=null){
					FileWriter csvWrite = new FileWriter(fileName, true);
					for(int i=0; i<ratesList.size(); i++){
						String f = ratesList.get(i).getY()+"";
						csvWrite.append(f);
						csvWrite.append(',');
		        	
					}				
					csvWrite.flush();
					csvWrite.close();
				}	
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
