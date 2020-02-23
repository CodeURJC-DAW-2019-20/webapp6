$(document).ready(function() {
	$("#add").click(function(){
	        //Data upload
			$.ajax({
				url: ('/addFromProduct/{id}/'+page),
				dataType: 'html',
				success: function(html) {
					$('#entrees').append(html);
					$(this).hide();
					page++;
				}
			});
			//Show button and hide spinner
			$(this).show();
			$('#loading').hide(); 
	 });
	
});