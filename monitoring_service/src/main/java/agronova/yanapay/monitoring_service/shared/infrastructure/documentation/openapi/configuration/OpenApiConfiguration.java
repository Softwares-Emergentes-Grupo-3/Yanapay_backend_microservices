package agronova.yanapay.monitoring_service.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI iotTestOpenApi() {
        // http://localhost:8082/swagger-ui/index.html#/
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("Monitoring Service")
                        .description("IoT and Machine learning system for greenhouses")
                        .version("v1.0.0")
                        .license(new License().name("HIGN 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("Agronova Documentation")
                        .url("https://github.com/Softwares-Emergentes-Grupo-3"));

        return openApi;
    }
}
