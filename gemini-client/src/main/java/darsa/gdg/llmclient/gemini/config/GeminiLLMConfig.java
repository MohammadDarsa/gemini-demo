package darsa.gdg.llmclient.gemini.config;

import darsa.gdg.llmclient.gemini.client.GeminiLLMClient;
import darsa.gdg.llmclient.llm.LLMClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Configuration class for the Gemini LLM client.
 */
@Configuration
@EnableConfigurationProperties(GeminiLLMProperties.class)
@EnableAutoConfiguration
@RequiredArgsConstructor
public class GeminiLLMConfig {

    /**
     * Gemini LLM properties.
     */
    private final GeminiLLMProperties properties;

    /**
     * Creates a REST client for the Gemini LLM API.
     *
     * @return the REST client
     */
    @Bean
    @ConditionalOnMissingBean
    public RestClient geminiRestClient() {
        return RestClient.builder()
                .baseUrl(properties.getUrl())
                .defaultHeader("x-goog-api-key", properties.getKey())
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public LLMClient geminiLLMClient(RestClient geminiRestClient) {
        return new GeminiLLMClient(geminiRestClient);
    }
}
