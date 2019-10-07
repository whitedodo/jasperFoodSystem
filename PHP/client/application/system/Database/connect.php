<?php
/*
	2019-08-31
	Author: Jasper(rabbit.white@daum.net)
	FileName: connect.php
	Goal: 
	Description:	
*/

class Connect{
	
	private $host;
	private $user;
	private $pw;
	private $dbName;
	
	public function __construct($host, $user, $pw, $dbName){
		$this->host = $host;
		$this->user = $user;
		$this->pw = $pw;
		$this->dbName = $dbName;
	}
	
	public function getHost(){
		return $this->host;	
	}
	
	public function getUser(){
		return $this->user;	
	}
	
	public function getPw(){
		return $this->pw;
	}
	
	public function getDBName(){
		return $this->dbName;
	}	
	
}
?>