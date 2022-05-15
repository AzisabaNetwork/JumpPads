# JumpPads
Turns `Light Weighted Pressure Plate` into a jump pad.

## Configuration (config.yml)

```yml
# jump pads are only enabled in these worlds
enabled-worlds:
  - enabled-in-this-world

# multiply jump power (y) by this value
jump-power-y: 1.1

# multiply jump power (x and z) by this value
jump-power: 2

# whether to accumulate speed. if true, the new velocity will be the sum of the old and the new.
accumulate-speed: false
```
