package goliath.loginrewards;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RewardTable {
    public static final HashMap<Integer, List<Pair<Integer, Item>>> REWARDS = new HashMap<>();

    static { // Temporary table representative of the Example in Formatting.md
        REWARDS.put(0, List.of(new Pair<>(4, Items.OAK_LOG), new Pair<>(3, Items.WHITE_WOOL))); // Day 0: 4 Oak Logs and 3 White Wool
        REWARDS.put(1, List.of(new Pair<>(12, Items.BAMBOO))); // Day 1: 23 Bamboo
        // Day 2 has no reward.
        REWARDS.put(3, List.of(new Pair<>(4, Items.RAW_IRON))); // Day 2: 4 Raw Iron
        // Day 4 has no reward.
        REWARDS.put(5, List.of(new Pair<>(5, Items.DIAMOND))); // Day 3: 5 Diamonds
    }
    public static Optional<List<Pair<Integer, Item>>> getRewards(int day) {
        return Optional.of(REWARDS.get(day));
    }
}
