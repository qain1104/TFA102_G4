
	$("#abc").on("mouseover", function(){
		// console.log($("i.fa-user").parent().next());
		$("#abc").parent().next().removeClass("-none");
	})
	$("div.choose_list").on("mouseleave", function(){
		$("div.choose_list").addClass("-none");
	})

//****************鼎謙****************
function toggleResetPswd(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle() // display:block or none
    $('#logreg-forms .form-reset').toggle() // display:block or none
}

function toggleSignUp(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle(); // display:block or none
    $('#logreg-forms .form-signup').toggle(); // display:block or none
}

function toggleSignUp2(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle(); // display:block or none
    $('#logreg-forms .form-signup-corp').toggle(); // display:block or none
}

$(()=>{
    // Login Register Form
    $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
    $('#logreg-forms #cancel_reset').click(toggleResetPswd);
    $('#logreg-forms #btn-signup').click(toggleSignUp);
    $('#logreg-forms #btn-signup-corp').click(toggleSignUp2);
    $('#logreg-forms #cancel_signup').click(toggleSignUp);
    $('#logreg-forms #cancel_signup_corp').click(toggleSignUp2);
})

$("#btn").on("click", function () {
     location.reload(true);
});
//****************鼎謙****************    
    