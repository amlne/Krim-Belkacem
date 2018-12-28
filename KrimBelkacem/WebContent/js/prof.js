//*********************
//** permutation entre ajouter recherche ****
//*********************

//******   ajouter  *********
$(document).ready(function(){
    $("#aajouter").click(function(){
        $("#form_recherche").hide();
        $("#arecherche").parent().removeClass("active");
        $("#form_ajouter").show();
        $("#aajouter").parent().addClass("active");
    });
});


//******  recherche *******
$(document).ready(function(){
    $("#arecherche").click(function(){
        $("#form_ajouter").hide();
        $("#aajouter").parent().removeClass("active");
        $("#form_recherche").show();
        $("#arecherche").parent().addClass("active");
    });
});


/*
******  cocher decocher  **********
*/

$(document).ready(function(){
    $("#cocher").click(
        function(){
            $("table input").prop("checked",true);
        }
    );
}
);

$(document).ready(function(){
    $("#decocher").click(
        function(){
            $("table input").prop("checked",false);
        }
    );
}
);

/*
******  decocher tous  *****
*/

$(document).ready(function(){
    $("#tous").click(function(){
        if($("#tous").is(":checked")){
            $("table td input").prop("checked",true);
        }else{
            $("table td input").prop("checked",false);
        }
    });
});
function upFile(){
    document.getElementById('uploader').click();
}
function changeImg(){
    var x = document.getElementById('uploader');
    var y = document.getElementById('upload');
    if(x.value == ''){
        y.src = 'images/notup.png';
    }else{
        y.src = 'images/upload.png';
    }
}

function optionFil(x){
	var tabOptions = document.getElementsByClassName('a');
	if(x.id == 'as1' || x.id == 'ras1'){
		for(var i = 0;i < tabOptions.length ;i++){
			if(tabOptions[i].className == 'a a1'){
				tabOptions[i].style.display = 'block';
			}else{
				tabOptions[i].style.display = 'none';
			}
		}
	}
	if(x.id == 'as2' || x.id == 'ras2'){
		for(var i = 0;i < tabOptions.length ;i++){
			if(tabOptions[i].className == 'a a2'){
				tabOptions[i].style.display = 'block';
			}else{
				tabOptions[i].style.display = 'none';
			}
		}
	}
	if(x.id == 'as3' || x.id == 'ras3'){
		for(var i = 0;i < tabOptions.length ;i++){
			if(tabOptions[i].className == 'a a3'){
				tabOptions[i].style.display = 'block';
			}else{
				tabOptions[i].style.display = 'none';
			}
		}
	}
}