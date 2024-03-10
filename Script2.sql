--<ScriptOptions statementTerminator=";"/>

CREATE TABLE commandeclient (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	code VARCHAR(255),
	datecommande DATETIME,
	etatcommande VARCHAR(255),
	identreprise INT,
	idclient INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE commandefournisseur (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	code VARCHAR(255),
	datecommande DATETIME,
	etatcommande VARCHAR(255),
	identreprise INT,
	idfournisseur INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE lignevente (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	identreprise INT,
	prix_unitaire DECIMAL(10 , 2),
	quantite DECIMAL(10 , 2),
	idarticle INT,
	idvente INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE category (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	code VARCHAR(255),
	designation VARCHAR(255),
	identreprise INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE roles (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	rolename VARCHAR(255),
	idutilisateur INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE entreprise (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	adresse1 VARCHAR(255),
	adresse2 VARCHAR(255),
	codepostale VARCHAR(255),
	pays VARCHAR(255),
	ville VARCHAR(255),
	codefiscal VARCHAR(255),
	description VARCHAR(255),
	email VARCHAR(255),
	nom VARCHAR(255),
	numtel VARCHAR(255),
	photo VARCHAR(255),
	siteweb VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE lignecommandeclient (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	identreprise INT,
	prixunitaire DECIMAL(10 , 2),
	quantite DECIMAL(10 , 2),
	idarticle INT,
	idcommandeclient INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE client (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	adresse1 VARCHAR(255),
	adresse2 VARCHAR(255),
	codepostale VARCHAR(255),
	pays VARCHAR(255),
	ville VARCHAR(255),
	identreprise INT,
	mail VARCHAR(255),
	nom VARCHAR(255),
	numtel VARCHAR(255),
	photo VARCHAR(255),
	prenom VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE mvtstk (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	datemvt DATETIME,
	identreprise INT,
	quantite DECIMAL(10 , 2),
	sourcemvt VARCHAR(255),
	typemvt VARCHAR(255),
	idarticle INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE fournisseur (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	adresse1 VARCHAR(255),
	adresse2 VARCHAR(255),
	codepostale VARCHAR(255),
	pays VARCHAR(255),
	ville VARCHAR(255),
	identreprise INT,
	mail VARCHAR(255),
	nom VARCHAR(255),
	num_tel VARCHAR(255),
	photo VARCHAR(255),
	prennom VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE lignecommandefournisseur (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	identreprise INT,
	prixunitaire DECIMAL(10 , 2),
	quantite DECIMAL(10 , 2),
	idarticle INT,
	idcommandefournisseur INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE utilisateur (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	adresse1 VARCHAR(255),
	adresse2 VARCHAR(255),
	codepostale VARCHAR(255),
	pays VARCHAR(255),
	ville VARCHAR(255),
	datedenaissance DATETIME,
	email VARCHAR(255),
	motdepasse VARCHAR(255),
	nom VARCHAR(255),
	photo VARCHAR(255),
	prenom VARCHAR(255),
	identreprise INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE article (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	codearticle VARCHAR(255),
	designation VARCHAR(255),
	identreprise INT,
	photo VARCHAR(255),
	prixunitaireht DECIMAL(10 , 2),
	prixunitairettc DECIMAL(10 , 2),
	tauxtva DECIMAL(10 , 2),
	idcategory INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE ventes (
	id INT NOT NULL,
	creationdate DATETIME NOT NULL,
	last_modified_date DATETIME,
	code VARCHAR(255),
	commentaire VARCHAR(255),
	datevente DATETIME,
	identreprise INT,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE INDEX FKk0l9s5k2stvo3i2s71jrdmlal ON lignecommandefournisseur (idcommandefournisseur ASC);

CREATE INDEX FKtdh351xwh8t4r8omustgvg2y0 ON lignevente (idvente ASC);

CREATE INDEX FKpt75sr5je032y1ppv8rw9nqh2 ON mvtstk (idarticle ASC);

CREATE INDEX FKthsj2spmxb6ygsyj39osvie55 ON lignecommandeclient (idcommandeclient ASC);

CREATE INDEX FKnwo5h4bghmo8jk7jpavwtwi6i ON article (idcategory ASC);

CREATE INDEX FK29ctec6walxsuc2jcixedf15s ON lignecommandeclient (idarticle ASC);

CREATE INDEX FK71xd67w89vvcotymi95xc1k59 ON roles (idutilisateur ASC);

CREATE INDEX FKobnh6mrbkqliny3g58mfpheqj ON lignevente (idarticle ASC);

CREATE INDEX FKatj6buy5fvy1cuf1wsm95dgvw ON commandefournisseur (idfournisseur ASC);

CREATE INDEX FK2t3ma3ko3u9hoiuqafjai8f9 ON commandeclient (idclient ASC);

CREATE INDEX FKs25oxp23762ei310opvngg5v1 ON lignecommandefournisseur (idarticle ASC);

CREATE INDEX FK1lqyf8cuumbj0iku4axqklfu3 ON utilisateur (identreprise ASC);

