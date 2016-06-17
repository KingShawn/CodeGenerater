$(function(){
    $(".codeArea").hide();
    $("#submitBtn").click(function(){
        var options ={   
                        url:'uploadFile.action',   
                        type:'post',                    
                        success:function(data){
                            if(data != null) {
                            $("#code").html(data);  
                            $(".codeArea").show(50);                                                          
                        }else{
                            alert("System error")
                         }   
                       }
                     }
        $("#uploadForm").ajaxSubmit(options);
    })
    $(".btn-clipboard").click(function(){
    	alert("ss");
    	$("#code").select();
    });
});