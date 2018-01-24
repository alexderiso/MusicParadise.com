-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carta`
--

DROP TABLE IF EXISTS `carta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carta` (
  `cod` char(6) NOT NULL,
  `scadenza` varchar(5) NOT NULL,
  `numero` char(16) NOT NULL,
  `nomeProprietario` varchar(45) NOT NULL,
  `cliente` varchar(30) NOT NULL,
  PRIMARY KEY (`cod`),
  KEY `fk1_idx` (`cliente`),
  CONSTRAINT `fk1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`nickname`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta`
--

LOCK TABLES `carta` WRITE;
/*!40000 ALTER TABLE `carta` DISABLE KEYS */;
INSERT INTO `carta` VALUES ('0','1/18','1234567890123456','Antonio Spera','Antonio'),('1','1/17','1234567890124456','Antonio Spera','Antonio');
/*!40000 ALTER TABLE `carta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `email` varchar(100) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  PRIMARY KEY (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('a@gmail.com','Antonio','Antonio','Spera','12345678');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composizione`
--

DROP TABLE IF EXISTS `composizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `composizione` (
  `prodotto` char(6) NOT NULL,
  `ordine` int(11) NOT NULL,
  PRIMARY KEY (`prodotto`,`ordine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composizione`
--

LOCK TABLES `composizione` WRITE;
/*!40000 ALTER TABLE `composizione` DISABLE KEYS */;
INSERT INTO `composizione` VALUES ('0',0),('1',1),('2',2);
/*!40000 ALTER TABLE `composizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto`
--

DROP TABLE IF EXISTS `foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto` (
  `codice` char(6) NOT NULL,
  `codice_prodotto` char(6) DEFAULT NULL,
  `immagine` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codice`),
  KEY `codice_prodotto` (`codice_prodotto`),
  CONSTRAINT `foto_ibfk_1` FOREIGN KEY (`codice_prodotto`) REFERENCES `prodottoincatalogo` (`codice`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto`
--

LOCK TABLES `foto` WRITE;
/*!40000 ALTER TABLE `foto` DISABLE KEYS */;
INSERT INTO `foto` VALUES ('0','0','immaginiProdotti/yamaha_batteria.jpg'),('1','0','immaginiProdotti/yamaha_batteria2.jpg'),('10','3','immaginiProdotti/chitarra_Fender2.jpg'),('11','3','immaginiProdotti/chitarra_Fender.jpg'),('12','4','immaginiProdotti/basso_fender.jpg'),('13','4','immaginiProdotti/basso_fender2.jpg'),('14','4','immaginiProdotti/basso_fender3.jpg'),('15','5','immaginiProdotti/amplificatore_marshall.jpg'),('16','5','immaginiProdotti/amplificatore_marshall2.jpg'),('17','5','immaginiProdotti/amplificatore_marshall3.jpg'),('18','6','immaginiProdotti/basso_fender_m7_2.jpg'),('19','6','immaginiProdotti/basso_fender_m7.jpg'),('2','0','immaginiProdotti/yamaha_batteria3.jpg'),('20','6','immaginiProdotti/basso_fender_m7_3.jpg'),('21','7','immaginiProdotti/tastiera_yamaha.jpg'),('22','7','immaginiProdotti/tastiera_yamaha2.jpg'),('23','7','immaginiProdotti/tastiera_yamaha3.jpg'),('24','8','immaginiProdotti/batteria_pearl2.jpg'),('25','8','immaginiProdotti/batteria_pearl.jpg'),('26','8','immaginiProdotti/batteria_pearl3.jpg'),('27','9','immaginiProdotti/software_ableton2.jpg'),('28','9','immaginiProdotti/software_ableton.jpg'),('29','9','immaginiProdotti/software_ableton3.jpg'),('3','1','immaginiProdotti/tamburo_batteria.jpg'),('30','10','immaginiProdotti/software_ableton_educational2.jpg'),('31','10','immaginiProdotti/software_ableton_educational.jpg'),('32','10','immaginiProdotti/software_ableton_educational3.jpg'),('33','11','immaginiProdotti/software_sony.jpg'),('34','11','immaginiProdotti/software_sony2.jpg'),('35','11','immaginiProdotti/software_sony3.jpg'),('36','12','immaginiProdotti/software_cubase.jpg'),('37','12','immaginiProdotti/software_cubase2.jpg'),('38','12','immaginiProdotti/software_cubase3.jpg'),('39','13','immaginiProdotti/amplificatore_fbt.jpg'),('4','1','immaginiProdotti/tamburo_batteria1.jpg'),('40','13','immaginiProdotti/amplificatore_fbt2.jpg'),('41','13','immaginiProdotti/amplificatore_fbt3.jpg'),('42','14','immaginiProdotti/amplificatore_jbl3.jpg'),('43','14','immaginiProdotti/amplificatore_jbl2.jpg'),('44','14','immaginiProdotti/amplificatore_jbl.jpg'),('45','15','immaginiProdotti/tastiera_yamahaP115-3.jpg'),('46','15','immaginiProdotti/tastiera_yamahaP115.jpg'),('47','15','immaginiProdotti/tastiera_yamahaP115-2.jpg'),('48','16','immaginiProdotti/accessori_evans3.jpg'),('49','16','immaginiProdotti/accessori_evans2.jpg'),('5','1','immaginiProdotti/tamburo_batteria2.jpg'),('50','16','immaginiProdotti/accessori_evans.jpg'),('51','17','immaginiProdotti/accessori_evans_RealFeel.jpg'),('52','17','immaginiProdotti/accessori_evans_RealFeel2.jpg'),('53','17','immaginiProdotti/accessori_evans_RealFeel3.jpg'),('54','18','immaginiProdotti/pianoforte_yamaha.jpg'),('55','18','immaginiProdotti/pianoforte_yamaha2.jpg'),('56','18','immaginiProdotti/pianoforte_yamaha3.jpg'),('57','19','immaginiProdotti/dj_pioneer3.jpg'),('58','19','immaginiProdotti/dj_pioneer.jpg'),('59','19','immaginiProdotti/dj_pioneer2.jpg'),('6','2','immaginiProdotti/chitarra_Yamaha.jpg'),('60','20','immaginiProdotti/dj_pioneer_DDJRB1.jpg'),('61','20','immaginiProdotti/dj_pioneer_DDJRB2.jpg'),('62','20','immaginiProdotti/dj_pioneer_DDJRB3.jpg'),('63','21','immaginiProdotti/tastiera_akai.jpg'),('64','21','immaginiProdotti/tastiera_akai2.jpg'),('65','21','immaginiProdotti/tastiera_akai3.jpg'),('66','22','immaginiProdotti/sax_yamaha.jpg'),('67','22','immaginiProdotti/sax_yamaha2.jpg'),('68','22','immaginiProdotti/sax_yamaha3.jpg'),('69','23','immaginiProdotti/homeRecording_pioneer.jpg'),('7','2','immaginiProdotti/chitarra_Yamaha2.jpg'),('70','23','immaginiProdotti/homeRecording_pioneer2.jpg'),('71','23','immaginiProdotti/homeRecording_pioneer3.jpg'),('72','24','immaginiProdotti/homeRecording_yamaha.jpg'),('73','24','immaginiProdotti/homeRecording_yamaha2.jpg'),('74','24','immaginiProdotti/homeRecording_yamaha3.jpg'),('75','25','immaginiProdotti/sax_yamahaYTS280.jpg'),('76','25','immaginiProdotti/sax_yamahaYTS280-2.jpg'),('77','25','immaginiProdotti/sax_yamahaYTS280-3.jpg'),('78','26','immaginiProdotti/tromba_yamaha.jpg'),('79','26','immaginiProdotti/tromba_yamaha2.jpg'),('8','2','immaginiProdotti/chitarra_Yamaha3.jpg'),('80','26','immaginiProdotti/tromba_yamaha3.jpg'),('81','27','immaginiProdotti/tastiera_casio.jpg'),('82','27','immaginiProdotti/tastiera_casio2.jpg'),('83','27','immaginiProdotti/tastiera_casio3.jpg'),('84','28','immaginiProdotti/accessorio_apple.jpg'),('85','28','immaginiProdotti/accessorio_apple2.jpg'),('86','28','immaginiProdotti/accessorio_apple3.jpg'),('87','29','immaginiProdotti/amplificatore_fbtSTAGEMAX12MA.jpg'),('88','29','immaginiProdotti/amplificatore_fbtSTAGEMAX12MA2.jpg'),('89','29','immaginiProdotti/amplificatore_fbtSTAGEMAX12MA3.jpg'),('9','3','immaginiProdotti/chitarra_Fender3.jpg'),('90','30','immaginiProdotti/dj_akai.jpg'),('91','30','immaginiProdotti/dj_akai2.jpg'),('92','30','immaginiProdotti/dj_akai3.jpg'),('93','31','immaginiProdotti/batteria_pearlEXL725.jpg'),('94','31','immaginiProdotti/batteria_pearlEXL725-2.jpg'),('95','31','immaginiProdotti/batteria_pearlEXL725-3.jpg'),('96','32','immaginiProdotti/batteria_pearlEXX725SP.jpg'),('97','32','immaginiProdotti/batteria_pearlEXX725SP-2.jpg'),('98','32','immaginiProdotti/batteria_pearlEXX725SP-3.jpg');
/*!40000 ALTER TABLE `foto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gestoreordini`
--

DROP TABLE IF EXISTS `gestoreordini`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gestoreordini` (
  `email` varchar(100) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `matricola` varchar(10) NOT NULL,
  PRIMARY KEY (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gestoreordini`
--

LOCK TABLES `gestoreordini` WRITE;
/*!40000 ALTER TABLE `gestoreordini` DISABLE KEYS */;
INSERT INTO `gestoreordini` VALUES ('b@gmail.com','paolo10','Paolo','Rossi','1234567@','1234567289');
/*!40000 ALTER TABLE `gestoreordini` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incarrello`
--

DROP TABLE IF EXISTS `incarrello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incarrello` (
  `prodotto` char(6) NOT NULL,
  `utente` varchar(30) NOT NULL,
  `quantita` int(11) NOT NULL,
  PRIMARY KEY (`prodotto`,`utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incarrello`
--

LOCK TABLES `incarrello` WRITE;
/*!40000 ALTER TABLE `incarrello` DISABLE KEYS */;
/*!40000 ALTER TABLE `incarrello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indirizzo`
--

DROP TABLE IF EXISTS `indirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `indirizzo` (
  `codice` char(6) NOT NULL,
  `indirizzo` varchar(30) NOT NULL,
  `citta` varchar(30) NOT NULL,
  `cap` char(5) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `Telefono` char(10) NOT NULL,
  `cliente` varchar(30) NOT NULL,
  PRIMARY KEY (`codice`),
  KEY `fk1_idx` (`cliente`),
  CONSTRAINT `fk2` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`nickname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzo`
--

LOCK TABLES `indirizzo` WRITE;
/*!40000 ALTER TABLE `indirizzo` DISABLE KEYS */;
INSERT INTO `indirizzo` VALUES ('0','Via Po','Marigliano','80034','Antonio','Spera','0818412205','Antonio');
/*!40000 ALTER TABLE `indirizzo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordine` (
  `utente` varchar(30) NOT NULL,
  `num_ordine` int(11) NOT NULL,
  `data_ordine` date NOT NULL,
  `totale` decimal(10,2) NOT NULL,
  `indirizzo` char(6) NOT NULL,
  `carta` char(16) NOT NULL,
  `stato` varchar(30) NOT NULL,
  `numero_traking` char(10) DEFAULT NULL,
  `corriere` varchar(30) DEFAULT NULL,
  `data_consegna` date DEFAULT NULL,
  PRIMARY KEY (`num_ordine`),
  KEY `ordine_ibfk_2` (`indirizzo`),
  KEY `ordine_ibfk_3` (`carta`),
  KEY `ordine_ibfk_1_idx` (`utente`),
  CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`utente`) REFERENCES `cliente` (`nickname`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`indirizzo`) REFERENCES `indirizzo` (`codice`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ordine_ibfk_3` FOREIGN KEY (`carta`) REFERENCES `carta` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES ('Antonio',0,'2018-01-22',155.30,'0','0','consegnato','1214235435','Bartolini','2018-01-22'),('Antonio',1,'2018-01-22',527.40,'0','0','consegnato','2423423423','Bartolini','2018-01-23'),('Antonio',2,'2018-01-22',155.30,'0','0','in preparazione','','',NULL);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodottoincatalogo`
--

DROP TABLE IF EXISTS `prodottoincatalogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prodottoincatalogo` (
  `codice` char(6) NOT NULL,
  `num_disponibilità` int(11) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `colore` varchar(20) DEFAULT NULL,
  `marca` varchar(20) DEFAULT NULL,
  `descrizione` varchar(2000) DEFAULT NULL,
  `peso` decimal(5,2) DEFAULT NULL,
  `prezzo` decimal(10,2) DEFAULT NULL,
  `data_inserimento` date DEFAULT NULL,
  `strumento` varchar(100) NOT NULL,
  PRIMARY KEY (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodottoincatalogo`
--

LOCK TABLES `prodottoincatalogo` WRITE;
/*!40000 ALTER TABLE `prodottoincatalogo` DISABLE KEYS */;
INSERT INTO `prodottoincatalogo` VALUES ('0',0,'Batteria Yamaha','Bianco','Yamaha','Bella',1.00,3500.00,'2017-06-29','batteria'),('1',2,'Batteria Tamburo T5','Nero','Tamburo','Batteria Tamburo T5',1.00,2500.00,'2017-06-11','batteria'),('10',0,'ABLETON Live 9 Educational','','ABLETON','VERSIONE EDUCATIONAL dedicata ad insegnanti, educatori, studenti universitari, scuole di musica pubbliche e private e Istituti SAE. ',1.00,229.00,'2017-06-30','Software'),('11',9,'SONY Acid Pro 7','','SONY','Nel mondo della creazione musicale digitale, il software ACID Pro è sempre emerso come qualcosa di totalmente differente. La sua tecnologia di adattamento dei loop, sia al tempo che alla tonalità del progetto non è seconda a nessuna, e le sue funzionalità permettono di \"spalmare\" i loop sulle tracce e di \"dipingere\" la struttura del nostro brano con pochi e semplici gesti.',1.00,149.00,'2017-06-30','Software'),('12',10,'STEINBERG Nuendo 7',NULL,'STEINBERG','Nuendo 7 porta l\'innovazione in ogni aspetto del lavoro audio-to-picture in cui sia in gioco l\'audio, la TV o la post-produzione di film. Le nuove caratteristiche includono funzionalità uniche del settore come un collegamento diretto con il middleware Audiokinetic Wwise, una soluzione integrata per l\'allineamento audio-to-picture, supporto fader VCA, strumenti di collaborazione avanzati, così come nuove opzioni di rendering per la gestione grandi quantità di file audio. ',1.00,1569.00,'2017-06-30','Software'),('13',3,'FBT VENTIS 112A','Nero','FBT','CASSA ATTIVA BIAMPLIFICATA CON DSP 12\" 900W',7.00,949.00,'2017-06-30','Amplificatore'),('14',6,'JBL PRX815XLFW','Nero','JBL','SUBWOOFER ATTIVO Wi-Fi 15\" 1500W',10.00,1090.00,'2017-07-01','Amplificatore'),('15',1,'YAMAHA P115 Black','Nero','YAMAHA','Il pianoforte digitale Yamaha P-115 continua la tradizione della best-seller P-Series, con funzioni ancora più facili da usare e una migliore qualità del suono. Disponibile nelle finiture bianca o nera, il P-115 offre il tocco ed il suono di un pianoforte Yamaha in un design compatto: E\' ideale per la casa, l\'home recording o l\'uso dal vivo.',1.00,599.00,'2017-07-01','Tastiere'),('16',11,'EVANS SO 2346 SoundOff Standard Kit','Nero','EVANS','Le sordine per batteria SoundOff sono la linea più popolare di prodotti disponibili per il controllo del volume. Le sordine forniscono una riduzione significativa del volume per mantenere il rumore durante le esercitazioni al minimo. I drum mute SoundOff dispongono di una costruzione duratura che può sopportare un uso frequente e sono un\'ottima idea regalo per tutti i batteristi.',1.00,39.00,'2017-07-01','Accessori'),('17',10,' EVANS RF12D RealFeel Practice Pad','Nero','EVANS','I pad per allenamento RealFeel by Evans sono la scelta più popolare disponibile per esercitarsi. Caratterizzati da una superficie in gomma naturale con una finitura in tessuto grigio scuro resistente all\'usura, RealFeel by Evans forniscono il miglior sostituto ad una batteria acustica per esercitarsi. Sono disponibili diversi modelli per soddisfare le singole esigenze.',1.00,49.00,'2017-07-01','Accessori'),('18',0,'YAMAHA YDP143 Arius Palissandro','Nero','YAMAHA','Il pianoforte digitale Yamaha Arius YDP-143 offre il suono ed un tocco di pianoforte acustico autentico adatto a qualsiasi aspirante pianista. L\'azione della tastiera Graded Hammer Standard (GHS) aiuta a costruire un tecnica corretta e la forza nelle dita per una facile transizione ai pianoforti acustici. Il migliorato Pure CF Engine offre registrazioni espressive ad alta risoluzione del pianoforte a coda da concerto Yamaha CFIIIS. La Damper Resonance ricrea digitalmente la profondità del suono del pianoforte acustico quando si usano i pedali, mentre riverberi recentemente migliorati replicano la sensazione di suonare in ambienti diversi. Con 192 note di polifonia, molte delle più complesse composizioni per pianoforte possono essere suonate senza alcuna perdita di note, e le note con sustain suonano in modo fedele. Il Arius YDP-143 è disponibile nelle finiture palissandro o noce nero, ed il mobile in stile supera tutte le aspettative di un piano di tipico elettrico.',1.00,775.00,'2017-07-01','Tastiere'),('19',2,'PIONEER DDJ-SB2','Nero','PIONEER','Gli aspiranti DJ hanno ora il controllo senza compromessi, con il lancio del controller entry level DDJ-SB2 per Serato DJ Intro e Serato DJ. Il DDJ-SB2 a due canali vanta tutte le caratteristiche dell\'originale DDJ-SB - tra cui Filter Fade, Performance Pad e funzionalità avanzate di solito non disponibili in questa fascia di prezzo - e aggiunge trim pot, level meter, effetto Pad Trans beat e d un controllo 4-deck.',1.00,259.00,'2017-07-01','DJ'),('2',3,'YAMAHA C40M SATINATA','Marrone chiaro','YAMAHA','Spendere poco ormai non vuol dire più accontentarsi di uno strumento di seconda scelta, come la C40 abilmente dimostra. ',1.00,115.00,'2017-05-30','Chitarra'),('20',10,'PIONEER DDJ-RB','Nero','PIONEER','modello bello, ma non troppo, ciaoen.. - 000 FASIJHAIS , ,',1.00,259.00,'2017-07-01','DJ'),('21',3,' AKAI MPK Mini MkII','Nero','AKAI','Il controller Akai MPK mini è dotato di 25 mini tasti sensibili alla velocity, un joystick a 4 vie per il controllo del pitch bend e della modulation, 8 potenziometri rotativi per l\'invio di messaggi di MIDI Control Change e altrettanti Pad retroilluminati sensibili alla velocity in stile MPC che si articolano su due banchi per un totale di 16 pad virtuali. I Pad funzionano in 3 differenti modalità che corrispondono a 3 differenti messaggi MIDI inviabili: nota, Control Change e Program Change.',1.00,98.00,'2017-07-01','Tastiere'),('22',8,'YAMAHA YAS480','Oro','YAMAHA','SASSOFONO CONTRALTO IN MIb LACCATO ORO',3.00,1699.00,'2017-07-01','Fiati'),('23',1,' PIONEER S-DJ50X B-Stock','Nero','PIONEER','ioneer DJ presenta la serie S-DJ X di diffusori monitor attivi, progettati pensando alla dance music. I modelli S-DJ X sono ricchi della più avanzata tecnologia di diffusione, sviluppata da Pioneer e dalla sub-brand pro audio TAD Labs (Technical Audio Devices Laboratories), per offrire monitoring accurato, bassi potenti e assoluta nitidezza delle frequenze medio-alte. ',1.00,159.00,'2017-07-01','Home recording'),('24',10,'YAMAHA HS5','Nero','YAMAHA','La nuova serie di sistemi di monitoraggio HS è stata creata dal team di progettazione Yamaha per offrire un prodotto eccezionalmente semplice, preciso e affidabile. La serie HS è stata progettata come un vero sistema di monitoraggio, nella tradizione dello storico Yamaha NS10M.',1.00,160.00,'2017-07-01','Home recording'),('25',2,'YAMAHA YTS280','Oro','YAMAHA','I sax YTS-280 offrono un perfetto inizio perché progettati pensando ai giovani principianti. Relativamente leggeri e con forma ergonomica, sono facili da maneggiare e da suonare. L\'intonazione è perfetta, come ci si aspetterebbe da Yamaha, ed è facile ottenere un grande suono. Il design Yamaha offre un strumento ottimale per i principianti, aiutando così notevolmente sia i progressi di apprendimento che la creatività. Basati sui modelli di sax altamente raccomandati i nuovi YTS-280 sono ulteriormente migliorati con un nuovo ricevitore del collo più stabile e una migliore connessione delle chiavi per i Si-Do# bassi.',1.00,1445.00,'2017-07-04','Fiati'),('26',6,'YAMAHA YTR2330','Oro','YAMAHA','La tromba YTR2330 è lo strumento perfetto per gli studenti che iniziano a suonare. L\'obiettivo principale della sua progettazione era quello di ottenere una tromba leggera e facile da suonare con grande suono e una perfetta intonazione. Yamaha progetta gli strumenti con particolare attenzione all\'ergonomia in modo da offrire un supporto ottimale per i principianti cha aiuta notevolmente l\'apprendimento, i progressi e la creatività. Per contribuire ad ottenere uno strumento più leggero, la YTR2330 è stata progettata senza la barra di connessione sulla pompa principale e con la campana a 2 pezzi in ottone giallo con spessore ideale. I pistoni in Monel sono noti per la loro ottima azione e per ridurre al minimo la necessità di manutenzione.',1.00,540.00,'2017-07-04','Archi'),('27',7,'CASIO CDP130 Black','Nero','CASIO','Effetti digitali eccellenti, 10 suoni e metronomo integrato: il CDP-130 è lo strumento ideale per accedere al fantastico mondo delle tastiere. Il sistema di altoparlanti migliorato produce un suono di pianoforte impressionante, reso ancora più spettacolare dal pulsante effetto auditorium integrato: basta premere il pulsante per donare al suono del pianoforte il reverbero di una sala da concerto, e l\'esibizione diventa quasi un\'esperienza reale. Grazie ai suoni di strumenti a corda migliorati, ogni brano è un\'esperienza unica, indimenticabile.',1.00,420.00,'2017-07-04','Tastiere'),('28',1,'APPLE MD464ZM','Bianco','APPLE','Con l\'adattatore Apple da Thunderbolt a FireWire Apple colleghi facilmente il tuo dispositivo Apple con porta Thunderbolt a un dispositivo FireWire. Piccolo e compatto, si collega alla porta Thunderbolt del dispositivo e la trasforma in una porta FireWire 800: hai fino a 7W per utilizzare periferiche alimentate via bus come dischi rigidi e dispositivi audio.',1.00,34.90,'2017-07-04','Accessori'),('29',2,'FBT StageMaxX 12MA','Nero','FBT','StageMaxX 12A è un diffusore biamplificato progettato per utilizzo come stage monitor ma utilizzabile anche per installazione fissa grazie agli accessori disponibili. Il cabinet in polipropilene estremamente robusto e privo di risonanze, reso possibile dalla enorme esperienza di FBT, si comporta acusticamente come un cabinet in legno ma permette di integrare tutti i componenti necessari ad avere una qualità senza compromessi in una forma compatta, elegante, poco invasiva sul palco ed estremamente comoda e facile da trasportare. Finalmente il giusto connubio di tutte le doti del perfetto stage monitor si fondono in un prodotto innovativo e competitivo.',1.00,850.00,'2017-07-04','Amplificatore'),('3',6,'Standard Stratocaster ','Rosso','FENDER','Il suono che ha creato leggende. La Stratocaster Standard SSS offre il leggendario suono Fender nello stile più classico.',2.00,630.00,'2017-06-25','Chitarra'),('30',10,'AKAI BT100','Nero','AKAI','Akai Professional BT100 è un giradischi esteticamente accattivante, di colore nero, dotato di trazione a cinghia con rotazione impostabile a 33 1/3, o 45 giri, di un braccio dritto, sul quale è montata una testina a magnete mobile con puntina intercambiabile (CR250), e di connettività Bluetooth per la riproduzione audio da diffusori amplificati che supportano questo standard di connessione audio senza fili. BT100 può essere collegato a diffusori, mixer e amplificatori tradizionali mediante l\'uscita stereo disponibile su connettori (L/R) di tipo RCA in prossimità dei quali è presente un interruttore utile all\'eventuale attivazione del preamplificatore RIAA integrato.',1.00,189.00,'2017-07-04','DJ'),('31',2,'PEARL EXL725SP','Nero','PEARL','Dopo 30 anni come batteria #1 di vendite nel mondo, la Export Series è ancora il nome che ogni batterista conosce. Avendo avviato migliaia di carriere di batteristi portando qualità e valore in un unico pacchetto, la Export di oggi continua a costruire leggende. Con caratteristiche come la composizione dei fusti Reference-inspired, la scelta di un involucro (EXX) o di una finitura laccato lucida (EXL), e l\'insuperabile pacchetto hardware Pearl serie 830, la Export è pronta ad alimentare il fuoco delle future icone della batteria. Il design dell\'attacco a tre vie del sisema di montaggio Pearl Opti-Loc utilizza isloators gomma in due punti tuning ed un altro in una delle due prese d\'aria del tamburo. Questo crea il massimo di sustain e risonanza, fornendo performance senza oscillazioni.',15.00,580.00,'2017-07-04','Batteria'),('32',2,'PEARL EXX725SP ','Bianco','PEARL','Il drum-set numero uno di vendite al mondo è tornato migliore che mai. La serie Export è un nome ogni batterista conosce. Qualsiasi batterista di successo di oggi è probabile che abbia iniziato a suonare su un kit Export. Questi kit hanno iniziato migliaia di batteristi offrendo contemporaneamente qualità e convenienza.',12.00,780.00,'2017-07-04','Batteria'),('4',5,'FENDER SQUIER JAZZ BASS AFFINITY V RW BLACK','Nero','FENDER','Rappresentando i migliori valori nel design di un basso sul mercato al giorno d\'oggi, i Jazz Bass V della serie Affinity hanno un tono potente e un feel veloce.',1.00,345.00,'2017-06-26','Basso'),('5',2,'MARSHALL JMD501','Nero','MARSHALL','MARSHALL JMD-1 JMD501 Combo Valvolare per Chitarra 50W 1x12\"',5.00,450.00,'2017-06-26','Amplificatore'),('6',5,'MARCUS MILLER M7 Swamp Ash 5 NT Natural','Marrone','FENDER',' Questa versione in Swamp Ash, è ricca di alti brillanti, bassi precisi e medie scavate. Il top in acero massello fiammato rende queste frequenze sempre corpose e risonanti. ',1.00,798.00,'2017-06-30','Basso'),('7',3,'YAMAHA Tyros 5 76 ','Grigio','YAMAHA','Tyros è una tastiera caratterizzata dalle performance migliori, con suoni, effetti e accompagnamenti sorprendenti e così realistici da sembrare di essere accompagnati dai migliori musicisti al mondo. Tyros5 conferisce un nuovo livello di realismo al suono come nessun altro strumento prima d\'ora. E\' la compagna perfetta per abbozzare composizioni, realizzare l\'arrangiamento perfetto o eseguire performance memorabili.',1.00,3770.00,'2017-06-30','Tastiere'),('8',2,' PEARL Target TGX625C Silver Sparkle ','Silver','PEARL','Le nuove batterie della serie Target offrono ai batteristi di oggi alta qualità, grandi performance, un drumset completo ad un prezzo ben al di sotto di molti altri kit che offrono meno. ',10.00,565.00,'2017-06-30','Batteria'),('9',9,'ABLETON Live 9 Intro ','','ABLETON','Ableton Live 9 getta un ponte tra studio e palco con un flusso di lavoro intuitivo che ti permette di fare quasi tutto in tempo reale. ',1.00,79.00,'2017-06-30','Software');
/*!40000 ALTER TABLE `prodottoincatalogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodottoordine`
--

DROP TABLE IF EXISTS `prodottoordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prodottoordine` (
  `codice` char(6) NOT NULL,
  `quantita` int(11) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `colore` varchar(20) DEFAULT NULL,
  `marca` varchar(20) DEFAULT NULL,
  `descrizione` varchar(2000) DEFAULT NULL,
  `peso` decimal(5,2) DEFAULT NULL,
  `prezzo` decimal(10,2) DEFAULT NULL,
  `strumento` varchar(100) NOT NULL,
  PRIMARY KEY (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodottoordine`
--

LOCK TABLES `prodottoordine` WRITE;
/*!40000 ALTER TABLE `prodottoordine` DISABLE KEYS */;
INSERT INTO `prodottoordine` VALUES ('0',1,'YAMAHA C40M SATINATA','Marrone chiaro','YAMAHA','Spendere poco ormai non vuol dire più accontentarsi di uno strumento di seconda scelta, come la C40 abilmente dimostra. ',1.00,115.00,'Chitarra'),('1',1,'CASIO CDP130 Black','Nero','CASIO','Effetti digitali eccellenti, 10 suoni e metronomo integrato: il CDP-130 è lo strumento ideale per accedere al fantastico mondo delle tastiere. Il sistema di altoparlanti migliorato produce un suono di pianoforte impressionante, reso ancora più spettacolare dal pulsante effetto auditorium integrato: basta premere il pulsante per donare al suono del pianoforte il reverbero di una sala da concerto, e l\'esibizione diventa quasi un\'esperienza reale. Grazie ai suoni di strumenti a corda migliorati, ogni brano è un\'esperienza unica, indimenticabile.',1.00,420.00,'Tastiere'),('2',1,'YAMAHA C40M SATINATA','Marrone chiaro','YAMAHA','Spendere poco ormai non vuol dire più accontentarsi di uno strumento di seconda scelta, come la C40 abilmente dimostra. ',1.00,115.00,'Chitarra');
/*!40000 ALTER TABLE `prodottoordine` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-24 10:56:05
