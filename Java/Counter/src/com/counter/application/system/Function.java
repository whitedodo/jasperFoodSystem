/*
 * Project Name: Rabbit Counter
 * FileName: Function.java
 * Created Date: 2019-09-03
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 
 * 
*/
package com.counter.application.system;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.text.BadLocationException;

public class Function {

	public KeyAdapter checkKeyAdapter(){
		
		KeyAdapter keyAdapter = new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (!((Character.isDigit(c) || 
							(c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
						e.consume();
				}
			}
		};
		
		return keyAdapter;
	}
}

