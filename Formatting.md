## JSON Format for Config ##

```
[ // List of login rewards
  {integer (day), [ // List of rewards for this day 
      {integer (count), "namespace:itemname"}, 
      {...}
    ]
  },
  {...}
]
```

## Example ##

```
[
  {0, [
      {4, "minecraft:oak_log"},
      {3, "minecraft:white_wool"}
    ]
  },
  {1, [
      {12, "minecraft:bamboo"}
    ]
  },
  {3, [
      {4, "minecraft:iron_ore"}
    ]
  },
  {5, [
      {5, "minecraft:diamond"}
    ]
  }
]
```
