

fun fridge(val input: String)
{
    val KlaudeClient = KlaudeCLient.Builder()
        .key("CLAUDE_API_KEY")
        .model("claude-3")
        .maxTokensToSample(1000)
        .build()

    val text = input
    KlaudeClient.complete($text)
    {
        result -> print(result ?: [empty])
    }

}