$(document).ready(function () {

    $('.nav li').click(function(){
        $('.nav li').removeClass('active');
        $(this).addClass('active');
    });

    $(".table .eBtn").on('click', function () {
        event.preventDefault(); //no request to the server
        var href = $(this).attr('href');

        $.get(href, function(student, status){
            $(".updateForm #firstname").val(student.firstName);
            $(".updateForm #lastname").val(student.lastName);
            $(".updateForm #mail").val(student.mail);
            $(".updateForm #phone").val(student.phone);
            $(".updateForm #dateOfBirth").val(student.dateOfBirth.substring(0, 10));
            $(".updateForm #studentname").text(student.firstName +" " + student.lastName + " ("+student.studentNumber+")");
        });
        $(".updateForm #updateModel").modal();
    });
});