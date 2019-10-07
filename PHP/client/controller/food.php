<?php
/*
  Subject: JasperFramework Version 2
  FileName: food.php
  Created Date: 2019-08-31
  Author: Dodo (rabbit.white@daum.net)
  Description:

*/

class Food{
  
  private $conn;
  private $skin_dir;
  
  public function __construct($conn, $skin_dir){
    $this->conn = $conn;
    $this->skin_dir = $skin_dir;
  }
  
  public function Home(){
    echo '야호';
  }
  
  public function Client($pageId){
    
    $foodDBclient = new foodDBClient($this->conn);
    $skin_dir = $this->skin_dir;
    $title = "매장 - 클라이언트";
    $locationID = $pageId;
    require_once './view/food/client/head.php';
    require_once './view/food/client/body.php';
    require_once './view/food/client/foot.php';
  }
  
  public function Counter($pageId){
    
    $foodDBclient = new foodDBClient($this->conn);
    $skin_dir = $this->skin_dir;
    $title = "매장 - 카운터";
    $locationID = $pageId;
    require_once './view/food/counter/head.php';
    require_once './view/food/counter/body.php';
    require_once './view/food/counter/foot.php';
  }
  
}

?>