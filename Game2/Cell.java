package Game2;

public class Cell {
    int state;

    public void setState(int state) {
        this.state = state;
    }
    public int getState()
    {
        return state;
    }
    
    public void die(){
        state=0;
    }
    public void birth(){
        state=1;
    }
    
    
}
