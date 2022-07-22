<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220301132434 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE admin MODIFY id_admin INT NOT NULL');
        $this->addSql('ALTER TABLE admin DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE admin ADD id_client INT DEFAULT NULL, ADD id_agent_serv INT DEFAULT NULL, ADD role VARCHAR(255) NOT NULL, CHANGE id_admin id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE admin ADD CONSTRAINT FK_880E0D76E173B1B8 FOREIGN KEY (id_client) REFERENCES client (id_client)');
        $this->addSql('ALTER TABLE admin ADD CONSTRAINT FK_880E0D76BAAC2CEC FOREIGN KEY (id_agent_serv) REFERENCES agent_service (id_agent_serv)');
        $this->addSql('ALTER TABLE admin ADD CONSTRAINT FK_880E0D76BF396750 FOREIGN KEY (id) REFERENCES fourriere (id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_880E0D76AA08CB10 ON admin (login)');
        $this->addSql('CREATE INDEX IDX_880E0D76E173B1B8 ON admin (id_client)');
        $this->addSql('CREATE INDEX IDX_880E0D76BAAC2CEC ON admin (id_agent_serv)');
        $this->addSql('ALTER TABLE admin ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE agent_service CHANGE login login VARCHAR(255) DEFAULT NULL, CHANGE cin numtel VARCHAR(8) NOT NULL');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_33CEF878AA08CB10 ON agent_service (login)');
        $this->addSql('ALTER TABLE client CHANGE instamon instamon DOUBLE PRECISION NOT NULL');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_C7440455AA08CB10 ON client (login)');
        $this->addSql('ALTER TABLE facture CHANGE dateentrer dateentrer DATETIME NOT NULL');
        $this->addSql('ALTER TABLE fourriere MODIFY idf INT NOT NULL');
        $this->addSql('ALTER TABLE fourriere DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE fourriere CHANGE flatitude flatitude DOUBLE PRECISION NOT NULL, CHANGE flongitude flongitude DOUBLE PRECISION NOT NULL, CHANGE idf id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE fourriere ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE parking MODIFY idp INT NOT NULL');
        $this->addSql('ALTER TABLE parking DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE parking ADD id_client INT DEFAULT NULL, CHANGE platitude platitude DOUBLE PRECISION NOT NULL, CHANGE plongitude plongitude DOUBLE PRECISION NOT NULL, CHANGE idp id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE parking ADD CONSTRAINT FK_B237527AE173B1B8 FOREIGN KEY (id_client) REFERENCES client (id_client)');
        $this->addSql('CREATE INDEX IDX_B237527AE173B1B8 ON parking (id_client)');
        $this->addSql('ALTER TABLE parking ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE reponse MODIFY id_rep INT NOT NULL');
        $this->addSql('ALTER TABLE reponse DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE reponse CHANGE id_rep id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE reponse ADD PRIMARY KEY (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE admin MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE admin DROP FOREIGN KEY FK_880E0D76E173B1B8');
        $this->addSql('ALTER TABLE admin DROP FOREIGN KEY FK_880E0D76BAAC2CEC');
        $this->addSql('ALTER TABLE admin DROP FOREIGN KEY FK_880E0D76BF396750');
        $this->addSql('DROP INDEX UNIQ_880E0D76AA08CB10 ON admin');
        $this->addSql('DROP INDEX IDX_880E0D76E173B1B8 ON admin');
        $this->addSql('DROP INDEX IDX_880E0D76BAAC2CEC ON admin');
        $this->addSql('ALTER TABLE admin DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE admin DROP id_client, DROP id_agent_serv, DROP role, CHANGE nom nom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel numtel VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE id id_admin INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE admin ADD PRIMARY KEY (id_admin)');
        $this->addSql('DROP INDEX UNIQ_33CEF878AA08CB10 ON agent_service');
        $this->addSql('ALTER TABLE agent_service ADD cin VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, DROP numtel, CHANGE nom nom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('DROP INDEX UNIQ_C7440455AA08CB10 ON client');
        $this->addSql('ALTER TABLE client CHANGE nom nom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel numtel VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE voitmat voitmat VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE instamon instamon DOUBLE PRECISION DEFAULT NULL, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE status status VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE facture CHANGE dateentrer dateentrer DATE NOT NULL');
        $this->addSql('ALTER TABLE fourriere MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE fourriere DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE fourriere CHANGE nomf nomf VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE flatitude flatitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE flongitude flongitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE id idf INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE fourriere ADD PRIMARY KEY (idf)');
        $this->addSql('ALTER TABLE parking MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE parking DROP FOREIGN KEY FK_B237527AE173B1B8');
        $this->addSql('DROP INDEX IDX_B237527AE173B1B8 ON parking');
        $this->addSql('ALTER TABLE parking DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE parking DROP id_client, CHANGE nomp nomp VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE platitude platitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE plongitude plongitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE id idp INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE parking ADD PRIMARY KEY (idp)');
        $this->addSql('ALTER TABLE reclamation CHANGE objet objet VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE etat etat VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE reponse MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE reponse DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE reponse CHANGE rps rps VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE id id_rep INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE reponse ADD PRIMARY KEY (id_rep)');
    }
}
