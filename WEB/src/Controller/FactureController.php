<?php

namespace App\Controller;

use App\Entity\Facture;
use App\Form\FactureType;
use App\Repository\FactureRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Encoder\JsonEncoder;

/**
 * @Route("/facture")
 */
class FactureController extends AbstractController

{
    /**
     * @Route("/", name="app_facture")
     
     */
    public function index(FactureRepository $FactureRepository,Request $request,NormalizerInterface $normalizer): Response
    {
        /*$donnees = $this->getDoctrine()->getRepository(Facture::class)->findAll();
        $facture = $paginator->paginate(
            $donnees, 
            $request->query->getInt('page', 1), 
           3 
        );
       
        return $this->render('facture/index.html.twig', [
            'Factures' => $Facture,
        
        ]);*/

        
        $facture = $this->getDoctrine()->getManager()->getRepository(Facture::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($facture);

        return new JsonResponse($formatted);

        
    }

    /**
     * @Route("/new", name="facture_new")
     * @Method("POST")
     */
    public function new(Request $request)
    {
        /*$Facture = new Facture();
        $form = $this->createForm(Facture2Type::class, $Facture);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($Facture);
            $entityManager->flush();

            return $this->redirectToRoute('Facture_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('Facture/new.html.twig', [
            'Facture' => $Facture,
            'form' => $form->createView(),
        ]);*/


        $facture = new Facture();
        $nbheure = $request->query->get("nbheure");
        $pu = $request->query->get("pu");
        $total = $request->query->get("total");
        $dateentrer = $request->query->get("dateentrer");
  
        
        $em = $this->getDoctrine()->getManager();
        
        $facture->setNbheure($nbheure);
        $facture->setPu($pu);
        $facture->setTotal($total);
        $facture->setDateentrer($dateentrer);
        
        
        $em->persist($facture);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($facture);
        return new JsonResponse($formatted);


    }

    /**
     * @Route("/{id_facture}", name="facture_show")
     * @Method("GET")
     */
    public function show(Request $request): Response
    {
        /*return $this->render('Facture/show.html.twig', [
            'Facture' => $Facture,
        ]);*/

        
        $id = $request->get("id_facture");

        $em = $this->getDoctrine()->getManager();
        $facture = $this->getDoctrine()->getManager()->getRepository(Facture::class)->find($id);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getDescription();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($facture);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/{id_facture}/edit", name="facture_edit")
     * @Method("PUT")
     */
    public function edit(Request $request): Response
    {
       /* $form = $this->createForm(Facture2Type::class, $Facture);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('Facture_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('Facture/edit.html.twig', [
            'Facture' => $Facture,
            'form' => $form->createView(),
        ]);*/

        $em = $this->getDoctrine()->getManager();
        $facture = $this->getDoctrine()->getManager()
                        ->getRepository(Facture::class)
                        ->find($request->get("id_facture"));

        $facture->setNbheure($request->query->get("nbheure"));
        $facture->setPu($request->query->get("pu"));
        $facture->setTotal($request->query->get("total"));
        $facture->setDateentrer($request->query->get("dateentrer"));
       
        

        $em->persist($facture);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($facture);
        return new JsonResponse("facture a ete modifiee avec success.");

    }

    /**
     * @Route("/delete/{id_facture}", name="facture_delete")
     * @Method ("DELETE")
     */
    public function delete(Request $request): Response
    {
       

        $id = $request->get("id_facture");

        $em = $this->getDoctrine()->getManager();
        $facture = $em->getRepository(Facture::class)->find($id);
        if($facture!=null ) {
            $em->remove($facture);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("facture a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id facture invalide.");

        
    }


    /**
     * @Route("/tri/facture", name="facture_tri")
     */
   /* public function Tri(Request $request,AdminRepository $repository): Response
    {
        
        $order=$request->get('type');
        if($order== "Croissant"){
            $admins = $repository->tri_asc();
        }
        else {
            $admins = $repository->tri_desc();
        }
       
        return $this->render('admin/index.html.twig', [
            'admins' => $admins
        ]);
    }


    /**
     * @Route("/recherche/admin", name="admin_search",methods={"GET"})
     */
   /* public function recherche(Request $request, AdminRepository $adminRepository)
    {
        $data=$request->get('data');
        $admin=$adminRepository->reche($data);
        return $this->render('admin/index.html.twig', [
            'admins' =>  $admin,


        ]);

    }*/
}
