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

}