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

    //fill select box with courses
    $(".sBtn, .eBtn").on("click",function () {
        if($(".courseSelection").children().length <= 0) {
            $.get("courses/raw", function (courses, status) {
                $.each(courses, function (i, item) {
                    $(".courseSelection").append($('<option>', {
                        value: item.id,
                        text: item.name
                    }));
                });
            });
        }
    });

    //add course to table in the student create model
    $(".updateForm .addCourse").on("click", function () {
        addCourseToTableByContext(".updateForm");
    });

    //add course to table in the student update model
    $(".addCourse").on("click", function () {
        addCourseToTableByContext("#createStudentModal");
    });

    $(".cancelModal").on("click", function () {
       $(".coursesTable tbody").empty();
    });

    function addCourseToTableByContext(context){
        var courseid = $(context+" .courseSelection").val();

        var coursidExists = false;
        $(context+' .coursesTable input[name="courseids[]"]').each(function(){
            if($(this).val() == courseid) courseid = true;
        });
        if(!coursidExists) {
            $.get("/courses/find/?id=" + courseid, function (course, status) {
                $(context+" .coursesTable tbody").last()
                    .append("<tr>" +
                        "<td>" + course.id + "</td>" +
                        "<td>" + course.name + "</td>" +
                        "<td>" + course.lecturer + "</td>" +
                        "<td>" + course.description + "</td>" +
                        "<td><button type=\"button\" onClick=\"$(this).closest('tr').remove();\"><span class=\"glyphicon glyphicon-trash\"></span></button>" +
                        "<input type='hidden' name='courseids[]' value='" + course.id + "' /></td>" +
                        "</tr>")
            })
        }
    }

    // fills the inputs of the update course modal
    $(".btnUpdateCourse").click( function () {
        $("#uc-id").val($(this).data('id'));
        $("#uc-name").val($(this).data('name'));
        $("#uc-description").val($(this).data('description'));
        $("#uc-lecturer").val($(this).data('lecturer'));
    });
});