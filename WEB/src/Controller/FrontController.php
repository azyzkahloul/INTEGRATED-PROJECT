<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FrontController extends AbstractController
{
    /**
     * @Route("/front", name="front")
     */
    public function index(): Response
    {
        return $this->render('baseFront.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }
     /**
     * @Route("/speakers-details", name="speakers-details")
     */
    public function detail(): Response
    {
        return $this->render('front/speakers-details.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }
    /**
     * @Route("/equipe", name="equipe")
     */
    public function equipe(): Response
    {
        return $this->render('front/equipe.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }

    /**
     * @Route("/addfacture", name="addfacture")
     */
    public function addfacture(): Response
    {
        return $this->render('front/baseaddfacture.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }
}
