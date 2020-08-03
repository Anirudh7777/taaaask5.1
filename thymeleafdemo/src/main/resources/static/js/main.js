/**
 * 
 */

$(document).ready(function(){
	
	$('.table .eBtn').on('click',function(event){
		event.preventDefault();
		
		console.log("Welcome");
		
		var href = $(this).attr('href');
		
		$.get(href,function(employee,status){
			$('.myForm #id').val(employee.id);
			$('.myForm #fname').val(employee.firstName);
			$('.myForm #lname').val(employee.lastName);
			$('.myForm #email').val(employee.email);
			$('.myForm #position').val(employee.position);
			$('.myForm #rating').val(employee.rating);
			$('.myForm #salary').val(employee.salary);
			console.log(employee);
		});
		
		$('.myForm #exampleModal').modal();

	});
	
});