package Game2;
public class GameOfLife {
    // Create array of type cells
    private Cell boardLayout[][] = new Cell[10][10];


    //create temporary array to hold data for next generation of output cells
    private int nextGenerationLayout[][] = new int[10][10];


    public GameOfLife() {

        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout.length; j++) {
                //setting all cell states to dead
                boardLayout[i][j] = new Cell(0);
                boardLayout[i][j].die();
                nextGenerationLayout[i][j] = 0;


                //Setting initials cells alive
                if (i > 2 && i < 6 && j > 2 && j < 6) {
                    boardLayout[i][j].comeAlive();
                }
            }
        }
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout.length; j++) {
                boardLayout[i][j].setNeighbours(boardLayout, i, j);
            }
        }
    }

    public GameOfLife(int userSetArray[][]) {
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout.length; j++) {
                //setting all cell states to dead
                boardLayout[i][j] = new Cell(0);
                nextGenerationLayout[i][j] = 0;

                for (int k = 0; k < userSetArray.length; k++) {

                    if (i == userSetArray[k][0] && j == userSetArray[k][1]) {
                        boardLayout[i][j].comeAlive();
                    }
                }
            }
        }
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout.length; j++) {
                boardLayout[i][j].setNeighbours(boardLayout, i, j);
            }
        }
    }

    // this function returns how many neighbours of the current cell is alive


    //This function executes rules for each cell
    //create rule type object

    //function to do one iteration for all cells
    public int[][] generateNextLayout() {


        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout.length; j++) {
                // run rules for all cells excepting border
                nextGenerationLayout[i][j] = boardLayout[i][j].runRules();
            }
        }
        //set new boardlayout and forget old layout
        //boardLayout=nextGenerationLayout;
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout.length; j++) {
                // run rules for all cells excepting border

                if (nextGenerationLayout[i][j] == 1) {
                    boardLayout[i][j].comeAlive();
                } else {
                    boardLayout[i][j].die();
                }
            }
        }
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout.length; j++) {
                boardLayout[i][j].setNeighbours(boardLayout, i, j);
            }
        }


        return nextGenerationLayout;
    }

    //Display the initial state of the GameOfLife
    public int[][] getInitialState() {
        int temp[][] = new int[10][10];
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout.length; j++) {
                if (boardLayout[i][j].getState() == 1) {
                    temp[i][j] = 1;

                } else {
                    temp[i][j] = 0;
                }

            }
            //System.out.println();
        }

        return temp;
    }
}



