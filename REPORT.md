#My Personal Cookbook Report

## Applicatie Beschrijving
In My Personal Cookbook kan de gebruiker zijn eigen persoonlijke kookboek samenstellen aan de hand van zelfbedachte recepten en zijn of haar favoriete recepten uit kookboeken en het internet. Zo hoef je niet meer al je recepter te verzamelen in een papierenboekje, maar heb je alles handig op één plek.

## Technisch Ontwerp
In mijn MainActivity staan buttons die allebei naar een nieuw scherm leiden. De View Cookbook button leidt naar RecipeListActivity, waarin een ListView met alle recepten staat. 

### Recepten Toevoegen
Als je op het plusje in de MainActivity klikt kom je in de TextOrPhotoActivity waarin je kan kiezen voor een Text Recipe of een Photo Recipe. Als je de "document" button klikt kom je in TextRecipeActivity en door op de "camera" button te klikken kom je in PhotoRecipeActivity. In TextRecipeActivity staan twee EditTexts, eentje voor de titel en de andere voor de tekst van het recept. Er is ook nog een button waarmee er een pop up menu wordt geopend. In dit menu kan je kiezen uit de optie om zelf een foto te nemen, of een afbeelding te kiezen uit de gallerij om aan het recept toe te voegen. Met de "save recipe" button wordt alle informatie uit de EditTexts, en de path naar de afbeelding, opgeslagen in de SQLite database door addRecipeInfo aan te roepen.   <br>
In de PhotoRecipeActivity staat ook een EditText voor de titel, de rest van het scherm bestaat uit buttons. Er is een button "select an image". Met de button kan er een afbeelding worden ingesteld die als omschrijving van je recept fungeert. Deze afbeelding kan worden ingesteld aan de hand van de camera of door een afbeelding uit de gallerij te kiezen. Net als in de TextRecipeActivity kan hier ook een extra afbeelding worden toegevoegd aan het recept aan de hand van de camera of de gallerij. Met de "save recipe" button worden alle foto paths en de titel uit de EditText opgeslagen in de SQLite Database door addPhotoRecipeInfo aan te roepen.<br>

### Recepten Bekijken
In RecipeListActivity staat een ListView met een custom recipe adapter. Elke rij in de ListView bestaat een (optionele) afbeelding van het recept, en een TextView met de titel van het recept. 




Clearly describe the technical design: how is the functionality implemented in your code? This should be like your DESIGN.md but updated to reflect the final application. First, give a high level overview, which helps us navigate and understand the total of your code (which components are there?). Second, go into detail, and describe the modules/classes and how they relate.


Clearly describe challenges that your have met during development. Document all important changes that your have made with regard to your design document (from the PROCESS.md). Here, we can see how much you have learned in the past month.
Defend your decisions by writing an argument of a most a single paragraph. Why was it good to do it different than you thought before? Are there trade-offs for your current solution? In an ideal world, given much more time, would you choose another solution?
Make sure the document is complete and reflects the final state of the application. The document will be an important part of your grade.
