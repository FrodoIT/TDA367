package scratch.network;

/**
 * Created by tejp on 2014-05-21.
 */
public class PacketRemoveCharacter {
    private int characterId, roomId;

    public PacketRemoveCharacter(int characterId, int roomId) {
        this.characterId = characterId;
        this.roomId = roomId;
    }

    public PacketRemoveCharacter() {
    }

    public int getRoomId() {
        return roomId;
    }

    public int getCharacterId() {
        return characterId;
    }
}
