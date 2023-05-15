package pad.backend;

/*
 * The field consists of tiles, each tile has two coordinates (x & y).
 */
public record Tile(int x, int y) {

    //Two tiles are equal when they have the same coordinates.
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tile tile)) return false;
        return this.x == tile.x() && this.y == tile.y();
    }
}
