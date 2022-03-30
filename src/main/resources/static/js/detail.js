
$(document).ready(function () {

})

// 메시지 작성자랑 인가 체크
function chkMessageUser(){
    if(username == null){
        alert("로그인이 필요합니다. 로그인페이지로 이동합니다.")
        location.href="/user/login";
        return false;
    }else if(msgName !== username){
        alert("권한이 없습니다.");
        return false;
    }else{
        return true;
    }

}
// comment 작성자랑 인가 체크
function chkCommentUser(id){
    let commentName = $('#'+id+'-commentName').text();
    console.log(username);
    console.log(commentName);

    if(username == null){
        alert("로그인이 필요합니다. 로그인페이지로 이동합니다.")
        console.log("로그인 필요");
        location.href="/user/login";
        return false;
    }else if(username !== commentName){
        console.log("권한 없음");

        alert("권한이 없습니다.");
        return false;
    }else{
        return true;
    }

}
// message section 인가 체크 및 버튼 스위칭
function messageSwitchBtn(){
    if(chkMessageUser()===false){
        return false;
    }
    let contents_section =  $('.edit');
    let contents = $('#1-contents');
    $('#editBtn').hide();
    $('#deleteBtn').hide();
    $('#sendBtn').show();
    contents_section.show();
    $('#1-textarea').val(contents.text());
    $(contents).hide();
}
// 메세지 아이디 변수 선언
let stringMsgId = location.href.split("/")[5];
let msgId = parseInt(stringMsgId);

// 메시지 수정
function updateMessage(){
    if(chkMessageUser()===false){
        return false;
    }
    let msgContents = $('#1-textarea').val();
    let data ={'contents' : msgContents , 'name' : msgName, 'title':msgTitle};
    $.ajax({
        type: "PUT",
        url: `/api/contents/${msgId}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 수정되었습니다.');
            alert(response);
            location.href = `/api/contents/${msgId}`;
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    });
}

function deleteMessage(){

    if(chkMessageUser()===false){
        return false;
    }
    $.ajax({
        type: "DELETE",
        url: `/api/contents/${msgId}`,
        contentType: "application/json",
        success: function (response) {
            alert('메시지가 성공적으로 삭제되었습니다.');
            location.href = `/`;
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    });

}
// comment작성
function createComt(){
    if(username == null){
        alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
        location.href='/user/login';
        return false;
    }

    let commentName = $('#commentName').text();
    let comment = $('#createComment').val();
    let data = {
        'name' : commentName,
        'comment' : comment,
        'parentsId': msgId
    }
    $.ajax({
        type: "POST",
        url: "/api/comment",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('코멘트가 성공적으로 등록되었습니다.');
            location.href = `/api/contents/${msgId}`;
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    });

}

// comment 버튼 스위칭
function switchBtn(id){
    if(chkCommentUser(id)===false){
        return false;
    }
    let comment     = $('#'+id+"-comment");
    let commentArea = $('#'+id+"-commentArea");
    let editBtn     = $('#'+id+"-editBtn");
    let deleteBtn   = $('#'+id+"-deleteBtn");
    comment.hide();
    commentArea.show();
    editBtn.hide();
    deleteBtn.hide();
}
// 코멘트 업데이트
function updateComment(id){
    if(chkCommentUser(id)===false){
        return false;
    }
    let commentEdit = $('#'+id+"-commentEdit");
    let data = {
            "comment" : commentEdit.val()
        }
    $.ajax({
        type: "PUT",
        url: "/api/comment/"+id,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('코멘트가 성공적으로 등록되었습니다.');
            location.href = `/api/contents/${msgId}`;
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    });
}


// 코멘트 삭제
function deleteComment(id){
    if(chkCommentUser(id)===false){
        return false;
    }

    $.ajax({
        type: "DELETE",
        url: "/api/comment/"+id,
        contentType: "application/json",
        success: function (response) {
            alert('코멘트가 성공적으로 삭제되었습니다.');
            location.href = `/api/contents/${msgId}`;
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    });
}

