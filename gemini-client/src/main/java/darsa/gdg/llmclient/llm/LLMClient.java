package darsa.gdg.llmclient.llm;

/**
 * This interface defines the methods that a client can use to communicate with the LLM service.
 */
public interface LLMClient {

    /**
     * Sends a prompt to the LLM service and returns the response.
     *
     * @param prompt the prompt to send to the LLM service
     * @return the response message from the LLM service
     */
    String chat(String prompt);
}
