<?php

namespace App\Repository;

use App\Entity\Amande;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Amande|null find($id, $lockMode = null, $lockVersion = null)
 * @method Amande|null findOneBy(array $criteria, array $orderBy = null)
 * @method Amande[]    findAll()
 * @method Amande[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class AmandeRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Amande::class);
    }

    // /**
    //  * @return Amande[] Returns an array of Amande objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('a')
            ->andWhere('a.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('a.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Amande
    {
        return $this->createQueryBuilder('a')
            ->andWhere('a.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
