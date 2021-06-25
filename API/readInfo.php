<?php 
	require_once 'connection.php';


	$query = "SELECT * FROM info";
	$result = mysqli_query($con, $query);
	$check = mysqli_affected_rows($con);

	if($check > 0){
		$response["Kode"] = 1;
		$response["Pesan"] = "Data Tersedia";
		$response["data"] = array();

		while($read = mysqli_fetch_object($result)){
			$F["id_info"] = $read -> id_info;
			$F["mk"] = $read -> mk;
			$F["judul"] = $read -> judul;
			$F["masuk"] = $read -> masuk;
			$F["deskripsi"] = $read -> deskripsi;

			array_push($response["data"], $F);
		}
	} else {
		$response["Kode"] = 0;
		$response["Pesan"] = "Data Tidak Tersedia";
	}
	
	echo json_encode($response);
	mysqli_close($con);
?>