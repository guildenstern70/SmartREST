/*
 * Copyright (c) Alessio Saltarin 2020.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.config;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.JsonPathLinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.hal.HalLinkDiscoverer;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.core.DelegatingLinkRelationProvider;
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.plugin.core.support.PluginRegistryFactoryBean;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Swagger is a simple yet powerful representation of your RESTful API.
 *
 * @see <a href="http://swagger.io">swagger doc</a>
 */
@SuppressWarnings({"deprecation", "SpringFacetCodeInspection"})
@Configuration
@EnableSwagger2
public class SwaggerConfig
{

    @Bean
    public LinkDiscoverers discoverers()
    {
        List<JsonPathLinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new HalLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

    @Bean
    public LinkRelationProvider provider()
    {
        return new EvoInflectorLinkRelationProvider();
    }

    @Bean
    @Primary
    public PluginRegistryFactoryBean<LinkRelationProvider, LinkRelationProvider.LookupContext>
    fixedPluginProvider()
    {

        PluginRegistryFactoryBean<LinkRelationProvider, LinkRelationProvider.LookupContext> factory
                = new PluginRegistryFactoryBean<>();

        factory.setType(LinkRelationProvider.class);
        Class<?> classes[] = new Class<?>[1];
        classes[0] = DelegatingLinkRelationProvider.class;
        factory.setExclusions(classes);

        return factory;
    }

    /**
     * @return Docket
     * @see <a href="http://swagger.io">swagger doc</a>
     */
    @Bean
    public Docket api()
    {
        String BASE_PACKAGE = "net.littlelite.smartrest.controller.rest";
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo("1.0"))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    @NotNull
    @Contract("_ -> new")
    private ApiInfo apiInfo(String version)
    {
        return new ApiInfo(
                "SmartREST",
                "OpenAPI REST Project Template",
                version,
                "Terms of service",
                "Alessio Saltarin <alessiosaltarin@gmail.com",
                "GIT Repository",
                "https://github.com/guildenstern70/SmartREST");
    }

    @NotNull
    @Contract(" -> new")
    private ApiKey apiKey()
    {
        return new ApiKey("apiKey", "access_token", "QUERY");
    }

    private SecurityContext securityContext()
    {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build();
    }

    @NotNull
    @Contract(pure = true)
    private List<ResponseMessage> getCustomizedResponseMessages()
    {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        return responseMessages;
    }

    @NotNull
    private List<SecurityReference> defaultAuth()
    {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("apiKey",
                authorizationScopes));
    }

}

