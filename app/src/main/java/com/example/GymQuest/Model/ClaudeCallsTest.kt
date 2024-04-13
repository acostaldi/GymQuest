import com.example.GymQuest.Model.ClaudeRepository

fun fridge(val input: String)
{
    val KlaudeClient = ClaudeRepository.Builder()
        .key("CLAUDE_API_KEY")
        .model("claude-3")
        .maxTokensToSample(1000)
        .build()

    val text = input
    KlaudeClient.complete($text)
    {
        var result -> print(result ?: [empty])
    }

}