<?php

namespace App\Entity;


use App\Repository\AgentServiceRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;



/**
 * @ORM\Entity(repositoryClass=AgentServiceRepository::class)
 */
class AgentService 
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     * @ORM\Column(type="integer")
     */
    private $id_agent_serv;

    /**
     * @ORM\Column(type="string", length=30)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 1,
     *      max = 20,
     *      minMessage = "Le nom  doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "Le nom  doit comporter au plus {{ limit }} caractères"
     * )
     */
    
    private $nom;

    /**
     * @ORM\Column(type="string", length=30)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 1,
     *      max = 20,
     *      minMessage = "Le nom  doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "Le nom  doit comporter au plus {{ limit }} caractères"
     * )
     */

    private $prenom;

    /**
     * @ORM\Column(type="string", length=8)
     * @Assert\NotBlank
     * @Assert\Length(
     *  value = 8,
     *  exactMessage = "Le numéro du téléphone doit comporter 8 caractères"
     * )
     */
    private $numtel;

    /**
     * @ORM\Column(type="string", unique=true, nullable=true)
     * @Assert\NotBlank
     * @Assert\Email(
     * message= "L email '{{ value }}' n'est pas valide."
     *  )
     */
    private $login;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     * @Assert\NotCompromisedPassword(
     * message= "Ce mot de passe doit comporter au moins une lettre majuscule et au moins une lettre minuscule"           
     * )
     * @Assert\Length(
     *      min = 8,
     *      max = 20,
     *   minMessage = "Le mot de passe  doit comporter au moins {{ limit }} caractères",
     *   maxMessage = "Le mot de passe  doit comporter au plus {{ limit }} caractères"         
     * )  
     */
    private $password;

    
    /**
     * @ORM\OneToMany(targetEntity=AgentService::class, mappedBy="admin")
     */
    private $admin;

    

    public function getId(): ?int
    {
        return $this->id_agent_serv;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getNumtel(): ?string
    {
        return $this->numtel;
    }

    public function setNumtel(string $numtel): self
    {
        $this->numtel = $numtel;

        return $this;
    }

    public function getLogin(): ?string
    {
        return $this->login;
    }

    public function setLogin(string $login): self
    {
        $this->login = $login;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getAdmin(): ?Admin
    {
        return $this->admin;
    }

    public function setAdmin(?Admin $admin): self
    {
        $this->admin = $admin;

        return $this;
    }
}
