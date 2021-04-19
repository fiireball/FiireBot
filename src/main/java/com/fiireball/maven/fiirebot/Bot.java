package com.fiireball.maven.fiirebot;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;





public class Bot {
	

	private static final Logger logger = LoggerFactory.getLogger(Bot.class);

	
	 public static void create(String token) {
		 	logger.info("Starting to create {} instance", Bot.class.getSimpleName());
		    final DiscordClient client = DiscordClient.create(token);
		    final GatewayDiscordClient gateway = client.login().block();

		    gateway.on(MessageCreateEvent.class).subscribe(event -> {
		      final Message message = event.getMessage();
		      if ("!ping".equals(message.getContent())) {
		        final MessageChannel channel = message.getChannel().block();
		        channel.createMessage("Pong!").block();
		      }
		    });
		    logger.info("Done creating {} instance", Bot.class.getSimpleName());
		    gateway.onDisconnect().block();
		  }
}
