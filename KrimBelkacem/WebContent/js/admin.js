//******   ajouter rubrique *********
$(document).ready(function(){
    $("#ajoutRub").click(function(){
        $("#form-rechercheRub").hide();
        $("#rechercheRub").parent().removeClass("active");
        $("#form-ajoutRub").show();
        $("#ajoutRub").parent().addClass("active");
    });
});


//******  recherche rubrique*******
$(document).ready(function(){
    $("#rechercheRub").click(function(){
        $("#form-ajoutRub").hide();
        $("#ajoutRub").parent().removeClass("active");
        $("#form-rechercheRub").show();
        $("#rechercheRub").parent().addClass("active");
    });
});

//**** ajouter planning*****
$(document).ready(function(){
    $("#ajoutPlanning").click(function(){
    	$("#divDel").hide();
    	$("#divPlan").show();
        $("#form-recherchePlanning").hide();
        $(".delAct").removeClass("active");
        $("#form-ajoutPlanning").show();
        $(".planAct").addClass("active");
    });
});
//***** recherche planning******
$(document).ready(function(){
    $("#recherchePlanning").click(function(){
    	$("#divDel").hide();
    	$("#divPlan").show();
        $("#form-ajoutPlanning").hide();
        $(".delAct").removeClass("active");
        $("#form-recherchePlanning").show();
        $(".planAct").addClass("active");
    });
});
//******** ajouter deliberation****
$(document).ready(function(){
    $("#ajoutDeliberation").click(function(){
    	$("#divPlan").hide();
    	$("#divDel").show();
        $("#form-rechercheDeliberation").hide();
        $(".planAct").removeClass("active");
        $("#form-ajoutDeliberation").show();
        $(".delAct").addClass("active");
    });
});
//***** recherchedeliberation******
$(document).ready(function(){
    $("#rechercheDeliberation").click(function(){
    	$("#divPlan").hide();
    	$("#divDel").show();
        $("#form-ajoutDeliberation").hide();
        $(".planAct").removeClass("active");
        $("#form-rechercheDeliberation").show();
        $(".delAct").addClass("active");
    });
});
function optionCls(x){
	var tabOptions = document.getElementsByClassName('a');
	if(x.id == 'as1'){
		for(var i = 0;i < tabOptions.length ;i++){
			if(tabOptions[i].className == 'a a1'){
				tabOptions[i].style.display = 'block';
			}else{
				tabOptions[i].style.display = 'none';
			}
		}
	}
	if(x.id == 'as2'){
		for(var i = 0;i < tabOptions.length ;i++){
			if(tabOptions[i].className == 'a a2'){
				tabOptions[i].style.display = 'block';
			}else{
				tabOptions[i].style.display = 'none';
			}
		}
	}
	if(x.id == 'as3'){
		for(var i = 0;i < tabOptions.length ;i++){
			if(tabOptions[i].className == 'a a3'){
				tabOptions[i].style.display = 'block';
			}else{
				tabOptions[i].style.display = 'none';
			}
		}
	}
}
function upFile(x){
    x.previousElementSibling.click();
}

function changeImg(z){
	
    if(z.value == ''){
        z.nextElementSibling.src = 'images/notup.png';
    }else{
        z.nextElementSibling.src = 'images/upload.png';
    }
}

