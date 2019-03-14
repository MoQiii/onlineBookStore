function picChange() {
	$("#vCode").attr("src", "/verifyCode/verifyCode?" + new Date().getTime());
}