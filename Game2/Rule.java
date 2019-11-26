package Game2;

public class Rule {

    public boolean lonelyDeathRule(int state, int aliveNeighbours ){
        if(state==1 && aliveNeighbours<2){
            return true;
        }
        return false;
    }

    public boolean overPopulationRule(int state, int aliveNeighbours ){
        if(state==1 && aliveNeighbours>3){
            return true;
        }
        return false;
    }

    public boolean birthRule(int state, int aliveNeighbours ){
        if(state==0 && aliveNeighbours==3){
            return true;
        }
        return false;
    }

}
