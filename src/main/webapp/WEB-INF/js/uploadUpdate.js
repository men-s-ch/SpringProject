$(function (){
    $('#uploadUpdateBtn').click(function () {
        let formData = new FormData($('#uploadUpdateForm')[0]);
       $.ajax({
           type: 'post',
           enctype: 'multipart/form-data',
           processData: false,
           contentType: false,
           url: '/uploadUpdate',
           data: formData,
           success : function(data) {
               alert(data);
               location.href='/uploadList';
           },
           error : function(e){
               console.log(e);
           }
       });
    })
});