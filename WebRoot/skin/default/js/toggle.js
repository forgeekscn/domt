$(document).ready(function(){
   $(".panel-title").toggle(function(){
     $(this).next(".panel-content").animate({height: 'toggle', opacity: 'toggle'}, "slow");
   },function(){
$(this).next(".panel-content").animate({height: 'toggle', opacity: 'toggle'}, "slow");
   });
});