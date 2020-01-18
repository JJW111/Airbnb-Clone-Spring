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
	
	if (typeof min != 'undefined' && min != null && min >= 0) {
		regexp = /^\d*$/;
	}
	
	if (regexp.test(value)) {
		console.log(value);
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
	
	if (typeof min != 'undefined' && min != null && min >= 0) {
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


