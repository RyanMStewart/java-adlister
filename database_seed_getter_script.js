let titles = [];
for(elem of document.querySelector('#srp-river-results > ul').children) {
	const item = elem.getElementsByClassName('s-item__title').item(0);
	if (item !== null)
		titles.push(item.innerText);
}

let newTitles = [];
for( title of titles) {
	title = title.replace("\n","");
	title = title.replace("SPONSORED", "");
	title = title.replace("NEW LISTING", "")
	title = title.replace("BRAND NEW FACTORY SEALED FREE SHIPPING", "")
	title = title.replace(/,/g, "")
	title = title.replace(/"/g, "")
	title = title.replace(/[^\x00-\x7F]/g, "");
	title = title.replace(/'/g, "");
	title = title.trim()
newTitles.push(title)
}

let outString = ""
for (title of newTitles) {
	outString += `${title}\n`
}