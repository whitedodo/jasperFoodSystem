<!-- Location Point -->
<table>
  <tr>
    <td>
      <h2><?php echo $foodDBclient->locationName($pageId); ?></h2>
    </td>
  </tr>
</table>

<!-- Ãâ·Â-->
<table>
  <tr>
    <td style="vertical-align:top;">
      <table class="type11">
          <thead>
          <tr>
              <th scope="cols">
                <?php
                  $foodDBclient->categoryTitle(1);
                ?>
              </th>
          </tr>
          </thead>
          
          <tbody>
          <?php
            $foodDBclient->clientView($pageId, 1);
          ?>
          </tbody>
          
      </table>  
    </td>
    <td style="vertical-align:top;">
      <table class="type11">
            <thead>
            <tr>
                <th scope="cols">
                <?php
                  $foodDBclient->categoryTitle(2);
                ?>
                </th>
            </tr>
            </thead>
            
            <tbody>
            <?php
              $foodDBclient->clientView($pageId, 2);
            ?>
            </tbody>      
      </table> 
    </td>
    <td style="vertical-align:top;">
      <table class="type11">
            <thead>
            <tr>
                <th scope="cols">
                <?php
                  $foodDBclient->categoryTitle(3);
                ?>
                </th>
            </tr>
            </thead>
            
            <tbody>
            <?php
              $foodDBclient->clientView($pageId, 3);
            ?>
            </tbody>      
      </table> 
    </td>
  </tr>
</table>