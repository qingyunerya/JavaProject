package sqldemo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
public class Loading extends JFrame implements WindowListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints contains;
	JMenuBar menubar;
	JMenu menu;
	JMenuItem menuitem;
	JToolBar toolbar;
	JButton button;
	JPanel panel;
	JTextField tfuname; 
	JTextField tfupword; 
	JLabel label;
	ImageIcon img;
	int SCRW,SCRH;
	int imgw,imgh;
	JPanel mainpanel;
	JPanel centerpanel;
	JPanel ctpanel;
	JPanel ccpanel;
	JPanel ccpanel1;
	JPanel ccpanel2;
	JPanel ccpanel3;
	JPanel cbpanel;
	SqlserverConn sq;
	StuDno stu;
	String Uname ="";
	String Upassword ="";
	String AccountNum ="";
	String stuBalance ="";
	//构造
	public Loading()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		img=new ImageIcon(Loading.class.getResource("/youxi.png"));
		SCRH=img.getIconHeight();
		SCRW=img.getIconWidth();
		this.setSize(SCRW,SCRH);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("充电桩登录系统");
		sq = new SqlserverConn();
		sq.setFrame(this);
		label=new JLabel(img);
		label.setBounds(0,0,SCRW,SCRH);
		this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		panel=new JPanel();
		mainpanel=new JPanel();
		mainpanel.setOpaque(false);
		panel.add(mainpanel);
		centerpanel=new JPanel();
		centerpanel.setLayout(new BorderLayout());
		centerpanel.setOpaque(false);
		mainpanel.add(centerpanel);
		ctpanel=new JPanel();
		ctpanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,50));
		ctpanel.setOpaque(false);
		centerpanel.add(ctpanel, BorderLayout.NORTH);
		label=new JLabel("<html><font color='black' size='8'>欢迎登录</font></html>");
		ctpanel.add(label);
		ccpanel=new JPanel();
		ccpanel.setLayout(new BorderLayout());
		ccpanel.setOpaque(false);
		centerpanel.add(ccpanel, BorderLayout.CENTER);
		ccpanel1=new JPanel();
		ccpanel1.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		ccpanel1.setOpaque(false);
		ccpanel.add(ccpanel1,BorderLayout.NORTH);
		label=new JLabel("<html><font color='blue' size='5'>用户名:</font></html>");
		ccpanel1.add(label);
		tfuname=new JTextField(10);
		tfuname.setHorizontalAlignment(JTextField.LEFT);
		ccpanel1.add(tfuname);
		ccpanel2=new JPanel();
		ccpanel2.setLayout(new FlowLayout(FlowLayout.CENTER,25,0));
		ccpanel2.setOpaque(false);
		ccpanel.add(ccpanel2,BorderLayout.CENTER);
		label=new JLabel("<html><font color='blue' size='5'>密码 :</font></html>");
		ccpanel2.add(label);
		tfupword=new JPasswordField(10);
		tfupword.setHorizontalAlignment(JTextField.LEFT);
		ccpanel2.add(tfupword);
		ccpanel3=new JPanel();
		ccpanel3.setLayout(new FlowLayout(FlowLayout.CENTER,80,20));
		ccpanel3.setOpaque(false);
		ccpanel.add(ccpanel3,BorderLayout.SOUTH);
		button=new JButton("登录");
		button.addActionListener(this);
		ccpanel3.add(button);
		button=new JButton("注册");
		button.addActionListener(this);
		ccpanel3.add(button);
		panel.setOpaque(false);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	//设置sq
	public SqlserverConn getsq()
	{
		return this.sq;
	}
	//登陆
	void loading()
	{
		Uname=tfuname.getText();
		Upassword=tfupword.getText();
		if(Uname.isEmpty()||Upassword.isEmpty())
		JOptionPane.showMessageDialog(this, "请输入完整信息", "提示", 1);
		else
		{
			switch(sq.userLoading(Uname, Upassword))
			{
				case -1:JOptionPane.showMessageDialog(this, "密码有误,或用户不存在", "提示", 1);
				break;
				case 0:JOptionPane.showMessageDialog(this, "你是管理员,将进入管理员系统", "提示", 1);
				stu=new StuDno(0);
				stu.setStuInfo(Uname, Upassword,AccountNum,stuBalance);
				stu.setsq(sq);
				break;
				case 1:
				JOptionPane.showMessageDialog(this, "你是用户,将进入用户系统", "提示", 1);
				AccountNum=sq.getDate("select * from 用户 where Uname='"+Uname+"' and Upassword='"+Upassword+"'",5);
				stuBalance=sq.getDate("select * from 用户 where Uname='"+Uname+"' and Upassword='"+Upassword+"'",4);
				stu=new StuDno(1);
				stu.setStuInfo(Uname, Upassword,AccountNum,stuBalance);
				stu.setsq(sq);
				break;
				case 2:JOptionPane.showMessageDialog(this, "你是超级管理员,将进入超级管理员系统", "提示", 1);
				stu=new StuDno(2);
				stu.setStuInfo(Uname, Upassword,AccountNum,stuBalance);
				stu.setsq(sq);	
				break;	
			}
		}
	}
	//注册
	void registing()
	{
		Uname=tfuname.getText();
		Upassword=tfupword.getText();
		if(Uname.isEmpty()||Upassword.isEmpty())
		JOptionPane.showMessageDialog(this, "请输入完整信息", "提示", 1);
		else
		{
			switch(sq.userLoading(Uname, Upassword))
			{
				case -2:
				JOptionPane.showMessageDialog(this, "数据库异常,请检查数据库", "提示", 1);
				break;
				case -1:
				stu=new StuDno();
				stu.setStuInfo(Uname, Upassword,"123456","0");
				stu.setsq(sq);
				stu.relieveAddUser();
				break;
				case 0:
				JOptionPane.showMessageDialog(this, "用户名已注册,请直接登录", "提示", 1);
				break;
				case 1:
				JOptionPane.showMessageDialog(this, "用户名已注册,请直接登录", "提示", 1);
				break;
			}
		}
	}
	//事件触发
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String type;
		JButton btn = null;
		type=e.getSource().toString();
		if(type.contains("JButton"))
		{
			btn= (JButton) e.getSource();
			if (btn.getText() == "登录")
			{
				loading();
			}
		}
		if(type.contains("JButton"))
		{
			btn= (JButton) e.getSource();
			if (btn.getText() == "注册")
			{
				registing();
			}
		}
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
	  Loading load =new Loading();
	  load.sq.setLoad(load);
	  load.addWindowListener(load);
	}
}
