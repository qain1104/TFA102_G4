$(function(){
    $("i.fa-user").on("mouseover", function(){
        // console.log($("i.fa-user").parent().next());
        $("i.fa-user").parent().next().removeClass("-none");
    })
    $("div.choose_list").on("mouseleave", function(){
        $("div.choose_list").addClass("-none");
    })
})