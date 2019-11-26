package Game2;

public class Board {
    Cell boardLayout[][]=new Cell[10][10];
    Cell nextGen[][]=new Cell[10][10];
    Board() {
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout.length; j++) {
                boardLayout[i][j] = new Cell();
                boardLayout[i][j].setState(0);
                nextGen[i][j] = new Cell();
                if(i>2&&i<6&&j>2&&j<6){
                    boardLayout[i][j].setState(1);
                }

            }
        }
    }
        public int getNeighboursInformation(Cell cell, int x, int y) {

            int aliveNeighbours = 0;
            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <= 1; j++) {

                    //aliveNeighbours = aliveNeighbours + boardLayout[getMod(i + x,10)][getMod(y + j,10)].getState();
                    aliveNeighbours = aliveNeighbours + boardLayout[(i + x)][(y + j)].getState();
                }
        }

                aliveNeighbours =aliveNeighbours - boardLayout[x][y].getState();
            //System.out.println(aliveNeighbours);
            return aliveNeighbours;
        }

        public void runRules(Cell cell, int x, int y, int aliveNeighbours){

        Rule rule=new Rule();

        if (rule.lonelyDeathRule(boardLayout[x][y].getState(),getNeighboursInformation(boardLayout[x][y],x,y))) {
            nextGen[x][y].die();
        }
        else if(rule.overPopulationRule(boardLayout[x][y].getState(),getNeighboursInformation(boardLayout[x][y],x,y))){
            nextGen[x][y].die();
        }
        else if(rule.birthRule(boardLayout[x][y].getState(),getNeighboursInformation(boardLayout[x][y],x,y))){
            nextGen[x][y].birth();
        }
        else{
            nextGen[x][y].setState(boardLayout[x][y].getState());
        }

        }

        public void execute (){

        int temp;
            for (int i = 1; i < boardLayout.length-1; i++) {
                for (int j = 1; j <boardLayout.length-1; j++) {
                    runRules(boardLayout[i][j],i,j,getNeighboursInformation(boardLayout[i][j],i,j));

                }
            }

            System.out.println();  System.out.println();  System.out.println();
            boardLayout=nextGen;
            for (int i = 0; i < nextGen.length; i++) {
                for (int j = 0; j < nextGen.length; j++) {
                    System.out.print(nextGen[i][j].getState());

                }
                System.out.println();
            }
        }

        public void firstStates(){

            for (int i = 0; i < boardLayout.length; i++) {
                for (int j = 0; j < boardLayout.length; j++) {
                    System.out.print(boardLayout[i][j].getState());

                }
                System.out.println();
            }
        }

//        public int getMod(int value, int modOf) {
//            if (value < 0) {
//
//                return value + modOf;
//            } else {
//                return value%modOf;
//            }
//        }
        public static void main(String [] args){
        Board board=new Board();
        board.firstStates();

       for(int i=0;i<10;i++) {
           board.execute();
       }
        }

    }

