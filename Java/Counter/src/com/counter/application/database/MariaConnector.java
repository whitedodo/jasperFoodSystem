/*
 * Project Name: CounterController
 * FileName: MariaConnector.java
 * Created Date: 2019-09-03
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 19-09-04 / Dodo / MySQL(MariaDB) UTF8 ����(�ѱ� ���� ����)
 * 
 * Reference:
 * https://downloads.mariadb.com/Connectors/java/connector-java-2.4.2/
 * https://dev.mysql.com/downloads/connector/j/ (2019-09-03)
 * http://mysql.mirror.kangaroot.net/Downloads/Connector-J/ (2019-09-03)
 * => mariadb-java-client-2.4.2.jar ����� ��
 */

package com.counter.application.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MariaConnector {

	private static Connection conn = null;
	
	/* ȯ�漳�� */
	private String dbms;
	private String driver;
	private String uHostName;
	private int port;
	private String uDBName;
	private String uId;
	private String uPwd;
	private String url;
	
	public MariaConnector(){
		
		initSetting();
		connectToDB();
	}

	public void connectToDB(){
		
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uId, uPwd);
			//conn.close();
			
			if( conn != null ) {
                System.out.println("DB ���� ����");
            }
            
        } catch (ClassNotFoundException e) { 
            System.out.println("����̹� �ε� ����");
        } catch (SQLException e) {
            System.out.println("DB ���� ����");
            e.printStackTrace();
        }		
		
	}
	
	public Connection getConn(){
		return conn;
	}
	
	public void close()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initSetting(){
		

		int index = 1;
		// ���� ���
		String currentDir = System.getProperty("user.dir");
		// ���� ��ü ����
		File file = new File(currentDir + "\\serverinfo.txt");
		try {
			// �Է� ��Ʈ�� ����
			FileReader fileReader = new FileReader(file);
			// �Է� ����
			BufferedReader bufReader = new BufferedReader(fileReader);
			String line = "";
			
			while ( (line = bufReader.readLine()) != null ){
				
				switch( index ){
					
					case 1:
						dbms = line;
						break;
						
					case 2:
						driver = line;
						break;
						
					case 3:
						uHostName = line;
						break;
						
					case 4:
						port = Integer.valueOf(line);
						break;
						
					case 5:
						uDBName = line;
						break;
						
					case 6:
						uId = line;
						break;
					
					case 7:
						uPwd = line;
						break;
					
					default:
						break;
				}
				
				index++;
			}
			
			// ���� ����
			if ( dbms.contains("maria") ){
				url = "jdbc:mariadb://" + uHostName + ":" + port + "/" + uDBName + "?useUnicode=true&characterEncoding=utf8";
			}
			else if ( dbms.contains("mysql") ){
				url = "jdbc:mysql://" + uHostName + ":" + port + "/" + uDBName + "?useUnicode=true&characterEncoding=utf8";
			}
			
			bufReader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}