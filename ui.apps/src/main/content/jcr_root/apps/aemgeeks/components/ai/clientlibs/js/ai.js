console.log("1");
(function ($, document) {
console.log("2");

    $(document).off("click", ".generate-ai").on("click", ".generate-ai", function () {
console.log("3");

        var prompt = $("input[name='./prompt']").val();

        $.ajax({
            url: "/bin/ai-content",
            type: "POST",
            data: { prompt: prompt },
            success: function (data) {
                $("input[name='./title']").val(data.title);
            },
            error: function (err) {
                console.log("Servlet error:", err);
            }
        });

    });

})(jQuery, document);