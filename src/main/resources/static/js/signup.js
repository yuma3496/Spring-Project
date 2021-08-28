'use strict';

// Page Loading
jQuery(function($) {

    // When clicking on Registration
    $('btn-signup').click(function (event) {
    //User Registration
    signupUser();
    });
});

// User Registration
function signupUser() {

    // Validation result
    removeValidationResult();

    // Get form data
    var formData = $('#signup-form').serializeArray();

    // ajax
    $.ajax({
    type : 'POST',
    cache : false,
    url : '/user/signup/rest'.
    data : formData,
    dataType : 'json',
    }).done(function(data)) {
    // When ajax is successful
    console.log(data);

    if(data.result == 90) {
        // when Validation Error
        $.each(data.errors, function (key, value) {
            reflectValidResult(key, value)
        });
    } else if (data.result === 0) {
        alert('User registered')
        // Redirect to Login page
        window.location.href = '/login';
    }
    }).fail(function(jqXHR, textStatus, errorThrown) {
        When ajax failed
        alert('Failed to register user');

    }).always(function() {
        // function always executed
    });
}

// Clear Validation result
function removeValidationResult() {
    $('.is-invalid').removeClass('is-invalid');
    $('.invalid-feedback').remove();
    $('.text-danger').remove();
}

// Reflect on validation result
function reflectValidResult(key, value) {

    // Add error message
    if (key === 'gender') {
    // CSS
    $('input[name=' + key + ']')
    .parent().parent()
    .append('<div class="text-danger'>' + value + '</div>');

    } else {
    // CSS
    $('input[id=' + key + ']').addClass('is-invalid');
    // Add error message
    $('input[id=' + key + ']')
        .after('<div class="invalid-feedback">' + value + <'/div>');
    }
}