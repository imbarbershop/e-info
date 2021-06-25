<?php 
	require_once 'connection.php';

	if ($con) {
		$nim = $_POST['nim'];
		$pass = $_POST['pass'];

		$query = "SELECT * FROM mahasiswa WHERE nim = '$nim' AND pass = '$pass'";
		$result = mysqli_query($con, $query);
		$response = array();

		$row = mysqli_num_rows($result);

		if ($row > 0) {
			array_push($response, array(
				'status' => 'OK'
			));
		} else {
			array_push($response, array(
				'status' => 'failed'
			));
		}
	} else {
		array_push($response, array(
			'status' => 'failed'
		));
	}

	echo json_encode(array('server_response' => $response));
	mysqli_close($con);
?>