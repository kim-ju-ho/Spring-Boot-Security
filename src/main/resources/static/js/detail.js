
$(document).ready(function () {

    $('#editBtn').on("click",function(){
        let contents_section =  $('.edit');
        let contents = $('#1-contents');
        $('#editBtn').hide();
        $('#deleteBtn').hide();
        $('#sendBtn').show();
        contents_section.show();
        $('#1-textarea').val(contents.text());
        $(contents).hide();
    });




})
let stringMsgId = location.href.split("/")[5];
let msgId = parseInt(stringMsgId);
function updateMessage(){
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

function createComt(){
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
    let comment     =$('#'+id+"-comment");
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

