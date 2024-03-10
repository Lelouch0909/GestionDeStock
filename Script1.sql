--<ScriptOptions statementTerminator=";"/>

ALTER TABLE `gestionstock`.`commandefournisseur` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`category` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`lignecommandeclient` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`lignecommandefournisseur` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`lignevente` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`utilisateur` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`mvtstk` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`commandeclient` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`roles` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`fournisseur` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`ventes` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`article` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`client` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`entreprise` DROP PRIMARY KEY;

ALTER TABLE `gestionstock`.`lignevente` DROP INDEX `gestionstock`.`FKtdh351xwh8t4r8omustgvg2y0`;

ALTER TABLE `gestionstock`.`mvtstk` DROP INDEX `gestionstock`.`FKpt75sr5je032y1ppv8rw9nqh2`;

ALTER TABLE `gestionstock`.`roles` DROP INDEX `gestionstock`.`FK71xd67w89vvcotymi95xc1k59`;

ALTER TABLE `gestionstock`.`lignecommandeclient` DROP INDEX `gestionstock`.`FKthsj2spmxb6ygsyj39osvie55`;

ALTER TABLE `gestionstock`.`commandefournisseur` DROP INDEX `gestionstock`.`FKatj6buy5fvy1cuf1wsm95dgvw`;

ALTER TABLE `gestionstock`.`lignecommandefournisseur` DROP INDEX `gestionstock`.`FKk0l9s5k2stvo3i2s71jrdmlal`;

ALTER TABLE `gestionstock`.`utilisateur` DROP INDEX `gestionstock`.`FK1lqyf8cuumbj0iku4axqklfu3`;

ALTER TABLE `gestionstock`.`lignevente` DROP INDEX `gestionstock`.`FKobnh6mrbkqliny3g58mfpheqj`;

ALTER TABLE `gestionstock`.`lignecommandefournisseur` DROP INDEX `gestionstock`.`FKs25oxp23762ei310opvngg5v1`;

ALTER TABLE `gestionstock`.`article` DROP INDEX `gestionstock`.`FKnwo5h4bghmo8jk7jpavwtwi6i`;

ALTER TABLE `gestionstock`.`commandeclient` DROP INDEX `gestionstock`.`FK2t3ma3ko3u9hoiuqafjai8f9`;

ALTER TABLE `gestionstock`.`lignecommandeclient` DROP INDEX `gestionstock`.`FK29ctec6walxsuc2jcixedf15s`;

DROP TABLE `gestionstock`.`lignecommandeclient`;

DROP TABLE `gestionstock`.`mvtstk`;

DROP TABLE `gestionstock`.`roles`;

DROP TABLE `gestionstock`.`commandeclient`;

DROP TABLE `gestionstock`.`utilisateur`;

DROP TABLE `gestionstock`.`article`;

DROP TABLE `gestionstock`.`fournisseur`;

DROP TABLE `gestionstock`.`lignevente`;

DROP TABLE `gestionstock`.`category`;

DROP TABLE `gestionstock`.`entreprise`;

DROP TABLE `gestionstock`.`ventes`;

DROP TABLE `gestionstock`.`client`;

DROP TABLE `gestionstock`.`commandefournisseur`;

DROP TABLE `gestionstock`.`lignecommandefournisseur`;

CREATE TABLE `gestionstock`.`lignecommandeclient` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`identreprise` INT,
	`prixunitaire` DECIMAL(10 , 2),
	`quantite` DECIMAL(10 , 2),
	`idarticle` INT,
	`idcommandeclient` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`mvtstk` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`datemvt` DATETIME,
	`identreprise` INT,
	`quantite` DECIMAL(10 , 2),
	`sourcemvt` VARCHAR(255),
	`typemvt` VARCHAR(255),
	`idarticle` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`roles` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`rolename` VARCHAR(255),
	`idutilisateur` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`commandeclient` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`code` VARCHAR(255),
	`datecommande` DATETIME,
	`etatcommande` VARCHAR(255),
	`identreprise` INT,
	`idclient` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`utilisateur` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`adresse1` VARCHAR(255),
	`adresse2` VARCHAR(255),
	`codepostale` VARCHAR(255),
	`pays` VARCHAR(255),
	`ville` VARCHAR(255),
	`datedenaissance` DATETIME,
	`email` VARCHAR(255),
	`motdepasse` VARCHAR(255),
	`nom` VARCHAR(255),
	`photo` VARCHAR(255),
	`prenom` VARCHAR(255),
	`identreprise` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`article` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`codearticle` VARCHAR(255),
	`designation` VARCHAR(255),
	`identreprise` INT,
	`photo` VARCHAR(255),
	`prixunitaireht` DECIMAL(10 , 2),
	`prixunitairettc` DECIMAL(10 , 2),
	`tauxtva` DECIMAL(10 , 2),
	`idcategory` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`fournisseur` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`adresse1` VARCHAR(255),
	`adresse2` VARCHAR(255),
	`codepostale` VARCHAR(255),
	`pays` VARCHAR(255),
	`ville` VARCHAR(255),
	`identreprise` INT,
	`mail` VARCHAR(255),
	`nom` VARCHAR(255),
	`num_tel` VARCHAR(255),
	`photo` VARCHAR(255),
	`prennom` VARCHAR(255),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`lignevente` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`identreprise` INT,
	`prix_unitaire` DECIMAL(10 , 2),
	`quantite` DECIMAL(10 , 2),
	`idarticle` INT,
	`idvente` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`category` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`code` VARCHAR(255),
	`designation` VARCHAR(255),
	`identreprise` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`entreprise` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`adresse1` VARCHAR(255),
	`adresse2` VARCHAR(255),
	`codepostale` VARCHAR(255),
	`pays` VARCHAR(255),
	`ville` VARCHAR(255),
	`codefiscal` VARCHAR(255),
	`description` VARCHAR(255),
	`email` VARCHAR(255),
	`nom` VARCHAR(255),
	`numtel` VARCHAR(255),
	`photo` VARCHAR(255),
	`siteweb` VARCHAR(255),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`ventes` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`code` VARCHAR(255),
	`commentaire` VARCHAR(255),
	`datevente` DATETIME,
	`identreprise` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`client` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`adresse1` VARCHAR(255),
	`adresse2` VARCHAR(255),
	`codepostale` VARCHAR(255),
	`pays` VARCHAR(255),
	`ville` VARCHAR(255),
	`identreprise` INT,
	`mail` VARCHAR(255),
	`nom` VARCHAR(255),
	`numtel` VARCHAR(255),
	`photo` VARCHAR(255),
	`prenom` VARCHAR(255),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`commandefournisseur` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`code` VARCHAR(255),
	`datecommande` DATETIME,
	`etatcommande` VARCHAR(255),
	`identreprise` INT,
	`idfournisseur` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `gestionstock`.`lignecommandefournisseur` (
	`id` INT NOT NULL,
	`creationdate` DATETIME NOT NULL,
	`last_modified_date` DATETIME,
	`identreprise` INT,
	`prixunitaire` DECIMAL(10 , 2),
	`quantite` DECIMAL(10 , 2),
	`idarticle` INT,
	`idcommandefournisseur` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE INDEX `FKtdh351xwh8t4r8omustgvg2y0` ON `gestionstock`.`lignevente` (`idvente` ASC);

CREATE INDEX `FKpt75sr5je032y1ppv8rw9nqh2` ON `gestionstock`.`mvtstk` (`idarticle` ASC);

CREATE INDEX `FK71xd67w89vvcotymi95xc1k59` ON `gestionstock`.`roles` (`idutilisateur` ASC);

CREATE INDEX `FKthsj2spmxb6ygsyj39osvie55` ON `gestionstock`.`lignecommandeclient` (`idcommandeclient` ASC);

CREATE INDEX `FKatj6buy5fvy1cuf1wsm95dgvw` ON `gestionstock`.`commandefournisseur` (`idfournisseur` ASC);

CREATE INDEX `FKk0l9s5k2stvo3i2s71jrdmlal` ON `gestionstock`.`lignecommandefournisseur` (`idcommandefournisseur` ASC);

CREATE INDEX `FK1lqyf8cuumbj0iku4axqklfu3` ON `gestionstock`.`utilisateur` (`identreprise` ASC);

CREATE INDEX `FKobnh6mrbkqliny3g58mfpheqj` ON `gestionstock`.`lignevente` (`idarticle` ASC);

CREATE INDEX `FKs25oxp23762ei310opvngg5v1` ON `gestionstock`.`lignecommandefournisseur` (`idarticle` ASC);

CREATE INDEX `FKnwo5h4bghmo8jk7jpavwtwi6i` ON `gestionstock`.`article` (`idcategory` ASC);

CREATE INDEX `FK2t3ma3ko3u9hoiuqafjai8f9` ON `gestionstock`.`commandeclient` (`idclient` ASC);

CREATE INDEX `FK29ctec6walxsuc2jcixedf15s` ON `gestionstock`.`lignecommandeclient` (`idarticle` ASC);

