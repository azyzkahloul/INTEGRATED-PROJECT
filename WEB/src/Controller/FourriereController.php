<?php

namespace App\Controller;

use App\Entity\Fourriere;
use App\Form\FourriereType;
use App\Repository\FourriereRepository;
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
 * @Route("/fourriere")
 */
class FourriereController extends AbstractController

{
    /**
     * @Route("/", name="app_fourriere")

     */
    public function index(FourriereRepository $adminRepository,Request $request,NormalizerInterface $normalizer): Response
    {
        /*$donnees = $this->getDoctrine()->getRepository(Admin::class)->findAll();
        $admin = $paginator->paginate(
            $donnees,
            $request->query->getInt('page', 1),
           3
        );

        return $this->render('admin/index.html.twig', [
            'admins' => $admin,

        ]);*/


        $fourriere = $this->getDoctrine()->getManager()->getRepository(Fourriere::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($fourriere);

        return new JsonResponse($formatted);


    }

    /**
     * @Route("/new", name="fourriere_new")
     * @Method("POST")
     */
    public function new(Request $request)
    {
        /*$admin = new Admin();
        $form = $this->createForm(Admin2Type::class, $admin);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($admin);
            $entityManager->flush();

            return $this->redirectToRoute('admin_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('admin/new.html.twig', [
            'admin' => $admin,
            'form' => $form->createView(),
        ]);*/
        $fourriere = new Fourriere();
        $nomf = $request->query->get("nomf");
        $nbplace = $request->query->get("nbplace");
        $flatitude = $request->query->get("flatitude");
        $flongtitude = $request->query->get("flongtitude");


        $em = $this->getDoctrine()->getManager();

        $fourriere->setNomf($nomf);
        $fourriere->setNbplace($nbplace);
        $fourriere->setFlatitude($flatitude);
        $fourriere->setFlongtitude($flongtitude);


        $em->persist($fourriere);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($fourriere);
        return new JsonResponse($formatted);


    }

    /**
     * @Route("/{id}/show", name="fourriere_show")
     * @Method("GET")
     */
    public function show(Request $request): Response
    {
        /*return $this->render('admin/show.html.twig', [
            'admin' => $admin,
        ]);*/


        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $fourriere = $this->getDoctrine()->getManager()->getRepository(Fourriere::class)->find($id);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getDescription();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($fourriere);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/edit/{id}", name="fourriere_edit")
     * @Method("PUT")
     */
    public function edit(Request $request): Response
    {
        /* $form = $this->createForm(Admin2Type::class, $admin);
         $form->handleRequest($request);

         if ($form->isSubmitted() && $form->isValid()) {
             $entityManager->flush();

             return $this->redirectToRoute('admin_index', [], Response::HTTP_SEE_OTHER);
         }

         return $this->render('admin/edit.html.twig', [
             'admin' => $admin,
             'form' => $form->createView(),
         ]);*/

        $em = $this->getDoctrine()->getManager();
        $fourriere = $this->getDoctrine()->getManager()
            ->getRepository(Fourriere::class)
            ->find($request->get("id"));

        $fourriere->setNomf($request->query->get("nomf"));
        $fourriere->setNbplace($request->query->get("nbplace"));
        $fourriere->setFlatitude($request->query->get("flatitude"));
        $fourriere->setFlongtitude($request->query->get("flongtitude"));



        $em->persist($fourriere);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($fourriere);
        return new JsonResponse("admin a ete modifiee avec success.");

    }

    /**
     * @Route("/delete/{id}", name="fourriere_delete")
     * @Method ("DELETE")
     */
    public function delete(Request $request): Response
    {
        /*if ($this->isCsrfTokenValid('delete'.$admin->getId(), $request->request->get('_token'))) {
            $entityManager->remove($admin);
            $entityManager->flush();
        }

        return $this->redirectToRoute('admin_index', [], Response::HTTP_SEE_OTHER);*/

        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $fourriere = $em->getRepository(Fourriere::class)->find($id);
        if($fourriere!=null ) {
            $em->remove($fourriere);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("admin a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id admin invalide.");


    }



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
