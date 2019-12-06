package Game2;

public class Cell {


    private int state;

    private Cell []  neighbours=new Cell[8];

    public void setNeighbours(Cell [][]layout,int x, int y){

        int cellIndex=0;
        for (int i = -1; i <2; i++) {
            for (int j = -1; j <2; j++) {
                  if(i==0&&j==0) {

                  }else
                      {
                    neighbours[cellIndex]=layout[getMod((i + x),10)][getMod((y + j),10)];
                    cellIndex++;
                  }
            }
        }
        //System.out.println(cellIndex);

    }





    private int getNumberOfAliveNeighbours() {
        int numberOfAliveNeighbours=0;
        for (int i = 0; i < neighbours.length; i++) {
            //System.out.println(neighbours[i].getState());
            if(neighbours[i].getState()==1){
                numberOfAliveNeighbours++;
            }

        }

        //System.out.println(numberOfAliveNeighbours);
        return numberOfAliveNeighbours;
    }








    public Cell(int state   ){
        this.state=state;
    }

    public void die(){
        setState(0);
    }

    public void comeAlive(){
        setState(1);
    }

    public void setState(int state) {
        this.state = state;
    }
    public int getState()
    {
        return state;
    }

    private boolean underpopulationDeathRule(){
        if( getState()==1 &&  getNumberOfAliveNeighbours()<2){
            return true;
        }
        return false;
    }

    private boolean overPopulationDeathRule(){
        if( getState()==1 &&  getNumberOfAliveNeighbours()>3){
            return true;
        }
        return false;
    }

    private boolean reproductionRule(){
        if( this.getState()==0 &&   this.getNumberOfAliveNeighbours()==3){
            return true;
        }
        return false;
    }

    private boolean livesOn(){
        if( getState()==1 && ( getNumberOfAliveNeighbours()==3 || getNumberOfAliveNeighbours()==2)){
            return true;
        }
        return false;
    }

    public int runRules(){
        int decision=this.getState();

                    if(reproductionRule()){
                        //if birth rule is true then give life to cell
                        decision=1;

                    }

                    else if (underpopulationDeathRule()) {
                        //if lonely cell is true then kill cell
                        decision=0;

                    }
                    else if(overPopulationDeathRule()){
                        //if cell is over populated then kill cell
                        decision=0;

                    }

                    else if(livesOn())
                    {
                        //if 2 or 3 neighbours are alive then let it be alive
                        decision=1;

                    }
                        return decision;
    }

    //If we need to implement circular rules, we need to use this function
        public int getMod(int value, int modOf) {
            if (value < 0) {

                return value + modOf;
            } else {
                return value%modOf;
            }
        }


}
