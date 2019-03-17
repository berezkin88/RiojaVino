setTimeout(function() {
    $('form').on('click', function () {
        // enable button if input checked
        if ($('.wine_check').is(':checked')) {
            $('#basket').prop('disabled', false);
        }

        // disable button if input not checked
        if (!$('.wine_check').is(':checked')) {
            $('#basket').prop('disabled', true);
        }
    })}, 2000
);


let skus = [];
let url;

setTimeout(() => {
    let items = $('input');

    let check = function() {
        for(i = 0; i < items.length; i++){
            skus[i] = {};
            skus[i]['checked'] = items[i].checked;
            skus[i]['sku'] = items[i].defaultValue;
        }
    }

    let buildUrl = () => {
        url = 'shop/basket?items='
        skus.forEach(e => {
            if(e['checked'] == true) {
                if (url.endsWith('=')) {
                    return url += e['sku'];
                } else {
                    return url += ','+e['sku'];
                }
            }
        });
    }

    $('input').on('click', function () {
        check();
        buildUrl();
    }
)}, 2000);

let refer = () => {
    return location.href=url;
}

let buy = () => {
    $.post("/./riojavino/buyService", function(data) {
        $('body').html(data);
    })
}