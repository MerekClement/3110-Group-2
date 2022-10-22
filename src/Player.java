public class Player {
    public String name;
    static int _instanceCount = 0;
    private int _id;
    private int _moves;
    private int _points;

    public Player(String name){
        this.name = name;
        _instanceCount++;
        this._id = _instanceCount;
        this._points = 0;
        this._moves = 0;
    }

    public int get_id() {
        return this._id;
    }
}
