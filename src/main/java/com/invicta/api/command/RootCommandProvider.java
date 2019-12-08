package com.invicta.api.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.RootCommandNode;

import java.util.concurrent.CompletableFuture;

/**
 * @author cookiedragon234 07/Dec/2019
 */
public class RootCommandProvider<T> extends RootCommandNode<T>
{
	@Override
	public CompletableFuture<Suggestions> listSuggestions(final CommandContext<T> context, final SuggestionsBuilder builder)
	{
		builder.suggest("thing");
		return builder.buildFuture();
	}
}
