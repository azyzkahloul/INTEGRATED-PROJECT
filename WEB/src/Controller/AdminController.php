<?php

namespace App\Controller;

use App\Entity\Admin;
use App\Form\Admin2Type;
use App\Repository\AdminRepository;
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
 * @Route("/admin")
 */
class AdminController extends AbstractController

{
    /**
     * @Route("/", name="admin_index")
     
     */
    public function index(AdminRepository $adminRepository,Request $request,NormalizerInterface $normalizer): Response
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

        
        $admin = $this->getDoctrine()->getManager()->getRepository(Admin::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($admin);

        return new JsonResponse($formatted);

        
    }

    /**
     * @Route("/new", name="admin_new")
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


        $admin = new Admin();
        $nom = $request->query->get("nom");
        $prenom = $request->query->get("prenom");
        $numtel = $request->query->get("numtel");
        $login = $request->query->get("login");
        $password = $request->query->get("password");
        
        $em = $this->getDoctrine()->getManager();
        
        $admin->setNom($nom);
        $admin->setPrenom($prenom);
        $admin->setNumtel($numtel);
        $admin->setLogin($login);
        $admin->setPassword($password);
        
        $em->persist($admin);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($admin);
        return new JsonResponse($formatted);


    }

    /**
     * @Route("/{id}", name="admin_show")
     * @Method("GET")
     */
    public function show(Request $request): Response
    {
        /*return $this->render('admin/show.html.twig', [
            'admin' => $admin,
        ]);*/

        
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $admin = $this->getDoctrine()->getManager()->getRepository(Admin::class)->find($id);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getDescription();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($admin);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/edit/{id}", name="admin_edit")
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
        $admin = $this->getDoctrine()->getManager()
                        ->getRepository(Admin::class)
                        ->find($request->get("id"));

        $admin->setNom($request->query->get("nom"));
        $admin->setPrenom($request->query->get("prenom"));
        $admin->setNumtel($request->query->get("numtel"));
        $admin->setLogin($request->query->get("login"));
        $admin->setPassword($request->query->get("password"));
        

        $em->persist($admin);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($admin);
        return new JsonResponse("admin a ete modifiee avec success.");

    }

    /**
     * @Route("/delete/{id}", name="admin_delete")
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
        $admin = $em->getRepository(Admin::class)->find($id);
        if($admin!=null ) {
            $em->remove($admin);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("admin a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id admin invalide.");

        
    }


    /**
     * @Route("/tri/admin", name="admin_tri")
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
