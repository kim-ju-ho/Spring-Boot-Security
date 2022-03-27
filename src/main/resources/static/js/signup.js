$(function(){
    $('#passwordConfirm').keyup(function(){
        let pwd = $('#password').val();
        let pwdConf = $('#passwordConfirm').val();

        if(pwd === pwdConf){
            $('#passwordChk').hide();
            console.log("비밀번호가 일치합니다.")

        }else{
            $('#passwordChk').show();
            console.log("비밀번호가 일치하지 않습니다. 확인해주세요.")
        }
    });
});
function onclickAdmin() {
// Get the checkbox
    var checkBox = document.getElementById("admin-check");
// Get the output text
    var box = document.getElementById("admin-token");

// If the checkbox is checked, display the output text
    if (checkBox.checked == true){
    box.style.display = "block";
    } else {
    box.style.display = "none";
    }
}

