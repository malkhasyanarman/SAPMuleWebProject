$(document).ready(function () {
    $('.table .edit-button').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (data, status) {
            $('.edit-modal #id').val(data.id);
            $('.edit-modal #legal_entity').val(data.legalEntity);
            $('.edit-modal #storage_location').val(data.storageLocation);
            $('.edit-modal #customer').val(data.customer);
            $('.edit-modal #agency').val(data.agency);
        })
        $('.edit-modal #edit-modal-inner').modal();
    });

    $('.create-button').on('click', function (event) {
        $('.create-modal #create-modal-inner').modal();
    });
});