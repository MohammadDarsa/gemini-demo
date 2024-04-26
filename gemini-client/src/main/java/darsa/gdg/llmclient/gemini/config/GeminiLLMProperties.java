package darsa.gdg.llmclient.gemini.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for the Gemini LLM client.
 */
@Configuration
@ConfigurationProperties("llm.client.gemini")
@EnableConfigurationProperties
@Getter
@Setter
public class GeminiLLMProperties {

    /**
     * The URL of the Gemini LLM server.
     */
    private String url;

    /**
     * The API key for the Gemini LLM server.
     */
    private String key;

}
