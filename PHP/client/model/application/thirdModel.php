<?php

class ThirdModel{
  
  private $pageName;
  private $subName;
  private $pageID;
  
  public function __construct($pageName, $subName, $pageID){
    $this->pageName = $pageName;
    $this->subName = $subName;
    $this->pageID = $pageID;
  }
  
  public function __destruct(){
    
  }
  
  public function getPageName(){
    return $this->pageName;
  }
  
  public function getSubName(){
    return $this->subName;
  }
  
  public function getPageID(){
    return $this->pageID;
  }
  
  public function setPageName($pageName){
    $this->pageName = $pageName;
  }
  
  public function setSubName($subName){
    $this->subName = $subName;
  }
  
  public function setPageID($pageID){
    $this->pageID = $pageID;
  }
  
}

?>