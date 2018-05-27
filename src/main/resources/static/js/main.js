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

    // fills the inputs of the update course modal
    $(".btnUpdateCourse").click( function () {
        $("#uc-id").val($(this).data('id'));
        $("#uc-name").val($(this).data('name'));
        $("#uc-description").val($(this).data('description'));
        $("#uc-lecturer").val($(this).data('lecturer'));
    });
});