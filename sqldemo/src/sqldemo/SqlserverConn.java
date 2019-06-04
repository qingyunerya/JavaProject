package sqldemo;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class SqlserverConn 
{
	public  Connection conn;
	Loading load;
	StuDno stu;
    public  Statement st;
    public PreparedStatement state;
    public  ResultSet rs;
    public ResultSetMetaData rm;
    JFrame frame;
    String database="充电桩";
    String user = "sa";//登录用户名
    String password = "2424753284";//登录密码
    public SqlserverConn()
    {
    }
  //获取链接
    public Connection getConn()
	{
	  String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
	  String url = "jdbc:sqlserver://localhost:1433;databaseName="+database;
	//：1433为数据库默认端口号，学生宿舍管理数据库为数据库名字
	  try { 
	   try {
		Class.forName(driverClassName);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(frame, "驱动加载失败,请检查是否导入", "提示", 1);
		e.printStackTrace();
	}
	   conn = DriverManager.getConnection(url, user, password);
	   System.out.println("数据库连接成功");
	  } catch (SQLException ex1)
	  {
	   JOptionPane.showMessageDialog(frame, "检测到你可能为初次使用,请先配置数据库", "提示", 1); 
	   stu=new StuDno();
	   stu.setsq(load.getsq());
	   stu.configDatabase();
	  } 
	  return conn;
	}
    public void setFrame(JFrame frame)
    {
    	this.frame=frame;
    }
    public void setLoad(Loading load)
    {
    	this.load=load;
    }
    void setDataBase(String database,String user,String password)
    {
    	this.database=database;
    	this.user=user;
    	this.password=password;
    }
	//关闭链接
	public  void Close()
	{
	  try {
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//sql更新
	public Boolean exeSqlUpdate(String sql)
	{
		 getConn();
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			  st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
		return true;
	}
	//sql更新
	public Boolean exeSqlUpdate(String sql,ArrayList<String> list)
	{
		 getConn();
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			
	        state=conn.prepareStatement(sql); 
	        for(int i=0;i<list.size();i++)
			{
				state.setString(i+1, list.get(i));    
			}
				state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
		return true;
	}

	//返回结果集行数
	public int getTableRows(String sql)
	{
	  int count =0;
	  try
	  {
		  getConn();
		  st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		  rs = st.executeQuery(sql);
		  rs.last();
		  count = rs.getRow();
	  }
	  catch(SQLException ex)
	  {
		  System.out.println("失败"+ex);
	  }
	  return count;
	}
	//返回标题数据
	public Vector<String> getTitleDate(String tabelname)
	{
		  Map<String, String> map=new HashMap<String, String>();
		  String key[]={"Uname","Upassword","Utype","Type","Balance","Uaccount","SNcode","ChargeNum"};
		  String values[]={"用户名","用户密码","用户类型","用户类型","余额","用户账户","用户车辆编码","用户车辆电量"};
		  for(int k=0;k<key.length;k++)
		  map.put(key[k],values[k]);
		  Vector<String> title=new Vector<String>();
		  String sql="select * from "+tabelname;
		  try
		  {
			  getConn();
			  st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  rs = st.executeQuery(sql);
			  rm=rs.getMetaData();
			  for(int i=1;i<=rm.getColumnCount();i++)
			  {
				  if(map.containsKey(rm.getColumnName(i)))
				  title.add(map.get(rm.getColumnName(i)));
				  else title.add(rm.getColumnName(i));
			  }
		  }
		  catch(SQLException ex)
		  {
			  System.out.println("失败"+ex);
		  }
		  return title;
	}	
	//返回特定列数据
	public String getDate(String sql,int i)
	{
	  String result = null;
	  try
	  {
		  getConn();
		  st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		  rs = st.executeQuery(sql);
		  while(rs.next())
		  {
			  result=rs.getString(i);
		  }
		  
	  }
	  catch(SQLException ex)
	  {
		  System.out.println("失败"+ex);
	  }
	  return result;
	}
	//返回表中所有数据
	public JTable getTable(String tablename)
	{
	  Vector<Vector<String>> stuInfoDate = new Vector<Vector<String>>();
	  Vector<String> date=null;
	  Vector<String> title=new Vector<String>();
	  JTable table = new JTable(8,8);
	  Map<String, String> map=new HashMap<String, String>();
	  String key[]={"Uname","Upassword","Utype","Type","Balance","Uaccount","SNcode","ChargeNum","DeviceCode","Fee","ServerFee","Status","Intencity","Voltage","AccountNum","StartTime","StopTime","BeforeChargeNum","AfterChargeNum","ChargeFee","ChargedNum","BeforeBalance","AfterBalance","IsPay","Ip","ChargeStatus","Location","Charge"};
	  String values[]={"用户名","用户密码","用户类型","用户类型","余额","用户账户","车辆编码","车辆电量","电桩编号","单位时间计费","服务费","状态","充电电流","充电电压","流水号","开始时间","结束时间","充前电量","充后电量","充电费","充电量","充前余额","充后余额","是否支付","Ip地址","充电状态","地点","充电量"};
	  for(int k=0;k<key.length;k++)
	  map.put(key[k],values[k]);
	  String sql="select * from "+tablename;
	  try
	  {
		  getConn();
		  st = conn.createStatement();
		  rs = st.executeQuery(sql);
		  rm=rs.getMetaData();
		  for(int i=1;i<=rm.getColumnCount();i++)
		  {
			  if(map.containsKey(rm.getColumnName(i)))
			  title.add(map.get(rm.getColumnName(i)));
			  else title.add(rm.getColumnName(i));
		  }
		  while(rs.next())
		  {
			  date= new Vector<String>();
			  for(int i=1;i<=rm.getColumnCount();i++)
			  date.add(rs.getString(i));
			  stuInfoDate.add(date);
		  }
		  table=new JTable(stuInfoDate,title);
		  return table;
	  }
	  catch(SQLException ex)
	  {
		  System.out.println("失败"+ex);
	  }
	  return table; 
	}
	//用户登录
	public int userLoading(String Uname,String Upassword)
	{
		try
		  {
			  getConn();
			  st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  rs = st.executeQuery("select * from 用户 where Uname='"+Uname+"' and Upassword='"+Upassword+"'");
			  while(rs.next())
			  {
				  String str=rs.getString(3);
				  return Integer.parseInt(str);
			  }  
		  }
		  catch(SQLException ex)
		  {
			  System.out.println("失败"+ex);
			  return -2;
		  }
		  return -1; 
	}
	public static void main(String[] args) throws SQLException
	{
		SqlserverConn sq = new SqlserverConn();
		//sq.exesql("insert into 离校  values("+list.get(0).getText()+","+list.get(1).getText()+","+list.get(2).getText()+","+list.get(3).getText()+")");
		//sq.exesql("insert into 离校  values(1611630214,404,2018.10.10,2018.10.11)");
		//Statement state=con.createStatement();   //容器
       // String sql="insert into 离校 values('1611630214','404','2018-12-12','2018-12-13')";   //SQL语句
        //state.executeUpdate(sql);        
		
		    String sql="insert into 用户  values(?,?,?,?,0)";
		    Connection conn = sq.getConn();//sql语句
	        PreparedStatement state=conn.prepareStatement(sql);                    //容器
	        state.setString(1, "1611630215");                                        //将第n个值替换成某个值
	        state.setString(2, "406");
	        state.setString(3, "1");
	        state.setString(4, "100");
	        state.executeUpdate();
	        //sq.getTitleDate("select * from 用户");
		
	}
	
}