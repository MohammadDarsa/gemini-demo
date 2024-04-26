package darsa.gdg.llmclient.gemini.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This class is used to generate a prompt for the LLM API.
 *
 * @author Action Prime
 */
@Getter
@Setter
public class GeminiGenerateRequest {

    /**
     * The contents of the prompt.
     */
    private List<GeminiContent> contents;

    /**
     * Creates a new prompt with the given prompt.
     *
     * @param prompt The prompt to use.
     */
    public GeminiGenerateRequest(String prompt) {
        this.contents = List.of(new GeminiContent().setParts(List.of(new GeminiPart().setText(prompt))));
    }

    /**
     * A class that represents the contents of a prompt.
     */
    @Getter
    @Setter
    public static class GeminiContent {

        /**
         * The parts of the prompt.
         */
        private List<GeminiPart> parts;

        /**
         * The role of the prompt.
         */
        private String role;
    }

    /**
     * A class that represents a part of a prompt.
     */
    @Getter
    @Setter
    public static class GeminiPart {

        /**
         * The text of the part.
         */
        private String text;
    }
}
