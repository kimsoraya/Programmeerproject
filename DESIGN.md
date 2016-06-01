#DESIGN DOCUMENT 

##API's
Voor mijn kookboek app heb ik een aantal verschillende API’s nodig. Ten eerste een API om de camera applicatie in mijn app te integreren. De camera is nodig om foto’s te maken van recepten en om een afbeelding in te stellen van je gerecht. Deze foto’s moeten ook meteen worden gebruik in de activiteit waarin de camera wordt aangeroepen. Daarnaast moet ik een API hebben voor de opslag in een database. In eerste instantie zal ik daarvoor gebruik maken van sqlite, dit is mijn MVP. Wanneer dit geïmplementeerd is ga ik proberen om ook een online database te maken, zodat recepten altijd terug te vinden zijn. Om een online database te maken kan ik gebruik maken van de API van Realm of Firebase. Voor de layout van mijn app wil ik gebruik gaan maken van een template. Deze heb ik nog niet uitgezocht omdat ik nog niet precies weet hoe ik wil dat de app eruit moet gaan zien. Ik zal opzoek gaan naar een template met een rustieke uitstraling. 

##Database
Mijn database zal bestaan uit één tabel. In deze tabel staan de velden ‘title’ (type TEXT), ‘category’ (type TEXT), ‘recipetext’ (type TEXT), ‘recipepic’ (type BLOB), ‘dishpic’ (type BLOB). Ik zou eventueel ook een aparte tabel kunnen maken voor de fotobestanden. De velden in de database komen voort uit een object dat ik aan zal maken voor de recepten. In dit object staat de titel, categorie, tekst van het recept, foto van het recept en een foto van het gerecht. 

