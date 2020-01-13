function setInputFilter(textbox, inputFilter, min, max) {
	["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(function(event) {
		textbox.addEventListener(event, function() {
	    	if (inputFilter(this.value, min, max)) {
	        	this.oldValue = this.value;
	        	this.oldSelectionStart = this.selectionStart;
	        	this.oldSelectionEnd = this.selectionEnd;
	      	} else if (this.hasOwnProperty("oldValue")) {
	        	this.value = this.oldValue;
	        	this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
	      	} else {
	        	this.value = "";
	      	}
    	});
	});
}


function integerFilter(value, min, max) {
	var regexp = /^-?\d*$/;
	
	if (min && min >= 0) {
		regexp = /^\d*$/;
	} 
		
	if (regexp.test(value)) {
		if (/^-\d*$/.test(value)) {
			value = -value; 
		}
		
		if (min) {
			if (value < min) {
				return false;
			}
		}
		
		if (max) {
			if (value > max) {
				return false;
			}
		}
		
		return true;
	} else {
		return false;
	}
}



function floatFilter(value, min, max) {
	var regexp = /^-?\d*\.?\d*$/;
	
	if (min && min >= 0) {
		regexp = /^\d*\.?\d*$/;
	} 
	
	if (regexp.test(value)) {
		if (/^-\d*\.?\d*$/.test(value)) {
			value = -value; 
		}
		
		if (min) {
			if (value < min) {
				return false;
			}
		}
		
		if (max) {
			if (value > max) {
				return false;
			}
		}
		
		return true;
	} else {
		return false;
	}
}


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
