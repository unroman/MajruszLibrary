package com.mlib.gamemodifiers.contexts;

import com.mlib.gamemodifiers.Context;
import com.mlib.gamemodifiers.data.OnItemCraftedData;
import com.mlib.gamemodifiers.parameters.ContextParameters;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Mod.EventBusSubscriber
public class OnItemCraftedContext extends Context< OnItemCraftedData > {
	static final List< OnItemCraftedContext > CONTEXTS = new ArrayList<>();

	public OnItemCraftedContext( Consumer< OnItemCraftedData > consumer, ContextParameters params ) {
		super( OnItemCraftedData.class, consumer, params );
		Context.addSorted( CONTEXTS, this );
	}

	public OnItemCraftedContext( Consumer< OnItemCraftedData > consumer ) {
		this( consumer, new ContextParameters() );
	}

	@SubscribeEvent
	public static void onItemCrafted( PlayerEvent.ItemCraftedEvent event ) {
		Context.accept( CONTEXTS, new OnItemCraftedData( event ) );
	}
}