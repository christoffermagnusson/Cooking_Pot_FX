package log;

import java.time.LocalDateTime;

public class Log {

	public static void write(String msg){
		 System.out.println(String.format("%s: %s", LocalDateTime.now().toString(),msg));
	}

}
