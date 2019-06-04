package com.skiff.www;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

/** 
* @ClassName: ChromeBrower 
* @Description 绫荤殑浣滅敤: 鏀寔Chrome娴忚鍣�
* @author 浣滆��:涓�鍙舵墎鑸�(skiff)
* @date 2018骞�3鏈�9鏃� 涓嬪崍4:28:34 
*  
*/
public class ChromeBrower {

    public static void main(String[] args) {  
        final String url = "http://www.baidu.com/";  
        final String title = "鐗╄祫缃�"; 
        Browser browser = new Browser();  
        BrowserView view = new BrowserView(browser);  
  
        JFrame frame = new JFrame();  
      //绂佺敤close鍔熻兘
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);        
        //涓嶆樉绀烘爣棰樻爮,鏈�澶у寲,鏈�灏忓寲,閫�鍑烘寜閽�
        frame.setUndecorated(true);  
        frame.add(view, BorderLayout.CENTER);  
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  
        frame.setLocationByPlatform(true);  
        frame.setVisible(true);  
        browser.loadURL(url);  
    }  
    
    
}