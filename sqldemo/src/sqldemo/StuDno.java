package sqldemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
public class StuDno extends JFrame implements WindowListener, ActionListener,MouseListener,HyperlinkListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SqlserverConn sq;
	StuDno stu;
	Vector<Vector<String>> stuInfoDate;
	Vector<String> stuInfoTitle;
	Browser browser;
	BrowserView view;
	JDialog dialog;
	JMenuBar menubar;
	JMenu menu;
	JMenuItem menuitem;
	JToolBar toolbar;
	JButton button;
	JPanel toppanel;
	JPanel toolpanel;
	JPanel centerpanel;
	JPanel cltoolpanel;
	JPanel clctoolpanel;
	JPanel ccmainpanel;
	JTabbedPane tabbedpane;
	JPanel ccpanel;
	JScrollPane scrollpane;
	JEditorPane jEditorPane1;
	GridBagConstraints contains;
	JTable stuinfotable;
	int i=-1;
	String stuUname="管理员";
	String stuUpassword="123456";
	String stuAccountNum="123456";
	String stuBlance="0";
	JTextField text;
	JPanel panel;
	JLabel label;
	JLabel infolabel;
	ArrayList<JTextField> list;
	//无界面构造
	public StuDno()
	{
		infolabel=new JLabel("<html><font color='black' size='4'>当前用户:</font><font color='blue' size='3'><br>用户名: <br>账户:  <br><账户余额:  <br>/font></html>");
	}
	//界面构造
	public StuDno(int i)
	{
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000,1000);
		this.setLocationRelativeTo(null);
		this.setTitle("充电桩管理系统");
		this.setResizable(false);
		this.i=i;
		menubar=new JMenuBar();
		this.setJMenuBar(menubar);
		menu=new JMenu("文件(F)");
		menubar.add(menu);
		menuitem=new JMenuItem("保存表格");
		menuitem.addActionListener(this);
		menu.add(menuitem);
		menu=new JMenu("编辑(E)");
		menubar.add(menu);
		menuitem=new JMenuItem("编辑表格");
		menuitem.addActionListener(this);
		menu.add(menuitem);
		menu=new JMenu("帮助(H)");
		menubar.add(menu);
		menuitem=new JMenuItem("作者信息");
		menuitem.addActionListener(this);
		menu.add(menuitem);
		toolbar=new JToolBar("数据表");
		if(i==1)
		{
			createToolButton("我要充电");
			createToolButton("我要充值");
			createToolButton("账单记录");
		}
		else
		{
			createToolButton("用户");
			createToolButton("电桩");
			createToolButton("账单");
		}
		toppanel=new JPanel();
		toppanel.setLayout(new BorderLayout());
		toppanel.setBackground(Color.lightGray);
		this.add(toppanel,BorderLayout.NORTH);
		toolpanel=new JPanel();
		toolpanel.setOpaque(false);
		toolpanel.add(toolbar);
		toppanel.add(toolpanel,BorderLayout.WEST);
		centerpanel=new JPanel();
		centerpanel.setLayout(new BorderLayout());
		this.add(centerpanel,BorderLayout.CENTER);
		cltoolpanel=new JPanel(new BorderLayout());
		cltoolpanel.setBackground(Color.lightGray);
		centerpanel.add(cltoolpanel,BorderLayout.WEST);
		toolbar=new JToolBar("操作");
		toolbar.setOrientation(JToolBar.VERTICAL);
		if(i==1)
		{
			createToolButton("退出登录");
		}
		else
		{
			createToolButton("添加用户");
			createToolButton("删除用户");
			createToolButton("查看账单");
			createToolButton("删除账单");
			createToolButton("添加电桩");
			createToolButton("删除电桩");
			createToolButton("电桩地图");
			createToolButton("配置电桩");
			createToolButton("用户充值");
			createToolButton("查看充电");
			createToolButton("用户视角");
		}
		cltoolpanel.add(toolbar,BorderLayout.NORTH);
		clctoolpanel=new JPanel(new BorderLayout());
		cltoolpanel.add(clctoolpanel,BorderLayout.CENTER);
		infolabel=new JLabel("<html><font color='black' size='4'>当前用户:</font><font color='blue' size='3'><br>用户名: <br>账户:  <br>账户余额:<br></font></html>");
		clctoolpanel.add(infolabel,BorderLayout.NORTH);
		ccmainpanel=new JPanel();
		centerpanel.add(ccmainpanel, BorderLayout.CENTER);
		ccmainpanel.setLayout(new BorderLayout());
		scrollpane=new JScrollPane();
		jEditorPane1= new JEditorPane();
		jEditorPane1.addHyperlinkListener(this);
		ccmainpanel.add(scrollpane);
		try
		{   
			String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1547187570170&di=11f05f4519d96d5af370000b05981853&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F7c1ed21b0ef41bd5f81eae7e5cda81cb38db3dee.jpg";
			browser = new Browser();  
			view = new BrowserView(browser);
			scrollpane.setViewportView(view);
			browser.loadURL(url);
		}catch(Exception e)
		{
			stuinfotable=new JTable(100, 20);
			scrollpane.setViewportView(stuinfotable);
			e.printStackTrace();
		}
		this.setVisible(true);
	}
	//设置用户信息
	void setStuInfo(String stuUname,String stuUpassword,String stuAccountNum,String stuBlance)
	{
		this.stuUname=stuUname;
		this.stuUpassword=stuUpassword;
		this.stuAccountNum=stuAccountNum;
		this.stuBlance=stuBlance;
		infolabel.setText("<html><font color='black' size='4'>当前用户:</font><font color='blue' size='3'><br>用户名: "+stuUname+"<br>账户: "+stuAccountNum+"<br>账户余额: "+stuBlance+"<br></font></html>");
	}
	//设置sq
	void setsq(SqlserverConn sq)
	{
		this.sq=sq;
	}
	//配置数据库
	void configDatabase()
	{
	    list=new ArrayList<JTextField>();
		dialog=new JDialog();
		dialog.setTitle("数据库配置");
		JPanel panel=new JPanel();
		dialog.setSize(400,400 );
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		contains=new GridBagConstraints();
		
		createContains(0,0,2,1,50,10);
		label=new JLabel("数据库名");
		panel.add(label,contains);
		createContains(2,0,4,1,50,10);
		text=new JTextField(10);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,1,2,1,10,10);
		label=new JLabel("数据库登录用户名");
		panel.add(label,contains);
		createContains(2,1,4,1,10,10);
		text=new JTextField(10);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,2,2,1,10,10);
		label=new JLabel("数据库登录密码");
		panel.add(label,contains);
		createContains(2,2,4,1,10,10);
		text=new JTextField(10);
		text.setText(this.stuUpassword);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		button=new JButton("提交数据库配置信息");
		button.addActionListener(this);
		createContains(0,3,4,1,30,110);
		panel.add(button,contains);
		
		dialog.setLayout(new BorderLayout());
		dialog.add(panel,BorderLayout.NORTH);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	//创建工具栏按钮
	void createToolButton(String str )
	{
		button=new JButton(str);
		button.addActionListener(this);
		toolbar.add(button);
	}
	//创建表格
	void stuInfoTable(String tablename)
	{
		stuinfotable=sq.getTable(tablename);
		stuinfotable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ccmainpanel.add(scrollpane);
		scrollpane.setViewportView(stuinfotable);
	}
	//表格包布
	protected void createContains(int x,int y,int w,int h,int iu,int il) 
	{
		contains.gridx=x;
		contains.gridy=y;
		contains.gridwidth=w;
		contains.gridheight=h;
		contains.insets=new Insets(iu,il,0,0);
		contains.fill=GridBagConstraints.HORIZONTAL;
	}
	//展示地图
	void showMap()
	{
			tabbedpane=new JTabbedPane();
			String url = "http://api.map.baidu.com/library/MarkerManager/1.2/example/MarkerManager.html";
			browser = new Browser();  
			view = new BrowserView(browser);
			tabbedpane.addTab("电桩分布图",view);
			scrollpane.setViewportView(tabbedpane);
			browser.loadURL(url);
	}
	//事件触发
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type;
		JMenuItem mnit = null;
		JButton btn = null;
		type=e.getSource().toString();
		dbBaseEvent(type,btn,e);
		stuEvent(type,btn,e);
		ManagerEvent(type, btn, e);
		ToolEvent(type, mnit, e);
		relieveEvent(type, btn, e);
		mangagerelieveEvent(type, btn, e);
	}
	//管理员提交事件
	void mangagerelieveEvent(String type,JButton btn,ActionEvent e)
	{
		if(type.contains("JButton"))
		{
			btn= (JButton) e.getSource();
			if (btn.getText() == "提交添加用户信息")
			{
				dialog.dispose();
				String sql="insert into 用户 values(?,?,?,?,?,?,?)";      
				ArrayList<String> name=new ArrayList<String>();
				name.add(list.get(0).getText());					
				name.add(list.get(1).getText());
				name.add(list.get(2).getText());
				name.add(list.get(3).getText());
				name.add(list.get(4).getText());
				name.add(list.get(5).getText());
				name.add(list.get(6).getText());
				if(sq.exeSqlUpdate(sql, name))					
				JOptionPane.showMessageDialog(this, "操作成功", "提示", 1);   
			}
			if (btn.getText() == "提交删除用户信息")
			{
				dialog.dispose();
				String sql="delete from 用户 where Uname=?";      
				ArrayList<String> name=new ArrayList<String>();
				name.add(list.get(0).getText());					
				if(sq.exeSqlUpdate(sql, name))					
				JOptionPane.showMessageDialog(this, "操作成功", "提示", 1);   
			}
			if (btn.getText() == "提交添加电桩信息")
			{
				dialog.dispose();
				String sql="insert into 电桩 values(?,?,?,?,?,?,?)";      
				ArrayList<String> name=new ArrayList<String>();
				name.add(list.get(0).getText());					
				name.add(list.get(1).getText());
				name.add(list.get(2).getText());
				name.add(list.get(3).getText());
				name.add(list.get(4).getText());
				name.add(list.get(5).getText());
				name.add(list.get(6).getText());
				if(sq.exeSqlUpdate(sql, name))					
				JOptionPane.showMessageDialog(this, "操作成功", "提示", 1);   
			}
			if (btn.getText() == "提交删除电桩信息")
			{
				dialog.dispose();
				String sql="delete from 电桩 where DeviceCode=?";      
				ArrayList<String> name=new ArrayList<String>();
				name.add(list.get(0).getText());					
				if(sq.exeSqlUpdate(sql, name))					
				JOptionPane.showMessageDialog(this, "操作成功", "提示", 1);   
			}
			if (btn.getText() == "提交删除账单信息")
			{
				dialog.dispose();
				String sql="delete from 账单 where Uname=?";      
				ArrayList<String> name=new ArrayList<String>();
				name.add(list.get(0).getText());					
				if(sq.exeSqlUpdate(sql, name))					
				JOptionPane.showMessageDialog(this, "操作成功", "提示", 1);   
			}
			if (btn.getText() == "提交充值信息")
			{
				dialog.dispose();
				String sql="update 用户 set Balance =Balance+? where Uaccount=?";      
				ArrayList<String> name=new ArrayList<String>();
				name.add(list.get(1).getText());
				name.add(list.get(0).getText());
				if(sq.exeSqlUpdate(sql, name))					
				JOptionPane.showMessageDialog(this, "操作成功", "提示", 1); 
				stuBlance=sq.getDate("select * from 用户 where Uname='"+stuUname+"' and Upassword='"+stuUpassword+"'",4);
				infolabel.setText("<html><font color='black' size='4'>当前用户:</font><font color='blue' size='3'><br>用户名: "+stuUname+"<br>账户: "+stuAccountNum+"<br>账户余额: "+stuBlance+"<br></font></html>");
			}
			if (btn.getText() == "提交配置")
			{
				dialog.dispose();
				String sql="update 电桩 set Fee=?,ServerFee=?,Status =?,Intencity=?,Voltage=? where DeviceCode=?";      
				ArrayList<String> name=new ArrayList<String>();
				name.add(list.get(1).getText());
				name.add(list.get(2).getText());
				name.add(list.get(3).getText());
				name.add(list.get(4).getText());
				name.add(list.get(5).getText());
				name.add(list.get(0).getText());
				if(sq.exeSqlUpdate(sql, name))					
				JOptionPane.showMessageDialog(this, "操作成功", "提示", 1);   
			}
			if (btn.getText() == "提交数据库配置信息")
			{
				dialog.dispose();
				String sql="update 用户 set Uaccount='0' where Uname='管理员'"; 
				ArrayList<String> name=new ArrayList<String>();
				name.add(list.get(0).getText());
				name.add(list.get(1).getText());
				name.add(list.get(2).getText());
				sq.setDataBase(name.get(0),name.get(1), name.get(2));
				if(sq.exeSqlUpdate(sql))					
				JOptionPane.showMessageDialog(this, "操作成功", "提示", 1);   
			}
		}
	}
	//用户提交事件
	void relieveEvent(String type,JButton btn,ActionEvent e)
	{
		if(type.contains("JButton"))
		{
			btn= (JButton) e.getSource();
			if (btn.getText() == "开始充电")
			{
				dialog.dispose();
				/*
				String sql="insert into 离校 values(?,?,?,?)";  
				ArrayList<String> name=new ArrayList<String>();
				name.add(list.get(0).getText());
				name.add(list.get(1).getText());
				name.add(list.get(2).getText());
				name.add(list.get(3).getText());
				if(sq.exeSqlUpdate(sql, name))*/
				JOptionPane.showMessageDialog(this, "操作成功", "提示", 1); 
			}
			if (btn.getText() == "退出登录")
			{
				if(sq!=null)sq.Close();
				this.dispose();
			}
		}
	}
	//工具栏操作事件
	void ToolEvent(String type,JMenuItem mnit,ActionEvent e)
	{
		if(type.contains("JMenuItem"))
		{
			mnit=(JMenuItem)e.getSource();
		    if(mnit.getText()=="作者信息")
			{
				dialog=new JDialog();
				dialog.setLocationRelativeTo(null);
				dialog.setTitle("作者信息");
				dialog.setSize(100,200 );
				label=new JLabel();
				label.setText("<html><font color='black' size='8'>欢迎登录</font></html>");
				dialog.setModal(true);
				dialog.setVisible(true);			
			}
		}
	}
	//用户操作事件
	void stuEvent(String type,JButton btn,ActionEvent e)
	{
		if(type.contains("JButton"))
		{
			btn= (JButton) e.getSource();
			if (btn.getText() == "我要充电")
			{
				list=new ArrayList<JTextField>();
				dialog=new JDialog();
				dialog.setTitle("远程充电");
				dialog.setSize(400,400 );
				dialog.setResizable(false);
				dialog.setLocationRelativeTo(null);
				panel=new JPanel();
				panel.setLayout(new GridBagLayout());
				contains=new GridBagConstraints();
				
				createContains(0,0,2,1,50,10);
				label=new JLabel("充电车辆编号");
				panel.add(label,contains);
				createContains(2,0,4,1,50,10);
				text=new JTextField(10);
				text.setText(sq.getDate("select * from 用户 where Uname='"+this.stuUname+"'",6));
				text.setHorizontalAlignment(JTextField.LEFT);
				list.add(text);
				panel.add(text,contains);
				
				createContains(0,1,2,1,10,10);
				label=new JLabel("充电桩编号");
				panel.add(label,contains);
				createContains(2,1,4,1,10,10);
				text=new JTextField(10);
				text.setHorizontalAlignment(JTextField.LEFT);
				list.add(text);
				panel.add(text,contains);
				
				createContains(0,2,2,1,10,10);
				label=new JLabel("充电开始时间");
				panel.add(label,contains);
				createContains(2,2,4,1,10,10);
				text=new JTextField(10);
				text.setHorizontalAlignment(JTextField.LEFT);
				list.add(text);
				panel.add(text,contains);
				
				createContains(0,3,2,1,10,10);
				label=new JLabel("充电结束时间");
				panel.add(label,contains);
				createContains(2,3,4,1,10,10);
				text=new JTextField(10);
				text.setHorizontalAlignment(JTextField.LEFT);
				list.add(text);
				panel.add(text,contains);
				
				button=new JButton("开始充电");
				button.addActionListener(this);
				createContains(0,4,4,1,30,150);
				panel.add(button,contains);
				
				dialog.setLayout(new BorderLayout());
				dialog.add(panel,BorderLayout.NORTH);
				dialog.setModal(true);
				dialog.setVisible(true);   
			}
			if (btn.getText() == "我要充值")
			{
				relieveAddBalance(this.stuAccountNum);
			}
			if (btn.getText() == "账单记录")
			{
				stuInfoTable("账单 where Uname='"+stuUname+"'"); 
			}
		}
	}
	//管理员操作事件
	void ManagerEvent(String type,JButton btn,ActionEvent e)
	{
				if(type.contains("JButton"))
				{
					btn= (JButton) e.getSource();
					if (btn.getText() == "添加用户")
					{
						relieveAddUser();   
					}
					if (btn.getText() == "删除用户")
					{
						relieveDelUser();
					}
					if (btn.getText() == "删除账单")
					{
						if(i==2)relieveDelBill();  
						else JOptionPane.showMessageDialog(this, "权限不够", "提示", 1);
					}
					if (btn.getText() == "添加电桩")
					{
						relieveAddDevice();   
					}
					if (btn.getText() == "删除电桩")
					{
						relieveDelDevice();    
					}
					if (btn.getText() == "配置电桩")
					{
						relieveConfigCharge();   
					}
					if (btn.getText() == "电桩地图")
					{
						showMap();	
					}
					if (btn.getText() == "用户充值")
					{
						relieveAddBalance("");   
					}
					if (btn.getText() == "查看账单")
					{
						stuInfoTable("账单");	
					}
					if (btn.getText() == "查看充电")
					{
						String sql="create view 充电 as select Uname,SNcode,DeviceCode,StartTime,StopTime,ChargedNum,ChargeFee from 账单";      
						if(sq.exeSqlUpdate(sql))
						{
							stuInfoTable("充电");
							sql="drop view 充电";
							sq.exeSqlUpdate(sql);
						}	
					}
					if (btn.getText() == "增加")
					{
						dialog=new JDialog();
						dialog.setTitle("增加");
						JPanel panel=new JPanel();
						dialog.setSize(400,400 );
						dialog.setResizable(false);
						dialog.setLocationRelativeTo(null);
						dialog.setLayout(new BorderLayout());
						JRadioButton row=new JRadioButton("行");
						JRadioButton col=new JRadioButton("列");
						JLabel label=new JLabel("请选择增加行或列");
						panel.add(label);
						panel.add(row);
						panel.add(col);
						ButtonGroup bg=new ButtonGroup();
						bg.add(row);
						bg.add(col);
						dialog.add(panel);
						dialog.setModal(true);
						dialog.setVisible(true);
					}
					if (btn.getText() == "用户视角")
					{
						stu=new StuDno(1);
						stu.setStuInfo(stuUname, stuUpassword,stuAccountNum,stuBlance);
						stu.setsq(sq);
					}
				}
	}	
	//数据库事件
	void dbBaseEvent(String type,JButton btn,ActionEvent e)
	{
		if(type.contains("JButton"))
		{
			btn= (JButton) e.getSource();
			if (btn.getText() == "用户")
			{
				stuInfoTable("用户");
			}
			if (btn.getText() == "电桩")
			{
				stuInfoTable("电桩");	
			}
			if (btn.getText() == "账单")
			{
				stuInfoTable("账单");	
			}
		}
	}
	//提交删除用户
	void relieveDelUser()
	{
		list=new ArrayList<JTextField>();
		dialog=new JDialog();
		dialog.setTitle("删除用户");
		JPanel panel=new JPanel();
		dialog.setSize(400,400 );
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		contains=new GridBagConstraints();
		
		createContains(0,0,2,1,50,10);
		label=new JLabel("用户名");
		panel.add(label,contains);
		createContains(2,0,4,1,50,10);
		text=new JTextField(10);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		button=new JButton("提交删除用户信息");
		button.addActionListener(this);
		createContains(0,1,4,1,30,120);
		panel.add(button,contains);
		
		dialog.setLayout(new BorderLayout());
		dialog.add(panel,BorderLayout.NORTH);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	//提交删除账单
	void relieveDelBill()
	{
		list=new ArrayList<JTextField>();
		dialog=new JDialog();
		dialog.setTitle("账单删除");
		JPanel panel=new JPanel();
		dialog.setSize(400,400 );
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		contains=new GridBagConstraints();
		
		createContains(0,0,2,1,50,10);
		label=new JLabel("用户名");
		panel.add(label,contains);
		createContains(2,0,4,1,50,10);
		text=new JTextField(10);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		button=new JButton("提交删除账单信息");
		button.addActionListener(this);
		createContains(0,1,4,1,30,120);
		panel.add(button,contains);
		
		dialog.setLayout(new BorderLayout());
		dialog.add(panel,BorderLayout.NORTH);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	//提交添加电桩
	void relieveAddDevice()
	{
		list=new ArrayList<JTextField>();
		dialog=new JDialog();
		dialog.setTitle("添加电桩");
		JPanel panel=new JPanel();
		dialog.setSize(400,400 );
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		contains=new GridBagConstraints();
		
		createContains(0,0,2,1,50,10);
		label=new JLabel("电桩编号");
		panel.add(label,contains);
		createContains(2,0,4,1,50,10);
		text=new JTextField(10);
		text.setText("1234");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,1,2,1,10,10);
		label=new JLabel("单位时间收费");
		panel.add(label,contains);
		createContains(2,1,4,1,10,10);
		text=new JTextField(10);
		text.setText("5");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,2,2,1,10,10);
		label=new JLabel("服务费");
		panel.add(label,contains);
		createContains(2,2,4,1,10,10);
		text=new JTextField(10);
		text.setText("5");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,3,2,1,10,10);
		label=new JLabel("状态");
		panel.add(label,contains);
		createContains(2,3,4,1,10,10);
		text=new JTextField(10);
		text.setText("正常");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,4,2,1,10,10);
		label=new JLabel("地点");
		panel.add(label,contains);
		createContains(2,4,4,1,10,10);
		text=new JTextField(10);
		text.setText("TJPU");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,5,2,1,10,10);
		label=new JLabel("充电电流");
		panel.add(label,contains);
		createContains(2,5,4,1,10,10);
		text=new JTextField(10);
		text.setText("11.00");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,6,2,1,10,10);
		label=new JLabel("充电电压");
		panel.add(label,contains);
		createContains(2,6,4,1,10,10);
		text=new JTextField(10);
		text.setText("220.00");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		button=new JButton("提交添加电桩信息");
		button.addActionListener(this);
		createContains(0,7,4,1,30,120);
		panel.add(button,contains);
		
		dialog.setLayout(new BorderLayout());
		dialog.add(panel,BorderLayout.NORTH);
		dialog.setModal(true);
		dialog.setVisible(true);
		
	}
	//提交删除电桩
	void relieveDelDevice()
	{
		list=new ArrayList<JTextField>();
		dialog=new JDialog();
		dialog.setTitle("删除电桩");
		JPanel panel=new JPanel();
		dialog.setSize(400,400 );
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		contains=new GridBagConstraints();
		
		createContains(0,0,2,1,50,10);
		label=new JLabel("电桩编号");
		panel.add(label,contains);
		createContains(2,0,4,1,50,10);
		text=new JTextField(10);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		button=new JButton("提交删除电桩信息");
		button.addActionListener(this);
		createContains(0,1,4,1,30,120);
		panel.add(button,contains);
		
		dialog.setLayout(new BorderLayout());
		dialog.add(panel,BorderLayout.NORTH);
		dialog.setModal(true);
		dialog.setVisible(true);
		
	}
	//提交添加用户
	void relieveAddUser()
	{
		list=new ArrayList<JTextField>();
		dialog=new JDialog();
		dialog.setTitle("添加用户");
		JPanel panel=new JPanel();
		dialog.setSize(400,400 );
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		contains=new GridBagConstraints();
		
		createContains(0,0,2,1,50,10);
		label=new JLabel("用户名");
		panel.add(label,contains);
		createContains(2,0,4,1,50,10);
		text=new JTextField(10);
		if(i==-1)text.setText(this.stuUname);
		if(i==-1)text.setEditable(false);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,1,2,1,10,10);
		label=new JLabel("用户密码");
		panel.add(label,contains);
		createContains(2,1,4,1,10,10);
		text=new JTextField(10);
		text.setText(this.stuUpassword);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,2,2,1,10,10);
		label=new JLabel("用户类型");
		panel.add(label,contains);
		createContains(2,2,4,1,10,10);
		text=new JTextField(10);
		text.setText("1");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		if(i==-1)text.setEditable(false);
		panel.add(text,contains);
		
		createContains(0,3,2,1,10,10);
		label=new JLabel("用户余额");
		panel.add(label,contains);
		createContains(2,3,4,1,10,10);
		text=new JTextField(10);
		text.setText("0");
		if(i==-1)text.setEditable(false);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,4,2,1,10,10);
		label=new JLabel("用户账户");
		panel.add(label,contains);
		createContains(2,4,4,1,10,10);
		text=new JTextField(10);
		text.setText("0");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,5,2,1,10,10);
		label=new JLabel("用户车辆编码");
		panel.add(label,contains);
		createContains(2,5,4,1,10,10);
		text=new JTextField(10);
		text.setText("CN1002");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,6,2,1,10,10);
		label=new JLabel("用户车辆电量");
		panel.add(label,contains);
		createContains(2,6,4,1,10,10);
		text=new JTextField(10);
		text.setText("0");
		if(i==-1)text.setEditable(false);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		button=new JButton("提交添加用户信息");
		button.addActionListener(this);
		createContains(0,7,4,1,30,120);
		panel.add(button,contains);
		
		dialog.setLayout(new BorderLayout());
		dialog.add(panel,BorderLayout.NORTH);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	//提交用户充值
	void relieveAddBalance(String Uaccount)
	{
		list=new ArrayList<JTextField>();
		dialog=new JDialog();
		dialog.setTitle("用户充值");
		JPanel panel=new JPanel();
		dialog.setSize(400,400 );
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		contains=new GridBagConstraints();
		
		createContains(0,0,2,1,50,10);
		label=new JLabel("充值账户");
		panel.add(label,contains);
		createContains(2,0,4,1,50,10);
		text=new JTextField(10);
		text.setText(Uaccount);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,1,2,1,10,10);
		label=new JLabel("充值金额");
		panel.add(label,contains);
		createContains(2,1,4,1,10,10);
		text=new JTextField(10);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		button=new JButton("提交充值信息");
		button.addActionListener(this);
		createContains(0,2,4,1,30,150);
		panel.add(button,contains);
		
		dialog.setLayout(new BorderLayout());
		dialog.add(panel,BorderLayout.NORTH);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	//提交配置电桩
	void relieveConfigCharge()
	{
		list=new ArrayList<JTextField>();
		dialog=new JDialog();
		dialog.setTitle("电桩配置");
		JPanel panel=new JPanel();
		dialog.setSize(400,400 );
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		contains=new GridBagConstraints();
		
		createContains(0,0,2,1,50,10);
		label=new JLabel("电桩编码");
		panel.add(label,contains);
		createContains(2,0,4,1,50,10);
		text=new JTextField(10);
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,1,2,1,10,10);
		label=new JLabel("单位时间费用");
		panel.add(label,contains);
		createContains(2,1,4,1,10,10);
		text=new JTextField(10);
		text.setText("5");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,2,2,1,10,10);
		label=new JLabel("服务费");
		panel.add(label,contains);
		createContains(2,2,4,1,10,10);
		text=new JTextField(10);
		text.setText("5");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,3,2,1,10,10);
		label=new JLabel("电桩状态");
		panel.add(label,contains);
		createContains(2,3,4,1,10,10);
		text=new JTextField(10);
		text.setText("空闲");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,4,2,1,10,10);
		label=new JLabel("充电电压");
		panel.add(label,contains);
		createContains(2,4,4,1,10,10);
		text=new JTextField(10);
		text.setText("220");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		createContains(0,5,2,1,10,10);
		label=new JLabel("充电电流");
		panel.add(label,contains);
		createContains(2,5,4,1,10,10);
		text=new JTextField(10);
		text.setText("11");
		text.setHorizontalAlignment(JTextField.LEFT);
		list.add(text);
		panel.add(text,contains);
		
		button=new JButton("提交配置");
		button.addActionListener(this);
		createContains(0,6,4,1,30,150);
		panel.add(button,contains);
		
		dialog.setLayout(new BorderLayout());
		dialog.add(panel,BorderLayout.NORTH);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	//测试连接方法
	public static void main(String[] args)
	{
	  StuDno studno =new StuDno(0);
	  studno.addWindowListener(studno);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		// TODO Auto-generated method stub
		
	}
}
