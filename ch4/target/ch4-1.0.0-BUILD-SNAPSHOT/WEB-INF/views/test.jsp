<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h2>commentTest</h2>
comment:<input type="text" name="comment"><br>
<button id="sendBtn" type="button">SEND</button>
<button id="modBtn" type="button">수정</button>
<h2>Data From Server :</h2>
<div id="commentList"></div>
<div id="replyForm" style="display:none">
  <input type="text" name="replyComment">
  <button id="wrtRepBtn" type="button">등록</button>
</div>
<script>

  let bno = 700;

  let showList = function (bno){
    $.ajax({
      type:'GET',       // 요청 메서드
      url: '/ch4/comments?bno='+bno,  // 요청 URI
      dataType : 'json', // 전송받을 데이터의 타입
      success : function(result){
        $("#commentList").html(toHtml(result));
      },
      error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
    }); // $.ajax()
  }

  $(document).ready(function(){
    showList(bno);

    $("#modBtn").click(function(){
      let cno = $(this).attr("data-cno");
      let comment=$("input[name=comment]").val(); //input 태그에 name이 comment인 것을 comment변수에 담고 그것을 ajax의 stringify로

      if(comment.trim()==''){ //공백 입력 막기
        alert("댓글을 입력해주세요.");
        $("input[name=comment]").focus()
        return;
      }
      $.ajax({
        type:'PATCH',       // 요청 메서드
        url: '/ch4/comments/'+cno,  // 요청 URI
        headers : { "content-type": "application/json"}, // 요청 헤더
        dataType : 'json', // 전송받을 데이터의 타입
        data : JSON.stringify({cno:cno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success : function(result){
          alert(result)
          showList(bno);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()
    })



    $("#wrtRepBtn").click(function(){
      let comment=$("input[name=replyComment]").val();
      let pcno=$("#replyForm").parent().attr("data-pcno"); //replyForm의 부모(li)에서 data-cno를 가져와서 pcno에 넣어준다 (답글의 부모)

      if(comment.trim()==''){ //공백 입력 막기
        alert("댓글을 입력해주세요.");
        $("input[name=replyComment]").focus()
        return;
      }
      $.ajax({
        type:'POST',       // 요청 메서드
        url: '/ch4/comments?bno='+bno,  // 요청 URI
        headers : { "content-type": "application/json"}, // 요청 헤더
        dataType : 'json', // 전송받을 데이터의 타입
        data : JSON.stringify({pcno:pcno,bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success : function(result){
          alert(result)
          showList(bno);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()

      //답글을 단 뒤 안보이게 한다
      $("#replyForm").css("display","none")
      $("input[name=replyComment]").val('') //값 비우기
      $("#replyForm").appendTo("body"); //원래 위치로 되돌려놓기 (특정 댓글 밑에 있는 것이 아니라 원래 있던 자리 body 아래에 돌려놓기)
    })




    $("#sendBtn").click(function(){
      let comment=$("input[name=comment]").val(); //input 태그에 name이 comment인 것을 comment변수에 담고 그것을 ajax의 stringify로

      if(comment.trim()==''){ //공백 입력 막기
        alert("댓글을 입력해주세요.");
        $("input[name=comment]").focus()
        return;
      }
      $.ajax({
        type:'POST',       // 요청 메서드
        url: '/ch4/comments?bno='+bno,  // 요청 URI
        headers : { "content-type": "application/json"}, // 요청 헤더
        dataType : 'json', // 전송받을 데이터의 타입
        data : JSON.stringify({bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success : function(result){
          alert(result)
          showList(bno);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()
    })


    $("#commentList").on("click",".modBtn",function() {
      let cno = $(this).parent().attr("data-cno"); //버튼의 부모가 li, 부모의 속성 중에 data-cno 가져오기
      let comment = $("span.comment", $(this).parent()).text();

      $("input[name=comment]").val(comment);
      $("#modBtn").attr("data-cno",cno);
    })


    $("#commentList").on("click",".replyBtn",function(){
      //1.replyForm을 옮기고
      $("#replyForm").appendTo($(this).parent()); //li태그 뒤에 붙인다

      //2.답글을 입력할 폼을 보여주고
      $("#replyForm").css("display","block");

    });

    $("#commentList").on("click",".delBtn",function(){
      let cno=$(this).parent().attr("data-cno"); //버튼의 부모가 li, 부모의 속성 중에 data-cno 가져오기
      let bno=$(this).parent().attr("data-bno");

      $.ajax({
        type:'DELETE',       // 요청 메서드
        url: '/ch4/comments/'+cno+'?bno='+bno,  // 요청 URI
        dataType : 'json', // 전송받을 데이터의 타입
        success : function(result){
          alert(result)
          showList(bno); //삭제된 뒤에 리스트를 가져와서 목록을 갱신해준다
        },
        error : function(){ alert("삭제 error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()
    })
  });

  let toHtml = function (comments){
    let tmp="<ul>";

    comments.forEach(function (comment){
      tmp+= '<li data-cno='+comment.cno
      tmp+= ' data-pcno='+comment.pcno
      tmp+= ' data-bno='+comment.bno+'>'
    if(comment.cno!=comment.pcno)
      tmp+= 'ㄴ'
      tmp+= ' comment=<span class="comment">'+comment.comment+'</span>'
      tmp+= ' commenter=<span class="commenter">'+comment.commenter+'</span>'
      tmp+= ' up_date='+comment.up_date
      tmp+= ' <button class="delBtn">삭제</button>'
      tmp+= ' <button class="modBtn">수정</button>'
      tmp+= ' <button class="replyBtn">답글</button>'
      tmp+= '</li>'
    })
    return tmp+"</ul>";
  }
</script>
</body>
</html>