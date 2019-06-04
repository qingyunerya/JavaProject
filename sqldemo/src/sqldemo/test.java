package sqldemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class test extends JFrame implements ActionListener,WindowListener,MouseListener{

	/**
	 * 
	 */
	JPopupMenu popupMenu;
	JMenuItem menuitem;
	private static final long serialVersionUID = 1L;
	public test()
	{
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000,1000);
		this.setLocationRelativeTo(null);
		this.setTitle("学生宿舍管理系统");
		this.setResizable(false);
		popupMenu=new JPopupMenu();
		menuitem=new JMenuItem("保存表格");
		menuitem.addActionListener(this);
		popupMenu.add(menuitem);
		this.setVisible(true);
		
	}
	private void triggerEvent(MouseEvent event) { //处理事件

		if (event.isPopupTrigger()) //如果是弹出菜单事件(根据平台不同可能不同)

		popupMenu.show(event.getComponent(),event.getX(),event.getY()); //显示菜单

		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test te=new test();
		te.addMouseListener(te);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		popupMenu.show(e.getComponent(),e.getX(),e.getY()); 
		System.out.println("ddd");
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		triggerEvent(e);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		triggerEvent(e);
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
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
