package CommonUtility;


//eclipse defined
import java.sql.*;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//user defined
import java.util.List;

public class mysqlUtility {
	//based on mysql basic utility function
	//static field

	private Connection conn = null;
	//public static 
	public Connection getConnection(String dbName){
		if(conn != null){
			return conn;
		}
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+dbName,"root","root");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	 
	public void Destructor(ResultSet rs,Statement stmt,Connection conn){
		try{
			if (rs != null) {
	            rs.close();
	        }
	        if (stmt != null) {
	            stmt.close();   
	        }
	        if (conn != null) {
	            conn.close();   
	        }
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static String getPrimaryKey(Connection conn,String dbName,String tbName){
		//the primary key attribute name is not union, is only one attribute name.
		String PKNames = "";
		try {
			 DatabaseMetaData dbmd = conn.getMetaData();
			 ResultSet primaryKey=dbmd.getPrimaryKeys(dbName,"PUBLIC",tbName);
			 while(primaryKey.next()){
				 PKNames = primaryKey.getString("COLUMN_NAME");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PKNames;
	}
	
	public static List<String> getANs(Connection conn,String tbName){
		List<String> ANs = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from " + tbName);
			ResultSetMetaData metaData=rs.getMetaData();
			int columnNum=metaData.getColumnCount();//获得数据表的列数
			for(int i=1;i<=columnNum;i++){
				String columnName=metaData.getColumnName(i);//获得指定列的列名,i的取值范围从1到“总的列数”
				ANs.add(columnName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ANs;
	}
	
	public static ResultSet executeQuerySql(Connection conn,String sql){
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			if(stmt != null && sql.length()>0){
				rs = stmt.executeQuery(sql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static Map<String,String> obtainANsAndAVs(Connection conn,String tbName,String sql){
		Statement stmt;
		ResultSet rs = null;
		List<String>  ANs = new ArrayList<String>();
		ANs.addAll(getANs(conn,tbName));
		Map<String,String> ANsAndAVs = new HashMap<String,String>();
		try {
			stmt = conn.createStatement();
			if(stmt != null && sql.length()>0){
				rs = stmt.executeQuery(sql);
			}
			while(rs.next()){
				for(String an:ANs){
					String av = rs.getString(an);
					ANsAndAVs.put(an,av);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ANsAndAVs;
	}
	
	public static List<String> obtainAVs(Connection conn,String sql,List<String> ANs){
		Statement stmt;
		ResultSet rs = null;
		List<String> AVs = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			if(stmt != null && sql.length()>0){
				rs = stmt.executeQuery(sql);
			}
			while(rs.next()){
				for(String an:ANs){
					String av = rs.getString(an);
					AVs.add(av);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AVs;
	}
	
	public static void main(String[] args){
		/* for TheResultForHighestProbability
		 * mysqlUtility.countAccuracy("adult4000_nofnlwgt_experimentaldata1_0_0_checkdirty", "adult4000_nofnlwgt_experimentaldata1_0_0", "adult4000_nofnlwgt");
		//adult4000_nofnlwgt_experimentaldata1_0_0_checkdirty, 
		//adult4000_nofnlwgt_experimentaldata1_0_0
		//adult4000_nofnlwgt
		 */		
		//adult4000_nofnlwgt_experimentaldata1_0_0_inferfdtemplate50_pw, 
		//adult4000_nofnlwgt_experimentaldata1_0_0
		//adult4000_nofnlwgt
	}
}
