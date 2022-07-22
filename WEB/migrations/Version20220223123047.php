<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220223123047 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE agent_service (id_agent_serv INT AUTO_INCREMENT NOT NULL, nom VARCHAR(30) NOT NULL, prenom VARCHAR(30) NOT NULL, numtel VARCHAR(8) NOT NULL, login VARCHAR(255) DEFAULT NULL, password VARCHAR(255) NOT NULL, UNIQUE INDEX UNIQ_33CEF878AA08CB10 (login), PRIMARY KEY(id_agent_serv)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE amande (id_amande INT AUTO_INCREMENT NOT NULL, total INT NOT NULL, PRIMARY KEY(id_amande)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE client (id_client INT AUTO_INCREMENT NOT NULL, nom VARCHAR(30) NOT NULL, prenom VARCHAR(30) NOT NULL, numtel VARCHAR(8) NOT NULL, voitmat VARCHAR(255) NOT NULL, instamon DOUBLE PRECISION NOT NULL, login VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, status VARCHAR(255) NOT NULL, UNIQUE INDEX UNIQ_C7440455AA08CB10 (login), PRIMARY KEY(id_client)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE facture (id_facture INT AUTO_INCREMENT NOT NULL, nbheure INT NOT NULL, pu INT NOT NULL, total INT NOT NULL, dateentrer DATETIME NOT NULL, PRIMARY KEY(id_facture)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('DROP TABLE user');
        $this->addSql('ALTER TABLE admin ADD id_client INT DEFAULT NULL, ADD id_agent_serv INT DEFAULT NULL, ADD id_rec INT DEFAULT NULL, ADD role VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE admin ADD CONSTRAINT FK_880E0D76E173B1B8 FOREIGN KEY (id_client) REFERENCES client (id_client)');
        $this->addSql('ALTER TABLE admin ADD CONSTRAINT FK_880E0D76BAAC2CEC FOREIGN KEY (id_agent_serv) REFERENCES agent_service (id_agent_serv)');
        $this->addSql('ALTER TABLE admin ADD CONSTRAINT FK_880E0D76BF396750 FOREIGN KEY (id) REFERENCES fourriere (id)');
        $this->addSql('ALTER TABLE admin ADD CONSTRAINT FK_880E0D76FAA12276 FOREIGN KEY (id_rec) REFERENCES reclamation (id_rec)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_880E0D76AA08CB10 ON admin (login)');
        $this->addSql('CREATE INDEX IDX_880E0D76E173B1B8 ON admin (id_client)');
        $this->addSql('CREATE INDEX IDX_880E0D76BAAC2CEC ON admin (id_agent_serv)');
        $this->addSql('CREATE INDEX IDX_880E0D76FAA12276 ON admin (id_rec)');
        $this->addSql('ALTER TABLE fourriere CHANGE flatitude flatitude DOUBLE PRECISION NOT NULL, CHANGE flongitude flongitude DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE parking ADD id_client INT DEFAULT NULL, CHANGE platitude platitude DOUBLE PRECISION NOT NULL, CHANGE plongitude plongitude DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE parking ADD CONSTRAINT FK_B237527AE173B1B8 FOREIGN KEY (id_client) REFERENCES client (id_client)');
        $this->addSql('CREATE INDEX IDX_B237527AE173B1B8 ON parking (id_client)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE admin DROP FOREIGN KEY FK_880E0D76BAAC2CEC');
        $this->addSql('ALTER TABLE admin DROP FOREIGN KEY FK_880E0D76E173B1B8');
        $this->addSql('ALTER TABLE parking DROP FOREIGN KEY FK_B237527AE173B1B8');
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, prenom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, cin VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, numtel VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, role LONGTEXT CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci` COMMENT \'(DC2Type:array)\', voitmat VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, instamon INT DEFAULT NULL, login VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, password VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, statut VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('DROP TABLE agent_service');
        $this->addSql('DROP TABLE amande');
        $this->addSql('DROP TABLE client');
        $this->addSql('DROP TABLE facture');
        $this->addSql('ALTER TABLE admin DROP FOREIGN KEY FK_880E0D76BF396750');
        $this->addSql('ALTER TABLE admin DROP FOREIGN KEY FK_880E0D76FAA12276');
        $this->addSql('DROP INDEX UNIQ_880E0D76AA08CB10 ON admin');
        $this->addSql('DROP INDEX IDX_880E0D76E173B1B8 ON admin');
        $this->addSql('DROP INDEX IDX_880E0D76BAAC2CEC ON admin');
        $this->addSql('DROP INDEX IDX_880E0D76FAA12276 ON admin');
        $this->addSql('ALTER TABLE admin DROP id_client, DROP id_agent_serv, DROP id_rec, DROP role, CHANGE nom nom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel numtel VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE fourriere CHANGE nomf nomf VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE flatitude flatitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE flongitude flongitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('DROP INDEX IDX_B237527AE173B1B8 ON parking');
        $this->addSql('ALTER TABLE parking DROP id_client, CHANGE nomp nomp VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE platitude platitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE plongitude plongitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE reclamation CHANGE objet objet VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE etat etat VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE reponse CHANGE rps rps VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
