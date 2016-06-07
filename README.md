# Programmeerproject
<br>

##PROPOSAL MY PERSONAL COOKBOOK
<br>
**Naam: Schuiten<br>
Studentnummer: 10191089**

*In de app My Personal Cookbook kan je recepten aanmaken en bewaren zodat je nooit meer je favoriete recepten kwijt kan raken. Als je een recept wil toevoegen kan je dit intypen, of een foto maken van een recept uit je favoriete kookboek. Daarnaast kan je aan elk recept een foto toevoegen van het resultaat.*
<br>
<br>
<br>
###Probleem
Stel je wilt dat heerlijke recept bewaren dat alleen je moeder kan maken. Of dat familierecept dat van generatie op generatie wordt doorgegeven. Dat wil je goed bewaren. Je zou het natuurlijk kunnen opschrijven op papier en bewaren in een mapje. Maar papier verouderd, vlekken maken je tekst onleesbaar en in het ergste geval houd je het recept te dicht bij het vuur en zie je je familierecept letterlijk in vlammen opgaan. 

###Features
####MVP
- Recepten opschrijven en opslaan
- Een foto maken van een recept en als recept opslaan. Bijvoorbeeld een recept uit een kookboek dat je heel vaak gebruikt.
- Screenshots maken van recepten online en deze als recept opslaan.
- Recepten categoriseren in zelf aangemaakte categorieën zoals; voorgerecht, vlees, vis en desserts.
- Bij elk recept kan je ook een foto toevoegen van je gerecht die dan bovenaan je recept komt te staan.

####Optioneel
- Favoriet functie: Hiermee worden je favorieten recepten in elke categorie bovenaan geplaatst.
- Zoekfunctie
- Afbeeldingen uit de gallerij toevoegen in plaats van zelf een foto nemen van je gerecht.
- Tekst toevoegen aan de "foto recepten".
- Een simpelere functie om online recepten op te slaan.
- Recepten in stappen opdelen.
- Online database

### Decomposing the problem
De app heeft op zijn minst zes verschillende schermen. Op het openingsscherm staat alleen de titel van de app samen met het logo, een plus knop om een recept toe te voegen en een uitklapbaar menu. Als je op het menu klikt is er een dropdown menu met de verschillende categorieën van je kookboek, hier kan dan verder op worden geklikt om de recepten te bekijken. Je gaat bijvoorbeeld naar de categorie vis, en daar staat een lijst met verschillende vis recepten. Door op één van de recepten te klikken kom je in een nieuw scherm waar bovenaan de optionele foto staat die je van je gerecht hebt genomen. Deze foto is bijgesneden om goed in de app te passen. Als je op de foto klikt wordt hij in vol scherm getoond. Ook dit zou problemen kunnen opleveren, omdat ik dit nog nooit heb eerder heb geïmplementeerd in een app. Echter, er zijn veel apps die op deze manier foto’s laten zien en vergroten, en ik zou dus gebruik kunnen maken van deze bestaande kennis. Onder deze foto staan vervolgens nog een keer de titel en daaronder het recept. 

Met het plusje kan je een recept toevoegen. Wanneer je een recept toe wil voegen komen je in een nieuw scherm, waarin je kan kiezen of je een recept wil toevoegen aan de hand van tekst of met een foto. Als je voor foto kiest kom je in een nieuw scherm waarin de camera app automatisch  wordt gestart. Dit zou een mogelijk probleem kunnen worden, omdat ik nooit eerder verschillende apps met elkaar heb laten werken. In dit geval moet er een externe app binnen de app worden geopend. Als je een foto hebt gemaakt, of hebt gekozen, kom je in een nieuw scherm waarin je een titel en een categorie aan je recept toe kan voegen. De titel en categorie zijn beiden verplichte velden om in te vullen. Als je een categorie invult die nog niet bestaat, wordt deze automatisch als nieuwe categorie aangemaakt. Bij het intypen van de categorie komt er een dropdown waarin de categorie suggesties staan. In het geval van een tekstinvoer, kan hieronder de tekst worden ingevoerd. Rechts van het kopje waar de titel wordt ingevuld staat ook een klein plusje waarop je op kan klikken. Met dit plusje kan je optioneel een foto van je gerecht toevoegen. 

### API's
Vervolgens moeten de recepten dus extern worden opgeslagen. Dit wordt waarschijnlijk het ingewikkeldste van de app. De recepten kunnen in ieder geval in het geheugen van de telefoon worden opgeslagen door gebruik te maken van MySQL. Om de recepten echt veilig te stellen wil ik dus later misschien gebruik gaan maken van een online database. Hiervoor zou ik gebruik kunnen maken van een gratis online database als myjson (http://myjson.com) of jsonblob (https://jsonblob.com). De voorbeelden die ze op de website van myjson hebben geschreven zijn echter niet gebaseerd op java, wat ik gebruik om mijn app te schrijven in Android Studio, wat het iets ingewikkelder maakt om het te implementeren in mijn code. Ik zou ook gebruik kunnen maken van een gratis hosting website, zoals 000webhost, waarop ik een online SQL database kan maken. Dit laatste lijkt me persoonlijk het handigst omdat er dan een server wordt aangemaakt waar alle data op staat. Dit is ook veiliger dan wanneer alle data wordt opgeslagen in een open database als myjson. Daarbij zal ik bij het gebruik van een online database ook moeten gaan nadenken over een inlogscherm en usernames. 

###Review of similar applications
Uiteraard heb ik mijn app idee ook vergeleken met bestaande recepten en kookboek apps. Wat mij opviel was dat de meeste van deze apps vooral bestaan om te zoeken naar bestaande recepten op het internet of uit een eigen database. Wat ik vooral zoek in een recepten app is dat ik recepten op kan slaan die ik zelf heb bedacht, of heb geleerd. Daarnaast heb ik veel kookboeken waarin twee of drie recepten staan die ik vaak kook, die zou ik ook graag aan zo’n dergelijke app willen toevoegen. Apps die gebruik maken van zo’n dergelijke bestaande database van recepten zijn ‘ChefTap’ en ‘Kookboek recepten’. Deze apps hebben echter wel de recepten op een mooie manier gecategoriseerd en ze maken goed gebruik van de foto’s die kunnen worden toegevoegd aan de recepten.  

De app die het meest op mijn app lijkt is ‘My CookBook’. In deze app kan je zelf recepten met de hand toevoegen, en ook recepten van het internet downloaden. De recepten die je van het internet kan download, kunnen alleen via geschikte websites worden gedownload. Wat problematisch is als je een heel goed recept vindt die niet bij deze geschikte websites hoort. Daar heb je bij mijn app geen last van, omdat je een screenshot maakt van de online recepten, in plaats van dat je ze download.  Als je in My Cookbook een recept van het internet download kan je foto’s, ingrediënten en opmerkingen toevoegen. Daarnaast kan je je recepten delen met vrienden, boodschappenlijstjes maken, de ingrediënten schalen op het aantal mensen dat mee eet, een spraakfunctie gebruiken om de recepten voor te laten lezen en verschillende onderdelen van de app persoonlijk aanpassen. 

Wanneer je met de hand recepten toevoegt, moet je een aantal velden invullen. Uiteraard moet je een titel geven, dat moet in mijn  app ook. Vervolgens kan je een foto toevoegen, hiervoor biedt de app verschillende mogelijkheden (online importeren, zelf foto maken, van de SD kaart).  Dan kan je een waardering aan het recept geven, wat ik zelf een beetje overbodig vind. Als ik een recept niet waardeer, zou ik het nooit aan mijn kookboek toevoegen. Ook kan je net als in mijn app een categorie toevoegen. Deze categorieën zijn al door de app gegenereerd en kan je niet in dit scherm aanpassen. Dat is jammer, je moet dan namelijk helemaal uit je recept, en naar de categorie instellingen gaan om dit aan te passen. Wat ik wel handig vind is dat je bereidingstijden kan toevoegen en voor hoe veel personen dit recept is. De ingrediënten lijst is ook mooi vormgegeven, je voegt elk ingrediënt apart toe met een plusje. Hetzelfde geld voor de instructies, je kan je gerecht in stappen verdelen door stappen toe te voegen met de plusjes. Ook kan je commentaar toevoegen en een eventuele bron. Om de een of andere reden wordt in deze app niet het woord “bron” gebruikt, maar “Kaynak”, wat wel zeer verwarrend is. 

Wat mijn app beter maakt  is zijn simpelheid. Door een deel van de functies die My CookBook wel heeft niet te gebruiken wordt mijn app een stuk overzichtelijker en esthetischer. Wat ik in het aanmaken van recepten in My Cookbook ook mis, is dat je verplicht bent om het in tekstvorm te doen. Zoals eerder beschreven zou ik favoriete recepten uit kookboeken ook in mijn kookboek app willen hebben. Dit doe ik aan de hand van het maken van een foto van het recept uit het boek, en deze vervolgens als recept op te slaan. Daarnaast is mijn app gratis en zal je ook niet worden gestoord door reclames in je app. 
