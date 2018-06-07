<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=ko>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>Web Image Classification</title>
</head>
<body>

	<table class="table-bordered table-hover">
		<tr>
			<td><h2>이미지 추론 페이지!</h2></td>
		</tr>
		<tr>
			<td><h3>Imagenet에서 학습한<br>1000개의 사물 추론</h3></td>
		</tr>
		<tr>
			<td>
			<form action="./upload.do" id="form" name="form" method="POST"
					enctype="multipart/form-data">
					<div class="form-group">
						<label for="uploadFile">이미지 촬영</label>
						<input type="file" id="uploadFile" name="uploadFile" capture="camera" accept="image/*" class="btn btn-default btn-lg">
						<p class="help-block">추론 할 이미지 선택 및 촬영</p>
						<input type="submit" class="btn btn-success btn-lg">
					</div>
				</form>
			</td>
		</tr>
	</table>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
	</script>
</body>
</html>
