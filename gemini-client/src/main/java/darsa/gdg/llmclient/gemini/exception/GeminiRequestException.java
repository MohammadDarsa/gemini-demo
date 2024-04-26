package darsa.gdg.llmclient.gemini.exception;

/**
 * Thrown when an error occurs while making a request to the Gemini API.
 */
public class GeminiRequestException extends RuntimeException {

    /**
     * Constructs a new {@link GeminiRequestException} with the specified detail message.
     *
     * @param message the detail message
     */
    public GeminiRequestException(String message) {
        super(message);
    }

}
