<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>データ取り込み画面</h1>
	<hr>
	<div id="UploadArea">
		<h3>ファイルアップロード</h3>
		<table>
			<tr>
				<td>属性</td>
				<td>
					<form method="POST" enctype="multipart/form-data"
						action="UploadServlet">
						<input type="file" name="fl" size="75" /> <input type="submit"
							value="アップロード" />
						<input type="hidden" name="type" value="1">
					</form>
				</td>
				<td>
					<form method="GET" enctype="multipart/form-data"
						action="webapi/DataController/getAttribute.json">
						<input type="submit" value="ダウンロード" />
					</form>
				</td>
			</tr>
			<tr>
				<td>配信ルール</td>
				<td>
					<form method="POST" enctype="multipart/form-data"
						action="UploadServlet">
						<input type="file" name="fl" size="75" /> <input type="submit"
							value="アップロード" />
						<input type="hidden" name="type" value="2">
					</form>
				</td>
				<td>
					<form method="GET" enctype="multipart/form-data"
						action="webapi/DataController/getRule.json">
						<input type="submit" value="ダウンロード" />
					</form>
				</td>
			</tr>
			<tr>
				<td>コンテンツ</td>
				<td>
					<form method="POST" enctype="multipart/form-data"
						action="UploadServlet">
						<input type="file" name="fl" size="75" /> <input type="submit"
							value="アップロード" />
						<input type="hidden" name="type" value="3">
					</form>
				</td>
				<td>
					<form method="GET" enctype="multipart/form-data"
						action="webapi/DataController/getContents.json">
						<input type="submit" value="ダウンロード" />
					</form>
				</td>
			</tr>
		</table>
	</div>
	<hr>
	<div id="MessageArea">
		<h3>反映結果</h3>
		${indexBean.message}
	</div>
	<hr>
	<div id="DiplayArea">
		<h3>登録内容</h3>
		<div>
			属性
			<table border="1">
				<tr>
					<th>HEMS端末ID</th>
					<th>属性</th>
				</tr>
				<c:forEach var="entry" items="${indexBean.attributeMap}">
					<tr>
						<td><c:out value="${entry.key}" /></td>
						<td><c:out value="${entry.value}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			配信ルール
			<table border="1">
				<tr>
					<th>コンテンツID</th>
					<th>属性</th>
				</tr>
				<c:forEach var="entry" items="${indexBean.ruleMap}">
					<tr>
						<td><c:out value="${entry.key}" /></td>
						<td><c:out value="${entry.value}" /></td>
					</tr>
				</c:forEach>
			</table>

		</div>
		<div>
			コンテンツ
			<table border="1">
				<tr>
					<th>コンテンツID</th>
					<th>URl</th>
				</tr>
				<c:forEach var="entry" items="${indexBean.contentsMap}">
					<tr>
						<td><c:out value="${entry.key}" /></td>
						<td><c:out value="${entry.value}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>