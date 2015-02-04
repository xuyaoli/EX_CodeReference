package CommonUtility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOp {

	
	/**
	 * 删除单个文件
	 * @param   sPath    被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
	   Boolean flag = false;
	   File file = new File(sPath);
	    // 路径为文件且不为空则进行删除
	   // System.out.println("isFile():"+file.isFile()+"\texists():"+file.exists());
	    if (file.isFile() && file.exists()) {
	        file.delete();
	        flag = true;
	    }
	    return flag;
	}
	
	public static void write2Log(boolean hasEnter,FileWriter logfileWriter, String message){
		String message1 = message;
		if(hasEnter){
			message1 = message + "\n";
		}
		System.out.print(message1);
		try {
			logfileWriter.write(message1);
			logfileWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void write2Log(FileWriter logfileWriter, String message){
		write2Log(false,logfileWriter,message);
	}
	
	public static void write2Logln(FileWriter logfileWriter, String message){
		write2Log(true,logfileWriter,message);
	}
	
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
