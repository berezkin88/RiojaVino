let wineSlot = $(".wine_slot");
let skus = [];
let url;

for (let i=0; i < wineSlot.length; i++) {
	if (wineSlot[i].attributes.style.nodeValue == "") {
		skus.push((wineSlot[i].children[0].children[1].innerText));
	}
}

$("#post_form").attr("action", $("#post_form").attr("action")+skus);

// process checkout
let buy = () => {
    $("#post_form").submit();
}