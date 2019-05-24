function picChange() {
	$("#vCode").attr("src", "/verifyCode/verifyCode?" + new Date().getTime());
}
function picChangeR() {
    $("#imgVerifyCode").attr("src", "/verifyCode/verifyCode?" + new Date().getTime());
}