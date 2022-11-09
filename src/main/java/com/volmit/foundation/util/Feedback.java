package com.volmit.foundation.util;

import com.volmit.foundation.Foundation;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Accessors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@Builder
@Data
@Accessors(chain = true, fluent = true)
public class Feedback {
    @Singular
    private List<SoundFeedback> sounds;
    @Singular
    private List<TextComponent> messages;

    public void send(CommandSender serverOrPlayer) {
       if(serverOrPlayer instanceof Player p) {
           for(SoundFeedback i : sounds) {
               i.play(p);
           }
       }

        for(TextComponent i : messages) {
            Foundation.audiences.sender(serverOrPlayer).sendMessage(i);
        }
    }
}
