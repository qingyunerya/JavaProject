package com.yunzhi.lianliankan;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public  class Lianliankan extends JFrame implements WindowListener,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	JPanel panel;
	JButton button;
	JLabel label;
	ImageIcon img;
	Dimension screen;
	int SCRW,SCRH;
	int COLS,ROWS;
	int activity;
	int imgw,imgh;
	public Lianliankan()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen=Toolkit.getDefaultToolkit().getScreenSize();
		img=new ImageIcon("src/menu.png");
		SCRH=img.getIconHeight();
		SCRW=img.getIconWidth();
		this.setBounds(screen.width/2-SCRW/2,screen.height/2-SCRH/2,SCRW,SCRH);
		this.setTitle("连连看");
		label=new JLabel(img);
		label.setBounds(0,0,SCRW,SCRH);
		this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		this.setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0,SCRH/2,SCRW,SCRH/2);
		buttonCreated("src/start.png","start",0);
		buttonCreated("src/more.png","more",1);
		panel.setOpaque(false);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	public Lianliankan(int i)
	{
		COLS=6;
		ROWS=5;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen=Toolkit.getDefaultToolkit().getScreenSize();
		img=new ImageIcon("src/youxi.png");
		SCRH=img.getIconHeight();
		SCRW=img.getIconWidth();
		this.setBounds(screen.width/2-SCRW/2,screen.height/2-SCRH/2,SCRW,SCRH);
		this.setTitle("连连看");
		label=new JLabel(img);
		label.setBounds(0,0,SCRW,SCRH);
		this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		this.setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(SCRH/2,SCRH/2,SCRW,SCRH/2);
		imgw=55;
		imgh=55;
		for(int cols = 1;cols < 6;cols++)
		{ 
			for(int rows = 0;rows < 5;rows++ )
			{
				buttonCreated2("src/"+cols+".png",cols+"",cols*imgw,rows*imgh,imgw,imgh);
			}
		}
		panel.setOpaque(false);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	public void buttonCreated(String file,String command,int y)
	{
		img=new ImageIcon(file);
		button=new JButton(img);
		button.setBounds(SCRW/2-img.getIconWidth()/2,
		SCRH/2+y*img.getIconHeight(),img.getIconWidth(),img.getIconHeight());
		button.setContentAreaFilled(false);
		button.addActionListener(this);
		button.setActionCommand(command);
		panel.add(button);
	}
	public void buttonCreated2(String file,String command,int x,int y,int w,int h)
	{
		java.awt.Image imgi;
		img=new ImageIcon(file);
		imgi=img.getImage().getScaledInstance(40,40,1);
		img.setImage(imgi);
		//btn1.setIcon(img);
		button=new JButton(img);
		button.setBounds(x,y,imgw,imgh);
		button.setContentAreaFilled(false);
		button.addActionListener(this);
		button.setActionCommand(command);
		panel.add(button);
	}
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		button=(JButton)e.getSource();
		if(button.getActionCommand().equals("start"))
		{
			this.dispose();
			//Lianliankan lianliankan=new Lianliankan(1);
			Game llk = new Game(); 
			llk.randomBuild(); 
			llk.init(); 
		}
		if(button.getActionCommand().equals("more"))
		{
			this.dispose();
			new Lianliankan();
			System.out.println("没有更多游戏");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Lianliankan();
	}
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
