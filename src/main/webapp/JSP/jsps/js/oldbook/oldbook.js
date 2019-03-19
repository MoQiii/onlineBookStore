var path="http://localhost:8080";
function deleteOldBook(bid) {
    $.ajax(
        {
            url:path+"/OldBook/delete",
            type:"post",
            data:{"bid":bid},
            success:function () {
                alert("下架成功");
                window.location.href=path+"/OldBook/oldBookList"
            },
            error:function () {
                alert("下架失败,请稍后重试");
            }
        }
    )
}