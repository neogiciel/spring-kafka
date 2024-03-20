package neogiciel.web;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;


/*
 * Trace
 */
public class Trace {

	static String filename = "D:/dev/log.txt";
	//static String filename = ConfigProvider.getConfig().getValue("trace.file", String.class);
	static Logger logger = Logger.getLogger(Trace.class.getName());
	
	public static void log(int level,String strLog) {
    	try {
    		
    		//Recuperation de la date courant
    	    Date date = Calendar.getInstance().getTime();  
    	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
    	    String strDate = dateFormat.format(date);  
    		
    	    //Ecriture du fichier
    	    BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
    	    writer.append("["+strDate + "] --> "+ strLog + "\n");
    	    writer.close();
    	    
    	    logger.info("["+strDate + "] --> "+ strLog);
    	    
    	} catch (Exception e) {
            e.printStackTrace();
        }
        
	}
    
}