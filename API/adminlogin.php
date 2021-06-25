<?php 
	require_once 'connection.php';

	if ($con) {
		$user_admin = $_POST['user_admin'];
		$pass_admin = $_POST['pass_admin'];

		$query = "SELECT * FROM admin WHERE user_admin = '$user_admin' AND pass_admin = '$pass_admin'";
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