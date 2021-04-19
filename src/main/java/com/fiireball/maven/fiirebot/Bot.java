package com.fiireball.maven.fiirebot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

public class Bot {
	
	 public static void create(String token) {
		    final DiscordClient client = DiscordClient.create(token);
		    final GatewayDiscordClient gateway = client.login().block();

		    gateway.on(MessageCreateEvent.class).subscribe(event -> {
		      final Message message = event.getMessage();
		      if ("!ping".equals(message.getContent())) {
		        final MessageChannel channel = message.getChannel().block();
		        channel.createMessage("Pong!").block();
		      }
		    });

		    gateway.onDisconnect().block();
		  }
}
