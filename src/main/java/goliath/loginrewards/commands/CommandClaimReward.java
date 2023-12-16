package goliath.loginrewards.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import goliath.loginrewards.LoginRewards;
import goliath.loginrewards.RewardTable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Pair;

import java.util.List;
import java.util.Optional;

public class CommandClaimReward {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = LoginRewards.BASE_COMMAND
                .then(CommandManager.literal("claim")
                    .then(CommandManager.argument("day", IntegerArgumentType.integer(0, 30))
                        .executes(CommandClaimReward::giveReward)
                    )
                );
        dispatcher.register(command);
    }

    private static final SimpleCommandExceptionType NO_REWARD = new SimpleCommandExceptionType(new LiteralMessage("There does not exist a reward for this day."));
    private static final SimpleCommandExceptionType NULL_PLAYER = new SimpleCommandExceptionType(new LiteralMessage("You must be a player to use this command."));

    public static int giveReward(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        int day = IntegerArgumentType.getInteger(context, "day");
        Optional<List<Pair<Integer, Item>>> optionalRewards = RewardTable.getRewards(day);
        if (optionalRewards.isEmpty()) {
            throw NO_REWARD.create();
        }
        List<Pair<Integer, Item>> rewards = optionalRewards.get();
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player == null) {
            throw NULL_PLAYER.create();
        }
        rewards.forEach(reward -> player.giveItemStack(new ItemStack(reward.getRight(), reward.getLeft())));
        return 1;
    }
}
