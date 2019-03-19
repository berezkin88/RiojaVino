$(document).ready(function () {
    /* home page */
    $("#banner").fadeIn(1500);

    /* success page */
    $("#success-message").fadeIn(1500);

    /* failure page */
    $("#failure-message").fadeIn(2500);

    /* shop page */
    $(".wines").load("/riojavino/shop/items #wines");
});