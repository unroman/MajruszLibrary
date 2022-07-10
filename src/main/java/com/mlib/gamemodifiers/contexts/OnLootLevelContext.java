package com.mlib.gamemodifiers.contexts;

import com.mlib.gamemodifiers.Context;
import com.mlib.gamemodifiers.data.OnLootLevelData;
import com.mlib.gamemodifiers.parameters.ContextParameters;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Mod.EventBusSubscriber
public class OnLootLevelContext extends Context< OnLootLevelData > {
	static final List< OnLootLevelContext > CONTEXTS = new ArrayList<>();

	public OnLootLevelContext( Consumer< OnLootLevelData > consumer, ContextParameters params ) {
		super( OnLootLevelData.class, consumer, params );
		Context.addSorted( CONTEXTS, this );
	}

	public OnLootLevelContext( Consumer< OnLootLevelData > consumer ) {
		this( consumer, new ContextParameters() );
	}

	@SubscribeEvent
	public static void onLootLevel( LootingLevelEvent event ) {
		Context.accept( CONTEXTS, new OnLootLevelData( event ) );
	}
}