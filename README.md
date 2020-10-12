# GeoFun-master

Allgemeine Bemerkungen:

Es empfiehlt sich, vor der Dokumentation das beiliegende Video anzusehen um ein ausführliches Verständnis über das Programm
zu erhalten. Es geht sehr ausführlich auf die App, die einzelnen Bausteine sowie den Entwicklungsprozess ein und betrachtet sowohl Front- als auch Backend.
Das Video ist unter den Datein in diesem Projekt zu finden.
Die schriftliche Dokumentation beschränkt sich deshalb auf einige ausführlichere Erklärungen bestimmter Code Bausteine, gibt jedoch keinen zweiten vollständigen Überblick über die App.

Projektteilnehmer sind: Julian Kammermeier & Alwina Bitter. Folgende Teile des Projekts wurden an folgende Teilnehmer verteilt und jeweils bis zum im Release vorzufindenden Stand  bearbeitet:

Julian Kammermeier: Erstellung Projekt und Github Repository; Erstellung Java Klassen; Erstellung und Design alle Layouts; Widget komplett; Datenbank komplett inklusive Helper Klassen, DAOs, Anpassung Manifest, etc; Verarbeitung der Datenbank Inhalte in den Spielmodi (Anzeigen in TextView, Auslesen aus DB, Erneuerung, etc.); Ablauf Spielmodi Infinite Mode und Interactive Mode (Scoring, Antwortüberprüfung, Fragegenerierung, etc.); Erstellung Karten Fragment + Klasse für Kartenverarbeitung; Apptutorial Video;

Alwina Bitter: Erstellung aller Intents zum Wechsel zwischen Klassen komplett; Bugfixing Layouts; Shared Preferences komplett; Standortzugriff (Erlaubnis erteilen, Abfrage Erlaubnis, Integration in Interactive Mode/Karten Fragment); Benachrichtigungsmanagement (Erlaubnis erteilen, Abfrage Erlaubnis, Benachrichtigungen erstellen);

Jeder der Teilnehmer war für die Dokumentation seiner Inhalte selbst verantwortlich.

Widget:
Der Kern des Widgets besteht aus einer Methode: "onUpdate" in der Klasse "geoFunWidgetProvider". Da das Widget statisch ist, werden alle Operationen innerhalb dieser Methode ausgeführt, statt ausgelagert zu werden.
Parameter der Methode sind ein Kontext, ein AppWidgetManager Objekt welches jedoch nicht manuell erstellt werden muss, und einem Integer Feld aus AppWidgetIDs, da mehrere Widgets gleichzeitig existieren können.
In einer for Schleife werden über alle Widgets im Feld folgende Operationen durchgeführt:
Erstellung eines neuen Intents mit context und MainActivity.class als Parameter zum Wechsel vom Widget in die App, genauer MainActivity.
Erstellung eines Pending Intens (sodass der AppWidgetManager mit den Permissions der App an sich den Intent verwalten kann), welchem der erstelle Intent übergeben wird.
Setzen des Views auf das vordefinierte Layout "widget".
Erstellen eines OnClickPendingIntents zur Verarbeitung des spezifischen Nutzerklicks auf den entsprechenden Button, welcher explizit angegeben wird.
Ausführen der onUpdate Methode der Klasse AppWidgetManager mit dem erstellen View und einer der Widget IDs als Eingabeparameter.


Datenbank und Helper Klassen:
Die Datenbank basiert auf dem Konzept einer Room Database und speichert ihre Inhalte lokal auf dem Gerät des Nutzers ab.
Neben der Datenbank an sich werden Klassen für die Elemente innerhalb Datenbank sowie DataAccessObjects (DAO) zum verwalten dieser benötigt. Da Zugriffe auf die Datenbank (DB) nach Android Design nicht im MainThread ablaufen dürfen, braucht es außerdem eine sogenannte Helper Klasse zum Zugriff auf die gewollten DB Operationen zur Laufzeit.
Eine Klasse (Answer, Country, Question & Score) welche ein Datenbank Element repräsentiert besteht aus verschiedenen Attributen, einem Konstruktor und entsprechenden Getter- und Setter-Methoden. Für jede dieser Klassen gibt es ein Interface welches Zugriffe auf die Datenbank (einfügen oder retrieven eines konreketen Elements) verarbeitet, als auch ein Interface welches im Zuge der DatabaseHelper Klasse ein Objekt der entsprechenden Datenbankelementklasse zurückgibt.
Die DatabaseHelper Klasse (DH) regelt die konkreten Zugriffe auf die DB zur Laufzeit. Sie bietet für jede Elementklasse der DB eine Methode zum einfügen oder finden.
Beim einfügen werden der Methode ein entsprechendes Objekt der einzufügenden Klasse sowie ein Konstruktor der AddElementTask oder FindElementTask Klasse übergeben. Die (Add/Find)ElementTask (im Programm spezifisch (Add/Find)AnswerTask, (Add/Find)QuestionTask, etc.) Klassen stellen Klassen dar, welche mit Hilfe verschiedener eigener Attribute, welche denen der Elementklassen gleichen und einem Konstruktor sowie einer "run" Methode welche spezifisch das DAO des gesuchten Elements anspricht um, im Falle dieser App mitHilfe der ID des Elements (oder dem Wert des Scores im Falle dieser Klasse), das gesuchte Element in der Datenbank finden und entsprechend ausgeben oder einfügen.

MainActivity:
Die "MainActivty" (MA) stellt den Kern der App dar, referenziert das Layout "activity_main", präsentiert sich dem Nutzer nach Start (per App Auswahl oder Widget) und erlaubt es vordergründig die verschiedenen Spielmodi sowie die Einstellungen zu erreichen. Dies geschiet mit Hilfe von im Layout referenzierten Buttons, welche einen Wechsel über entsprechende Intents in die jeweilige Ziel Activity erlauben. Über onClickListener werden die Buttons überwacht während die "onClick" Methode per "switch - case" die dem gewählten Button entsprechenden Aktionen initialisert.
Hintergründig füllt die MA bei Start außerdem die Quizdatenbank mit Fragen, Ländern und Antworten. Hierfür wird eine Instanz des DataBaseHelpers erstellt, sowie ArrayLists für Fragen und Länder. Per "fillDatabase" werden die Methoden "createAnswers", "createQuestions" und "createCountries" ausgeführt, welche ihrerseits jeweils die Objekte ihrer Klassen initialisieren und mit Hilfe des "dbh" der Datenbank übergeben.

ActivityInfinte:
Die ActivityInfinite (AI) stellt den Infinite Modus der App dar. Hier kann der Nutzer beliebig lange, theoretisch unendlich Fragen aus der Datenbank mit Hilfe der angezeigten Antworten beantworten. Sie referenziert das Layout activity_infinite und besteht hauptsächlich aus zwei TextViews zum Anzeigen von Frage und Land und vier Buttons welche die Antwortmöglichkeiten repräsentieren.
Zu Activitystart wird ein Objekt der DatabaseHelper Klasse, ein RandomNumberGenerator, die TextViews sowie Buttons, ein Helper String und ein Integer Wert zur Repräsentation des Scores erstellt.
Per "SetUpButtonsAndViews" werden den Objekten Werte zugewiesen. Die "onClick" Methode verwaltet das Verhalten der App sollte ein Button gedrückt werden.
Die Text Views werden per Datenbankanfrage befüllt. Pro Antwortzyklus (= Klick auf einen der AntwortButtons) und bei ActivityStart werden den textViews entsprechend ein Land und eine Frage zugewiesen ("setQuestion).
Die Buttons werden nach selbigem Schema befüllt, jedoch wird hier geachtet, dass die Antworttypen denen der Frage entsprechen, sodass nur sinnvole Antworten angezeigt werden. Entsprechen die zufällig gewählten und den Buttons hinzugefügten Antworten nicht dem Fragetyp wird der Prozess wiederholt bis alle Antwortmöglichkeiten sinnvoll befüllt sind (nicht realisiert, aktuell werden Antworten nur einmal zufällug ausgewählt - Bei nicht korrekten Antworttypen bleibt der Button leer) ("setAnswers").
Per Klick auf einen der Buttons wird die den Button füllende Antwort mit der Frage verglichen ("CheckAnswer") und bei einer korrekten Antwort der Score um eines erhöht, während bei einer falschen Antwort dieser auf 0 zurückgesetzt wird nachdem sein Wert zum Speichern an die Datenbank übergeben wird. Der jeweils höchste Score des Modus wird danaben als Highscore angezeigt (nicht realisiert, Highscore Abfrage fehlt).
Nach Klick auf eine Antwort werden diese inklusive Frage und Land zurückgesetzt, bevor das Spiel erneut beginnt.
Per Klick auf den Zurückpfeil kann außerdem zurück zum Menü der MainActivity gelangt werden.

ActivityTimeRush:
Diese Activity referenziert das Layout "activity_timerush" und ist in ihrem Aufbau dem des Infinite Mode sehr ähnlich. Einziger Unterschied ist ein Timer, welcher entsprechend der Einstellung (testSeconds für Funktionalitätstest und realModeSeconds für tatsächlichen Spielbetrieb) einen Countdown startet. Während ansonsten der Spielmodus dem des InfiniteMode gleicht, wird zusätzlich bei Erreichen des Timers von 0 der Score gespeichert und auf 0 zurückgesetzt bevor der Timer erneut startet.

ActivityInteractive:
Diese Activty referenziert das Layout "activity_interactive", welches zusätlich zu einem Zurück Button, welcher einen zur MainActivity bringt eine Karte in Form eines Fragments beinhaltet. Außerdem enthält das Layout TextViews für die Fragestellung.
Die Activity an sich enhält erneut ein Objekt der Klasse DatabaseHelper, ein Objekt der Klasse Random und einen textView zum Setzten des Landes.
Zu Activity Start wird die Karte geladen und ein Land mit Hilfe der Datenbank festgelegt. Der Nutzer kann nun auf der Karte einen Marker setzten, wo er die Lage der Hauptstadt dieses Landes vermutet. Abhängig vom eigenen Standort des Nutzers wird dann die Beantwortung der Frage angestoßen (nicht realisiert, aktuell wird lediglich ein Marker per default auf Regensburg gesetzt).
Per zurück Button kann außerdem der Intent zum Wechseln auf die MainActivity angestoßen werden.

ActivitySettings:
Die Activvity ActivitySettings beinhaltet die Standortabfrage und -speicherung mithilfe von Shared Preferences. Hierzu wurden öffentliche, statische finale Strings SHARED_PREFERENCES, LOCATION_SWITCH und NOTIFICATION_SWITCH angelegt, um diese als Key mit der Methode putBoolean des SharedPreferences Konstruktors einem vordefinierten Wert, hier: die Zustände der jeweiligen Switches, zuweisen zu können. Es wurden zudem ein privater statischer finaler int Wert angelegt, der in der Standordpermissionabfrage (Methode getLocationPermission()) als Wert benutzt wird, welcher in einem Stringarray gespeichert wird. 
Zudem wurden die Variablen der Switches locationSwitch und notificationSwitch und des Boolean switchOnOff erstell, damit auf diese im Laufe des Codes zugegriffen werden kann.
In der Methode onCreate wird das Layout activity_settings aufgerufen, welches drei Elemente enthält: Einen Zurück-Button, einen Switch für den Standortzugriff (locationSwitch) und einen Switch für die Benachrichtigungen. Auf diese drei Elemente wurde ein onClickListener gesetzt, auf welche in der nächsten Methode onClick (View v) der Einfachheit halber mittels eines Switchcase in Abhängigkeit der Buttin/Switch-Ids zugegriffen wird. Jedes case beinhaltet eine Methode, die einen eigenen Intent erhält, welches im Falle des geklickten Zurück-Buttons auf die Hauptseite zurückleitet, im Falle der Auswahl des Locationsswitches die Methode changeLocationSetting() aufruft und im Falle der Auswahl des Switches notificationSwitch die Methode changeNotificationSetting() aufruft.   
Die methode changeLocationSetting vergleicht, ob die Permission im Manifest bereits erteilt wurde. Falls dies der Fall ist, wird ein Toast mit dem Text "Standortzugriff wurde bereits erteilt" angezeigt. Falls der Standortzugriff nicht erteilt wurde, wird diese in der nächsten Methode, getLocationPermission(), eingeholt.  
getLocationPermission() zeigt das von Android vordefinierte, gewohnte Fenster zur Standortabfrage an mit den Optionen OK und Abbrechen. Der Standort wird hierbei nur einmal abgefragt, erst bei Deinstallation und Reinstallation der App wird der Standort erneut abgefragt, sonst bleibt der Standort aktiviert, die Berechtigung erlischt durch Schließen der App nicht. Wenn die Option OK gewählt wird, wird die Auswahl gespeichert und anschließend geladen und angezeigt. Die Speicherung der Zustände der Switches geschieht durch SharedPreferences.  


Bekannte Bugs:
Widget: "Problem Loading Widget" Anzeige statt Widget Layout;
Widget: Button reagiert nicht auf Klick;
TimeRush/Infinite Modus: Frage/Land und Antwort Felder bleiben leer;
In allen drei Fällen hilft (mehrmaliges) de- & reinstallieren der App auf dem Emulator/Gerät
ActivitySettings: SharedPreferences speichert Buttons, lässt jedoch nicht zu, dass die Buttons ausgeschaltet werden, nachdem beide aktiviert sind. 
