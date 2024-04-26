package darsa.gdg.demo.client;

import darsa.gdg.demo.config.VectorDbConfig;
import darsa.gdg.llmclient.gemini.config.GeminiLLMConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {GeminiLLMConfig.class, GeminiClient.class, VectorDbConfig.class})
@TestPropertySource(value = {"classpath:application-test.properties", "classpath:secrets-test.properties"})
@ActiveProfiles("test")
class GeminiClientTest {

    @Autowired
    private GeminiClient geminiClient;

    @Test
    void send_gemini_message_and_expect_response() {
        String responseMessage = geminiClient.sendMessage("only respond with 'hello world' and only that");
        System.out.println(responseMessage);
        assertThat(responseMessage).isEqualTo("hello world");
    }

    @Test
    void model_remembers_my_name() {
        String responseMessage = geminiClient.sendMessageWithHistory("my name is darsa");
        System.out.println(responseMessage);

        responseMessage = geminiClient.sendMessageWithHistory("what is my name? ONLY RESPOND WITH MY NAME AND ONLY THAT");
        System.out.println(responseMessage);

        assertThat(responseMessage).isEqualTo("darsa");
    }
}
