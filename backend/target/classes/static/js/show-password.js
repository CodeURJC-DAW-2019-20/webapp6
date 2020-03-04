function showPass() {
$(".toggle-password").click(function() {
	$(this).toggleClass("fa-eye fa-eye-slash");
	var x = document.getElementById("password-edit");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
});
}