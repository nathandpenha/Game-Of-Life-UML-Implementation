package Game2;
import java.util.Random;

import javax.swing.*;
import java.awt.*;

public class Board {
    // Create array of type cells
    Cell boardLayout[][]=new Cell[10][10];


    //create temporary array to hold data for next generation of output cells
    Cell nextGen[][]=new Cell[10][10];



    public Board() {

        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout.length; j++) {
                //setting all cell states to dead
                boardLayout[i][j] = new Cell(0);
                boardLayout[i][j].setState(0);
                nextGen[i][j] = new Cell(0);

//                Random rand = new Random();
//                boardLayout[i][j].setState(rand.nextInt(2));

    //Setting initials cells alive
                if(i>2&&i<6&&j>2&&j<6){
//                    //i>2&&i<5&&j>2&&j<5 ignorei>2&&i<6&&j>2&&j<6 i>2&&i<4&&j>2 && j<4  i>2 && i<4 && j>2 && j<6
//                    //setting some cells to alive
                    boardLayout[i][j].setState(1);
                }

            }
        }
    }

    public Board(int userSetArray[][]) {

        for (int i = 0; i < userSetArray.length; i++) {


//            System.out.print(userSetArray[i][0]+"  ");
//            System.out.println(userSetArray[i][1]);
            }


                for (int i = 0; i < boardLayout.length; i++) {


            for (int j = 0; j < boardLayout.length; j++) {
                //setting all cell states to dead
                boardLayout[i][j] = new Cell(0);
                nextGen[i][j] = new Cell(0);

                for(int k=0;k<userSetArray.length;k++){

                        if(i==userSetArray[k][0]&&j==userSetArray[k][1]){
                            boardLayout[i][j].setState(1);
                        }

                }


                }

            }
        }

    // this function returns how many neighbours of the current cell is alive
        public int getNeighboursInformation(Cell cell, int x, int y) {

            int aliveNeighbours = 0;
            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <= 1; j++) {

                    //aliveNeighbours = aliveNeighbours + boardLayout[getMod(i + x,10)][getMod(y + j,10)].getState();
                    aliveNeighbours = aliveNeighbours + boardLayout[(i + x)][(y + j)].getState();
                }
        }

                aliveNeighbours =aliveNeighbours - boardLayout[x][y].getState();
            System.out.print(aliveNeighbours);
            return aliveNeighbours;
        }


    //This function executes rules for each cell
    //create rule type object
        public void runRules(Cell cell, int x, int y, int aliveNeighbours){

        Rule rule=new Rule();

         if(rule.birthRule(cell.getState(),aliveNeighbours)){
        //if birth rule is true then give life to cell
                nextGen[x][y].birth();
            }

       else if (rule.lonelyDeathRule(cell.getState(),aliveNeighbours)) {
           //if lonely cell is true then kill cell
           nextGen[x][y].die();
        }
        else if(rule.overPopulationRule(cell.getState(),aliveNeighbours)){
            //if cell is over populated then kill cell
            nextGen[x][y].die();
        }

        else if(rule.survive(cell.getState(),aliveNeighbours))
            {
                //if 2 or 3 neighbours are alive then let it be alive
            nextGen[x][y].setState(1);
        }

        }
        //function to do one iteration for all cells
        public Cell[][] execute (){
            for (int i = 1; i < boardLayout.length-1; i++) {
                System.out.println();
                for (int j = 1; j <boardLayout.length-1; j++) {
                    // run rules for all cells excepting border
                    runRules(boardLayout[i][j],i,j,getNeighboursInformation(boardLayout[i][j],i,j));

                }
            }

            System.out.println();  System.out.println();  System.out.println();
            //set new boardlayout and forget old layout
            //boardLayout=nextGen;
            for (int i = 1; i < boardLayout.length-1; i++) {
                for (int j = 1; j <boardLayout.length-1; j++) {
                    // run rules for all cells excepting border
                    boardLayout[i][j].setState(nextGen[i][j].getState());
                }
            }



                return boardLayout;
        }
        //Display the initial state of the board
        public int[][] firstStates(){
        int temp[][]=new int[10][10];
            for (int i = 0; i < boardLayout.length; i++) {
                for (int j = 0; j < boardLayout.length; j++) {
                    if(boardLayout[i][j].getState()==1)
                    {
                        temp[i][j]=1;

                    }
                    else{temp[i][j]=0;}

                }
                //System.out.println();
            }

            return temp;
        }
//If we need to implement circular rules, we need to use this function
//        public int getMod(int value, int modOf) {
//            if (value < 0) {
//
//                return value + modOf;
//            } else {
//                return value%modOf;
//            }
//        }







        }



