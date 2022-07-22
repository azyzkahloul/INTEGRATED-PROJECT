<?php

namespace App\Entity;

use App\Repository\AmandeRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Constraints\Range;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity(repositoryClass=AmandeRepository::class)
 */
class Amande
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups ("post:read")
     */
    private $id_amande;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank
     * @Assert\Range(
     *      min = 0,
     *      max = 9999999,
     *      notInRangeMessage = "Le prix doit etre entre {{ min }} et {{ max }} ",
     *      minMessage = "Le prix doit etre positif")
     * @Groups ("post:read")
     */
    private $total;

    public function getId(): ?int
    {
        return $this->id_amande;
    }

    public function getTotal(): ?int
    {
        return $this->total;
    }

    public function setTotal(int $total): self
    {
        $this->total = $total;

        return $this;
    }
}
