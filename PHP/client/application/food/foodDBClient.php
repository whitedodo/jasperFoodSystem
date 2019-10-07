<?php
/*
  Subject: JasperFramework Version 2
  FileName: foodDBClient.php
  Created Date: 2019-09-01
  Author: Dodo (rabbit.white@daum.net)
  Description:

*/

class foodDBClient{
  
  private $conn;
  
  public function __construct($conn){
    $this->conn = $conn;
  }
  
  public function clientView($locationID){
    
    $index = 1;
    
    $link = mysql_connect($this->conn->getHost(), 
		                      $this->conn->getUser(), 
		                      $this->conn->getPw()) or 
		                      die('Could not connect' . mysql_error());
		                      
		mysql_set_charset('utf8',$link);
		
		mysql_select_db($this->conn->getDBName()) or die('Could not select database');
		mysql_query("set session character_set_connection=utf8;");
    mysql_query("set session character_set_results=utf8;");
    mysql_query("set session character_set_client=utf8;");

    $query = sprintf("SELECT * FROM jasper_food_client_order WHERE" .
                     " locationID = %s" .
                     " AND complete = %s" .
                     " ORDER BY id DESC",
                      mysql_real_escape_string($locationID),
                      mysql_real_escape_string("1")); // SQL 인젝션 점검

		$result = mysql_query($query) or die('Query failed: ' . mysql_error());
		
		// DB Article
		while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
		  
		  echo "&nbsp;&nbsp;&nbsp;";
      echo $row['orderID'];
 		  echo "&nbsp;&nbsp;&nbsp;";
      echo "\n";
      
      if ( $index == 5 ){
        echo "<br><br><br>";
      }
      
      $index++;
      
		}
		
		// Free resultset
		mysql_free_result($result);
		
		// Closing connection
		mysql_close($link);
		
  }
  
}

?>