function showPass() {
  var x = document.getElementById("password-edit");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}