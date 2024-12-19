# ExamenMicroservicesJava

## Créer les images et lancer les conteneurs
```cd ApiGateway && docker build -t ApiGateway . && docker run -it --name ApiGateway ApiGateway && cd .. && cd RendezVous && docker build -t RendezVous . && docker run -it --name RendezVous RendezVous && cd .. && cd ServiceDossierMedical && docker build -t ServiceDossierMedical . && docker run -it --name ServiceDossierMedical ServiceDossierMedical && cd .. && cd ServiceEureka && docker build -t ServiceEureka . && docker run -it --name ServiceEureka ServiceEureka && cd .. && cd ServicePatient && docker build -t ServicePatient . && docker run -it --name ServicePatient ServicePatient && cd .. && cd ServicePracticien && docker build -t ServicePracticien . && docker run -it --name ServicePracticien ServicePracticien && cd .. ```

## ApiGateway 
```localhost:8095```

## Endpoints
```localhost:8095/patients/```
```localhost:8095/practiciens/```
```localhost:8095/dossiers/```
```localhost:8095/historiques/```
```localhost:8095/rendezvous/```

## Serveur Eureka 

```localhost:8094```