import java.util.ArrayList;
import java.util.HashMap;

public class Room {

	// VARIABLES
	// each room object gets a hashmap of adjecent rooms
	private HashMap<String, Room> adjecentRooms;
	private ArrayList<Item> itemsInRoom;
	private ArrayList<Enemy> enemysInRoom;

	private String roomDescription;
	private int roomNumber;

	// CONSTRUCTOR
	public Room(int roomNumber, String description) {
		this.adjecentRooms = new HashMap<>();
		this.itemsInRoom = new ArrayList<>();
		this.enemysInRoom = new ArrayList<>();

		this.roomNumber = roomNumber;
		this.roomDescription = description;
	}

	public void setAdjacentRoom(String direction, Room room) {
		// voeg aanliggende kamers toe aan een specifieke room
		this.adjecentRooms.put(direction, room);
	}

	public Room getAdjacentRoom(String direction) {
		return this.adjecentRooms.get(direction);
	}

	public String getDescription() {
		return this.roomDescription;
	}

	public void addItem(Item item) {
		itemsInRoom.add(item);
	}

	public void addEnemy(Enemy enemy) {
		enemysInRoom.add(enemy);
	}

	public boolean checkRoom(String direction) {

		// check if there is a exit
		if (adjecentRooms.containsKey(direction)) {
			return true;
		}
		return false;
	}

	// This is called everytime a room is entered.
	public void enterRoom(String direction) {
		System.out.println(this.getDescription());
	}

	public ArrayList<Item> getItemsInRoom() {
		return itemsInRoom;
	}

	public boolean checkRoomForItem(String itemName) {
		for (Item i : itemsInRoom) {
			if (itemName.equals(i.getName())) {
				return true;
			}
		}
		return false;
	}

	// Used for the look command, prints varius things in the room.
	public void lookAround() {

		// Print out if there are any items in the room.
		if (itemsInRoom != null) {
			for (Item i : itemsInRoom) {
				System.out.println("You can see a item in the room, it is a " + i.getName());
			}
		}

		// Print out if there are any enemys in the room.
		if (enemysInRoom != null) {
			for (Enemy i : enemysInRoom) {
				System.out.println("Enemy found! There is a " + i.getEnemyName());
			}
		}

		// Print out if there are any exits.
		if (adjecentRooms.containsKey("north")) {
			System.out.println("There is a door to the north");
		}
		if (adjecentRooms.containsKey("east")) {
			System.out.println("There is a door to the east");
		}

		if (adjecentRooms.containsKey("south")) {
			System.out.println("There is a door to the south");
		}

		if (adjecentRooms.containsKey("west")) {
			System.out.println("There is a door to the west");
		}
	}

	// Print UsageText when item is on ground.
	public void useItemRoom(String itemName) {
		for (Item i : itemsInRoom) {

			if (itemName.equals(i.getName())) {
				System.out.println(i.getUsageText());
			}

		}
	}

	// Return true if there is a enemy in current room named enemyName.
	public boolean checkEnemyInRoom(String enemyName) {
		for (Enemy i : enemysInRoom) {
			if (enemyName.equals(i.getEnemyName())) {
				System.out.println("Engaged enemy in combat!");
				return true;
			}
			System.out.println("combat failed");
		}
		return false;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	// Remove an enemy from current room.
	public void removeEnemy(String enemyName) {
		for (int i = 0; i < enemysInRoom.size(); i++) {
			if (enemyName.equals(enemysInRoom.get(i).getEnemyName())) {
				System.out.println(enemysInRoom.get(i).getEnemyName() + " has been defeated!");
				enemysInRoom.remove(i);
			}
		}
	}

}
