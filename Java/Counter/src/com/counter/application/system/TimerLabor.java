package com.counter.application.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimerLabor implements ActionListener {

	private int n = 0;
	
	public void actionPerformed(ActionEvent e) {
		
		Date date = new Date();
		n++;
		
		System.out.println(n + "번째 호출:" + date.toString() );
	}
	
}
