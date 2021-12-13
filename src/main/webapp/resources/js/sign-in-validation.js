const form  = document.getElementsByTagName('form')[0];

const email = document.getElementById("email");
const pass = document.getElementById("password");

const emailError = document.querySelector('#email + span.error');
const passError = document.querySelector('#password + span.error');

var textInputArray = [email, pass];
var textInputErrorsArray = [emailError, passError];

textInputArray.forEach(function(elem){
	elem.addEventListener("focus", function(){
		elem.setAttribute("required", "required");
		var elemError = textInputErrorsArray[textInputArray.indexOf(elem)];
		if(elem.validity.valid) {
			elemError.textContent = '';
			elemError.className = 'error';
		} else {
			showError(elem);
		}
	});
});

textInputArray.forEach(function(elem) {
    elem.addEventListener("input", function() {
		var elemError = textInputErrorsArray[textInputArray.indexOf(elem)];
		if(elem.validity.valid) {
			elemError.textContent = '';
			elemError.className = 'error';
		} else {
			showError(elem);
		}
    });
});

form.addEventListener('submit', function (event) {
	textInputArray.forEach(elem => elem.setAttribute("required", "required"));
	textInputArray.forEach(function(elem) {
		if(!elem.validity.valid){
			showError(elem);
			event.preventDefault();
		}
	});    
});

function showError(elem) {
	var elemError = textInputErrorsArray[textInputArray.indexOf(elem)];
	if(elem.validity.valueMissing) {
		elemError.textContent = 'You need to enter an ' + elem.id;
		elemError.className = 'error active';
	} else if(elem.validity.tooShort) {
		elemError.textContent = 'Min field length must be ' + elem.minLength +
		' characters. You entered ' + elem.value.length + '.';
		elemError.className = 'error active';
	} else if(elem.validity.typeMismatch) {
		elemError.textContent = 'type mismatch';
		elemError.className = 'error active';
	}	
}