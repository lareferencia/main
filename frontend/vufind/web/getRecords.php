<?php
 $id=$_GET["id"];

if(isset($_GET["iso"]))
   $iso=$_GET["iso"];
   else
 	$iso="AR";
	
if(isset($_GET["url"]))
   $url2=$_GET["url"];
   else
 	$url2="http://200.0.207.91:8080/public/listInvalidRecordsInfoBySnapshotID/92";
	
	$json2 = file_get_contents($url2);
	$data2 = json_decode($json2, TRUE);

	$countr="";
    $countn=0;	
    $ni="";	
	
	//echo $url2;
	$datetemp="";
	$ni="";
	$countr="";
	$first=true;
	$first2=true;
    $link="";
	$lurl="";
	$ident="";
	$outputN="";
	$output7.= "<table border='0' style='font-family:Verdana;font-size:8pt;background-color:#E5ECF9'>";
	$output7.=  "<tr> <th>Pa&iacute;s</th><th>ID</th><th>Identificador</th><th>Estatus</th><th>Detalle</th><th></th></tr>";
		foreach($data2 as $red){
			foreach($red as $key => $value){
			foreach($value as $key2 => $value2){
			//echo print_r($key2);
			//echo print_r($value2);
			//echo "****************************************************";
				if ($key2==="rel")
				{
				//echo print_r($value2);
				
				    
				    $link=$value2;
					if ($link=="next") $link="siguiente";
					if ($link=="first") $link="primera";
					if ($link=="last") $link="&uacute;ltima";
					if ($link=="self") $link="actual";
					if ($link=="previous") $link="previa";
					//echo $value."-";
				}
				if ($key2==="href")
				{
				$outputN.= "<a style='font-family:Verdana;font-size:8pt;color:#2E8CB4;text-decoration: none' href='http://200.0.207.91/vufind/getRecords.php?iso=".$iso."&url=".$value2."'>".$link."</a>-";
				}
			if ($key2==="id")
				{
				//echo print_r($value2);
				    $ni=$value2;
					//echo $value."-";
				}	
				if ($key2==="identifier")
				{			
					  	$output7 .=  "<tr><td>$iso</td><td>".$ni."</td>";
						$output7 .= "<td>".$value2."</td>";
						$ident=$value2;
					 }
				 if ($key2==="status")
					  {
						$output7 .= '<td><a href="#" style="font-family:Verdana;font-size:8pt;color:#2E8CB4;text-decoration: none" onclick="window.open(\'http://200.0.207.91/vufind/getRecordValidation.php?id='.$ni.'&oid='.$ident.'\',\'reporte\',\'width=500,height=436,scrollbars=yes\');return false;">'.$value2.'</a></td>';
					 }
				 if ($key2==="belongsToCollectionDetails")
					 {
						$output7 .= "<td>".$value2."</td>";

					}
			    
					 } 
				  }
				}
			
		 $output7.= '</table>';		 

echo $outputN;
echo  $output7;
echo $outputN;
?>
