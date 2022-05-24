import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private Player player;
	private Scanner scanner = new Scanner(System.in);;
	public boolean keepPlaying = true;
	private Room TeleportRoom;
	private ArrayList<Room> teleportableRooms;
	private ArrayList<Item> lootTable;

	private Room exit;
	private Item winCondition;

	// CONSTRUCTOR
	public Game() {

		player = new Player();
		this.teleportableRooms = new ArrayList<>();
		this.lootTable = new ArrayList<>();

		// CREATE ROOMS
		Room entrance = new Room(1,
				"You step through the gate of the castle on to creaking rotting floorboards, it seem to be at the entrace of the castle.");
		Room bigRoom1 = new Room(2,
				"Opening a heavy wooden door you step inside a large open room, the room seems to be the entrance of the great hall.");
		Room smallRoom1 = new Room(3,
				"You step inside a small room filled with dust and old boxes, it looks like the pantry, maybe there is something usefull lying around?");
		Room bigRoom2 = new Room(4,
				"You find yourself in a giant ornate room. The floor is made out of stone instead of wood, old red carpet stains the ground, paintings on the walls are faded and decaying. In the middle of the room stands a large table surounded by chairs. You have entered the great hall of the castle.");
		Room smallRoom2 = new Room(5,
				"Opening the door you find a small side room of the great hall, this room leads to the back of the castle.");
		Room corridor = new Room(6, "Opening the door a small corridor apears in sight.");
		Room storageRoom = new Room(7,
				"You enter a decent sized room filled with crates and barrels covered in spiderwebs, this must be the storage room of the castle.");
		Room hallway = new Room(8,
				"A large hallway aprears in front of you, red carpet and wooden boards lay the ground. The hallway appears to be build inside the outer walls of the castle.");
		Room wall1 = new Room(9,
				"You enter the beginning of a room located inside the castle wall, there is a treasure chest hidden behind a loose stone.");
		Room wall2 = new Room(10, "You enter the back part of the castle wall, the floar creaks beneath your feet.");
		Room guardRoom = new Room(11,
				"You have entered the barracks, this is the room where the guards used to live. They don't seem very alive though.");
		Room towerRoom = new Room(12,
				"You have found the tower room, the inside is large and spacious. The room is surprisingly well maintaned, You can see a treasure chest inside!");
		Room teleportRoom = new Room(13,
				"You step inside the room, you hear a loud expolosion and the ground glows with a bright purple light.");

		// ADD ROOMS TO ARRAY LIST FOR TELEPORTER
		teleportableRooms.add(entrance);
		teleportableRooms.add(bigRoom1);
		teleportableRooms.add(smallRoom1);
		teleportableRooms.add(bigRoom2);
		teleportableRooms.add(smallRoom2);
		teleportableRooms.add(corridor);
		teleportableRooms.add(storageRoom);
		teleportableRooms.add(hallway);
		teleportableRooms.add(wall1);
		teleportableRooms.add(wall2);
		teleportableRooms.add(guardRoom);
		teleportableRooms.add(towerRoom);

		// SET ADJECENT ROOMS
		entrance.setAdjacentRoom("west", bigRoom1);

		bigRoom1.setAdjacentRoom("north", smallRoom1);
		bigRoom1.setAdjacentRoom("east", entrance);
		bigRoom1.setAdjacentRoom("west", bigRoom2);

		smallRoom1.setAdjacentRoom("south", bigRoom1);

		bigRoom2.setAdjacentRoom("north", smallRoom2);
		bigRoom2.setAdjacentRoom("east", bigRoom1);
		bigRoom2.setAdjacentRoom("south", corridor);

		smallRoom2.setAdjacentRoom("south", bigRoom2);
		smallRoom2.setAdjacentRoom("west", hallway);

		corridor.setAdjacentRoom("north", bigRoom2);
		corridor.setAdjacentRoom("east", storageRoom);
		corridor.setAdjacentRoom("west", guardRoom);

		storageRoom.setAdjacentRoom("west", corridor);

		hallway.setAdjacentRoom("east", smallRoom2);
		hallway.setAdjacentRoom("south", teleportRoom);
		hallway.setAdjacentRoom("west", wall1);

		wall1.setAdjacentRoom("east", hallway);
		wall1.setAdjacentRoom("south", wall2);

		wall2.setAdjacentRoom("north", wall1);
		wall2.setAdjacentRoom("east", guardRoom);
		wall2.setAdjacentRoom("south", towerRoom);

		guardRoom.setAdjacentRoom("east", corridor);
		guardRoom.setAdjacentRoom("west", wall2);

		towerRoom.setAdjacentRoom("north", wall2);

		teleportRoom.setAdjacentRoom("north", hallway);

		// CREATE ITEMS
		Item stick = new Item("Stick", "This is a useless stick!");
		Item macGuffin = new Item("Macguffin", "The item radiates great mystical power");
		Item sword = new Item("Sword", "You swing the sword with great conviction");
		Item coinPurse = new Item("Coinpurse", "a small bag filled with gold coins");
		Item dagger = new Item("Dagger", "You thrust with the dagger at great speed");
		Item bow = new Item("Bow", "You solemly stare at the bow, realising you dont know how to use one.");

		Item gem = new Item("Gem", "Shiny!");
		Item coin = new Item("Coin", "A solid gold coin");
		Item charm = new Item("Charm", "The charm is decorated with pure gold");
		Item trash = new Item("Trash", "Nothing but a pile of rubbish");
		Item boot = new Item("Boot", "An old shoe, sole not included");

		// CREATE ENEMYS
		Enemy goblin = new Enemy("Goblin");
		Enemy bat = new Enemy("Bat");
		Enemy skeleton = new Enemy("Skeleton");

		// ADD ITEMS TO LOOTTABLE
		lootTable.add(boot);
		lootTable.add(gem);
		lootTable.add(trash);
		lootTable.add(charm);
		lootTable.add(coin);

		// ADD ITEMS TO ROOM
		smallRoom1.addItem(sword);
		storageRoom.addItem(stick);
		wall1.addItem(coinPurse);
		towerRoom.addItem(macGuffin);
		guardRoom.addItem(dagger);
		wall2.addItem(bow);

		// ADD ENEMYS TO ROOM
		bigRoom2.addEnemy(goblin);
		guardRoom.addEnemy(skeleton);
		wall1.addEnemy(bat);

		// SET START CONDITIONS
		player.setCurrentRoom(entrance);
		exit = entrance;
		winCondition = macGuffin;
		TeleportRoom = teleportRoom;

		startGame();
	}

	// Asks if the player wants to start game.
	private void startGame() {
		long startDelay = System.currentTimeMillis();

		System.out.println(
				"Welcome adventurer! You are about to enter the ruins of the forgotten castle, an ancient keep lost to time.");
		System.out.println("Your goal is to retrieve the legendary macguffin to the exit of the dungeon");
		System.out.println("You can move through the dungeon with: go 'cardinal direction'");

		while (System.currentTimeMillis() - startDelay < 7000) {
		}

		System.out.println("Start your adventure? enter 'yes' or 'no'");
		String startConfirmationInput = scanner.nextLine();
		handlestartConfirmation(startConfirmationInput);
	}

	// Loop for if the player input is a invalid command.
	private void startGameRetry() {
		long startDelay = System.currentTimeMillis();
		while (System.currentTimeMillis() - startDelay < 2000) {
		}

		System.out.println("Start your adventure? enter 'yes' or 'no'");
		String startConfirmationInput = scanner.nextLine();
		handlestartConfirmation(startConfirmationInput);
	}

	// Handles player input for StartGame();
	private void handlestartConfirmation(String startConfirmation) {
		String input = null;
		input = startConfirmation;

		switch (input) {
		case "yes":
			System.out.println("==========================");
			System.out.println(exit.getDescription());
			run();
			break;

		case "no":
			System.out.println("Maybe next time then.");
			break;

		default:
			System.out.println("ERROR: Unknown command, Enter 'yes' or 'no'");
			startGameRetry();
			break;
		}
	}

	// Loop that keeps the game running.
	private void run() {
		while (keepPlaying) {

			// wincondition check
			if (checkWin()) {
				winGame();
				break;
			}

			// teleport room check
			if (checkTeleport()) {
				doTeleport();
			}

			System.out.println("==========================");
			System.out.println("Awaiting command");
			String userInput = scanner.nextLine();
			System.out.println("==========================");
			handleCommand(userInput);
		}
	}

	// handler for player input
	private void handleCommand(String userInput) {
		String command = null;
		String extraInput = null;

		String[] inputArray = userInput.split(" ");
		command = inputArray[0];

		// Handle commands if input is 1 word long.
		if (inputArray.length == 1) {
			switch (command) {
			case "pack":
				handlePackCommand();
				break;

			case "help":
				handleHelpCommand();
				break;

			case "look":
				handleLookCommand();
				break;
			case "quit":
				handleQuitCommand();
				break;

			default:
				System.out.println("ERROR: Unknown command, try again.");
				break;
			}
		}

		// Handle commands if input is 2 words long.
		if (inputArray.length == 2) {
			extraInput = inputArray[1];

			switch (command) {
			case "go":
				checkRoomTravel(extraInput);
				break;

			case "get":
				handleGetCommand(extraInput);
				break;

			case "drop":
				handleDropCommand(extraInput);
				break;

			case "use":
				handleUseCommand(extraInput);
				break;
			case "fight":
				handleFightCommand(extraInput);
				break;
			default:
				System.out.println("ERROR: Unknown command, try again.");
				break;
			}
		}
		// Handle commands if input is longer than 2.
		if (inputArray.length >= 3) {
			System.out.println("ERROR: command can only be two words long.");
		}

	}

	// EVENT HANDLER

	// Handles fight command.
	private void handleFightCommand(String monsterName) {

		// Check if specified monster is in room.
		// Remove monster from room.
		// Generate loot, give loot to room.

		if (player.getCurrentRoom().checkEnemyInRoom(monsterName)) {
			player.getCurrentRoom().removeEnemy(monsterName);
			calculateLoot();

		} else {
			System.out.println("Error: no such monster found");
		}
	}

	// Handles look command.
	private void handleLookCommand() {
		player.getCurrentRoom().lookAround();
	}

	// Handles pack command.
	private void handlePackCommand() {
		System.out.println("Backpack:");
		player.showBackPack();
	}

	// Handles help command.
	private void handleHelpCommand() {
		System.out.println("go: used to move around rooms");
		System.out.println("get: used to pick items of the ground");
		System.out.println("drop: dorp items from backpack to ground");
		System.out.println("use: use item in backpack or room");
		System.out.println("pack: show backpack inventory");
		System.out.println("help: shows available commands");
		System.out.println("look: get information about current room");
		System.out.println("fight: enter combat with a enemy in the room");
	}

	// Handles use command.
	private void handleUseCommand(String itemName) {

		// check backpack
		// use item if in backpack
		// if not check room
		// use item if in room

		if (player.checkItem(itemName)) {
			player.useItemBackPack(itemName);
		} else {
			if (player.getCurrentRoom().checkRoomForItem(itemName)) {
				player.getCurrentRoom().useItemRoom(itemName);
			} else {
				System.out.println("Error: no such Item found");
			}
		}
	}

	// Handles drop command.
	private void handleDropCommand(String itemName) {

		// first checks if the called item is actualy in the backpack.
		// if so add item to room and remove from backpack.
		
		if (player.checkItem(itemName)) {
			player.dropItem(itemName);
		} else {
			System.out.println("Error: No such Item found");
		}
	}

	// Handles get command.
	private void handleGetCommand(String itemName) {

		// first checks if the called item is actualy in the room.
		// if so add item to backpack and remove from room
		
		if (player.getCurrentRoom().checkRoomForItem(itemName)) {
			player.addItemBackPack(itemName);
		} else {
			System.out.println("Error: No such item in room.");
		}
	}

	// Handles go command.
	private boolean checkRoomTravel(String direction) {

		// als de houdige room een exit heeft in de direction die door de speler input
		// word aangegeven.
		// dan word de huidige room overschreven met de direction die megegeven word
		// door de input.
		// door te kijken naar de omliggende kamers.

		if (player.getCurrentRoom().checkRoom(direction)) {
			player.setCurrentRoom(player.getCurrentRoom().getAdjacentRoom(direction));
			System.out.println("you travel " + direction);
		} else {
			System.out.println("Error: No exit found");
			return false;
		}

		// make the player enter the new room.
		player.getCurrentRoom().enterRoom(direction);

		return true;

	}

	// Handles quit command.
	private void handleQuitCommand() {
		stopGame();
		System.out.println("Bye");
	}

	public void winGame() {
		stopGame();
		System.out.println("Congratulations adventurer, you have retrieved the artifact. You won!");
		System.out.println("Game shutting down....");
	}

	public boolean checkWin() {
		if (player.getCurrentRoom().equals(exit)) {
			if (player.checkItem(winCondition.getName())) {
				return true;
			}
		}
		return false;
	}

	// Checks of the player is in teleporter room. if true calls doTeleport();
	public boolean checkTeleport() {

		if (player.getCurrentRoom().equals(TeleportRoom)) {
			System.out.println("this is a teleport room");
			return true;
		}
		return false;
	}

	// Teleports the player to a random room
	public void doTeleport() {
		int random = (int) (Math.random() * teleportableRooms.size());
		Room tempRoom = teleportableRooms.get(random);
		
		player.setCurrentRoom(tempRoom);
		System.out.println("You have been teleported to room: " + player.getCurrentRoom().getRoomNumber());
		System.out.println(tempRoom.getDescription());
	}

	// Ends the run() loop
	private void stopGame() {
		keepPlaying = false;
	}

	// Gets a random item from the lootTable array and adds it to the room.
	public void calculateLoot() {
		int random = (int) (Math.random() * lootTable.size());

		if (lootTable.isEmpty() == false) {
			player.getCurrentRoom().addItem(lootTable.get(random));
			lootTable.remove(random);
			System.out.println("Some loot has been dropped");
		} else
			System.out.println("Loot pool is empty, ask dev to add more items");
	}
}