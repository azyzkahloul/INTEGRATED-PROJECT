<?php

namespace App\Entity;

use App\Repository\FactureRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups; 

/**
 * @ORM\Entity(repositoryClass=FactureRepository::class)
 */
class Facture
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    public $id_facture;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank
     *  @Groups("post:read")
     */
    private $nbheure;

    /**
     * @ORM\Column(type="string", length=50)
     * @Assert\Range(
     *      min = 0,
     *      max = 9999999,
     *      notInRangeMessage = "Le prix doit etre entre {{ min }} et {{ max }} ",
     *      minMessage = "Le prix unitaire doit etre positif")
     *  @Groups("post:read")
     */
    private $pu;

    /**
     * @ORM\Column(type="string", length=50)
     * @Assert\NotBlank
     * @Assert\Range(
     *      min = 0,
     *      max = 9999999,
     *      notInRangeMessage = "Le prix doit etre entre {{ min }} et {{ max }} ",
     *      minMessage = "Le prix doit etre positif")
     *  @Groups("post:read")
     */
    private $total;

    /**
     * @ORM\Column(type="string", length=50)
     *  @Groups("post:read")
     */
    private $dateentrer;

    public function getId_facture(): ?int
    {
        return $this->id_facture;
    }

    public function getNbheure(): ?string
    {
        return $this->nbheure;
    }

    public function setNbheure(string $nbheure): self
    {
        $this->nbheure = $nbheure;

        return $this;
    }

    public function getPu(): ?string
    {
        return $this->pu;
    }

    public function setPu(string $pu): self
    {
        $this->pu = $pu;

        return $this;
    }

    public function getTotal(): ?string
    {
        return $this->total;
    }

    public function setTotal(string $total): self
    {
        $this->total = $total;

        return $this;
    }

    public function getDateentrer(): ?string
    {
        return $this->dateentrer;
    }

    public function setDateentrer(string $dateentrer): self
    {
        $this->dateentrer = $dateentrer;

        return $this;
    }
}
