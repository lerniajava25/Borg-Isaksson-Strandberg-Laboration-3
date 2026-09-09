# Borg-Isaksson-Strandberg-Laboration-3

## Borg reflektion

Min upplevelse är att Spring Boot underlättar utvecklingen väldigt mycket. Jag har tidigare arbetat med Express i JavaScript och ser både likheter och tydliga skillnader. Båda används för att bygga webbapplikationer och REST-API:er och förenklar exempelvis routing, hantering av HTTP-anrop och JSON-data.

En tydlig skillnad är att Express är ett mer minimalistiskt och flexibelt ramverk där utvecklaren själv får fatta många beslut om applikationens struktur och vilka andra verktyg som ska användas. Spring Boot är mer låst men erbjuder mycket funktionalitet och automatisk konfiguration från början. Jag känner igen samma princip från Next.js, där ramverket också i hög grad bestämmer hur applikationen ska struktureras.

Det finns både för- och nackdelar med detta. Express ger större frihet, medan Spring Boot enligt min upplevelse gör det enklare att skapa en konsekvent och robust applikation eftersom mycket färdig och testad funktionalitet finns i ramverket. Ett konkret exempel från kursen är hantering av JSON. I Laboration 2 behövde vi själva använda Jackson för att omvandla JSON-data från ett API till Java-objekt. I Spring Boot kan motsvarande konvertering till stor del ske automatiskt, exempelvis när JSON i ett HTTP-anrop omvandlas till ett Java-objekt med @RequestBody.

En annan grundläggande skillnad är språken. Java är statiskt typat, vilket innebär att typer kontrolleras redan vid kompilering, medan JavaScript är dynamiskt typat och fler typfel därför kan upptäckas först när programmet körs. Även miljöerna skiljer sig i hur de erbjuder funktionalitet. I Node.js/Express finns exempelvis testning, validering och andra funktioner genom separata bibliotek som utvecklaren själv får välja och kombinera. Spring Boot erbjuder istället ett mer sammanhållet ekosystem där många vanliga funktioner redan är integrerade och konfigureras automatiskt. 

Tillsammans med den statiska typningen och Spring Boots tydliga struktur upplever jag att detta ger goda förutsättningar för att bygga robusta backend-applikationer, även om det samtidigt innebär att utvecklaren behöver lära sig Spring Boots sätt att strukturera och konfigurera en applikation.

## Isaksson reflektion


Kallas “opinionated” – utgår från standardinställningar. Ramverket bestämmer mycket om hur dependency injection (DI) görs, hur säkerhet sätts upp. Utvecklare kan kringgå repetitivt och komplext konfigurationsarbete. T.ex. @Service-annotation säger att klassen ska hanteras, instansieraras och tillgängliggöras för andra delar av systemet (DI).

Kräver ingen matchning av bibliotek. Med Spring Boot inkluderar man bara en så kallad Starter (t.ex. spring-boot-starter-web eller spring-boot-starter-test). Spring Boot paketerar då en kombination av alla bibliotek som behövs i exakt rätt versioner.

Spring Boot har en inbyggd server – inget behov av att konfigurera och installera en extern applikationsserver.


Java/Spring Boot har statiskt typat; problem upptäcks vid kompilering. Node.js är dynamiskt typat (kräver mer enhetstester). 

Skillnader:

Vid uppstart: Spring Boot mer resurskrävande att starta applikationen. Node.js är väldigt snabb jämförelsevis och kräver lite minne i vila.

Node.js kan sägas vara mer optimerat för I/O-intensiva applikationer (chatta, strömma etc.), medans Java/Spring Boot är passar mer för CPU-intensiva uppgifter. 

Java kan fördela arbetet över datorns alla processorkärnor via multithreading. Det kan vara användbart t.ex. vid tunga matematiska beräkningar och bildbehandling. Node.js körs på en enda thread, vilket kan märkas om blockering sker till följd av tunga beräkningar.

Node.js kräver att utvecklaren själv väljer bibliotek för databaser, validering och säkerhet, till skillnad från Spring Boot (se ovan).

Likheter:

Båda är populära val för att bygga backend-system och REST-API:er.

Att lägga till bibliotek i pom.xml görs via maven eller gradle och i Node.js via  npm och yarn i package.json. Båda har stora ekosystem.

Båda miljöerna tillhandahåller databasanslutningar, molntjänster, containerisering, mikrotjänst-arkitektur.

Båda miljöerna har stora communityn.
