# Commune Algeria Android Java

Pour accélérer dev des apps android "DZ" voici 3 fichiers qui pourrons vous aidez .

## Model :

Commune.java

## Database Helper :

CommuneDatabase.java

## Data :

commune.csv

# Usage 

1 - Mettez le fichier commune.csv dans dossier assets .

2 - Mettez dans onCreate de votre class Application la ligne suivante :

```java
Commune.CommuneCsvToDatabase(App.getInstance().getContext());
```
3 - Pour récupérer la liste des communes sous forme de liste dans l'activity /fragment :

```java
CommuneDatabase communeDatabase = new CommuneDatabase(App.getInstance().getContext());
ArrayList<Commune> communes = communeDatabase.getAllCommune();
```
# Credit
 initiated by Addell - <elhaddad.addel@gmail.com>

