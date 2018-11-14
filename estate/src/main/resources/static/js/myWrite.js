function sele_Change(){
	var objS = document.getElementById("addr");
    var get_map = objS.options[objS.selectedIndex].value;
    $.post("/building/getAddrName",
    		{addr:get_map},
    		function(data){
    			var htmlInfo = "";
    			for(var i = 0;i<data.length;i++){
    				var item = data[i];
    				htmlInfo+="<option value='"+item.buildingId+"'>"+item.bname+"</option>";
    			}
    			$("#room").html(htmlInfo);
    		} 
  	   
     );
}