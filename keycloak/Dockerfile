# Utiliser l'image officielle de Keycloak
FROM quay.io/keycloak/keycloak:latest

# Définir le répertoire de travail
WORKDIR /opt/keycloak

# Définir les variables d'environnement pour un compte admin
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

# Exposer le port par défaut
EXPOSE 8080

# Démarrer Keycloak en mode développement
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]
