



const form  = document.getElementsByTagName('form')[0];

const name = document.getElementById("name");
const surname = document.getElementById("surname");
const email = document.getElementById("email");
const pass = document.getElementById("password");
const confirmPass = document.getElementById("confirm-password");

const emailError = document.querySelector('#email + span.error');
const nameError = document.querySelector('#name + span.error');
const surnameError = document.querySelector('#surname + span.error');
const passError = document.querySelector('#password + span.error');
const confirmPassError = document.querySelector('#confirm-password + span.error');

var textInputArray = [name, surname, email, pass, confirmPass];
var textInputErrorsArray = [nameError, surnameError, emailError, passError, confirmPassError];

console.log("IN JS");


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

confirmPass.addEventListener("input", function (event) {
  if(confirmPass.value != pass.value){
	  confirmPass.setCustomValidity("Not equal");
	  confirmPassError.textContent = "Passwords don't match";
	  confirmPassError.className = 'error active';
  } else {
	  confirmPass.setCustomValidity("");
	  confirmPassError.textContent = "";
	  confirmPassError.className = 'error';
  }
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
		elemError.textContent = 'You need to enter ' + elem.id;
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