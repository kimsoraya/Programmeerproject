#My Personal Cookbook Report

## Applicatie Beschrijving
In My Personal Cookbook kan de gebruiker zijn eigen persoonlijke kookboek samenstellen aan de hand van zelfbedachte recepten en zijn of haar favoriete recepten uit kookboeken en het internet. Zo hoef je niet meer al je recepter te verzamelen in een papierenboekje, maar heb je alles handig op één plek.

## Technisch Ontwerp
In mijn MainActivity staan buttons die allebei naar een nieuw scherm leiden. De View Cookbook button leidt naar RecipeListActivity, waarin een ListView met alle recepten staat. 
![Afbeelding classes overzicht](https://github.com/kimsoraya/Programmeerproject/blob/master/doc/My%20Personal%20Cookbook_v2.pdf)

### Recepten Toevoegen
Als je op het plusje in de MainActivity klikt kom je in de TextOrPhotoActivity waarin je kan kiezen voor een Text Recipe of een Photo Recipe. Als je de "document" button klikt kom je in TextRecipeActivity en door op de "camera" button te klikken kom je in PhotoRecipeActivity. In TextRecipeActivity staan twee EditTexts, eentje voor de titel en de andere voor de tekst van het recept. Er is ook nog een button waarmee er een pop up menu wordt geopend. In dit menu kan je kiezen uit de optie om zelf een foto te nemen, of een afbeelding te kiezen uit de gallerij om aan het recept toe te voegen. Met de "save recipe" button wordt alle informatie uit de EditTexts, en de path naar de afbeelding, opgeslagen in de SQLite database door addRecipeInfo aan te roepen.   <br>
In de PhotoRecipeActivity staat ook een EditText voor de titel, de rest van het scherm bestaat uit buttons. Er is een button "select an image". Met de button kan er een afbeelding worden ingesteld die als omschrijving van je recept fungeert. Deze afbeelding kan worden ingesteld aan de hand van de camera of door een afbeelding uit de gallerij te kiezen. Net als in de TextRecipeActivity kan hier ook een extra afbeelding worden toegevoegd aan het recept aan de hand van de camera of de gallerij. Met de "save recipe" button worden alle foto paths en de titel uit de EditText opgeslagen in de SQLite Database door addPhotoRecipeInfo aan te roepen.<br>

### Recepten Bekijken
In RecipeListActivity staat een ListView met een custom recipe adapter (RecipeAdapter), die zijn informatie uit de RecipeDataProvider haalt. Elke rij in de ListView bestaat een (optionele) afbeelding van het recept, en een TextView met de titel van het recept. Door op de titel te klikken kom je in de ShowRecipeActivity. In de intent naar deze activity moet al de informatie van het recept worden meegegeven. Dit gebeurt door de titel uit de TextView van de ListView item op te vragen. Aan de hand van deze titel kunnen de bijbehorende gegevens worden opgehaald uit de database. Als er lang op de titel wordt gedrukt wordt deze verwijderd door deleteTitle aan te roepen. <br>
In de ShowRecipeActivity wordt de meegestuurde informatie uit RecipeListActivity opgehaald. De recept titel wordt als een header bovenaan het scherm geplaatst, met daaronder de tekst of de afbeelding van het Foto Recept. De afbeelding van het Foto Recept moet van een foto path worden omgezet in een Bitmap, dit wordt gedaan door convertSrcToBitmap aan te roepen. 

### Database
Ik gebruik voor deze app de SQLite database, om deze te gebruiken heb ik een RecipeDatabaseHelper class aangemaakt. Daarnaast heb is er ook een RecipeContract class. Hierin staan alle constanten die ik mijn database gebruik; de kolomnamen en de tabelnaam. In de RecipeDataBaseHelper wordt vervolgens een tabel aangemaakt met de kolommen "title", "photo", "text" en "photo_recipe". Al deze kolommen hebben een TEXT datatype, van de afbeeldingen wordt namelijk alleen de photo path opgeslagen in de database. Verder staan er een aantal functies in de RecipeDatabaseHelper die ik in mijn activities aanroep. Ten eerste addRecipeInfo, deze wordt aangeroepen in de TextRecipeActivity om een recept met een extra afbeelding toe te voegen. AddRecipeInfoTwo wordt ook in de TextRecipeActivity aangeroepen, maar dan wanneer je een recept wil opslaan zonder een extra afbeelding. <br>
Met addPhotoRecipeInfo kan ik op dezelfde manier recepten in de PhotoRecipeActivity opslaan. Ook hier heb ik weer een addPhotoRecipeInfoTwo om foto's zonder extra afbeelding mee op te slaan. <br>
Dan is er getRecipeInfo die een cursor terug geeft. Met deze cursor loop ik door de gespecificeerde kolommen heen om de titels en de bijbehorende afbeeldingen op te halen en deze in de RecipeListActivity weer te geven. <br>
Vervolgens zijn er drie functies die ik gebruik om bepaalde onderdelen van de tabel te selecteren. Ten eerste de getRecipePhotoText. Deze wordt aangeroepen in de RecipeListActivity wanneer de gebruiker op de titel klikt van een recept dat een Foto Recept bevat. De foto path van de bijbehorende afbeelding wordt meegegeven aan de intent naar ShowPhotoRecipe. Iets dergelijks gebeurt wanneer in RecipeListActivity getRecipeText wordt aangeroepen. Alleen wordt er in de intent nu een tekst van het recept meegegeven, in plaats van een afbeelding. De laatste is de deleteTitle. Deze wordt aangeroepen in de RecipeListActivity wanneer de gebruiker lang op een item in de ListView klikt, hiermee kan het recept worden verwijderd. 

##Uitdagingen
De grootste uitdaging voor mij tijdens dit project was 


Clearly describe challenges that your have met during development. Document all important changes that your have made with regard to your design document (from the PROCESS.md). Here, we can see how much you have learned in the past month.


Defend your decisions by writing an argument of a most a single paragraph. Why was it good to do it different than you thought before? Are there trade-offs for your current solution? In an ideal world, given much more time, would you choose another solution?
Make sure the document is complete and reflects the final state of the application. The document will be an important part of your grade.
