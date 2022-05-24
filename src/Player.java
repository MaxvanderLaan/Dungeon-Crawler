import java.util.ArrayList;

public class Player {

	// VARIABLES
	private ArrayList<Item> BackPack;
	private Room currentRoom;

	// CONSTRUCTOR
	public Player() {
		BackPack = new ArrayList<Item>();
	}

	// Returns true if the specified item is found in the backpack.
	public boolean checkItem(String itemName) {
		for (Item i : BackPack) {
			if (itemName.equals(i.getName())) {
				return true;
			}
		}
		return false;
	}

	// used by handleGetCommand. Takes a item from a room and adds it to backpack.
	public void addItemBackPack(String itemName) {
		for (Item i : currentRoom.getItemsInRoom()) {

			if (itemName.equals(i.getName())) {
				this.RemoveItemFromRoom(i); // call RemoveItemFromRoom
				BackPack.add(i);
				System.out.println("you have added " + itemName + " to your backpack.");
				return;
			}
		}
	}

	// Adds ItemName to room and removes ItemName from backpack.
	public void dropItem(String itemName) {
		for (Item i : BackPack) {

			if (itemName.equals(i.getName())) {
				this.addItemToRoom(i);
				BackPack.remove(i);
				System.out.println("You have dropped " + itemName);
				return;
			}
		}
	}

	public void addItemToRoom(Item droppedItem) {
		currentRoom.getItemsInRoom().add(droppedItem);

	}

	private void RemoveItemFromRoom(Item addedItem) {
		currentRoom.getItemsInRoom().remove(addedItem);
	}

	public void setCurrentRoom(Room room) {
		currentRoom = room;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void showBackPack() {
		for (Item i : BackPack) {
			System.out.println(i.getName());
		}
	}

	public void useItemBackPack(String itemName) {
		for (Item i : BackPack) {

			if (itemName.equals(i.getName())) {
				System.out.println(i.getUsageText());
			}
		}
	}
}