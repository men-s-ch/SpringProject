$(function(){
   $('#allCheck').click(function(){
      if($('#allCheck').is(':checked'))
         $('input[name=check]').prop('checked', true);//아이디 속성은 하나밖에 안됨
      else
         $('input[name=check]').prop('checked', false);
   });// 전체선택
   $('input[name=check]').click(function(){
      let total = $('input[name=check]').length;
      let checked = $('input[name=check]:checked').length;

      if(total != checked) $('#allCheck').prop('checked', false);
      else $('#allCheck').prop('checked', true);
   }); //개별선택
   //선택삭제
   $('#deleteBtn').click(function(){
      let selectedSeqs = [];

       // 체크된 항목들의 seq 값을 수집
       $('input[name=check]:checked').each(function(){
           let seq = $(this).closest('tr').find('input[name=seq]').val();
           selectedSeqs.push(seq);
       });

       if(selectedSeqs.length === 0) {
           alert("선택된 항목이 없습니다.");
           return;
       }

       $.ajax({
          type : 'post',
          url : '/uploadDelete',
          traditional: true,
          data : { 'seqs[]' : selectedSeqs },
          success : function(){
             alert('삭제완료');
             location.href = '/uploadList';
          },
          error : function(e){
             console.log(e);
          }
       });

   });

});