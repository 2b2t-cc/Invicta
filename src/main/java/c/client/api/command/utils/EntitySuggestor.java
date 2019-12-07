package c.client.api.command.utils;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * @author cookiedragon234 07/Dec/2019
 */
public class EntitySuggestor<T extends String> implements SuggestionProvider<T>
{
	protected Stream<Entity> getEntities()
	{
		return Minecraft.getMinecraft().world.loadedEntityList.stream();
	}
	
	@Override
	public CompletableFuture<Suggestions> getSuggestions(CommandContext<T> context, SuggestionsBuilder builder) throws CommandSyntaxException
	{
		getEntities()
			.map(Entity::getName)
			.forEach(builder::suggest);
		
		return builder.buildFuture();
	}
}
