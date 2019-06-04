package sqldemo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.TitleEvent;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * 鎸哄ソ浣跨殑.....
 * @author ck
 *
 */
public class Liulanqi {//鍩轰簬鏍囩寮忕殑娴忚鍣�

 private volatile String newUrl = null;// 鏈�鏂拌緭鍏ョ殑閾炬帴

 private volatile boolean loadCompleted = false;//琛ㄧず褰撳墠椤甸潰瀹屽叏瀵煎叆
 
 private volatile boolean openNewItem=false;//琛ㄧず鏂扮殑椤甸潰鍦ㄦ柊绐楀彛涓墦寮�
 
 /*
  * 娴忚鍣ㄧ殑褰撳墠鏍囩鍙傛暟
  */
 
 private TabItem tabItem_now;//褰撳墠鏍囩椤�
 
 private Browser browser_now;//褰撳墠鍔熻兘娴忚鍣�

 /*
  * 娴忚鍣ㄨ缃弬鏁�
  */
 private String homePage = "http://api.map.baidu.com/library/MarkerManager/1.2/example/MarkerManager.html";// 娴忚鍣ㄧ殑棣栭〉

 /*
  * 娴忚鍣ㄥ褰㈠竷缃�
  */
 private Button button_back;//鍚庨��鎸夐挳

 private Button button_forward;//鍚戝墠鎸夐挳

 private Button button_go;//鍓嶈繘鎸夐挳

 private Button button_stop;//鍋滄鎸夐挳

 private Combo combo_address;// 鍦板潃鏍�

 private Browser browser_default = null;// 娴忚绐楀彛

 private ProgressBar progressBar_status;// 缃戦〉鎵撳紑杩涘害琛紝鍗抽〉闈㈠鍏ユ儏鍐垫爮

 private Label label_status;// 鏈�缁堢綉椤垫墦寮�杩囩▼鏄剧ず

 private TabFolder tabFolder;// Browser鐨勫鍣�

 private Composite composite_tool;// 宸ュ叿鏍忓尯鍩�

 private Composite composite_browser;// 娴忚绐楀彛鍖哄煙

 private Composite composite_status;// 鐘舵�佹爮鍖哄煙

 protected Display display;

 protected Shell shell_default;
 protected Shell shell_default1;
 /**
  * Launch the application
  * 
  * @param args
  */

 /**
  * Open the window
  */
 public void open() {
  display = Display.getDefault();
  shell_default = new Shell(display);
  shell_default1 = new Shell();
  createContents();

  shell_default.open();
  shell_default.layout();
  while (!shell_default.isDisposed()) {
   if (!display.readAndDispatch())
    display.sleep();
  }
 }

 /**
  * Create contents of the window
  */
 protected void createContents() {
  shell_default.setSize(800, 800);
  shell_default.setText("充电桩分布图");
  GridLayout gl_shell = new GridLayout();
  gl_shell.marginWidth = 0;// 缁勪欢涓庡鍣ㄨ竟缂樼殑姘村钩璺濈
  gl_shell.marginHeight = 0;// 缁勪欢涓庡鍣ㄨ竟缂樼殑鍨傜洿璺濈
  gl_shell.horizontalSpacing = 0;// 缁勪欢涔嬮棿鐨勬按骞宠窛绂�
  gl_shell.verticalSpacing = 0;// 缁勪欢涔嬮棿鐨勫瀭鐩磋窛绂�
  shell_default.setLayout(gl_shell);

  /*
   * 鍒涘缓娴忚鍣ㄧ晫闈�
   */
  //createMenu();//娌℃湁瀹炵幇
  createTool();
  createBrowser();
  createStatus();

  /*
   * 鍒涘缓娴忚鍣ㄧ浉鍏充簨浠剁洃鍚�
   */
  runThread();
 }

 /*
  * 鍒涘缓鍩烘湰宸ュ叿鏍忥紝涓嶅寘鎷浉鍏充簨浠剁洃鍚�
  */
 private void createTool() {

  composite_tool = new Composite(shell_default1, SWT.BORDER);
  // GridData()绗竴涓弬鏁版槸姘村钩鎺掑垪鏂瑰紡锛岀浜屼釜鍙傛暟鏄瀭鐩存帓鍒楁柟寮�,绗笁涓槸姘村钩鎶㈠崰鏄惁,绗洓涓弬鏁版槸鍨傜洿鎶㈠崰鏄惁
  GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false);
  gd_composite.heightHint = 30;// 楂樺害鍜屽搴�
  gd_composite.widthHint = 549;
  composite_tool.setLayoutData(gd_composite);
  GridLayout fl_composite = new GridLayout();
  fl_composite.numColumns = 8;
  composite_tool.setLayout(fl_composite);

  button_back = new Button(composite_tool, SWT.NONE);
  button_back.setLayoutData(new GridData(27, SWT.DEFAULT));// 璁剧疆澶у皬鍜屾牸寮�
  button_back.setText("<-");

  button_forward = new Button(composite_tool, SWT.NONE);
  button_forward.setLayoutData(new GridData(24, SWT.DEFAULT));
  button_forward.setText("->");
  
  combo_address = new Combo(composite_tool, SWT.BORDER);
  final GridData gd_combo_3 = new GridData(SWT.FILL, SWT.LEFT, true,
    false);// 鍦ㄧ獥鍙ｅ彉鍖栨椂锛岃嚜鍔ㄦ墿灞曟按骞虫柟鍚戠殑澶у皬
  gd_combo_3.widthHint = 300;// 璧峰瀹藉害
  gd_combo_3.minimumWidth = 50;// 璁剧疆鏈�灏忓搴�
  combo_address.setLayoutData(gd_combo_3);

  button_go = new Button(composite_tool, SWT.NONE);
  button_go.setLayoutData(new GridData(25, SWT.DEFAULT));
  button_go.setText("go");

  button_stop = new Button(composite_tool, SWT.NONE);
  button_stop.setLayoutData(new GridData(24, SWT.DEFAULT));
  button_stop.setText("stop");

  final Label label = new Label(composite_tool, SWT.SEPARATOR
    | SWT.VERTICAL);
  label.setLayoutData(new GridData(2, 17));

 }

 /*
  * 鍒涘缓娴忚鍣紝涓嶅寘鎷浉鍏充簨浠剁洃鍚�
  */
 private void createBrowser() {
  composite_browser = new Composite(shell_default, SWT.NONE);
  final GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true,
    true);// 鍏呮弧绐楀彛,涓旀按骞冲拰鍨傜洿鏂瑰悜闅忕獥鍙ｈ�屽彉
  gd_composite.heightHint = 273;
  composite_browser.setLayoutData(gd_composite);
  GridLayout gl_composite = new GridLayout();
  gl_composite.marginHeight = 0;// 浣跨粍浠朵笂涓嬫柟鍚戝鍣�
  gl_composite.marginWidth = 0;// 浣跨粍浠跺乏鍙虫柟鍚戝崰婊″鍣�
  composite_browser.setLayout(gl_composite);

  tabFolder = new TabFolder(composite_browser, SWT.NONE);
  final GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true,
    true);
  gd_tabFolder.heightHint = 312;
  gd_tabFolder.widthHint = 585;
  tabFolder.setLayoutData(gd_tabFolder);  
  
  /*
   * 涓烘爣绛炬坊鍔犲彸閿姛鑳�
   */
  tabFolder.addMouseListener(new MouseAdapter(){
   @Override
   public void mouseUp(MouseEvent e) {
    if(e.button==3){//鍙抽敭
     Menu menu_itemRightMouse=new Menu(shell_default,SWT.POP_UP);
     tabFolder.setMenu(menu_itemRightMouse);
     MenuItem menuItem_itemClose=new MenuItem(menu_itemRightMouse,SWT.NONE);
     menuItem_itemClose.setText("鍏抽棴褰撳墠鏍囩");
     menuItem_itemClose.addSelectionListener(new SelectionAdapter(){
      @Override
      public void widgetSelected(SelectionEvent e) {
       if(tabFolder.getItemCount()!=1){//涓嶆槸鍙瓨鍦ㄤ竴涓爣绛剧殑鎯呭喌涓�
        browser_now.dispose();
        tabItem_now.dispose();
        tabFolder.redraw();
       }else{//鍙湁涓�涓爣绛�
        browser_now.setUrl(":blank");
        browser_now.setText("");
       }
      }
     });
     MenuItem menuItem_itemCloseAll=new MenuItem(menu_itemRightMouse,SWT.NONE);
     menuItem_itemCloseAll.setText("鍏抽棴鎵�鏈夋爣绛�");
     menuItem_itemCloseAll.addSelectionListener(new SelectionAdapter(){
      @Override
      public void widgetSelected(SelectionEvent e) {
       shell_default.close();
      }
     });
    }
   }
  });
    

  final TabItem tabItem_default = new TabItem(tabFolder, SWT.NONE);
  browser_default = new Browser(tabFolder, SWT.NONE);
  tabItem_default.setControl(browser_default);
  browser_default.setUrl(homePage);// 鏄剧ず娴忚鍣ㄩ椤�
  
  
  /*
   * 鎶婂垵濮嬪寲鐨勬爣绛剧疆椤�,閫変腑
   */
  tabFolder.setSelection(tabItem_default);

 }

 /*
  * 鍒涘缓娴忚鍣ㄥ簳閮ㄧ姸鎬佹爮锛屼笉鍖呮嫭鐩稿叧浜嬩欢鐩戝惉
  */
 private void createStatus() {
  composite_status = new Composite(shell_default, SWT.NONE);
  final GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true,
    false);// 鍙傛暟true浣跨姸鎬佹爮鍙互鑷姩姘村钩浼哥缉
  gd_composite.heightHint = 20;
  gd_composite.widthHint = 367;
  composite_status.setLayoutData(gd_composite);
  GridLayout gl_composite = new GridLayout();
  gl_composite.numColumns = 2;
  gl_composite.marginBottom = 5;
  composite_status.setLayout(gl_composite);

  label_status = new Label(composite_status, SWT.NONE);
  GridData gd_status = new GridData(SWT.FILL, SWT.CENTER, true, false);
  gd_status.heightHint = 13;
  gd_status.widthHint = 525;
  label_status.setLayoutData(gd_status);

  progressBar_status = new ProgressBar(composite_status, SWT.BORDER
    | SWT.SMOOTH);
  progressBar_status.setLayoutData(new GridData(80, 12));
  progressBar_status.setVisible(false);// 鎵撳紑杩囩▼鍒濆涓嶅彲瑙�

 }

 private void runThread( ) {
  
  /*
   * 娴忚鍣ㄦ柊鏍囩鍓嶈繘銆佸悗閫�鎸夐挳鐨勯粯璁ゅ彲鐢ㄦ�т负涓嶅彲鐢�
   */
  button_back.setEnabled(false);
  button_forward.setEnabled(false);
  
  /*
   * 鑾峰彇娴忚鍣ㄧ殑褰撳墠鏍囩鍜屽姛鑳紹rowser
   */
  tabItem_now=tabFolder.getItem(tabFolder.getSelectionIndex());
  browser_now=(Browser) tabItem_now.getControl();
   
  /*
   * 閫変腑浜嬩欢鍙戠敓鏃讹紝淇敼褰撳墠娴忚鍣ㄦ爣绛�
   */
  tabFolder.addSelectionListener(new SelectionAdapter(){
   @Override
   public void widgetSelected(SelectionEvent e) {
    TabItem temp=(TabItem) e.item;
    if(temp!=tabItem_now){//闃叉閲嶉�変竴涓爣绛撅紝棰勯槻澶氭瑙﹀彂鐩稿悓浜嬩欢
     tabItem_now=temp;
     browser_now=(Browser)tabItem_now.getControl();
     //System.out.println("褰撳墠鏍囩琚慨鏀逛簡");//璋冭瘯璇彞
     
     /*
      * 鍦ㄧ浉搴旂殑鏍囩涓紝鍓嶈繘銆佸悗閫�鎸夐挳鍙敤鎬ф槸涓嶄竴鏍风殑
      */
     if(browser_now.isBackEnabled()){//鍚庨��鎸夐挳鐨勫彲鐢ㄦ��
      button_back.setEnabled(true);
     }else{
      button_back.setEnabled(false);
     }
     if(browser_now.isForwardEnabled()){//鍓嶈繘鎸夐挳鐨勫彲鐢ㄦ��
      button_forward.setEnabled(true);
     }else{
      button_forward.setEnabled(false);
     }
      
    }
   }
  }); 
  
  /*
   * 娣诲姞娴忚鍣ㄧ殑鍚庨��銆佸悜鍓嶃�佸墠杩涖�佸仠姝㈡寜閽簨浠剁洃鍚�
   */
  button_back.addSelectionListener(new SelectionAdapter() {
   @Override
   public void widgetSelected(SelectionEvent arg0) {
    if (browser_now.isBackEnabled()){//鏈鍙悗閫�
     browser_now.back();
     button_forward.setEnabled(true);//涓嬫鍙墠杩涳紝鍓嶈繘鎸夐挳鍙敤
     //System.out.println("鍙悗閫�");//璋冭瘯璇彞
    }
    if(!browser_now.isBackEnabled()){//涓嬫涓嶅彲鍚庨��锛屽悗閫�鎸夐挳涓嶅彲鐢�
     button_back.setEnabled(false);
    }
   }
  });

  button_forward.addSelectionListener(new SelectionAdapter() {
   @Override
   public void widgetSelected(SelectionEvent arg0) {
    if (browser_now.isForwardEnabled()){//鏈鍙墠杩�
     browser_now.forward();
     button_back.setEnabled(true);//鍚庨��鎸夐挳鍙敤
     //System.out.println("鍙悜鍓�");//璋冭瘯璇彞
    }
    if(!browser_now.isForwardEnabled()){//涓嬫涓嶅彲鍓嶈繘锛屽墠杩涙寜閽笉鍙敤
     button_forward.setEnabled(false);     
    }
   }
  });

  button_stop.addSelectionListener(new SelectionAdapter() {
   @Override
   public void widgetSelected(SelectionEvent arg0) {
    browser_now.stop();
   }
  });
  
  combo_address.addKeyListener(new KeyAdapter() {// 鎵嬪姩杈撳叆鍦板潃鏍忓悗锛屾寜鍥炶溅閿浆鍒扮浉搴旂綉鍧�
   @Override
   public void keyReleased(KeyEvent e) {
    if (e.keyCode == SWT.CR) {//鍥炶溅閿Е鍙戜簨浠�
     browser_now.setUrl(combo_address.getText());
    }
   }
  });

  /*
   * 1>鍦╝ddOpenWindowListener()涓嬬殑open()鍐欏叆e.browser = browser_new鎯呭喌涓嬶紝瀵煎叆鏂扮殑瓒呯骇閾炬帴,
   * 鍙湁褰撶偣鍑婚〉闈笂鐨勯摼鎺�,涓旈摼鎺ヤ笉鍦ㄦ柊鐨勯〉闈㈡墦寮�鏃舵墠浼氬彂鐢�.
   * 2>鍦╝ddOpenWindowListener()涓嬬殑open()涓嶅啓鍏.browser = browser_new鎯呭喌涓嬶紝瀵煎叆鏂扮殑瓒呯骇閾炬帴锛�
   * 鍙湁褰撶偣鍑婚〉闈笂鐨勯摼鎺�,涓旈摼鎺ュ湪鏂扮殑椤甸潰鎵撳紑鏃舵墠浼氬彂鐢�.
   * 闄や簡浠ヤ笂涓ょ澶栵紝褰撶劧杩樺寘鎷琤rowser.back()銆乥rowser.forward()銆乥rowser.go()銆乥rowser.setUrl()鍙戠敓鏃惰Е鍙�,
   * 浣哻hanging()鍦ㄤ笉鍐欏叆e.browser = browser_new鎯呭喌涓�,涓嶈browser.setUrl()瑙﹀彂
   */
  browser_now.addLocationListener(new LocationAdapter() {
   @Override
   public void changing(LocationEvent e) {// 琛ㄧず瓒呯骇閾炬帴鍦板潃鏀瑰彉浜�
    if(openNewItem==false){//鏂扮殑椤甸潰鍦ㄥ悓涓�鏍囩涓墦寮�
     button_back.setEnabled(true);//鍚庨��鎸夐挳鍙敤,姝ゅ彞鏄悗閫�鎸夐挳鍙敤鍒ゅ畾鐨勯�昏緫寮�濮嬬偣
    }
    //System.out.println("location_changing");// 璋冭瘯璇彞
   }

   @Override
   public void changed(LocationEvent e) {// 鎵惧埌浜嗛〉闈㈤摼鎺ュ湴鍧�
    combo_address.setText(e.location);// 鏀瑰彉閾炬帴鍦板潃鏄剧ず
    /*
     * 鏂扮殑椤甸潰宸茬粡鎵撳紑,browser鐨凩ocationListener宸茬粡鐩戝惉瀹屾瘯,openNewItem鍥炲榛樿鍊�
     */
    if(openNewItem==true){
     openNewItem=false;
    }
    //System.out.println("location_changed");// 璋冭瘯璇彞
    
   }

  });

  /*
   *  鏂扮殑瓒呯骇閾炬帴椤甸潰鐨勫鍏ョ殑鐧惧垎姣�,鍦ㄥ鍏ユ柊鐨勯〉闈㈡椂鍙戠敓锛屾鏃堕摼鎺ュ湴鍧�宸茬煡
   */
  browser_now.addProgressListener(new ProgressAdapter() {
   @Override
   public void changed(ProgressEvent e) {//鏈簨浠朵笉鏂彂鐢熶簬椤甸潰鐨勫鍏ヨ繃绋嬩腑
    progressBar_status.setMaximum(e.total);// e.total琛ㄧず浠庢渶寮�濮嬮〉闈㈠埌鏈�缁堥〉闈㈢殑鏁板��
    progressBar_status.setSelection(e.current);
    if (e.current != e.total) {//椤甸潰杩樻病瀹屽叏瀵煎叆
     loadCompleted = false;
     progressBar_status.setVisible(true);// 椤甸潰鐨勫鍏ユ儏鍐垫爮鍙
    } else {
     loadCompleted = true;
     progressBar_status.setVisible(false);// 椤甸潰瀵煎叆鎯呭喌鏍忎笉鍙
    }
    //System.out.println("progress_changed");//璋冭瘯璇彞

   }

   @Override
   public void completed(ProgressEvent arg0) {//鍙戠敓鍦ㄤ竴娆″鍏ラ〉闈㈡椂,鏈洃鍚櫒changed浜嬩欢鏈�鍚庝竴娆″彂鐢熶箣鍓�
    //System.out.println("progress_completed");//璋冭瘯璇彞
   }
  });

  /*
   *  鑾峰彇椤甸潰鍐呭杩囩▼,鏂囧瓧鏄剧ずaddProgressListener()杩囩▼,鍚屾椂杩樿兘妫�娴嬪埌宸叉墦寮�椤甸潰鐨勫瓨鍦ㄧ殑瓒呯骇閾炬帴,灏辨槸鐢ㄧ粰鍔熻兘鏉ヨ幏鍙�
   *  鏂扮殑閾炬帴鍦板潃鐨�
   */
  browser_now.addStatusTextListener(new StatusTextListener() {
   public void changed(StatusTextEvent e) {
    if (loadCompleted == false) {
     label_status.setText(e.text);
    } else {
     newUrl = e.text;//椤甸潰瀵煎叆瀹屾垚锛屾崟鎹夐〉闈笂鍙兘鎵撳紑鐨勯摼鎺�
    }
    //System.out.println("statusText_changed");//璋冭瘯璇彞
   }
  });

  /*
   * 鏄剧ず椤甸潰鐨勬彁绀鸿鍙ワ紝鍦ㄦ柊鐨勯〉闈㈠鍏ユ椂鍙戠敓
   */
  browser_now.addTitleListener(new TitleListener() {
   public void changed(TitleEvent e) {
    shell_default.setText("充电桩分布图");
    if (e.title.length() > 0) {//鏄剧ず褰撳墠椤甸潰鎻愮ず瀛楃鍦ㄦ爣绛句笂
    // tabItem_now.setText(e.title.substring(0, 3) + "..");
    } else {
     tabItem_now.setText(e.title);
    }
   // tabItem_now.setToolTipText(e.title);//鏍囩鏄剧ず鎻愮ず绗�
   }
  });

  /*
   * 鎵撳紑鏂扮殑椤甸潰锛屽綋鍓嶆墦寮�椤甸潰鏂扮殑閾炬帴闇�瑕佸湪鏂扮殑绐楀彛椤甸潰鎵撳紑鏃跺彂鐢�.addOpenWindowListener涓媜pen()涓殑涓�鍙�
   * e.browser = browser_new;鍏抽敭閮ㄥ垎.鑱旂郴addOpenWindowListener銆乤ddVisibilityWindowListener
   * 鍜宎ddDisposeListener鐨勫�间紶閫掓灑绾�
   */
  browser_now.addOpenWindowListener(new OpenWindowListener() {// 鍦ㄥ綋鍓嶉〉闈腑鎵撳紑鐐瑰嚮鐨勯摼鎺ラ〉闈�
     public void open(WindowEvent e) {
      Browser browser_new = new Browser(tabFolder, SWT.NONE);
      TabItem tabItem_new = new TabItem(tabFolder, SWT.NONE);
      tabItem_new.setControl(browser_new);
      tabFolder.setSelection(tabItem_new);//鏂版墦寮�鐨勯〉闈㈡爣绛剧疆椤�
      tabFolder.redraw();//鍒锋柊瀹瑰櫒
      browser_new.setUrl(newUrl);//鏂版爣绛句腑璁剧疆鏂扮殑閾炬帴鍦板潃
      openNewItem=true;//鏂扮殑椤甸潰鍦ㄦ柊鐨勬爣绛句腑鎵撳紑
   
      /*
       * 鍏抽敭閮ㄥ垎,鍛婄煡鏂扮殑椤甸潰鐢眀rowser_new鎵撳紑,鍙瀹炵幇杩欏彞灏变笉浼氬脊鍑烘搷浣滅郴缁熼粯璁ょ殑娴忚鍣ㄤ簡
       */
      e.browser = browser_new;
      //System.out.println("OpenWindowListener_open");//璋冭瘯璇彞
      
      /*
       * 涓烘祻瑙堝櫒鏂扮殑鏍囩娣诲姞浜嬩欢鐩戝惉
       */
      display.syncExec(new Runnable(){
       public void run() {
        runThread(); 
       }
      });
           
      
     }
    });
  
  /*
   * 娴忚鍣ㄥ叧闂簨浠�,鍏抽棴褰撳墠鍔熻兘娴忚鍣�,涓嶇劧鐨勮瘽娴忚鍣ㄤ富绐楀彛鍏抽棴浜嗭紝杩樻湁杩涚▼鍦ㄨ繍琛�
   */
  browser_now.addCloseWindowListener(new CloseWindowListener(){
   public void close(WindowEvent e) {
    browser_now.dispose();
   }
  });

 }
}

