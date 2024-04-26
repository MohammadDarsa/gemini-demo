package darsa.gdg.llmclient.gemini.client;


import darsa.gdg.llmclient.gemini.config.GeminiLLMConfig;
import darsa.gdg.llmclient.gemini.config.GeminiLLMProperties;
import darsa.gdg.llmclient.llm.LLMClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {GeminiLLMConfig.class})
@TestPropertySource(value = {"classpath:application-test.properties", "classpath:secrets-test.properties"})
@ActiveProfiles("test")
class GeminiLLMClientTest {

    @Autowired
    private LLMClient geminiLLMClient;

    @Test
    @ConditionalOnProperty(name = "enable-llm-tests", havingValue = "true")
    void send_gemini_message_and_expect_response() {
        String responseMessage = geminiLLMClient.chat("only respond with 'hello world' and only that");
        assertThat(responseMessage).isEqualTo("hello world");
    }
}
