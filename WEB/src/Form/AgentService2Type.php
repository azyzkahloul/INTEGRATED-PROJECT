<?php

namespace App\Form;

use App\Entity\AgentService;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class AgentService2Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder

            ->add('nom', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])
            ->add('prenom', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])
            ->add('numtel', TextType::class,[
                'attr' => ['class' => 'form-control'],
            ])
            ->add('login', EmailType::class,[
                'attr' => ['class' => 'form-control'],
            ])
            ->add('password', PasswordType::class,[
                'attr' => ['class' => 'form-control'],
            ])

        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => AgentService::class,
        ]);
    }
}
