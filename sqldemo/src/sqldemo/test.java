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
		this.setTitle("ѧ���������ϵͳ");
		this.setResizable(false);
		popupMenu=new JPopupMenu();
		menuitem=new JMenuItem("������");
		menuitem.addActionListener(this);
		popupMenu.add(menuitem);
		this.setVisible(true);
		
	}
	private void triggerEvent(MouseEvent event) { //�����¼�

		if (event.isPopupTrigger()) //����ǵ����˵��¼�(����ƽ̨��ͬ���ܲ�ͬ)

		popupMenu.show(event.getComponent(),event.getX(),event.getY()); //��ʾ�˵�

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
