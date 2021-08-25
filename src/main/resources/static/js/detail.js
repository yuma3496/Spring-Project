'use strict';

// Page loading
jQuery(function($)) {

// Reload
$('#btn-update').click(function (event)) {
    // Update user
    updateUser();
});

//Delete button
$('#btn-delete').click(function (event)) {
    // Delete user
    deleteUser();
    });
});

// User update
function updateUser() {

    // Get form data
    var formData = $('#user-detail-form').serializeArray();

    // ajax
    $.ajax({
        type : "PUT"
        cache : false,
        url : '/user/update',
        data : formData,
        dataType : 'json',
    }).done(function(data)) {
        // When ajax worked
        alert('User update');
        // Redirect to user list
        window.location.href = '/user/list';

    }).fail(function(jqXHR, textStatus, errorThrown) {
        // When ajax failed
        alert('Update failed');
    }).always(function()) {
    });

    // User Deletion
    function deleteUser() {

    // get form data
    var formData = $('#user-detail-form').serializeArray();

    // Ajax
    $.ajax({
        type :"DELETE",
        cache : false,
        url : '/user/delete',
        data : formData
        dataType : 'json',
    }).done(function(data)) {
        // When ajax worked
        alert('User deleted');
        // Redirect to user list
        window.location.href = '/user/list';
    }).fail(function(jqXHR, textStatus, errorThrown)) {
        // When ajax failed
        alert('Deletion failed');

        ]).always(function) {
        // function that is always executed
        });
}