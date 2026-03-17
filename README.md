# 🏥 HSP — Projet SLAM 2 (JavaFX)

> Application de bureau développée dans le cadre de l'épreuve **E4 du BTS SIO option SLAM**, session **2025–2026**.

---

## 📋 Présentation

**HSP** est une application de bureau JavaFX permettant la gestion d'un service d'urgence d'un établissement de santé (clinique/hôpital). Elle offre une interface graphique pour gérer les patients, les rendez-vous et les stocks.

Ce dépôt est le pendant **client lourd (JavaFX)** du projet HSP, complémentaire à l'application web Symfony.

---

## 🛠️ Stack technique

| Technologie    | Usage                              |
|----------------|------------------------------------|
| Java           | Langage principal                  |
| JavaFX         | Interface graphique (GUI)          |
| Maven          | Gestion des dépendances et build   |
| JDBC / MySQL   | Connexion à la base de données     |

---

## 📁 Structure du projet

```
HSP-Projet-SLAM2-JAVAFX/
├── src/
│   └── main/
│       ├── java/           # Code source Java (Controllers, Models, DAO…)
│       └── resources/      # Fichiers FXML, CSS, images
├── target/
│   └── classes/            # Classes compilées (généré par Maven)
├── pom.xml                 # Configuration Maven et dépendances
├── mvnw                    # Maven Wrapper (Linux/macOS)
└── mvnw.cmd                # Maven Wrapper (Windows)
```

---

## 🚀 Installation & lancement

### Prérequis

- **JDK 17+** (ou JDK 21 LTS recommandé)
- **Maven 3.8+** *(ou utiliser le Maven Wrapper inclus)*
- **MySQL / MariaDB**
- Un IDE compatible JavaFX : [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/)

### 1. Cloner le dépôt

```bash
git clone https://github.com/Nico180803/HSP-Projet-SLAM2-JAVAFX.git
cd HSP-Projet-SLAM2-JAVAFX
```

### 2. Configurer la base de données

Créer la base de données et configurer les identifiants de connexion dans le fichier de configuration JDBC du projet (ex. `DatabaseConnection.java` ou `config.properties`).

```
URL      : jdbc:mysql://localhost:3306/hsp
User     : votre_utilisateur
Password : votre_mot_de_passe
```

### 3. Compiler le projet

```bash
# Avec Maven Wrapper (recommandé)
./mvnw clean install       # Linux / macOS
mvnw.cmd clean install     # Windows

# Avec Maven installé globalement
mvn clean install
```

### 4. Lancer l'application

```bash
./mvnw javafx:run
# ou
mvn javafx:run
```

Vous pouvez aussi lancer directement depuis votre IDE en exécutant la classe principale (`Main.java`).

---

## 🗃️ Base de données

Ce projet partage le même schéma de base de données que l'application web Symfony HSP.  
Se référer au fichier `HSP_MCD_Final.drawio` disponible dans le dépôt web pour le modèle conceptuel.

---

## 👥 Équipe

| Membre     | Rôle                           |
|------------|--------------------------------|
| Nico180803 | Développeur principal          |
| *(autres membres à compléter)* | |

---

## 📝 Contexte scolaire

Ce projet est réalisé dans le cadre de l'**épreuve E4** du **BTS SIO option SLAM** (Services Informatiques aux Organisations — Solutions Logicielles et Applications Métiers), session 2025–2026.

Il s'inscrit dans le référentiel de compétences SLAM, notamment :
- Conception et développement d'une application client lourd
- Interaction avec une base de données via JDBC
- Conception d'interfaces graphiques avec JavaFX
