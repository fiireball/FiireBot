package com.fiireball.maven.fiirebot;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;





public class FiireBot {
	

	private static final Logger logger = LoggerFactory.getLogger(FiireBot.class);

	
	 public static void create(String token) {
		 	logger.info("Starting to create {} instance", FiireBot.class.getSimpleName());
		    final DiscordClient client = DiscordClient.create(token);
		    final GatewayDiscordClient gateway = client.login().block();
		    
		    
		    
		    gateway.getEventDispatcher().on(ReadyEvent.class)
		        .subscribe(event -> {
		          User self = event.getSelf();
		          System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
		        });

		    gateway.getEventDispatcher().on(MessageCreateEvent.class)
		        .map(MessageCreateEvent::getMessage)
		        .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
		        .filter(message -> message.getContent().equalsIgnoreCase("!pong"))
		        .flatMap(Message::getChannel)
		        .flatMap(channel -> channel.createMessage("Ping!"))
		        .subscribe();

		    gateway.on(MessageCreateEvent.class).subscribe(event -> {
		      final Message message = event.getMessage();
		      if ("!ping".equals(message.getContent())) {
		        final MessageChannel channel = message.getChannel().block();
		        channel.createMessage("Pong!").block();
		      }
		    });
		    logger.info("Done creating {} instance", FiireBot.class.getSimpleName());
		    gateway.onDisconnect().block();
		  }
}
