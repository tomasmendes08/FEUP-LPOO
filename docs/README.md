# LPOO_65 - ROGUE ONE

Rogue One is a game in which the player controls a hero that explores a dungeon and advances through its levels, collecting gold and killing enemies.

This project was developed by Daniel Garcia Silva (up201806524@fe.up.pt) and Tomás Mendes (up201806522@fe.up.pt) for LPOO 2019/2020.

## Implemented Features

* **Main Menu** - the game begins with a main menu, that gives the player the option to begin a new game, learn how to play, or exit the game. Upon beginning a game, an introduction to it is showed.

* **Walking** - the player can move in a direction that is not a wall or another monster, by pressing the arrow keys.

* **Picking up gold** - the player can walk to a tile that has gold in it, and it will acquire it.

* **Stat Display** - a section of the game window will be reserved for the display of the player's stats, such as player level, health points, experience points, etc.

* **Information Display** - under the *Stat Display*, relevant information is displayed to the player (such as dealing/taking damage, leveling up or picking up gold).

* **Level Display** - the remaining section of the window will be reserved for the dungeon map display. On odd levels, the dungeon fills the whole space reserved for it, bordering the rectangle with walls, while on even levels, two rooms are displayed, with a random corridor going from one to the other. On each level, there are 2 Gold spawns (with random value between 1 and 50), between 1 and 3 enemies, 1 staircase and the hero. However, the gold and the staircase might be hidden under the enemies.

* **Battling** - the player can attack a monster that is in a tile directly next to it, by pressing the A key. If the monster's health points reach 0, the monster is killed and the player receives experience points. If they don't, the monster can attack the player, taking his health points. The damage calculation takes into account the dungeon level and the player's attack and defense stats.

* **Advancing levels** - the player can advance to the next dungeon level by walking on top of a staircase tile.

* **Healing** - when the hero collects 450 gold, their health is automatically regenerated and they lose the gold.

* **Pausing** - at any point, the player can press the ESC key and the game will be paused. The player can then choose to resume, go to the main menu and start over, or exit the game.

* **Game Over** - if the hero's health reaches 0, the game is lost and the player will have to start over.


![Main Menu](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/main%20menu.png?raw=true)

![How to play](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/how%20to%20play.png?raw=true)

![Picked Up Gold](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/picked%20up%20gold.png?raw=true)

![Attacked](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/attacked.png?raw=true)

![Leveled up](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/leveled%20up.png?raw=true)

![Healed](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/healed.png?raw=true)

![Pause](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/pause.png?raw=true)

![Game over](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/game%20over.png?raw=true)


## Design

In this project, we used the Model-View-Controller architectural pattern, organizing our code in 3 packages: [MainPackage.data](https://github.com/FEUP-LPOO/lpoo-2020-g65/tree/master/src/main/java/MainPackage.data), [MainPackage.gui](https://github.com/FEUP-LPOO/lpoo-2020-g65/tree/master/src/main/java/MainPackage.gui) and [MainPackage.controller](https://github.com/FEUP-LPOO/lpoo-2020-g65/tree/master/src/main/java/MainPackage.gui).
This solves our **Single Responsibility Principle** issues regarding user interaction. 

### Info log

#### Problem in Context

In the [ArenaView](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/src/main/java/gui/ArenaView.java) class, we implemented an info log that is composed of 3 strings, that get "scrolled" up everytime the game sends another info to the user (this information can range from leveling up to picking up gold). We do this by changing the 2nd line to the 1st, the 3rd to the 2nd, and assigning a new string to the 3rd line, in the `sendInfo` method. This method takes an `ACTION` and a value as parameters (depending on the action, the value represents different things). 

We had a long Switch Statement to solve the 3rd line assingment problem, which did not *smell* very nice.

#### How we solved it

One option could have been creating classes for the different Infos and use polymorphism, but this seemed unnecessary, given that their only purpose would be to store a string (making them *Data Classes*), and there are 7 different possibilities. So we ruled out polymorphism. We decided on using a map.

#### Implementation

In the constructor, we build the map using `buildInfoMap`. This map uses `ACTION` as keys, and strings as values. However, these strings are actually string formats.

In the `sendInfo`method, we merely switch the lines up and then use String.format to insert the value parameter in the right place.

#### Consequences

The use of the map allowed us to remove the long switch statement and, at the same time, avoid the creation of Data Classes. We still had to build the map, but that seemed the most clean decision in terms of Design.



### Alternating between Menu and Play

#### Problem in Context

We wanted to have a main menu, an instructions menu and some intermediate states during the journey of our hero. In this sense, we needed to implement this code without excessive usage of conditional logic because this would violate the **Single Responsibility Principle**.  

#### The Pattern

This can be solved through the *State* pattern.
> This pattern allows you to represent different states with different subclasses. We can switch to a different MainPackage.controller.state of the application by switching to another implementation (i.e., another subclass).

This prevents the **SRP** violation by separating the methods in different classes and using polymorphism.

#### Implementation

The UML Diagram will look similar to this:

![UML Diagram](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/state_pattern_uml.PNG)

#### Consequences

The use of the State pattern in the current design allows the following benefits:

* Simplifies the code of the context by using polymorphism instead of using conditionals or switch cases.

* The states become more explicit to read than using a series of flags.

* Every state belongs to a different class which makes the design respect the **SRP**.

* The design pattern follows the open/close principle by allowing the introduction of new states without changing the current state or any other state.

## Known code smells and refactoring suggestions

### Switch Statements and Long Method

In the [PlayState](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/src/main/java/MainPackage.controller/state/PlayState.java) class, the `processKey` method has a switch statement that deals with the action that the player can take, regarding movement and battling. This, together with the `STRIKE` case and the last lines of code that deal with picking up gold violate the *Single Responsibility Principle*.

This method is, at the same time, responsible for:

* **MOVEMENT** - moving the hero and picking up gold;
* **BATTLING** - calculating damage dealt and taken, experience earned and leveling up the hero;
* **PAUSE** - changing to the `PauseState`.

A possible refactoring could consist of:
* joining the `UP`, `DOWN`, `LEFT` and `RIGHT` cases into one `MOVE` case, passing the direction as a parameter to the `moveHero` method;
* creating a `pickUpGold` method in the [ArenaController](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/src/main/java/MainPackage.controller/ArenaController.java) class that does exactly what is done at the end of the while loop, and calling it inside the `moveHero` method (and deleting that portion of code from the `processKey` method);
* creating a `battle` method in the [ArenaController](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/src/main/java/MainPackage.controller/ArenaController.java) class that is called in the `STRIKE` case, and that calls other methods (such as `calcDmgDealt` and `calcEXPEarned`) created to separate the several responsibilities inherent to battling.

This would still leave us the switch statement, but it would be a much less complicated one, and the `processKey` method would no longer be a Long Method. More importantly, it would not violate the Single Responsibility Principle.


### Message Chains

In the [ArenaView](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/src/main/java/MainPackage.gui/ArenaView.java) class, the methods that draw the elements of the game (`drawWalls`, `drawHeroAndStats` and `drawGold`) all rely on series of 3 calls, however this code smell is somewhat necessary and does not indicate a problem, since it is caused by our MVC architecture implementation, which separates the display, MainPackage.data and control elements into different classes.

## Testing

Running tests with coverage:

![TestsWithCoverage](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/TestsWithCoverage.PNG)

Running mutation testing:

![MutationTesting](https://github.com/FEUP-LPOO/lpoo-2020-g65/blob/master/docs/MutationTesting.PNG)

## Self-Evaluation

* Daniel Garcia Silva: 50%
* Tomás Mendes: 50%
