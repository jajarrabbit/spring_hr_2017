// $(function () {
//     $('.input-group.date').datepicker({
//         language: 'ja',
//         autoclose   : true
//     });
// });

$(function () {
	$('body').on('click','.input-group.date',function(){
        $(this).datepicker(
        {
            language: 'en',
            autoclose   : true,
            format : 'yyyy/mm/dd',
            clearBtn: true
        });
        $(this).datepicker("show");
	});
});