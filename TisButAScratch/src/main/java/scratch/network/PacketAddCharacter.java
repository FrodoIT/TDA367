package scratch.network;

/**
 * Created by tejp on 2014-05-21.
 */
public class PacketAddCharacter {
    private int characterId, roomId;

    public PacketAddCharacter(int characterId, int roomId) {
        this.characterId = characterId;
        this.roomId = roomId;
    }

    public PacketAddCharacter() {
    }

    public int getRoomId() {
        return roomId;
    }

    public int getCharacterId() {
        return characterId;
    }
}
