<?php
namespace App\Controller;

use App\Entity\Parking;
use App\Form\ParkingType;
use App\Repository\ParkingRepository;
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
 * @Route("/parking")
 */
class ParkingController extends AbstractController
{
    /**
     * @Route("/", name="app_parking")
     */
    public function index(ParkingRepository $parkingRepository,Request $request,NormalizerInterface $normalizer): Response
    { 
        $parking = $this->getDoctrine()->getManager()->getRepository(Parking::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($parking);

        return new JsonResponse($formatted);
    }


    /**
     * @Route("/new", name="parking_new")
     * @Method("POST")
     */
    public function new(Request $request)
    {
       
        $parking = new Parking();
        $nomp = $request->query->get("nomp");
        $nbplace = $request->query->get("nbplace");
        $adresse = $request->query->get("adresse");
        $description = $request->query->get("description");
      
        
        $em = $this->getDoctrine()->getManager();
        
        $parking->setNomp($nomp);
        $parking->setNbplace($nbplace);
        $parking->setAdresse($adresse);
        $parking->setDescription($description);
       
        
        $em->persist($parking);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($parking);
        return new JsonResponse($formatted);

    }

    /**
     * @Route("/{id}", name="parking_show")
     * @Method("GET")
     */
    public function show(Request $request): Response
    {
       
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $parking = $this->getDoctrine()->getManager()->getRepository(Parking::class)->find($id);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getDescription();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($parking);
        return new JsonResponse($formatted);
    }

    

 
    /**
     * @Route("/edit/{id}", name="parking_edit")
     * @Method("PUT")
    */
    public function edit(Request $request, Parking $parking, EntityManagerInterface $entityManager): Response
    {
        $em = $this->getDoctrine()->getManager();
        $parking = $this->getDoctrine()->getManager()
                        ->getRepository(Parking::class)
                        ->find($request->get("id"));

        $parking->setNomp($request->query->get("nomp"));
        $parking->setNbplace($request->query->get("nbplace"));
        $parking->setAdresse($request->query->get("adresse"));
        $parking->setDescription($request->query->get("description"));
       
        

        $em->persist($parking);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($parking);
        return new JsonResponse("parking ete modifiee avec success.");

    }
  
    /** 
     * @Route("/delete/{id}", name="parking_delete")
     * @Method ("DELETE")
     */
    public function delete(Request $request, Parking $parking, EntityManagerInterface $entityManager): Response
    {
       

        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $Parking = $em->getRepository(Parking::class)->find($id);
        if($Parking!=null ) {
            $em->remove($Parking);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("parking a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id parking invalide.");

        
    }
    

/**
     * @Route("/tri/parking", name="parking_tri")
     */
    public function Tri(Request $request,ParkingRepository $repository): Response
    {
        // Retrieve the entity manager of Doctrine
        $order=$request->get('type');
        if($order== "Croissant"){
            $parkings = $repository->tri_asc();
        }
        else {
            $parkings = $repository->tri_desc();
        }
        // Render the twig view
        return $this->render('parking/index.html.twig', [
            'parkings' => $parkings
        ]);
}


/**
     * @Route("/recherche/parking", name="parking_search")
     */
    public function recherche(Request $request, ParkingRepository $parkingRepository){
        $data=$request->get('data');
        $parking=$parkingRepository->reche($data);
        return $this->render('parking/index.html.twig', [
            'parkings' =>  $parking,


        ]);

    }





}