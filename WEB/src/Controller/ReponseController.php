<?php

namespace App\Controller;

use App\Entity\Reponse;
use App\Form\Reponse2Type;
use App\Repository\ReponseRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Encoder\JsonEncoder;

/**
 * @Route("/reponse")
 */
class ReponseController extends AbstractController

{
    /**
     * @Route("/", name="app_reponse")
     
     */
    public function index(ReponseRepository $reponseRepository,Request $request, PaginatorInterface $paginator,NormalizerInterface $normalizer): Response
    {
        /*$donnees = $this->getDoctrine()->getRepository(Reponse::class)->findAll();
        $reponse = $paginator->paginate(
            $donnees, 
            $request->query->getInt('page', 1), 
           3 
        );
       
        return $this->render('reponse/index.html.twig', [
            'reponses' => $reponse,
        
        ]);*/

        
        $reponse = $this->getDoctrine()->getManager()->getRepository(Reponse::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reponse);

        return new JsonResponse($formatted);

        
    }

    /**
     * @Route("/new", name="reponse_new")
     * @Method("POST")
     */
    public function new(Request $request)
    {
        /*$reponse = new Reponse();
        $form = $this->createForm(Reponse2Type::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($reponse);
            $entityManager->flush();

            return $this->redirectToRoute('front', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reponse/new.html.twig', [
            'reponse' => $reponse,
            'form' => $form->createView(),
        ]);*/


        $reponse = new Reponse();
        $rps = $request->query->get("rps");
        
        $em = $this->getDoctrine()->getManager();
        
        $reponse->setRps($rps);
        
        $em->persist($reponse);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reponse);
        return new JsonResponse($formatted);


    }

    /**
     * @Route("/{id}", name="reponse_show")
     * @Method("GET")
     */
    public function show(Request $request): Response
    {
        /*return $this->render('reponse/show.html.twig', [
            'reponse' => $reponse,
        ]);*/

        
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $reponse = $this->getDoctrine()->getManager()->getRepository(Reponse::class)->find($id);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getDescription();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($reponse);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/edit/{id}", name="reponse_edit")
     * @Method("PUT")
     */
    public function edit(Request $request): Response
    {
       /* $form = $this->createForm(Reponse2Type::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_reponse', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reponse/edit.html.twig', [
            'reponse' => $reponse,
            'form' => $form->createView(),
        ]);*/

        $em = $this->getDoctrine()->getManager();
        $reponse = $this->getDoctrine()->getManager()
                        ->getRepository(Reponse::class)
                        ->find($request->get("id"));

        $reponse->setRps($request->query->get("rps"));
        

        $em->persist($reponse);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reponse);
        return new JsonResponse("reponse a ete modifiee avec success.");

    }

    /**
     * @Route("/delete/{id}", name="reponse_delete")
     * @Method ("DELETE")
     */
    public function delete(Request $request): Response
    {
        /*if ($this->isCsrfTokenValid('delete'.$reponse->getId(), $request->request->get('_token'))) {
            $entityManager->remove($reponse);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_reponse', [], Response::HTTP_SEE_OTHER);*/

        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $reponse = $em->getRepository(Reponse::class)->find($id);
        if($reponse!=null ) {
            $em->remove($reponse);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("reponse a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id reponse invalide.");

        
    }


    /**
     * @Route("/tri/reponse", name="reponse_tri")
     */
   /* public function Tri(Request $request,ReponseRepository $repository): Response
    {
        
        $order=$request->get('type');
        if($order== "Croissant"){
            $reponses = $repository->tri_asc();
        }
        else {
            $reponses = $repository->tri_desc();
        }
       
        return $this->render('reponse/index.html.twig', [
            'reponses' => $reponses
        ]);
    }


    /**
     * @Route("/recherche/reponse", name="reponse_search",methods={"GET"})
     */
   /* public function recherche(Request $request, ReponseRepository $ReponseRepository)
    {
        $data=$request->get('data');
        $reponse=$ReponseRepository->reche($data);
        return $this->render('reponse/index.html.twig', [
            'reponses' =>  $reponse,


        ]);

    }*/
}
