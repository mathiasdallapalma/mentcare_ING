# Mental Care

## Scenarios:
 	 	 	
### 1. LOGIN
|   |   |
|---|---|
| **Assumptions** | Un utente si trova sulla pagina di login e vuole autenticarsi sulla piattaforma MentCare attraverso le credenziali fornite al momento dell’iscrizione. L’utente dovrebbe possedere tali credenziali e dovrebbero coincidere con quelle presenti nei registri di sistema della piattaforma in modo da garantire l’accesso appropriato.|
|**Flow**|L’utente inserisce nel form di login la mail e la password nei rispettivi campi e clicca sul bottone di login. Il sistema verifica che le credenziali siano corrette e se lo sono autentica la sessione e reindirizza la navigazione alla pagina home della piattaforma.|
|**GUI objects**|• form di login<br>• bottone "login"|
|**Errors**|Se l’utente ha inserito delle credenziali errate la pagina mostra un messaggio di errore e i campi del form vengono resettati.|
|**Altre attività**| Un utente che non riesce ad accedere alla piattaforma può fare richiesta di nuove credenziali attraverso la procedura di recupero credenziali.|
|**Sysytem status**|La connessione è stata autenticata e l’utente è stato reindirizzato alla pagina home della piattaforma.

### 2. VISUALIZZAZIONE DETTAGLI PAZIENTE
| | |
|-|-|
|**Assumptions**| Un utente autenticato vuole visualizzare nel dettaglio i dati relativi ad uno specifico paziente che sono stati precedentemente registrati nel sistema. **NOTA: Se non ci piace, prima della consegna lo sostituiamo con un altro scenario, ad esempio aggiungi visita.**|
|**Flow**|• L’utente, a partire dalla home, ha cliccato sulla riga relativa al paziente che vuole visualizzare nel dettaglio, a questo punto il sistema reindirizza la navigazione alla pagina desiderata.<br>• L’utente ha digitato il link “MentCare.com/{id_paziente}” e il sistema ha reindirizzato la navigazione alla pagina desiderata.
|
|**GUI objects**|• Sezione per i dettagli paziente <br>• Sezione dettagli medici
|**Errors**|L’id_paziente digitato nel link non è associato ad alcun paziente catalogato nel sistema, quindi la piattaforma mostra un messaggio di errore che avvisa l’utente che l’id non è corretto.|
|**Sysytem status**| La pagina mostra i dati dettagliati dello specifico paziente. Alcuni dei dati mostrati sono: informazioni anagrafiche, di contatto, esito delle visite e medicine assegnate al paziente.|

### 3. REPORT TOTALE PAZIENTI
| | |
|-|-|
|**Assumptions**| Un utente autenticato vuole scaricare statistiche basate sui dati dei pazienti salvati nel sistema. L’utente si trova nella pagina home della piattaforma.|
|**Flow**|L’utente clicca sull'apposito bottone “scarica report statistiche”, il sistema chiede tramite finestra all'utente di specificare se esportare il file in formato TXT o PDF. L'utente può scegliere il formato o annullare l'operazione. In caso di esito positivo il sistema avvia il download.|
|**GUI objects**|• bottone report|
|**Report**|• lista sintetica dei pazienti-statistiche generali  <br>• lista medicine assegnate<br>• lista malattie comuni
|**Errors**| Il sistema potrebbe trovarsi in uno stato in cui non è possibile generare un report per esempio se nessun paziente è stato ancora registrato. In questo caso la pagina mostra un messaggio di errore.|
|**Sysytem status**| La pagina mostra un messaggio di operazione riuscita, nel backend viene aggiunta una voce ai log e viene creato il file richiesto in una cartella apposita. Infine il file contenente il report è stato scaricato sul dispositivo dell’utente che ha richiesto la funzione.|

### 4. AGGIUNTA VISITA PAZIENTE
**NOTA ho modificato da valutazione a visita
| | |
|-|-|
|**Assumptions**|Un utente autenticato vuole aggiungere una visita ad uno specifico paziente. L’utente si trova sulla pagina dei dettagli del paziente.|
|**Flow**|L'utente clicca sul pulsante aggiungi visita e la navigazione viene reindirizzata alla pagina di aggiunta visita. L’utente inserisce nel form di valutazione i dati relativi alla visita (data, motivo, esito, note) nei rispettivi campi e clicca sul bottone di conferma. Il sistema verifica che i dati siano consistenti e se lo sono li salva associandoli allo specifico paziente.|
|**GUI objects**|• bottone “aggiungi” <br> pagina dedicata alla registrazione di una nuova visita:<br>• form dati <br>• bottoni "accetta" e "annulla"|
|**Errors**|I dati inseriti potrebbero contenere degli errori per esempio la data inserita è futura rispetto a quella odierna oppure il campo motivo non è stato selezionato correttamente.|
|**Sysytem status**|I dati inseriti vengono salvati nel sistema e sono visualizzabili come prima voce nella sezione Visite.|

### 5. REPORT PAZIENTE
| | |
|-|-|
|**Assumptions**| Un utente autenticato vuole scaricare un file report contenente le informazioni di uno specifico paziente. L’utente si trova nella pagina di dettaglio del paziente.
|**Flow**| L'utente clicca sul bottone per scaricare un report (cartella clinica del paziente e dettagli extra come appuntamenti effettuati, dottori precedenti, lista medicine e "note" di altri dottori). Il sistema chiede tramite finestra all'utente di specificare se esportare il file in formato TXT o PDF. L'utente può scegliere il formato e completare l'operazione con l'utilizzo del bottone "accetta", il sistema avvia il download. Altrimenti può annullare l’operazione. 
|**GUI objects**|• bottone report|
|**Report**|• cartella clinica del paziente(note di dottori)<br>• dettagli extra come appuntamenti <br>• effettuati<br>• dottori<br>• lista medicine da prendere
|**Errors**| L'utente dottore potrebbe chiudere la finestra prima che il file venga generato, annullando l'operazione anche se il file sarà comunque generato in backend.|
|**Sysytem status**| Con esito positivo il sistema registra l'evento di modifica, genera e invia le info dell'evento agli utenti legati al paziente infine reindirizza l'utente  nella pagina del paziente da cui era partito, qui può verificare l'avvenuta modifica. Nel caso in cui l'utente annulli l’operazione il sistema lo reindirizza alla pagina del paziente.|

### 6. MODIFICA PRESCRIZIONE PAZIENTE
| | |
|-|-|
|**Assumptions**| Un utente autenticato vuole modificare la prescrizione di un certo paziente o aggiungere nel caso in cui non ne abbia già una.|
|**Flow**| L'utente tramite la pagina del paziente utilizza il bottone “modifica prescrizione” nella sezione della prescrizione per alterare i medicinali e/o trattamenti che un paziente deve seguire. Il sistema  reindirizza la navigazione  alla pagina in cui  è presente la prescrizione precedente e un form. L'utente tramite il form può alterare il testo e tramite bottoni i "accetta" e "annulla" può confermare o annullare l'operazione.|
|**GUI objects**|• bottone “modifica” nella sezione prescrizione medica<br>pagina dedicata:<br>• identificativo paziente<br>• testo precedente<br>• form per il nuovo testo<br>• bottoni accetta o annulla|
|**Errors**| I dati inseriti potrebbero contenere degli errori per esempio la data inserita è futura rispetto a quella odierna oppure i medicinali inseriti non sono compatibili con il paziente oppure la quantità di medicinali non è corretta. In ogni caso viene mostrata una finestra con un messaggio dell’errore specifico e l’utente dovrà correggere il testo inserito.**NOTA: analisi delle stringhe x allergie e simili.**|
|**Other**| Altri utenti legati al paziente a cui è stata effettuata la modifica ricevono una email di notifica, essa contiene l'utente che ha effettuato il cambiamento, un'identificazione del paziente, il testo e data+ora.|
|**Sysytem status**| Il sistema registra l'evento di modifica, genera e invia le info dell'evento agli utenti legati al paziente. Infine il sistema reindirizza la navigazione alla pagina del paziente, in cui  può verificare l'avvenuta modifica.|

### 7. AGGIUNTA PAZIENTE
| | |
|-|-|
|**Assumptions**| Un utente autenticato vuole aggiungere un nuovo paziente al sistema. L'utente si trova nella pagina home.|
|**GUI objects**|• bottone “aggiungi paziente”<br>  pagina dedicata alla registrazione di un nuovo utente paziente nel sistema:<br>•form dati anagrafici<br>• bottoni "accetta" e "annulla"|
|**Flow**| L'utente utilizza l'apposito bottone nella home "aggiungi paziente" e viene reindirizzato dal sistema in una pagina dedicata alla creazione di un nuovo profilo paziente. L'utente inserisce i vari dati anagrafici del nuovo paziente nell'apposito form, inoltre deve inserire le allergie o farmaci che non può assumere. Infine l’utente deve confermare o annullare l'operazione tramite appositi bottoni.**NOTA: Sto pensando ad un text box in cui inserire i vari nomi, poi facciamo un “split” e ogni parola la inseriamo in un “database delle allergie”, ovviamente con il mock. Casomai ne parliamo per semplificare questa roba.
Risposta mia: perfetto con lo split secondo me**|
|**Errors**| L'utente potrebbe provare a creare più profili dello stesso paziente, tramite il codice fiscale il sistema assicura che non accada.|
|**Sysytem status**| Nel caso di inserimento andato a buon fine il nuovo utente viene aggiunto al sistema e l’utente viene reindirizzato alla pagina home. Con esito negativo la pagina mostra un errore specifico.|