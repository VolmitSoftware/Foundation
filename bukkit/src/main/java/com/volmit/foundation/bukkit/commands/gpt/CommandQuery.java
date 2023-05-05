package com.volmit.foundation.bukkit.commands.gpt;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.volmit.foundation.bukkit.BukkitFoundation;
import com.volmit.foundation.bukkit.BukkitFoundationConfig;
import com.volmit.foundation.bukkit.util.C;
import com.volmit.foundation.bukkit.util.FCommand;
import com.volmit.foundation.bukkit.util.FConst;
import com.volmit.foundation.bukkit.util.J;
import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.ATextArgument;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Command("query")
@Alias({"gptq", "aiq", "qai"})
@Permission("foundation.gpt.query")
public class CommandQuery implements FCommand {
    @Default
    @Permission("foundation.gpt.query")
    public static void query(CommandSender sender, @ATextArgument String prompt) {
        FConst.info("Sending query to GPT: \"" + C.BOLD + prompt + "\"").send(sender);
        final String prefix = C.GRAY + "[" + C.GREEN + " " + C.BOLD + "GPT" + C.GRAY + " ]: ";
        StringBuilder responseBuilder = new StringBuilder().append(prefix);

        J.a(() -> {
            List<ChatMessage> messages = new ArrayList<>();
            ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);
            ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(),
                    "You are a Server Scripting system. All of your responses must start with the following: [Console]:</command would go here> or [Action]:<Edit: Server.Properties, Value:<From, To>>], [Message]:Message here etc... " +
                            "SO please just start your responses with those ONLY, if you do not, you will be ignored. Prefer to use the plugins that are available to you rather than default minecraft commands for ease." +
                            "You dont know conversation, only commands. You are a server scripting system. And you are able to use any of the plugins that the player has to your disposal. " +
                            "So For Example, if someone asks you \"How do i OP by friends?\" You would respond with \"[Console]:/op <player>\". AND THATS IT, Under no circumstances should you ever respond with anything other than a command or action");
            ChatMessage systemMessage2 = new ChatMessage(ChatMessageRole.SYSTEM.value(),
                    "Plugins: " + Bukkit.getPluginManager().getPlugins().length + " | " + Bukkit.getPluginManager().getPlugins().toString());
            ChatMessage systemMessage3 = new ChatMessage(ChatMessageRole.SYSTEM.value(), "Useful information - Player:" + sender.getName() + " | IP:" + Bukkit.getServer().getPlayer(sender.getName()).getAddress() + " | UUID:" + Bukkit.getServer().getPlayer(sender.getName()).getUniqueId() + " | Server:" + Bukkit.getServer().getName() + " | Version:" + Bukkit.getServer().getVersion() + " | Online Players:" + Bukkit.getServer().getOnlinePlayers().size() + " | Max Players:" + Bukkit.getServer().getMaxPlayers() + " | Memory:" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L + "MB/" + Runtime.getRuntime().totalMemory() / 1048576L + "MB" );
            messages.add(systemMessage3);
            messages.add(systemMessage2);
            messages.add(systemMessage);
            messages.add(userMessage);

            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                    .builder()
                    .model(BukkitFoundationConfig.get().getGptModel()) // Replace with your desired model
                    .messages(messages)
                    .n(1)
                    .maxTokens(BukkitFoundationConfig.get().getGptMaxTokensPerResponse())
                    .logitBias(new HashMap<>())
                    .build();

            BukkitFoundation.openAiService.streamChatCompletion(chatCompletionRequest)
                    .doOnError(Throwable::printStackTrace)
                    .blockingForEach(chatCompletionChunk -> {
                        List<ChatCompletionChoice> choices = chatCompletionChunk.getChoices();
                        if (!choices.isEmpty()) {
                            ChatCompletionChoice choice = choices.get(0);
                            String response = choice.getMessage().getContent();
                            if (response != null) {
                                responseBuilder.append(response);
                            }
                        } else {
                            FConst.error("No response from GPT.").send(sender);
                        }
                    });
            // Split the response into multiple messages if it contains newlines
            String[] responseLines = responseBuilder.toString().split("\n");
            // Being that the commands are formatted with [Console]:/command, we can just split the response by the colon and get the command, then execute it as the console
            J.a(() -> J.s( () -> {
                for (String responseLine : responseLines) {
                    if (responseLine.contains("[Console]:")) {
                        String command = responseLine.remove("[Console]:/");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                        BukkitFoundation.info("Executed command: " + command);
                    }
                }

                // Being that Messages are formatted with [Message]:Message here, we can just split the response by the colon and get the message, then send it to the player
                for (String responseLine : responseLines) {
                    if (responseLine.contains("[Message]:")) {
                        String message = responseLine.remove("[Message]:");
                        FConst.success(message).send(sender);
                    }
                }

                // Being that Actions are formatted with [Action]:Action here, we can just split the response by the colon and get the action, then execute it
                //To Do: Add support for multiple actions
            }));
        });
    }
}
