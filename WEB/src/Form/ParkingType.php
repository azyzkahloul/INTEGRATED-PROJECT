<?php

namespace App\Form;

use App\Entity\Parking;
use Doctrine\DBAL\Types\IntegerType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ParkingType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder

            ->add('nomp', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])
            ->add('nbplace', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])
            ->add('adresse', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])
            ->add('description', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Parking::class,
        ]);
    }
}
