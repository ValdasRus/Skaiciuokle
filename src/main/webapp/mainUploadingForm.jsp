<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<body>
<h1>
    Upload Multiple *.txt files:
</h1>
</body>
<form name="fileUploadForm" enctype="multipart/form-data" action="/" method="POST">
    <p>
        <input id="fileInput" type="file" name="uploadFiles" multiple>
    </p>
    <p>
        <input type="submit" value="Upload Files">
    </p>
</form>

<c:if test="${isNotTxt}">
    <h1>Either you forgot to select, or selected item is not a valid *.txt file</h1>
</c:if>


</html>