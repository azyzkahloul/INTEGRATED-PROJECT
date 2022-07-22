<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220214182907 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE agent_service (id_agent_serv INT AUTO_INCREMENT NOT NULL, nom VARCHAR(30) NOT NULL, prenom VARCHAR(30) NOT NULL, numtel VARCHAR(8) NOT NULL, login VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, PRIMARY KEY(id_agent_serv)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE amande (id_amande INT AUTO_INCREMENT NOT NULL, total INT NOT NULL, PRIMARY KEY(id_amande)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE client (id_client INT AUTO_INCREMENT NOT NULL, nom VARCHAR(30) NOT NULL, prenom VARCHAR(30) NOT NULL, numtel VARCHAR(8) NOT NULL, voitmat VARCHAR(255) NOT NULL, instamon DOUBLE PRECISION NOT NULL, login VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, status VARCHAR(255) NOT NULL, PRIMARY KEY(id_client)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE facture (id_facture INT AUTO_INCREMENT NOT NULL, nbheure INT NOT NULL, pu INT NOT NULL, total INT NOT NULL, dateentrer DATE NOT NULL, PRIMARY KEY(id_facture)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('DROP TABLE user');
        $this->addSql('ALTER TABLE admin MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE admin DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE admin CHANGE id id_admin INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE admin ADD PRIMARY KEY (id_admin)');
        $this->addSql('ALTER TABLE fourriere MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE fourriere DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE fourriere CHANGE id id_f INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE fourriere ADD PRIMARY KEY (id_f)');
        $this->addSql('ALTER TABLE parking MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE parking DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE parking CHANGE id id_p INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE parking ADD PRIMARY KEY (id_p)');
        $this->addSql('ALTER TABLE reponse MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE reponse DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE reponse CHANGE id id_rep INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE reponse ADD PRIMARY KEY (id_rep)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, prenom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, cin VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, numtel VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, role LONGTEXT CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci` COMMENT \'(DC2Type:array)\', voitmat VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, instamon INT DEFAULT NULL, login VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, password VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, statut VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('DROP TABLE agent_service');
        $this->addSql('DROP TABLE amande');
        $this->addSql('DROP TABLE client');
        $this->addSql('DROP TABLE facture');
        $this->addSql('ALTER TABLE admin MODIFY id_admin INT NOT NULL');
        $this->addSql('ALTER TABLE admin DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE admin CHANGE nom nom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel numtel VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE id_admin id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE admin ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE fourriere MODIFY id_f INT NOT NULL');
        $this->addSql('ALTER TABLE fourriere DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE fourriere CHANGE nomf nomf VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE flatitude flatitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE flongitude flongitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE id_f id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE fourriere ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE parking MODIFY id_p INT NOT NULL');
        $this->addSql('ALTER TABLE parking DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE parking CHANGE nomp nomp VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE platitude platitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE plongitude plongitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE id_p id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE parking ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE reclamation CHANGE objet objet VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE etat etat VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE reponse MODIFY id_rep INT NOT NULL');
        $this->addSql('ALTER TABLE reponse DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE reponse CHANGE rps rps VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE id_rep id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE reponse ADD PRIMARY KEY (id)');
    }
}
