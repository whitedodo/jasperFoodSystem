<!-- 제목명 -->

<table class="type10">
  <tbody>
    <tr>
      <td>
        주문번호
      </td>
    </tr>
  </tbody>      
</table> 

<!-- 카운터 -->
<table class="type11">
  <tbody>
    <tr>
      <td>
      <?php
        $foodDBclient->clientView($locationID);
      ?>
      </td>
    </tr>
  </tbody>      
</table> 
