var cardIDs = []; //holds card file names
var cardIx = 0; //index of card being displayed via card id. for the card id, card+1
const maxCards = 49; //max number of cards. here for easy replacement if you want to add more cards

//labels cards with file name and puts them in cardIDs
for (var i = 0; i < maxCards; i++){
//append # + png to end of cardIDs array
    cardIDs.push((i+1)+ ".png")
}

//called onclick for both buttons to change the image
function view(direction){
//if direction is right --> add 1 to the index to go foward in the collection
//if direction is left --> subtract 1 from the index to go backwards in the collection
    (direction == "right") ? (cardIx = (cardIx == maxCards-1) ? 1 : cardIx+1) : (cardIx = (cardIx==0) ? maxCards-1 : cardIx-1)
//sets img element in index.html to display the specified card based on the in index determined from line 14
//sources images from assets folder - all assets were created by Allison Zhang
    document.getElementById("card-image").setAttribute("src", "../cards/assets/" + cardIDs[cardIx]);
}
