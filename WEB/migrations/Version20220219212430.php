<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220219212430 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE admin CHANGE id id INT AUTO_INCREMENT NOT NULL, CHANGE numtel numtel VARCHAR(15) NOT NULL, ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE agent_service MODIFY id_agent_serv INT NOT NULL');
        $this->addSql('ALTER TABLE agent_service DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE agent_service CHANGE login login VARCHAR(255) DEFAULT NULL, CHANGE id_agent_serv id_agent INT AUTO_INCREMENT NOT NULL');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_33CEF878AA08CB10 ON agent_service (login)');
        $this->addSql('ALTER TABLE agent_service ADD PRIMARY KEY (id_agent)');
        $this->addSql('ALTER TABLE client MODIFY id_client INT NOT NULL');
        $this->addSql('ALTER TABLE client DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE client CHANGE id_client idclient INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE client ADD PRIMARY KEY (idclient)');
        $this->addSql('ALTER TABLE facture MODIFY id_facture INT NOT NULL');
        $this->addSql('ALTER TABLE facture DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE facture CHANGE id_facture idfacture INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE facture ADD PRIMARY KEY (idfacture)');
        $this->addSql('ALTER TABLE fourriere MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE fourriere DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE fourriere CHANGE id idfourriere INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE fourriere ADD PRIMARY KEY (idfourriere)');
        $this->addSql('ALTER TABLE parking MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE parking DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE parking CHANGE id idparking INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE parking ADD PRIMARY KEY (idparking)');
        $this->addSql('ALTER TABLE reclamation MODIFY id_rec INT NOT NULL');
        $this->addSql('ALTER TABLE reclamation DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE reclamation CHANGE id_rec idrec INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE reclamation ADD PRIMARY KEY (idrec)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE admin MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE admin DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE admin CHANGE id id INT NOT NULL, CHANGE nom nom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel numtel VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE agent_service MODIFY id_agent INT NOT NULL');
        $this->addSql('DROP INDEX UNIQ_33CEF878AA08CB10 ON agent_service');
        $this->addSql('ALTER TABLE agent_service DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE agent_service CHANGE nom nom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel numtel VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE id_agent id_agent_serv INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE agent_service ADD PRIMARY KEY (id_agent_serv)');
        $this->addSql('ALTER TABLE client MODIFY idclient INT NOT NULL');
        $this->addSql('ALTER TABLE client DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE client CHANGE nom nom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel numtel VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE voitmat voitmat VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE status status VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE idclient id_client INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE client ADD PRIMARY KEY (id_client)');
        $this->addSql('ALTER TABLE facture MODIFY idfacture INT NOT NULL');
        $this->addSql('ALTER TABLE facture DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE facture CHANGE idfacture id_facture INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE facture ADD PRIMARY KEY (id_facture)');
        $this->addSql('ALTER TABLE fourriere MODIFY idfourriere INT NOT NULL');
        $this->addSql('ALTER TABLE fourriere DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE fourriere CHANGE nomf nomf VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE flatitude flatitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE flongitude flongitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE idfourriere id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE fourriere ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE parking MODIFY idparking INT NOT NULL');
        $this->addSql('ALTER TABLE parking DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE parking CHANGE nomp nomp VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE platitude platitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE plongitude plongitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE idparking id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE parking ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE reclamation MODIFY idrec INT NOT NULL');
        $this->addSql('ALTER TABLE reclamation DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE reclamation CHANGE objet objet VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE etat etat VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE idrec id_rec INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE reclamation ADD PRIMARY KEY (id_rec)');
        $this->addSql('ALTER TABLE reponse CHANGE rps rps VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
