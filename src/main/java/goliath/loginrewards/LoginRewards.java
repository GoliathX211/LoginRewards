package goliath.loginrewards;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import goliath.loginrewards.commands.CommandClaimReward;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.gen.structure.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginRewards implements ModInitializer {
	public static final String MOD_ID = "loginrewards";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final LiteralArgumentBuilder<ServerCommandSource> BASE_COMMAND = CommandManager.literal(MOD_ID);

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			LOGGER.info("Registering claim command");
			CommandClaimReward.register(dispatcher);
		}); // Register commands
	}
}