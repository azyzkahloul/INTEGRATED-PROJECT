<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\Reclamation2Type;
use App\Repository\ReclamationRepository;
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
 * @Route("/reclamation")
 */
class ReclamationController extends AbstractController

{
    /**
     * @Route("/", name="app_reclamation")
     
     */
    public function index(ReclamationRepository $reclamationRepository,Request $request, PaginatorInterface $paginator,NormalizerInterface $normalizer): Response
    {
        /*$donnees = $this->getDoctrine()->getRepository(Reclamation::class)->findAll();
        $reclamation = $paginator->paginate(
            $donnees, 
            $request->query->getInt('page', 1), 
           3 
        );
       
        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamation,
        
        ]);*/

        
        $reclamation = $this->getDoctrine()->getManager()->getRepository(Reclamation::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reclamation);

        return new JsonResponse($formatted);

        
    }

    /**
     * @Route("/new", name="reclamation_new")
     * @Method("POST")
     */
    public function new(Request $request)
    {
        /*$reclamation = new Reclamation();
        $form = $this->createForm(Reclamation2Type::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($reclamation);
            $entityManager->flush();

            return $this->redirectToRoute('front', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);*/


        $reclamation = new Reclamation();
        $objet = $request->query->get("objet");
        $description = $request->query->get("description");
        $etat = $request->query->get("etat");
        
        $em = $this->getDoctrine()->getManager();
        
        $reclamation->setObjet($objet);
        $reclamation->setDescription($description);
        $reclamation->setEtat($etat);
        
        $em->persist($reclamation);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reclamation);
        return new JsonResponse($formatted);


    }

    /**
     * @Route("/{id_rec}", name="reclamation_show")
     * @Method("GET")
     */
    public function show(Request $request): Response
    {
        /*return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);*/

        
        $id_rec = $request->get("id_rec");

        $em = $this->getDoctrine()->getManager();
        $reclamation = $this->getDoctrine()->getManager()->getRepository(Reclamation::class)->find($id_rec);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getDescription();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($reclamation);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/edit/{id_rec}", name="reclamation_edit")
     * @Method("PUT")
     */
    public function edit(Request $request): Response
    {
       /* $form = $this->createForm(Reclamation2Type::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_reclamation', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);*/

        $em = $this->getDoctrine()->getManager();
        $reclamation = $this->getDoctrine()->getManager()
                        ->getRepository(Reclamation::class)
                        ->find($request->get("id_rec"));

        $reclamation->setObjet($request->query->get("objet"));
        $reclamation->setDescription($request->query->get("description"));
        $reclamation->setEtat($request->query->get("etat"));
        

        $em->persist($reclamation);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reclamation);
        return new JsonResponse("reclamation a ete modifiee avec success.");

    }

    /**
     * @Route("/delete/{id_rec}", name="reclamation_delete")
     * @Method ("DELETE")
     */
    public function delete(Request $request): Response
    {
        /*if ($this->isCsrfTokenValid('delete'.$reclamation->getId_rec(), $request->request->get('_token'))) {
            $entityManager->remove($reclamation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_reclamation', [], Response::HTTP_SEE_OTHER);*/

        $id_rec = $request->get("id_rec");

        $em = $this->getDoctrine()->getManager();
        $reclamation = $em->getRepository(Reclamation::class)->find($id_rec);
        if($reclamation!=null ) {
            $em->remove($reclamation);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("reclamation a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id reclamation invalide.");

        
    }


    /**
     * @Route("/tri/reclamation", name="reclamation_tri")
     */
   /* public function Tri(Request $request,ReclamationRepository $repository): Response
    {
        
        $order=$request->get('type');
        if($order== "Croissant"){
            $reclamations = $repository->tri_asc();
        }
        else {
            $reclamations = $repository->tri_desc();
        }
       
        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamations
        ]);
    }


    /**
     * @Route("/recherche/reclamation", name="reclamation_search",methods={"GET"})
     */
   /* public function recherche(Request $request, ReclamationRepository $ReclamationRepository)
    {
        $data=$request->get('data');
        $reclamation=$ReclamationRepository->reche($data);
        return $this->render('reclamation/index.html.twig', [
            'admins' =>  $admin,


        ]);

    }*/
}
