package scratch.network;

/**
 * Created by tejp on 2014-05-21.
 */
public class PacketMoveCharacter {
    private int characterId, fromRoomId, toRoomId;

    public PacketMoveCharacter(int characterId, int fromRoomId, int toRoomId) {
        this.characterId = characterId;
        this.fromRoomId = fromRoomId;
        this.toRoomId = toRoomId;
    }

    public PacketMoveCharacter() {
        //used for serialization
    }

    public int getCharacterId() {
        return characterId;
    }

    public int getFromRoomId() {
        return fromRoomId;
    }

    public int getToRoomId() {
        return toRoomId;
    }
}
