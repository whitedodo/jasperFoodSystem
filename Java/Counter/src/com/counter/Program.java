/*
 * Project Name: Rabbit Counter
 * FileName: Program.java
 * Created Date: 2019-09-03
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 
 * 
*/
package com.counter;

import java.awt.EventQueue;

import com.counter.member.*;
import com.counter.view.*;

public class Program {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MainFrm frame2 = new MainFrm(1);
					Login frame = new Login();
					frame.setVisible(true);
					//frame2.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
		
	}

}
