<?php
/*
  Subject: JasperFramework Version 2
  FileName: index.php
  Created Date: 2019-08-31
  Author: Dodo (rabbit.white@daum.net)
  Description:

*/

header("Content-Type: text/html; charset=UTF-8");
header('X-Frame-Options: DENY');  // 'X-Frame-Options'

require_once 'controller.php';

$root = "C:\APM_Setup\htdocs\client";
$directories = '';

$realDir = $root . $directories;
require_once $realDir . '/application/system/JasperFunction.php';
require_once $realDir . '/application/system/AES256.php';
require_once $realDir . '/application/system/Database/connect.php';
require_once $realDir . '/model/application/thirdModel.php';

$jasperFunction = new JasperFunction();

$pageName = $jasperFunction->convertToUTF8( $_GET['pageName'] );
$subName = $jasperFunction->convertToUTF8( $_GET['subName'] );
$pageID = $jasperFunction->convertToUTF8( $_GET['pageID'] );

$thirdModel = new ThirdModel( $pageName, $subName, $pageID );

$controller = new Controller( $thirdModel, $realDir );
$controller->run();

?>