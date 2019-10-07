<?php
/*
  Subject: JasperFramework Version 2
  FileName: controller.php
  Created Date: 2019-08-31
  Author: Dodo (rabbit.white@daum.net)
  Description:

*/

class Controller{
  
	private $conn;
  private $directories;
  private $thirdModel;
  
  function __construct($thirdModel, $directories){
 		$this->conn = new Connect('localhost', 'rabbit2me', '1234', 'rabbit2me');
    $this->thirdModel = $thirdModel;
    $this->directories = $directories;
  }
  
  function __destruct(){
    unset($this->thirdModel);
  }
  
  private function getConn(){
    return $this->conn;
  }
  
  public function run(){
    
    $connect = $this->getConn();
    
    require_once $this->directories . '/controller/food.php';
    require_once $this->directories . '/application/food/foodDBClient.php';
    //require_once $this->directories . '/controller/board.php';
    //require_once $this->directories . '/controller/member.php';
    
    $thirdModel = $this->thirdModel;
    
    $pageName = $thirdModel->getPageName();
    $subName = $thirdModel->getSubName();
    $pageId = $thirdModel->getPageId();
    
    // аж╧╝
    if ( strcasecmp ( $pageName, 'food' ) == 0 ){
      $food = new Food($connect, '/client');
      
      if ( strcasecmp ( $subName, 'client') == 0 ){    
        $food->Client( $pageId );
      }
      else if ( strcasecmp ( $subName, 'counter') == 0 ){
        $food->Counter( $pageId );
      }
    }
    
    if ( strcasecmp ( $pageName, 'board' ) == 0 ){
      $board = new Food($connect);
      $board->Home();
    }
    
  }
  
};

?>