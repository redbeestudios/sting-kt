package com.redbee.pokemonapi.config

import com.redbee.pokemonapi.config.rest.RestTemplateErrorHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.Duration

@Configuration
class AppConfig : WebMvcConfigurer {

    @Bean
    fun getRestTemplate(
            restTemplateBuilder: RestTemplateBuilder,
            @Value("\${rest.client.default.timeout}") timeout: Int
    ): RestTemplate {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(timeout.toLong()))
                .setReadTimeout(Duration.ofMillis(timeout.toLong()))
                .errorHandler(RestTemplateErrorHandler())
                .build()
    }

}
