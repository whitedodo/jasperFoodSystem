<?php
/*
  Subject: JasperFramework Security 1
  FileName: aes256.php
  Created Date: 2019-08-31
  Author: Dodo (rabbit.white@daum.net)
  Description:

*/

/* AES256 Example */

class AES256 {
  
    private $key;
    private $key_size;
    private $iv;
    private $iv_size;
    
    public function __construct(){
      # show key size use either 16, 24 or 32 byte keys for AES-128, 192
      # and 256 respectively
      $this->key = pack('H*', "bcb04b7e103a0cd8b54763051cef08bc55abe029fdebae5e1d417e2ffb2a00a3");
      $this->key_size = strlen($this->key);
      
      # create a random IV to use with CBC encoding
      $this->iv_size = mcrypt_get_iv_size(MCRYPT_RIJNDAEL_128, MCRYPT_MODE_CBC);
      $this->iv = mcrypt_create_iv($this->iv_size, MCRYPT_RAND);
    }

    function encoder($plaintext) {

      # creates a cipher text compatible with AES (Rijndael block size = 128)
      # to keep the text confidential 
      # only suitable for encoded input that never ends with value 00h
      # (because of default zero padding)
      $ciphertext = mcrypt_encrypt(MCRYPT_RIJNDAEL_128, $this->key,
                                   $plaintext, MCRYPT_MODE_CBC, $this->iv);
  
      # prepend the IV for it to be available for decryption
      $ciphertext = $this->iv . $ciphertext;
      
      # encode the resulting cipher text so it can be represented by a string
      $ciphertext_base64 = base64_encode($ciphertext);
  
      return $ciphertext_base64;
    } 
    
    function decoder($ciphertext_base64) {

      $ciphertext_dec = base64_decode($ciphertext_base64);
    
      # retrieves the IV, iv_size should be created using mcrypt_get_iv_size()
      $iv_dec = substr($ciphertext_dec, 0, $this->iv_size);
      
      # retrieves the cipher text (everything except the $iv_size in the front)
      $ciphertext_dec = substr($ciphertext_dec, $this->iv_size);
  
      # may remove 00h valued characters from end of plain text
      $plaintext_dec = mcrypt_decrypt(MCRYPT_RIJNDAEL_128, $this->key,
                                      $ciphertext_dec, MCRYPT_MODE_CBC, $iv_dec);
      
      return $plaintext_dec;
    }
}

?>