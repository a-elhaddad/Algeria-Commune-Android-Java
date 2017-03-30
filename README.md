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

2 - Mettez dans onCreate de votre class Application :

```java


public class App extends Application {
    private Context context;
    private Activity activity;
    private static App sInstance;
    public static synchronized App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        context = getApplicationContext();
        Commune.CommuneCsvToDatabase(App.getInstance().getContext());
    }

    public Context getContext() {
        return context;
    }

    public Activity getCurrentAct() {
        return activity;
    }

    public void setCurrentAct(Activity activity) {
        this.activity = activity;
    }
 }
```
3 - Pour récupérer la liste des communes sous forme de liste dans l'activity, fragment ou service :

```java
CommuneDatabase communeDatabase = new CommuneDatabase(App.getInstance().getContext());
ArrayList<Commune> communes = communeDatabase.getAllCommune();
```
# Credit
 initiated by Addell - <elhaddad.addel@gmail.com>

