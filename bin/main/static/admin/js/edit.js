function setFileToNull(id, labelText) {
	var input = document.getElementById(id);
	$(input).val('');
	$(input).siblings(".custom-file-label").addClass("selected").html(labelText);
}


function customFileInputOnChange(input) {
	var fileName = $(input).val().split("\\").pop();
	$(input).siblings(".custom-file-label").addClass("selected").html(fileName);
}


function deleteFileInput(e) {
	$(e).closest('.file-upload').remove();
}


function removeFileHidden(hiddenId) {
	if (hiddenId) {
		var hidden = $("[file-id=" + hiddenId + "]");
		if (hidden != null) {
			$(hidden).remove();
		}
	}
}


$(document).ready(function() {
	
	$('button[multiple-file-upload-add]').on("click", function() {
		var multipleFileUpload = $(this).siblings(".multiple-file-upload");
		var content = $(multipleFileUpload).html();
		var formName = $(multipleFileUpload).attr('form-name');
		$(this).before(content);
		$(this).prev().find('input[type=file]').attr('name', formName);
	}).trigger("click");
	
});