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
    String database="���׮";
    String user = "sa";//��¼�û���
    String password = "2424753284";//��¼����
    public SqlserverConn()
    {
    }
  //��ȡ����
    public Connection getConn()
	{
	  String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
	  String url = "jdbc:sqlserver://localhost:1433;databaseName="+database;
	//��1433Ϊ���ݿ�Ĭ�϶˿ںţ�ѧ������������ݿ�Ϊ���ݿ�����
	  try { 
	   try {
		Class.forName(driverClassName);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(frame, "��������ʧ��,�����Ƿ���", "��ʾ", 1);
		e.printStackTrace();
	}
	   conn = DriverManager.getConnection(url, user, password);
	   System.out.println("���ݿ����ӳɹ�");
	  } catch (SQLException ex1)
	  {
	   JOptionPane.showMessageDialog(frame, "��⵽�����Ϊ����ʹ��,�����������ݿ�", "��ʾ", 1); 
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
	//�ر�����
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
	//sql����
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
	//sql����
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

	//���ؽ��������
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
		  System.out.println("ʧ��"+ex);
	  }
	  return count;
	}
	//���ر�������
	public Vector<String> getTitleDate(String tabelname)
	{
		  Map<String, String> map=new HashMap<String, String>();
		  String key[]={"Uname","Upassword","Utype","Type","Balance","Uaccount","SNcode","ChargeNum"};
		  String values[]={"�û���","�û�����","�û�����","�û�����","���","�û��˻�","�û���������","�û���������"};
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
			  System.out.println("ʧ��"+ex);
		  }
		  return title;
	}	
	//�����ض�������
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
		  System.out.println("ʧ��"+ex);
	  }
	  return result;
	}
	//���ر�����������
	public JTable getTable(String tablename)
	{
	  Vector<Vector<String>> stuInfoDate = new Vector<Vector<String>>();
	  Vector<String> date=null;
	  Vector<String> title=new Vector<String>();
	  JTable table = new JTable(8,8);
	  Map<String, String> map=new HashMap<String, String>();
	  String key[]={"Uname","Upassword","Utype","Type","Balance","Uaccount","SNcode","ChargeNum","DeviceCode","Fee","ServerFee","Status","Intencity","Voltage","AccountNum","StartTime","StopTime","BeforeChargeNum","AfterChargeNum","ChargeFee","ChargedNum","BeforeBalance","AfterBalance","IsPay","Ip","ChargeStatus","Location","Charge"};
	  String values[]={"�û���","�û�����","�û�����","�û�����","���","�û��˻�","��������","��������","��׮���","��λʱ��Ʒ�","�����","״̬","������","����ѹ","��ˮ��","��ʼʱ��","����ʱ��","��ǰ����","������","����","�����","��ǰ���","������","�Ƿ�֧��","Ip��ַ","���״̬","�ص�","�����"};
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
		  System.out.println("ʧ��"+ex);
	  }
	  return table; 
	}
	//�û���¼
	public int userLoading(String Uname,String Upassword)
	{
		try
		  {
			  getConn();
			  st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  rs = st.executeQuery("select * from �û� where Uname='"+Uname+"' and Upassword='"+Upassword+"'");
			  while(rs.next())
			  {
				  String str=rs.getString(3);
				  return Integer.parseInt(str);
			  }  
		  }
		  catch(SQLException ex)
		  {
			  System.out.println("ʧ��"+ex);
			  return -2;
		  }
		  return -1; 
	}
	public static void main(String[] args) throws SQLException
	{
		SqlserverConn sq = new SqlserverConn();
		//sq.exesql("insert into ��У  values("+list.get(0).getText()+","+list.get(1).getText()+","+list.get(2).getText()+","+list.get(3).getText()+")");
		//sq.exesql("insert into ��У  values(1611630214,404,2018.10.10,2018.10.11)");
		//Statement state=con.createStatement();   //����
       // String sql="insert into ��У values('1611630214','404','2018-12-12','2018-12-13')";   //SQL���
        //state.executeUpdate(sql);        
		
		    String sql="insert into �û�  values(?,?,?,?,0)";
		    Connection conn = sq.getConn();//sql���
	        PreparedStatement state=conn.prepareStatement(sql);                    //����
	        state.setString(1, "1611630215");                                        //����n��ֵ�滻��ĳ��ֵ
	        state.setString(2, "406");
	        state.setString(3, "1");
	        state.setString(4, "100");
	        state.executeUpdate();
	        //sq.getTitleDate("select * from �û�");
		
	}
	
}