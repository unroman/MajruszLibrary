package com.mlib.gamemodifiers.contexts;

import com.mlib.gamemodifiers.Context;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class OnPickupXpContext extends Context {
	static final List< OnPickupXpContext > CONTEXTS = new ArrayList<>();

	public OnPickupXpContext( String configName, String configComment ) {
		super( configName, configComment );
		CONTEXTS.add( this );
	}

	public OnPickupXpContext() {
		this( "OnPickupXp", "" );
	}

	@SubscribeEvent
	public static void onPickupXp( PlayerXpEvent.PickupXp event ) {
		handleContexts( context->new Data( context, event ), CONTEXTS );
	}

	public static class Data extends Context.Data {
		public final PlayerXpEvent.PickupXp event;
		public final Player player;

		Data( Context context, PlayerXpEvent.PickupXp event ) {
			super( context, event.getEntityLiving() );
			this.event = event;
			this.player = event.getPlayer();
		}
	}
}
