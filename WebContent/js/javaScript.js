function validateForm() {
    var x = document.forms["myForm"]["documentName"].value;
    if (x == null || x == "") {
        alert("Name must be filled out");
        return false;
    }
}

function validateFormCreare() {
    var x = document.forms["myForm"]["cabinet_name"].value;
    var y = document.forms["myForm"]["owner_name"].value;
    if ((x == null || x == "") && (y == null || y == "")) {
        alert("Name and owner must be filled out");
        return false;
    }
}

function validateName(Field){
	if(Field.value == null || Field.value == ""){
		document.getElementById("numeError").innerHTML = "Enter a name";
		return false;
	}
else {
	document.getElementById("numeError").innerHTML = "";
}
return true;
}

function validateOwner(Field){
	if(Field.value == null || Field.value == ""){
		document.getElementById("ownerError").innerHTML = "Enter a owner";
		return false;
	}
else {
	document.getElementById("ownerError").innerHTML = "";
}
return true;
}