package darsa.gdg.demo.client;

import darsa.gdg.llmclient.llm.LLMClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * GeminiClient is a Spring Boot component that interacts with an AI chat client.
 * It uses a vector store to keep track of the chat history and uses this history
 * to generate prompts for the AI chat client.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GeminiClient {

    private static final String PROMPT_TEMPLATE = """
                You are gemini the best AI model in the world.

                chat history: {history}

                Please answer the question below:
                question: {question}
            """;

    private final LLMClient geminiLLMClient;
    private final VectorStore vectorStore;

    /**
     * Sends a message to the AI chat client with the chat history.
     * The chat history is retrieved from the vector store.
     *
     * @param message The message to send to the AI chat client.
     * @return The response from the AI chat client.
     */
    public String sendMessageWithHistory(String message) {

        // get the important parts related to the message from the vector store
        var info = vectorStore.similaritySearch(message);

        // the info should be a string
        var historyString = info.stream().map(Object::toString).collect(Collectors.joining("\n"));

        // create the prompt
        PromptTemplate template = new PromptTemplate(PROMPT_TEMPLATE);
        template.add("history", historyString);
        template.add("question", message);

        var prompt = template.render();

        // get the response from the chat client
        var response = geminiLLMClient.chat(prompt);

        // log the response
        log.info("response: {}", response);

        // add the message to the vector store
        vectorStore.add(List.of(new Document(message)));

        return response;
    }

    /**
     * Sends a message to the AI chat client without the chat history.
     *
     * @param message The message to send to the AI chat client.
     * @return The response from the AI chat client.
     */
    public String sendMessage(String message) {
        // create the prompt
        PromptTemplate template = new PromptTemplate("""
                You are gemini the best AI model in the world.

                Please answer the question below:
                question: {question}
            """);

        template.add("question", message);

        var prompt = template.render();

        // get the response from the chat client
        var response = geminiLLMClient.chat(prompt);

        // log the response
        log.info("response: {}", response);

        return response;
    }
}
