$(document).ready(function () {

    $('.nav li').click(function(){
        $('.nav li').removeClass('active');
        $(this).addClass('active');
    });

    $(".table .eBtn").on('click', function () {
        event.preventDefault(); //no request to the server
        var href = $(this).attr('href');

        $.get(href, function(jsonobject, status){
            var student = jsonobject;
            var date = new Date(student.dateOfBirth);
            $(".updateForm #firstname").val(student.firstName);
            $(".updateForm #lastname").val(student.lastName);
            $(".updateForm #mail").val(student.mail);
            $(".updateForm #phone").val(student.phone);
            $(".updateForm #dateOfBirth").val(date.toLocaleDateString('de-DE',{ month: '2-digit', day: '2-digit', year: 'numeric'}));
            $(".updateForm #studentname").text(student.firstName +" " + student.lastName + " ("+student.studentNumber+")");
            $(".updateForm #studentNumber").val(student.studentNumber);
            $(".updateForm #adressID").val(student.address.id);
            $(".updateForm #id").val(student.id);
            $(".updateForm #town").val(student.address.town);
            $(".updateForm #zip").val(student.address.zip);
            $(".updateForm #street").val(student.address.street);
            $(".updateForm #house_number").val(student.address.houseNumber);
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