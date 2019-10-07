<?php
/*
  Subject: JasperFramework Version 2
  FileName: JasperFunction.php
  Created Date: 2019-08-31
  Author: Dodo (rabbit.white@daum.net)
  Description:

*/

class JasperFunction{

    // 한글 지원
    public function convertToUTF8($strTxt)
    {
        if(iconv("utf-8", "utf-8", $strTxt) == $strTxt){
            return $strTxt;
        }
        else
        {
            return iconv("euc-kr", "utf-8", $strTxt );
        }
    }
    
    // 수행시간 측정
    public function getExecutionTime() {
        list($usec, $sec) = explode(" ", microtime());
        return ((float)$usec + (float)$sec);
    }
    
}

?>