package com.haazer.school.config

import org.eclipse.microprofile.config.inject.ConfigProperty
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class KeycloakConfig {

    @ConfigProperty(name = "quarkus.oidc.client-id")
    lateinit var clientId: String

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    lateinit var clientSecret: String

    @ConfigProperty(name = "quarkus.oidc.token-path")
    lateinit var tokenPath: String

    @ConfigProperty(name = "quarkus.keycloak.admin-client-id")
    lateinit var adminClientId: String

    @ConfigProperty(name = "quarkus.keycloak.admin-username")
    lateinit var adminUsername: String

    @ConfigProperty(name = "quarkus.keycloak.admin-password")
    lateinit var adminPassword: String

    @ConfigProperty(name = "keycloak.admin-url")
    lateinit var keycloakAdminUrl: String

    @ConfigProperty(name = "quarkus.oidc.token-path-master")
    lateinit var tokenPathMaster: String

}
