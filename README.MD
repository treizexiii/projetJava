# Projet java servlet

## installation

- Dans le répertoire WEB-INF créer un dossier "classes" si non existant.
- lancer la commande ./build.sh (utiliser un environement linux, GIT bash par exemple, sous windows)
- A la racine du projet, lancer les commandes suivant pour construire et démarrer le docker:
  - docker build -t app .
  - docker run --name app --rm -it -p 8888:8080 app
