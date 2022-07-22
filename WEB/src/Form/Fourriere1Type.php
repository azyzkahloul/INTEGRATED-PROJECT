<?php

namespace App\Form;

use App\Entity\Fourriere;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class Fourriere1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder

            ->add('nomf', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])
            ->add('nbplace', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])
            ->add('flatitude', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])
            ->add('flongitude', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])


        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Fourriere::class,
        ]);
    }
}
