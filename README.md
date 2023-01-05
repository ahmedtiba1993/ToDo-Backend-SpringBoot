# ToDo-Backend-SpringBoot

ToDo est un platforme qui vous permet de visualiser les tâches en cours , les tâches urgentes , les taches favories , les taches d'aujordhui et celles qui sont terminées, ou encore de mettre en avant les échéances pour planifier son temps et celui des équipes

# Les technologies
- Java 11
- Spring Boot 2.6.3
- Lombok
- Swagger
- MySql
- Spring security avec JWT Token

# Package com.todo.*
- auth : contient les classes de sécurité qui permet de personnaliser totalement l’authentification (JWT Token) et le contrôle d’accès.
- config : Contient des classes de configuration qui sont scannées au démarrage de l’application tels que configuration de sécurité et swagger
- controller : Réceptionner la requête et fournir la réponse 
- controller.api : Contient les interface API de notre projet
- dto : Contient toutes les objets dto de notre projets , DTO est un objet qui transporte des données entre processus.
- exception : gérer toutes les exceptions levées par les classes contrôleur et service , implémenter la gestion globale des exceptions et personnaliser la réponse en fonction du type d'exception.
- handler : Contient l'intercepteur de les exceptions
- model : Implémentation des objets métiers qui seront manipulés par les autres couches
- repository : Interaction avec les sources de données externes (Communiquer avec la source de données )
- service : Contient les interface de chaque service
- service.impl : Implémentation des traitements métiers spécifiques à l’application ( Exécuter les traitements métiers )
- utils : Contient les constantes
- validator: Contient les couches de validation des données

# Diagramme de classe

![ToDo drawio](https://user-images.githubusercontent.com/72476268/210871080-2e4e6f86-38f8-48dc-8f39-9cd674c36fd8.png)







