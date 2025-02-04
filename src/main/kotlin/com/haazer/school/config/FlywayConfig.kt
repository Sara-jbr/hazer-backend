package com.haazer.school.config

import io.quarkus.arc.DefaultBean
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.Produces
import org.flywaydb.core.Flyway
import javax.sql.DataSource

@ApplicationScoped
class FlywayConfig {

    @Inject
    lateinit var dataSource: DataSource

    @Produces
    @DefaultBean
    fun flyway(): Flyway {
        return Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:db/migration")
            .load()
    }
}