<?php

namespace App\Controller;

use App\Entity\Admin;
use App\Entity\Client;
use App\Form\ClientType;
use App\Repository\ClientRepository;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;

/**
 * @Route("/client")
 */
class ClientController extends AbstractController
{
    /**
     * @Route("/mobile/", name="app_client_mobile")
     */
    public function indexmobile(Request $request): Response
    {
        /* $donnees = $this->getDoctrine()->getRepository(Client::class)->findBy([],['voitmat' => 'asc']);
         $clients = $paginator->paginate(
             $donnees, // Requête contenant les données à paginer (ici nos articles)
             $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
             3 // Nombre de résultats par page
         );

         return $this->render('client/index.html.twig', [
             'clients' => $clients,
         ]);*/
        $client = $this->getDoctrine()->getManager()->getRepository(Client::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($client);

        return new JsonResponse($formatted);

    }
    /**
     * @Route("/", name="app_client", methods={"GET"})
     */
    public function index(Request $request, PaginatorInterface $paginator): Response
    {
        $donnees = $this->getDoctrine()->getRepository(Client::class)->findBy([],['voitmat' => 'asc']);
        $clients = $paginator->paginate(
            $donnees, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            3 // Nombre de résultats par page
        );

        return $this->render('client/index.html.twig', [
            'clients' => $clients,
        ]);

    }


    /**
     * @Route("/mobile/new", name="app_client_new_mobile")
     * @Method("POST")
     */
    public function newmobile(Request $request, EntityManagerInterface $entityManager,UserPasswordEncoderInterface $passwordEncoder)
    {
        /*$client = new Client();
        $form = $this->createForm(ClientType::class, $client);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $client->setPassword($passwordEncoder->encodePassword($client, $client->getPassword()));
            $entityManager->persist($client);
            $entityManager->flush();

            return $this->redirectToRoute('front2', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('client/new.html.twig', [
            'client' => $client,
            'form' => $form->createView(),
        ]);*/
        $client = new Client();
        $nom = $request->query->get("nom");
        $prenom = $request->query->get("prenom");
        $numtel = $request->query->get("numtel");
        $voitmat = $request->query->get("voitmat");
        $login = $request->query->get("login");
        $password = $request->query->get("password");

        $em = $this->getDoctrine()->getManager();

        $client->setNom($nom);
        $client->setPrenom($prenom);
        $client->setNumtel($numtel);
        $client->setVoitmat($voitmat);
        $client->setLogin($login);
        $client->setPassword($password);

        $em->persist($client);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($client);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/new", name="app_client_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager,UserPasswordEncoderInterface $passwordEncoder): Response
    {
        $client = new Client();
        $form = $this->createForm(ClientType::class, $client);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $client->setPassword($passwordEncoder->encodePassword($client, $client->getPassword()));
            $entityManager->persist($client);
            $entityManager->flush();

            return $this->redirectToRoute('front2', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('registration/register.html.twig', [
            'client' => $client,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("mobile/{id_client}", name="app_client_show_mobile")
     * @Method("GET")
     */
    public function showmobile(Client $client, Request $request): Response
    {
        /* return $this->render('client/show.html.twig', [
             'client' => $client,
         ]);*/
        $id = $request->get("id_client");

        $em = $this->getDoctrine()->getManager();
        $client = $this->getDoctrine()->getManager()->getRepository(Client::class)->find($id);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getDescription();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($client);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/{id_client}", name="app_client_show", methods={"GET"})
     */
    public function show(Client $client): Response
    {
        return $this->render('client/show.html.twig', [
            'client' => $client,
        ]);
    }

    /**
     * @Route("/editmobile/{id_client}", name="app_client_edit_mobile")
     * @Method("PUT")
     */
    public function editmobile(Request $request, Client $client, EntityManagerInterface $entityManager,UserPasswordEncoderInterface $passwordEncoder): Response
    {
        /*$form = $this->createForm(ClientType::class, $client);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $client->setPassword($passwordEncoder->encodePassword($client, $client->getPassword()));
            $entityManager->flush();

            return $this->redirectToRoute('app_client', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('client/edit.html.twig', [
            'client' => $client,
            'form' => $form->createView(),
        ]);*/
        $em = $this->getDoctrine()->getManager();
        $client = $this->getDoctrine()->getManager()
            ->getRepository(Client::class)
            ->find($request->get("id_client"));

        $client->setNom($request->query->get("nom"));
        $client->setPrenom($request->query->get("prenom"));
        $client->setNumtel($request->query->get("numtel"));
        $client->setLogin($request->query->get("login"));
        $client->setPassword($request->query->get("password"));


        $em->persist($client);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($client);
        return new JsonResponse("Client a ete modifiee avec success.");
    }

    /**
     * @Route("/{id_client}/edit", name="app_client_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Client $client, EntityManagerInterface $entityManager,UserPasswordEncoderInterface $passwordEncoder): Response
    {
        $form = $this->createForm(ClientType::class, $client);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $client->setPassword($passwordEncoder->encodePassword($client, $client->getPassword()));
            $entityManager->flush();

            return $this->redirectToRoute('app_client', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('client/edit.html.twig', [
            'client' => $client,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/{id_client}/deletemobile", name="app_client_delete_mobile")
     * @Method ("DELETE")
     */
    public function deletemobile(Request $request, Client $client, EntityManagerInterface $entityManager): Response
    {
        /*if ($this->isCsrfTokenValid('delete'.$client->getId(), $request->request->get('_token'))) {
            $entityManager->remove($client);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_client', [], Response::HTTP_SEE_OTHER);*/
        $id = $request->get("id_client");

        $em = $this->getDoctrine()->getManager();
        $client = $em->getRepository(Client::class)->find($id);
        if($client!=null ) {
            $em->remove($client);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Client a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id client invalide.");
    }

    /**
     * @Route("/{id_client}", name="app_client_delete", methods={"POST"})
     */
    public function delete(Request $request, Client $client, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$client->getId(), $request->request->get('_token'))) {
            $entityManager->remove($client);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_client', [], Response::HTTP_SEE_OTHER);
    }
}
