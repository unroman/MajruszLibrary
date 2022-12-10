package com.mlib.tests;

import com.mlib.MajruszLibrary;
import com.mlib.gamemodifiers.Contexts;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;
import org.apache.commons.lang3.mutable.MutableInt;

@GameTestHolder( MajruszLibrary.MOD_ID )
public class ContextTests extends BaseTest {
	@PrefixGameTestTemplate( false )
	@GameTest( templateNamespace = "mlib", template = "empty_test" )
	public static void contextPriority( GameTestHelper helper ) {
		Contexts.getInstances().forEach( contexts->{
			MutableInt max = new MutableInt( Integer.MIN_VALUE );
			contexts.forEach( context->{
				int priority = context.getParams().getPriorityAsInt();
				if( priority < max.getValue() ) {
					helper.fail( String.format( "%s has invalid priority", getFullSimpleName( context.getClass() ) ) );
				}
				max.setValue( priority );
			} );
		} );
		helper.succeed();
	}

	public ContextTests() {
		super( ContextTests.class );
	}
}
