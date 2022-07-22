<?php

namespace App\Entity;


use App\Controller\ParkingController;
use App\Repository\ParkingRepository;
use Symfony\Component\HttpFoundation\Response;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Security\Core\Parking\ParkingInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * @ORM\Entity(repositoryClass=ParkingRepository::class)
 */
class Parking
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @Groups ("post:read")
     * @ORM\Column(type="integer")
     */
    
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups ("post:read")
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 1,
     *      max = 20,
     *      minMessage = "Le nom  doit comporter au moins {{ limit }} caractères",
     *      maxMessage = "Le nom  doit comporter au plus {{ limit }} caractères"
     * )
     */
    private $nomp;
    /**
     * @ORM\Column(type="string")
     * @Groups ("post:read")
     * @Assert\NotBlank(
     * message = "Le nombre des places  ne doit pas être vide")
     */
    private $nbplace;

     /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     * @Groups ("post:read")
     * @Assert\LessThanOrEqual(
     *       value = 90,
     *       message = "La latitude  ne doit  pas etre supérieure à 90"
     * )
     * @Assert\GreaterThanOrEqual(
     *       value = -90,
     *       message = "La latitude  ne doit pas être inférieure à -90"
     * )
     * 
     */
    private $adresse;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     * @Groups ("post:read")
     * @Assert\LessThanOrEqual(
     *       value = 180,
     *       message = "La longitude  ne doit pas être supérieure à 180"
     * )
     * @Assert\GreaterThanOrEqual(
     *       value = -180,
     *       message = "La longitude  ne doit pas être inférieure à -180"
     * )
     */
    private $description;

   

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomp(): ?string
    {
        return $this->nomp;
    }

    public function setNomp(string $nomp): self
    {
        $this->nomp = $nomp;

        return $this;
    }

    public function getNbplace(): ?string
    {
        return $this->nbplace;
    }

    public function setNbplace(string $nbplace): self
    {
        $this->nbplace = $nbplace;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

 
}
