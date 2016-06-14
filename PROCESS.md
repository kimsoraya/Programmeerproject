# day 1
Wrote a project proposal. I found an app that is very close to my app, so I had to find a way to make my app stand out. I did this not by making an even more complicated app, but going back to the basics, making a simple and easy to use app. Without comercials. The app will basically have two functions. View your cookbook and add reciped by writing a recipe or take a picture of a recipe. 

# dag 2
Begonnen aan Design Document door een UML diagram te maken. Wat ik vooral tegenkwam was de manier waarop ik de database moet gaan implementeren. Ik wil in ieder geval beginnen met de sqlite database omdat ik hier al ervaring mee heb. Wat een mogelijk probleem kan worden is dat de hoeveelheid afbeeldingen niet in deze database passen. Later zal ik gaan kijken naar een online database. Waar ik ook over moet gaan nadenken is hoe ik de foto app lanceer binnen mijn app. Hier maak ik me echter minder zorgen om, omdat dit een functie is die veel apps hebben en het zal daarom minder moeilijk zijn om uit te zoeken. 

# dag 3
In de ochtend heb ik mijn design document ingeleverd zodat ik verder kon gaan met programmeren. Ik ben vooral beginnen met het aanmaken van de verschillende activities en buttons, zodat ik in ieder geval al tussen de verschillende schermen kan navigeren. 

# dag 4
Ik heb ondertussen ook twee nieuwe versies gemaakt in android studio. De tweede versie was nodig omdat ik een lagere minimale API wilde instellen, en dat kon niet meer via het project. Om die reden ben ik met een leeg project gestart waarin ik mijn oude code heb gekopieerd. De derde versie heb ik moeten maken omdat ik probeerde mijn telefoon aan mijn Android Studio te koppelen. Vanwege een onverklaarbare reden (waarschijnlijk omdat ik Android Studio op een MacBook gebruik) lukt dit niet, en na een paar pogingen wilde mijn Gradle helemaal niet meer werken. 

Vandaag moest ik beginnen aan het opslaan van de informatie die de user schrijft in de Write Recipe Activity, deze opgeslagen informatie moet vervolgens worden opgehaald in de View Recipe Activity. Waar ik op dit moment vooral moeite mee heb is hoe ik het uitklapbare menu ga implementeren. Als je op het kookboek menu klikt moeten de verschillende categorieen worden uitgeklapt. Het probleem is dat deze categorieen dynamisch worden aangemaakt door de user, ze voeren bij een nieuw recept een categorie in, en deze wordt dan automatisch aangemaakt als nieuwe categorie. Vervolgens moet elke aangemaakt categorie doorlinken naar een nieuwe activity waarin alle recepten die binnen die categorie vallen. Dit is mijn tweede probleem van vandaag. In dit scherm waar alle recepten staan moeten alleen alle titels staan, en op die titels moet geklikt kunnen worden. Pas als er op de titel wordt geklikt kan men de rest van het recept zien. Alleen de titels moeten dus uit de internal storage worden opgehaald, en als daar op wordt geklikt moet de rest van de informatie uit de internal storage worden opgehaald. 

Voor mijn prototype houd ik het nog simpel, als je op 'My Cookbook' klikt komt je meteen in een scherm waar je de recepten titels op kan halen, dit moet nu nog aan de hand van een 'View Recipes' button. Later moet je de recepten titels automatisch zien als je naar één van de categorieën gaat. Nadat ik dit geïmplenteerd had kwam ik erachter dat mijn 'View Recipe' button maar één recepttitel laat zien. Om dit op te lossen moet ik een adapter class maken met xml file voor een single list item. In de xml bepaal ik dan hoe één item eruit ziet en in de adapter class bepaal ik de functionaliteit van de lijst items. Dit geheel wordt vervolgens aangeroepen in mijn Category Activity, door het geheel in een listview van de xml van deze activity te zetten. 

# dag 6
Tip gekregen om in plaats van text files te gebruiken om de titels en categorieen op te slaan, deze in de sqlite database op te slaan. 

# dag 7
In principe staat het skelet voor mijn sqlite database. Voordat ik hem kan testen moet ik nog uitvinden hoe ik foto's van de camera in de database krijg. Ik wil namelijk dat men een foto kan nemen van zijn gerecht. In het recepten overzicht zie je dan links in de listview die foto, en rechts daarvan de (klikbare) titel van het recept. 

# dag 8
Gisteren heb ik zo veel code verandert in een poging tot het toevoegen van de foto's aan mijn database, dat het een enorm zooitje is geworden en ik niet meer wist waar ik mee bezig was. Daarom vandaag terug gegaan naar de staat waarin alles nog wel werkte en "opnieuw" begonnen. 

Foto path is inmiddels niet langer gehardcode, maar elke foto heeft een timestamp als naam. 
Het lijkt erop dat het ook is gelukt om de foto path in de sqlite database te stoppen. 
Begonnen aan het ophalen van de informatie (foto + titel) in de CategoryActivity.

# dag 9
Ik heb vandaag geprobeerd mijn database volledig werkend te krijgen en in mijn category activity al de informatie uit de database op te halen. 
Het is me gelukt om titels uit de database op te halen en in de listview te laten zien. Alleen zijn de bijbehorende foto's nu nog de gehardcode foto van paella die ik er ooit hebt ingezet. 
Je kan ook al klikken op de recepttitels om het recept te bekijken, deze activity is verder alleen nog leeg.

#dag 10
Recipe adapter en recipe data provider aangepast aan foto path content. Geprobeerd om de photopath om te zetten in Bitmap zodat ze te zien zijn in de recepten listview. Werkt nog niet. 

#dag 11
Als je op de titel klikt in de titel listview wordt er een nieuwe activity geopend waarin de titel nog een keer gedisplayed wordt. Hieronder zal dan de tekst van het recept komen. 


Wat staat er verder nog op mijn to do:
- Ophalen van foto's uit sqlite met photo path en image view stoppen van category activity
- Hoe kan ik het beste de recepten van de "foto recepten" opslaan. Wellicht moet ik daar een aparte tabel voor aanmaken in mijn database.
- Implementeren dat de beschrijving van het recept wordt opgeslagen en kan worden opgehaald als er op een recept wordt geklikt. 
- Zorgen dat als er op een recept wordt geklikt de user het recept in juiste weergave ziet. Dit betekend bovenaan een foto van het resulaat, daaronder een titel en daaronder de uitleg in tekst of foto-vorm. 
- - Nieuwe categorieen automatisch toevoegen aan het dropdown menu in het hoofdscherm, en een suggestie geven wanneer de gebruiker het categorie veld invult. 

