<?php

namespace App\Controller;

use App\Entity\AgentService;
use App\Form\AgentService2Type;
use App\Repository\AgentServiceRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/agent/service")
 */
class AgentServiceController extends AbstractController
{
    /**
     * @Route("/", name="app_agentservice", methods={"GET"})
     */
    public function index(AgentServiceRepository $agentServiceRepository): Response
    {
        return $this->render('agent_service/index.html.twig', [
            'agent_services' => $agentServiceRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="agent_service_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $agentService = new AgentService();
        $form = $this->createForm(AgentService2Type::class, $agentService);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($agentService);
            $entityManager->flush();

            return $this->redirectToRoute('app_agentservice', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('agent_service/new.html.twig', [
            'agent_service' => $agentService,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id_agent_serv}", name="agent_service_show", methods={"GET"})
     */
    public function show(AgentService $agentService): Response
    {
        return $this->render('agent_service/show.html.twig', [
            'agent_service' => $agentService,
        ]);
    }

    /**
     * @Route("/{id_agent_serv}/edit", name="agent_service_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, AgentService $agentService, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(AgentService2Type::class, $agentService);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_agentservice', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('agent_service/edit.html.twig', [
            'agent_service' => $agentService,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id_agent_serv}", name="agent_service_delete", methods={"POST"})
     */
    public function delete(Request $request, AgentService $agentService, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$agentService->getId(), $request->request->get('_token'))) {
            $entityManager->remove($agentService);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_agentservice', [], Response::HTTP_SEE_OTHER);
    }
}
