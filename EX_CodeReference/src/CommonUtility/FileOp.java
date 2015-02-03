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
	
	public static void write2Log(FileWriter logfileWriter, String message) throws IOException{
		System.out.print(message);
		logfileWriter.write(message);
		logfileWriter.flush();
	}
	// FileWriter logfileWriter= new FileWriter(filePath ,true);//write to debugLog.txt
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public static void test(){
		String logPath = "./Output/log.txt";
		deleteFile(logPath);
	}
}
