var current_id;
$('#coundSendMessageButton').on('click',function(){
    current_id = $(this).attr('data-id');
});
$('#mail .mail-submit').on('click',function(){
    <c:if test="${empty sessionUser}">
    alert("请先登录");
    return;
    </c:if>
    $.ajax({
        type:'POST',
        url: '/ajax/messages/sendMesage.html',
        data: {
            'recieveID': current_id,
            'content': $('#mail .mail-content').val()
        },
        success: function(data){
            alert("发送成功")
            $("#mail").hide();
        }
    })
})