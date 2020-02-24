$(document).ready(function(){
sum2()
  $("button").click(function(){
	var id = $(this).attr("id")
	pid = "product" + id
	$.ajax({
		  url: ("https://localhost:8443/api/product/"+id),
		  method: 'DELETE',
		}).done(function (item) {
	        console.log("Deleted product " + id)
	    })
    $("#" + pid).remove();
	sum2()
  });
});

function sum2(){
	var sum=0;
	$('.sum_item').each(function(){
		var item_val=parseFloat($(this).val());
		if(isNaN(item_val)){
			item_val=0;
		}
		sum+=item_val;
		$('#total').val(sum.toFixed(2));
		document.getElementById("total").innerHTML = sum.toFixed(2)+"€";
	});
	if($('.sum_item').length==false){
		document.getElementById("total").innerHTML ="0.0€";
	}
}

function sum(){
	   var num1 = parseFloat(document.getElementById("totalCheck").value);
	   var num2 = parseFloat(document.getElementById("tax").value);
	   var n = num1 + num2;
	   var sum = n.toFixed(2) + "€";

	   document.getElementById("sum").innerHTML = sum;
	}

function loadProducts(callback) {
    $.ajax({
        url: 'http://localhost:8080/product'
    }).done(function (product) {
        console.log('Products loaded: ' + JSON.stringify(product));
        callback(product);
    })
}

function updateProduct(product) {
    $.ajax({
        method: 'PUT',
        url: ('http://localhost:8080/api/product/'+product.id),
        data: JSON.stringify(product),
        processData: false,
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (product) {
        console.log("Updated product: " + JSON.stringify(product))
    })
}

/*function updateQty(){
	  $("button").click(function(){
		var result = $(this).attr("id")
		var sst = "sst"+result
		var result2 = document.getElementById(sst);
		var sst2 = result2.value
		if( !isNaN( sst2 )) 
			result2.value++;
	  });
}*/