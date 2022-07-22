<?php

namespace App\Entity;

use App\Repository\ClientRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=ClientRepository::class)
 * @UniqueEntity(fields={"login"}, message="There is already an account with this login")
 * @method string getUserIdentifier()
 */
class Client implements UserInterface, \Serializable
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id_client;

    /**
     * @ORM\Column(type="string", length=30)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 1,
     *      max = 20,
     *      minMessage = "Le nom  doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "Le nom  doit comporter au plus {{ limit }} caractères"
     * )
     * @Groups ("post:read")
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=50)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 1,
     *      max = 20,
     *      minMessage = "Le nom  doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "Le nom  doit comporter au plus {{ limit }} caractères"
     * )
     * @Groups ("post:read")
     */

    private $prenom;

    /**
     * @ORM\Column(type="string", length=8)
     * @Assert\NotBlank
     * @Assert\Length(
     *  value = 8,
     *  exactMessage = "Le numéro du téléphone doit comporter 8 caractères"
     * )
     * @Groups ("post:read")
     */
    private $numtel;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Saisir l'immatricule ")
     * @Groups ("post:read")
     */
    private $voitmat;



    /**
     * @ORM\Column(type="string", length=255 ,unique=true)
     * @Assert\NotBlank(message="Saisir le login ")
     * @Assert\Email(message = "The email '{{ value }}' is not a valid email.")
     * @Groups ("post:read")
     */
    private $login;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     * @Assert\NotCompromisedPassword(
     * message= "Ce mot de passe doit comporter au moins une lettre majuscule et au moins une lettre minuscule"
     * )
     *  @Assert\Length(
     *      min = 8,
     *      max = 20,
     *   minMessage = "Le mot de passe  doit comporter au moins {{ limit }} caractères",
     *   maxMessage = "Le mot de passe  doit comporter au plus {{ limit }} caractères"
     * )
     * @Groups ("post:read")
     */
    private $password;





    /**
     * @ORM\Column(type="boolean")
     */
    private $isVerified = false;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $reset_token;

    /**
     * @ORM\Column(type="string", length=255,nullable=true)
     */
    private $status;

    /**
     * @ORM\Column(type="integer",nullable=true)
     */
    private $Reset;




    public function getId(): ?int
    {
        return $this->id_client;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(?string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(?string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getNumtel(): ?string
    {
        return $this->numtel;
    }

    public function setNumtel(?string $numtel): self
    {
        $this->numtel = $numtel;

        return $this;
    }

    public function getVoitmat(): ?string
    {
        return $this->voitmat;
    }

    public function setVoitmat(?string $voitmat): self
    {
        $this->voitmat = $voitmat;

        return $this;
    }



    public function getLogin(): ?string
    {
        return $this->login;
    }

    public function setLogin(?string $login): self
    {
        $this->login = $login;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(?string $password): self
    {
        $this->password = $password;

        return $this;
    }


    /**
     * @see UserInterface
     */
    public function getSalt()
    {
        // not needed when using the "bcrypt" algorithm in security.yaml
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials()
    {
        // If you store any temporary, sensitive data on the user, clear it here
        // $this->plainPassword = null;
    }


    /**
     * {@inheritdoc}
     */
    public function serialize(): string
    {
        // add $this->salt too if you don't use Bcrypt or Argon2i
        return serialize([$this->id_client, $this->login, $this->password]);
    }

    /**
     * {@inheritdoc}
     */
    public function unserialize($serialized): void
    {
        // add $this->salt too if you don't use Bcrypt or Argon2i
        [$this->id_client, $this->login, $this->password] = unserialize($serialized, ['allowed_classes' => false]);
    }

    /**
     * @return string
     */
    public function getResetToken() :?string
    {
        return $this->reset_token;
    }

    /**
     * @param string $resetToken
     */
    public function setResetToken(?string $reset_token): void
    {
        $this->reset_token = $reset_token;
    }



    public function isVerified(): bool
    {
        return $this->isVerified;
    }

    public function setIsVerified(bool $isVerified): self
    {
        $this->isVerified = $isVerified;

        return $this;
    }


    public function getRoles()
    {
        return array("ROLE_USER");
    }

    public function getUsername()
    {
        return $this->login;
    }



    public function __call($name, $arguments)
    {
        // TODO: Implement @method string getUserIdentifier()
    }

    public function getStatus(): ?string
    {
        return $this->status;
    }

    public function setStatus(string $status): self
    {
        $this->status = $status;

        return $this;
    }

    public function getReset(): ?int
    {
        return $this->Reset;
    }

    public function setReset(int $Reset): self
    {
        $this->Reset = $Reset;

        return $this;
    }








}
