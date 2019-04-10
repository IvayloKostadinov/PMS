$(function() {

    $("#createTaskForm").validate({
        rules: {
            title: "required",
            project: "required",
            user: "required",
            meeting: "required",
            dateCreated: "required",
            meetingDate: "required",
            deadline: "required",
            taskNumber: "required",
            status: "required",
            projectPhase: "required",
            expectedOutcome: "required",
            description: "required"
        },
        messages: {
            title: "Task title is required.",
            project: "Project is required.",
            user: "Designator is required.",
            meeting: "Meeting is required.",
            dateCreated: "Assigned date is required.",
            meetingDate: "Meeting is required.",
            deadline: "Deadline date is required.",
            taskNumber: "Task is required.",
            status: "Status is required.",
            projectPhase: "Phase is required.",
            expectedOutcome: "Expected result is required.",
            description: "Task description is required."
        }
    });
} );

