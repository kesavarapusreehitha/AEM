console.log("1");
(function ($, $document) {
    "use strict";

    $document.on("click", ".ai-generator-button", function () {
        const $button = $(this);
        console.log("AI Generator button clicked");
        console.log("2");

        // Try multiple ways to find the prompt value
        const $promptContainer = $(".ai-generator-prompt");
        let prompt = $promptContainer.find("textarea, coral-textarea").val();


        if (!prompt) {
            // Fallback for some Granite UI versions
            prompt = $promptContainer.val();
        }

        console.log("Captured prompt:", prompt);

        if (!prompt || prompt.trim() === "") {
            alert("Please enter a prompt in the AI Prompt field!");
            return;
        }

        $button.attr("disabled", "disabled").text("Generating...");

        $.ajax({
            url: "/bin/ai/generate",
            data: { prompt: prompt },
            success: function (data) {
                console.log("AI Response received:", data);

                // Ensure data is an object
                const response = typeof data === 'string' ? JSON.parse(data) : data;

                // Use a broader search for the target fields
                const $dialog = $button.closest("coral-dialog, .coral-FixedColumns-column, form");

                const updateField = (selector, value) => {
                    const $field = $dialog.find(selector).find("input, textarea, coral-textfield, coral-textarea");
                    if ($field.length) {
                        $field.val(value);
                        console.log("Updated field " + selector + " with: " + value);
                    } else {
                        console.warn("Could not find field for selector: " + selector);
                        // Try direct attribute match if class search fails
                        const $altField = $dialog.find("[name='" + selector.replace('.ai-target-', './') + "']");
                        if ($altField.length) {
                            $altField.val(value);
                            console.log("Updated field via name attribute: " + value);
                        }
                    }
                };

                updateField(".ai-target-title", response.title);
                updateField(".ai-target-description", response.description);
                updateField(".ai-target-cta", response.cta);
            },
            error: function (xhr, status, error) {
                console.error("AI Generation failed:", status, error);
                alert("AI generation failed. Please check the network tab or OSGi logs.");
            },
            complete: function () {
                $button.removeAttr("disabled").text("Generate Content");
            }
        });
    });

})(Granite.$, jQuery(document));
