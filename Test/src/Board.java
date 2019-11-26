import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Board {

    private JPanel ui = null;
    public Cell chk[][]= new Cell[10][10];
    Board() {
        evolve();
    }

    public void evolve() {
        if (ui!=null) return;

        ui = new JPanel(new GridLayout(10,10,20,20));
        ui.setBorder(new EmptyBorder(4,4,4,4));
        for(int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
        chk[i][j]=new Cell();
            }
        }
        for(int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                chk[i][j].setAlive();
            }
        }
        for(int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {

                ui.add(chk[i][j]);
            }
        }

    }

    public void execute(){
        for(int i=1; i<9; i++) {
            for (int j=1; j<9; j++){
                shouldIDie(chk,i,j);
                comeAlive(chk,i,j);

            }}
        draw();
        run();
       // execute();
        }
    public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            //System.out.println(i);

    }
        public void draw(){
        ui.revalidate();
        ui.repaint();

        ui.removeAll();
        ui.invalidate();
        ui.repaint();
        ui.setBorder(new EmptyBorder(4,4,4,4));

        ui.revalidate();
        ui.repaint();
        ui.requestFocus();
        for(int ii=0; ii<10; ii++) {
            for (int jj=0; jj<10; jj++) {
                chk[ii][jj]=new Cell();
                ui.add(chk[ii][jj]);
            }
        }
    }


    public void shouldIDie(Cell cell[][], int i, int j){
        Rule rule=new Rule();
        if(rule.die( cell,i,j)){
            cell[i][j].die();
        }

    }
    public void comeAlive(Cell cell[][], int i, int j){
        Rule rule=new Rule();
        if(rule.birth( cell,i,j)){
            cell[i][j].setAlive();
        }

    }

public   JPanel getUI(){
        return ui;
}


    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.pack();
        //f.setMinimumSize(f.getSize());
        f.setPreferredSize(new Dimension(400, 300));

        f.setVisible(true);
        Board b=new Board();
        f.add(b.getUI());

        for(int i=0;i<100;i++) {
            b.execute();
        }

    }

}

class Cell extends JCheckBox{

    public boolean alive;

    public void die(){
        this.setSelected(false);
        alive=false;

    }
    public void setAlive(){
        this.setSelected(true);
        alive=true;

    }


    public boolean getCurrentState(){
        return this.isSelected();

    }
    //public void getNeighborInformation(){}

    public void setState(boolean state){
        this.setSelected(state);
        alive=state;
    }

}


class Rule{
    public boolean birth(Cell chk[][],int row, int column) {
        int flag = 0;

        if (chk[row][column + 1].isSelected()) {
            flag++;
        }
        if (chk[row][column - 1].isSelected()) {
            flag++;
        }
        if (chk[row + 1][column].isSelected()) {
            flag++;
        }
        if (chk[row - 1][column].isSelected()) {
            flag++;
        }
        if (flag > 2) {
            System.out.println(row+" "+column+" comes alive");
            return true;
        } else {
            return false;
        }
    }

    public boolean die(Cell chk[][],int row, int column){
        int flag=0;

        if(chk[row][(column+1)%10].isSelected()){
            flag++;
        }
        if(chk[row][((column-1)%10)].isSelected()){
            flag++;
        }
        if(chk[(row+1)%10][column].isSelected()){
            flag++;
        }
        if(chk[(row-1)%10][column].isSelected()){
            flag++;
        }
        if(flag<2 || flag>3){
            System.out.println(row+" "+column+" dies");
            return true;
        }else {
            return false;
        }
    }
    public boolean survive(Cell chk[][],int row, int column){
        int flag=0;

        if(chk[row][column+1].isSelected()){
            flag++;
        }
        if(chk[row][column-1].isSelected()){
            flag++;
        }
        if(chk[row+1][column].isSelected()){
            flag++;
        }
        if(chk[row-1][column].isSelected()){
            flag++;
        }
        if(flag<2){


            return true;
        }else {
            return false;
        }
    }



}
