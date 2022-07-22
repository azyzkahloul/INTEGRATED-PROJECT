<?php

namespace App\Controller;

use App\Entity\Amande;
use App\Repository\AmandeRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\HttpFoundation\JsonResponse;


/**
 * @Route("/amande")
 */
class AmandeController extends AbstractController
{
    /**
     * @Route("/", name="app_amande", methods={"GET"})
     * @throws \Symfony\Component\Serializer\Exception\ExceptionInterface
     */
    public function index(AmandeRepository $amandeRepository,NormalizerInterface $normalizer): Response
    {
        $amandeRepository=$this->getDoctrine()->getRepository(Amande::class) ;
        $am=$amandeRepository->findAll();
        $jsonContent=$normalizer->normalize($am,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/new", name="app_amande_new")
     * @Method("POST")
     */
    public function new(Request $request)
    {
        $amande = new Amande();
        $total= $request->query->get("total");
        $amande->setTotal($total);

        try {
            $em = $this->getDoctrine()->getManager();

            $em->persist($amande);
            $em->flush();
            return new JsonResponse('Amande ajoutée');
        } catch (\Exception $e) {
            return new JsonResponse('Echec' . $e->getMessage());
        }
    }

    /**
     * @Route("/show/{id}", name="app_amande_show", methods={"GET"})
     */
    public function show($id,AmandeRepository $amandeRepository,NormalizerInterface $normalizer): Response
    {
        $amandeRepository=$this->getDoctrine()->getRepository(Amande::class) ;
        $am=$amandeRepository->find($id);
        $jsonContent=$normalizer->normalize($am,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/update/{id}", name="app_amande_edit")
     */
    public function edit(Request $request,$id,NormalizerInterface $normalizer)
    {
        $em = $this->getDoctrine()->getManager();
        $amande = $em->getRepository(Amande::class)->find($id);
        $amande->setTotal($request->get("total"));
        //$em->persist($amande);
        $em->flush();

        $jsonContent=$normalizer->normalize($amande,'json',['groups'=>'post:read']);

        return new JSONResponse("Information updated succesfully".json_encode($jsonContent));
        //$serializer = new Serializer([new ObjectNormalizer()]);
        //$formatted = $serializer->normalize($amande);
        //return new JsonResponse("success");
    }

    /**
     * @Route("/delete/{id}", name="app_amande_delete")
     * @Method ("DELETE")
     */
    public function delete(Request $request, EntityManagerInterface $em,$id)
    {

        $id = $request->get("id");
        $em = $this->getDoctrine()->getManager();
        $amande= $em->getRepository(Amande::class)->find($id);
        if($amande != null)
        {
            $em->remove($amande);
            $em->flush();

            return new JsonResponse("Amande supprimée ");
        }
        return new JsonResponse("Amande introuvable");
        //if ($this->isCsrfTokenValid('delete'.$amande->getId(), $request->request->get('_token'))) {
          //  $amandeRepository->remove($amande);
        //}

        //return $this->redirectToRoute('app_amande', [], Response::HTTP_SEE_OTHER);
    }
}


//Tansesh thot use Symfony\Component\Serializer\Annotation\Groups; lfouk f entité Amande o @Groups ("post:read") kbal kol attribut 
 