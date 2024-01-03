You may use the compiled applications to run the game without needing to compile using Processing. For windows users, just run `windows-amd64/one-big-fighters.exe`; for MacOS and Linux users, just run `linux-amd64/one-big-fighters`.

# Screenshots
![image](https://github.com/hansjarettong/one-big-fighters/assets/60498410/3850bb1c-f9ce-4110-882f-9810798a9516)
![image](https://github.com/hansjarettong/one-big-fighters/assets/60498410/a5968058-e16d-4a92-91aa-61d2d0b78fd9)
![image](https://github.com/hansjarettong/one-big-fighters/assets/60498410/e656c2a8-a057-4708-a717-94b971c98179)


# Project Report: One Big Fighters

**Note: This is the report we submitted together with the actual game as part of the final requirements of our CE21 class (Introduction to Computing).**

## Group Members:
- Hans Jarett Ong
- Johnny Ong
- Jongmin Yim

## Game/Program Title: One Big Fighters

### Program Description:
Our program, "One Big Fighters," is a two-player fighting game set in the Ateneo environment, featuring locations such as the Rizal library. The objective is simple: players engage in combat, aiming to reduce their opponent's health to zero to secure victory. The game offers two basic moves—punch and kick—to achieve this goal.

### Instructions:
The game starts with an introduction screen displaying the title and a "Start Game" button. Upon initiation, players select characters and a fighting location. Controls for Player 1 include W to jump, A to move left, D to move right, G to punch, and H to kick. Player 2 utilizes arrow keys for movement, "." for punching, and "/" for kicking. The first player to deplete their opponent's health wins.

### Areas for Improvement:
1. **Graphics:**
   - The title screen appears simplistic; consider enhancing it with more engaging visuals.
   - Address pixelation issues, especially when zooming in on the winner.
2. **Variety of Movements:**
   - Introduce special moves or combo sequences, adding depth to the gameplay.
   - Enhance character animations to provide a more dynamic and visually appealing experience.
3. **More Characters:**
   - Expand the character roster with distinct movements and finishing moves for each.

### Details on Project Challenges:

1. **Jumping, Punching, Kicking:**
   - *Problem:* Initial use of the `keyPressed` function posed challenges for actions like jumping, punching, and kicking.
   - *Solution:* Implemented a "switchboard" system to toggle boolean variables, enabling precise control and timing for various actions.

2. **Lag in Keyboard Controls:**
   - *Problem:* Simultaneous key presses by both players resulted in control lag.
   - *Solution:* Consolidated controls into a single public `keyPressed` function, resolving the competition for control between Fighter objects.

3. **GIF Animation:**
   - *Problem:* Standard image functions didn't support animated GIFs.
   - *Solution:* Integrated the "gifAnimation" library by Kevin Weiner to enable the display of animated GIFs.

4. **Don't Loop Inside Draw:**
   - *Problem:* Certain actions needed to occur only once within the `draw` function.
   - *Solution:* Implemented a boolean (`done_this_once`) to control when specific actions were executed, preventing unnecessary repetition.

### Bug Report:
There is a known bug related to the collision detector of Fighter 1 and Fighter 2. This issue allows Player 2 to push Player 1. Switching the code order would result in the reverse scenario. Further debugging and adjustments are needed to address this collision problem effectively.
