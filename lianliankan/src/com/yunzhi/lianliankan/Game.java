package com.yunzhi.lianliankan;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
class Game implements ActionListener 
{ 
JFrame mainFrame; 
int SCRW;
int SCRH;
int COLS=10;
int ROWS=5;
JLabel label;
Dimension screen;
JPanel mainpanel,centerPanel,southPanel,northPanel;
ImageIcon img;
java.awt.Image imgi;
JButton aniButton[][] = new JButton[COLS][ROWS];
JButton exitButton,randButton,newlyButton; 
JLabel score=new JLabel("0"); 
JLabel fraction;
JButton firstButton,secondButton;
int grid[][] = new int[COLS+2][ROWS+2];
static boolean pressInformation=false; 
int x0=0,y0=0,x=0,y=0,fristMsg=0,secondMsg=0,validateLV; 
int i,j,k,n;
int imgw,imgh;
public void init(){
screen=Toolkit.getDefaultToolkit().getScreenSize();
img=new ImageIcon("src/youxi.png");
SCRH=img.getIconHeight();
SCRW=img.getIconWidth();
mainFrame=new JFrame("连连看"); 
mainFrame.setBounds(screen.width/2-SCRW/2,screen.height/2-SCRH/2,SCRW,SCRH);
label=new JLabel(img);
label.setBounds(0,0,SCRW,SCRH);
mainFrame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
img=new ImageIcon("src/1.png");
imgi=img.getImage().getScaledInstance(55,55,1);
img.setImage(imgi);
imgw=img.getIconWidth()+2;
imgh=img.getIconHeight()+2;
mainpanel=new JPanel();
mainFrame.setContentPane(mainpanel);
mainpanel.setLayout(new BorderLayout());
mainpanel.setOpaque(false);
centerPanel=new JPanel(); 
southPanel=new JPanel(); 
northPanel=new JPanel();
centerPanel.setOpaque(false);
southPanel.setOpaque(false);
northPanel.setOpaque(false);
mainpanel.add(centerPanel,"Center"); 
mainpanel.add(southPanel,"South"); 
mainpanel.add(northPanel,"North"); 
centerPanel.setLayout(null); 
for(int cols = 0;cols < COLS;cols++){ 
for(int rows = 0;rows < ROWS;rows++ ){
img=new ImageIcon("src/"+String.valueOf(grid[cols+1][rows+1])+".png");
imgi=img.getImage().getScaledInstance(55,55,1);
img.setImage(imgi);
aniButton[cols][rows]=new JButton(img);
aniButton[cols][rows].setLayout(null);
aniButton[cols][rows].setBounds(SCRW/2-COLS*imgw/2+cols*imgw,rows*imgh,
img.getIconWidth(),img.getIconHeight());
aniButton[cols][rows].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
aniButton[cols][rows].setBorder(null);
aniButton[cols][rows].setContentAreaFilled(false);
aniButton[cols][rows].setMargin(null);
aniButton[cols][rows].addActionListener(this); 
centerPanel.add(aniButton[cols][rows]); 
} 
} 
exitButton=new JButton("返回"); 
exitButton.setContentAreaFilled(false);
exitButton.setBorder(null);
exitButton.addActionListener(this);
img=new ImageIcon("src/shuaxin.png");
randButton=new JButton("随机");
randButton.setContentAreaFilled(false);
randButton.setBorder(null);
randButton.addActionListener(this); 
newlyButton=new JButton("刷新");
newlyButton.setContentAreaFilled(false);
newlyButton.setBorder(null);
newlyButton.addActionListener(this); 
southPanel.add(exitButton); 
southPanel.add(randButton); 
southPanel.add(newlyButton); 
score.setText(String.valueOf(Integer.parseInt(score.getText())));
img=new ImageIcon("src/fenshu.png");
fraction=new JLabel(img);
northPanel.add(fraction);
northPanel.add(score); 
mainFrame.setVisible(true); 
} 
public void randomBuild()
{ 
	int randoms,cols,rows; 
	for(int twins=1;twins<=COLS*ROWS/2;twins++) 
	{ 
		randoms=(int)(Math.random()*9+1); 
		for(int alike=1;alike<=2;alike++) 
		{ 
			cols=(int)(Math.random()*COLS+1); 
			rows=(int)(Math.random()*ROWS+1); 
			while(grid[cols][rows]!=0) 
			{ 
			cols=(int)(Math.random()*COLS+1); 
			rows=(int)(Math.random()*ROWS+1); 
			} 
		this.grid[cols][rows]=randoms; 
		} 
	} 
} 
public void fraction()
{ 
score.setText(String.valueOf(Integer.parseInt(score.getText())+100)); 
} 
public void reload() 
{ 
int save[] = new int[COLS*ROWS]; 
int n=0,cols,rows; 
int grid[][]= new int[COLS+10][ROWS+10]; 
for(int i=0;i<=COLS;i++) { 
for(int j=0;j<=ROWS;j++) { 
if(this.grid[i][j]!=0) { 
save[n]=this.grid[i][j]; 
n++; 
} 
} 
} 
n=n-1; 
this.grid=grid; 
while(n>=0) { 
cols=(int)(Math.random()*COLS+1); 
rows=(int)(Math.random()*ROWS+1); 
while(grid[cols][rows]!=0) { 
cols=(int)(Math.random()*COLS+1); 
rows=(int)(Math.random()*COLS+1); 
} 
this.grid[cols][rows]=save[n]; 
n--; 
} 
mainFrame.setVisible(false); 
pressInformation=false; 
init(); 
for(int i = 0;i < COLS;i++){ 
for(int j = 0;j < ROWS;j++ ){ 
if(grid[i+1][j+1]==0) 
aniButton[i][j].setVisible(false); 
} 
} 
} 
public void estimateEven(int placeX,int placeY,JButton bz) 
{ 
	if(pressInformation==false) 
	{ 
		x=placeX; 
		y=placeY; 
		secondMsg=grid[x][y]; 
		secondButton=bz; 
		pressInformation=true; 
	} 
	else 
	{ 
		x0=x; 
		y0=y; 
		fristMsg=secondMsg; 
		firstButton=secondButton; 
		x=placeX; 
		y=placeY; 
		secondMsg=grid[x][y]; 
		secondButton=bz; 
		if(fristMsg==secondMsg && secondButton!=firstButton)xiao();
	} 
} 
public void xiao() 
{  
	if((x0==x &&(y0==y+1||y0==y-1)) || ((x0==x+1||x0==x-1)&&(y0==y))) remove(); 
	else
	{ 
		for (j=0;j<ROWS+2;j++ ) 
		{ 
			if (grid[x0][j]==0)
			{ 
				if (y>j)
				{ 
					for (i=y-1;i>=j;i-- )
					{ 
						if (grid[x][i]!=0) 
						{ 
							k=0; 
							break; 
						} 
						else  k=1;
					} 
					if (k==1) 
					{ 
						linePassOne(); 
					} 
				} 
				if (y<j)
				{ 
					for (i=y+1;i<=j ;i++ )
					{  
						if (grid[x][i]!=0)
						{ 
							k=0; 
							break; 
						} 
						else { k=1; } 
					} 
					if (k==1)
					{ 
						linePassOne(); 
					} 
				} 
				if (y==j )
				{ 
					linePassOne(); 
				} 
			} 
			if (k==2) 
			{ 
				if (x0==x) 
				{ 
					remove(); 
				} 
				if (x0<x) 
				{ 
					for (n=x0;n<=x-1;n++ ) 
					{ 
						if (grid[n][j]!=0) 
						{ 
							k=0; 
							break; 
						} 
						if(grid[n][j]==0 && n==x-1) 
						{ 
							remove(); 
						} 
					} 
				} 
				if (x0>x) 
				{ 
					for (n=x0;n>=x+1 ;n-- ) 
					{ 
						if (grid[n][j]!=0) 
						{ 
							k=0; 
							break; 
						} 
						if(grid[n][j]==0 && n==x+1) 
						{ 
							remove(); 
						} 
					} 
				} 
			} 
		} 
		for (i=0;i<COLS+2;i++ ) 
		{
			if (grid[i][y0]==0) 
			{ 
				if (x>i) 
				{ 
					for (j=x-1;j>=i ;j-- ) 
					{ 
						if (grid[j][y]!=0) 
						{ 
							k=0; 
							break; 
						} 
						else { k=1; } 
					} 
					if (k==1) 
					{ 
						rowPassOne(); 
					} 
				} 
				if (x<i) 
				{ 
					for (j=x+1;j<=i;j++ ) 
					{ 
						if (grid[j][y]!=0) 
						{ 
							k=0; 
							break; 
						} 
						else { k=1; } 
					} 
					if (k==1) 
					{ 
						rowPassOne(); 
					} 
				} 
				if (x==i) 
				{ 
					rowPassOne(); 
				} 
			} 
			if (k==2)
			{ 
				if (y0==y) 
				{ 
					remove(); 
				} 
				if (y0<y) 
				{ 
					for (n=y0;n<=y-1 ;n++ ) 
					{ 
						if (grid[i][n]!=0) 
						{ 
							k=0; 
							break; 
						} 
						if(grid[i][n]==0 && n==y-1) 
						{ 
							remove(); 
						} 
					} 
				} 
				if (y0>y) { 
					for (n=y0;n>=y+1 ;n--) 
					{ 
						if (grid[i][n]!=0) 
						{ 
							k=0; 
							break; 
						} 
						if(grid[i][n]==0 && n==y+1) 
						{ 
							remove(); 
						} 
					} 
				} 
			} 
		} 
	} 
} 
public void linePassOne(){ 
if (y0>j){ 
for (i=y0-1;i>=j ;i-- ){ 
if (grid[x0][i]!=0) { 
k=0; 
break; 
} 
else { k=2; } 
} 
} 
if (y0<j){  
for (i=y0+1;i<=j ;i++){ 
if (grid[x0][i]!=0) { 
k=0; 
break; 
} 
else{ k=2; } 
} 
} 
} 
public void rowPassOne(){ 
if (x0>i) { 
for (j=x0-1;j>=i ;j-- ) { 
if (grid[j][y0]!=0) { 
k=0; 
break; 
} 
else { k=2; } 
} 
} 
if (x0<i) { 
for (j=x0+1;j<=i ;j++ ) { 
if (grid[j][y0]!=0) { 
k=0; 
break; 
} 
else { k=2; } 
} 
} 
} 
public void remove(){ 
firstButton.setVisible(false); 
secondButton.setVisible(false); 
fraction(); 
pressInformation=false; 
k=0; 
grid[x0][y0]=0; 
grid[x][y]=0; 
} 
public void actionPerformed(ActionEvent e)
{ 
		if(e.getSource()==newlyButton)
		{ 
			int grid[][] = new int[COLS+2][ROWS+2]; 
			this.grid = grid; 
			randomBuild(); 
			mainFrame.setVisible(false); 
			pressInformation=false; 
			init(); 
		} 
		if(e.getSource()==exitButton) 
		{
			new Lianliankan();mainFrame.dispose();} 
			if(e.getSource()==randButton) 
			reload(); 
			for(int cols = 0;cols <COLS;cols++){ 
			for(int rows = 0;rows < ROWS;rows++ ){ 
			if(e.getSource()==aniButton[cols][rows]) 
			estimateEven(cols+1,rows+1,aniButton[cols][rows]); 
			} 
		} 
	}
} 


