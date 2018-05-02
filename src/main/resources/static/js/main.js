$(document).ready(function () {
    $(".table .eBtn").on('click', function () {
        event.preventDefault(); //no request to the server
        var href = $(this).attr('href');

        $.get(href, function(student, status){
            $(".updateForm #id").val(student.id);
            $(".updateForm #name").val(student.name);
            $(".updateForm #course").val(student.course);
            $(".updateForm #studentname").text(student.name);
        });
        $(".updateForm #updateModel").modal();
    });
});