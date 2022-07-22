<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220222223806 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE admin ADD id_client INT DEFAULT NULL, ADD id_agent_serv INT DEFAULT NULL, CHANGE Role role VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE admin ADD CONSTRAINT FK_880E0D76E173B1B8 FOREIGN KEY (id_client) REFERENCES client (id_client)');
        $this->addSql('ALTER TABLE admin ADD CONSTRAINT FK_880E0D76BAAC2CEC FOREIGN KEY (id_agent_serv) REFERENCES agent_service (id_agent_serv)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_880E0D76AA08CB10 ON admin (login)');
        $this->addSql('CREATE INDEX IDX_880E0D76E173B1B8 ON admin (id_client)');
        $this->addSql('CREATE INDEX IDX_880E0D76BAAC2CEC ON admin (id_agent_serv)');
        $this->addSql('ALTER TABLE agent_service CHANGE login login VARCHAR(255) DEFAULT NULL');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_33CEF878AA08CB10 ON agent_service (login)');
        $this->addSql('ALTER TABLE client ADD parking_id INT NOT NULL');
        $this->addSql('ALTER TABLE client ADD CONSTRAINT FK_C7440455F17B2DD FOREIGN KEY (parking_id) REFERENCES parking (id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_C7440455AA08CB10 ON client (login)');
        $this->addSql('CREATE INDEX IDX_C7440455F17B2DD ON client (parking_id)');
        $this->addSql('ALTER TABLE facture CHANGE dateentrer dateentrer DATETIME NOT NULL');
        $this->addSql('ALTER TABLE fourriere CHANGE flatitude flatitude DOUBLE PRECISION NOT NULL, CHANGE flongitude flongitude DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE parking CHANGE platitude platitude DOUBLE PRECISION NOT NULL, CHANGE plongitude plongitude DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE reclamation ADD admin_id INT NOT NULL');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE606404642B8210 FOREIGN KEY (admin_id) REFERENCES admin (id)');
        $this->addSql('CREATE INDEX IDX_CE606404642B8210 ON reclamation (admin_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE admin DROP FOREIGN KEY FK_880E0D76E173B1B8');
        $this->addSql('ALTER TABLE admin DROP FOREIGN KEY FK_880E0D76BAAC2CEC');
        $this->addSql('DROP INDEX UNIQ_880E0D76AA08CB10 ON admin');
        $this->addSql('DROP INDEX IDX_880E0D76E173B1B8 ON admin');
        $this->addSql('DROP INDEX IDX_880E0D76BAAC2CEC ON admin');
        $this->addSql('ALTER TABLE admin DROP id_client, DROP id_agent_serv, CHANGE nom nom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel numtel VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE role Role TEXT DEFAULT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('DROP INDEX UNIQ_33CEF878AA08CB10 ON agent_service');
        $this->addSql('ALTER TABLE agent_service CHANGE nom nom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel numtel VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE client DROP FOREIGN KEY FK_C7440455F17B2DD');
        $this->addSql('DROP INDEX UNIQ_C7440455AA08CB10 ON client');
        $this->addSql('DROP INDEX IDX_C7440455F17B2DD ON client');
        $this->addSql('ALTER TABLE client DROP parking_id, CHANGE nom nom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prenom prenom VARCHAR(30) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numtel numtel VARCHAR(8) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE voitmat voitmat VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE login login VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE status status VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE facture CHANGE dateentrer dateentrer DATE NOT NULL');
        $this->addSql('ALTER TABLE fourriere CHANGE nomf nomf VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE flatitude flatitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE flongitude flongitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE parking CHANGE nomp nomp VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE platitude platitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE plongitude plongitude VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE606404642B8210');
        $this->addSql('DROP INDEX IDX_CE606404642B8210 ON reclamation');
        $this->addSql('ALTER TABLE reclamation DROP admin_id, CHANGE objet objet VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE etat etat VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE reponse CHANGE rps rps VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
