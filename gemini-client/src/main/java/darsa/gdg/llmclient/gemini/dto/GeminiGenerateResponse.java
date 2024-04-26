package darsa.gdg.llmclient.gemini.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This class is a data transfer object that represents the response from the Gemini API's generate endpoint.
 *
 * @author ActionPrime
 */
@Getter
@Setter
public class GeminiGenerateResponse {

    /**
     * A list of possible completions for the user's prompt.
     */
    private List<GeminiCandidates> candidates;

    /**
     * Information about the safety of the completions, including the probability that each completion is safe.
     */
    private GeminiPromptFeedback promptFeedback;

    /**
     * A class that represents a possible completion for the user's prompt.
     */
    @Getter
    @Setter
    public static class GeminiCandidates {

        /**
         * The content of the completion.
         */
        private GeminiGenerateRequest.GeminiContent content;

        /**
         * A reason why the completion was chosen, if it was chosen for finishing the prompt.
         */
        private String finishReason;

        /**
         * The index of the completion in the list of completions.
         */
        private Long index;

        /**
         * A list of safety ratings for the completion, indicating the probability that the completion is safe for the user's prompt.
         */
        private List<GeminiSafetyRating> safetyRatings;
    }

    /**
     * A class that represents a safety rating for a completion.
     */
    @Getter
    @Setter
    public static class GeminiSafetyRating {

        /**
         * The category of the safety rating, indicating the type of safety risk.
         */
        private String category;

        /**
         * The probability that the completion is safe, represented as a value between 0 and 1.
         */
        private String probability;
    }

    /**
     * A class that represents feedback about the safety of the completions.
     */
    @Getter
    @Setter
    public static class GeminiPromptFeedback {

        /**
         * A list of safety ratings for the completions, indicating the probability that each completion is safe for the user's prompt.
         */
        private List<GeminiSafetyRating> safetyRatings;
    }

    /**
     * Returns the content of the first completion in the list of completions, as text.
     *
     * @return the content of the first completion in the list of completions, as text
     */
    public String getResult() {
        return candidates.get(0).content.getParts().get(0).getText();
    }
}
