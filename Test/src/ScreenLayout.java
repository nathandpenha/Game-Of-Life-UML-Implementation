package Game2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ScreenLayout extends JPanel
{
    //Setting Buttons on UI
    JButton buttons[][] = new JButton[10][10];

    //Using a board
    Board board=new Board();

    //Setting other buttons
    JButton start=new JButton("Next Step");
    JButton reset=new JButton("Reset");

    //Constructor for the screen
    public ScreenLayout()
    {
        setLayout(new GridLayout(11,10));
        initializeButtons();
    }

    public void initializeButtons()
    {  int firstState[][]=board.firstStates();// get initial states from the board
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();


                if(firstState[i][j]==1){
                    //if the cell is alive
                    buttons[i][j].setText("X");
                    buttons[i][j].setBackground(Color.WHITE);

                }else {
                    //if the cell is dead
                    buttons[i][j].setText("O");
                    buttons[i][j].setBackground(Color.BLACK);

                }

                add(buttons[i][j]); //add button to screen

            }
        }
        add(start);
        start.addActionListener(new buttonListener());
        add(reset);
        reset.addActionListener(new buttonListener());
    }

    private class buttonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e) {

            JButton buttonClicked = (JButton) e.getSource(); //get the particular button that was clicked

            if (buttonClicked.getText( )== "Reset") {
                board=new Board();
                int firstState[][]=board.firstStates();

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(firstState[i][j]==1){
                            buttons[i][j].setText("X");
                            buttons[i][j].setBackground(Color.WHITE);

                        }else {


                            buttons[i][j].setText("O");
                            buttons[i][j].setBackground(Color.BLACK);

                        }


                    }
                }
            }else{

                Cell[][] cell;
                cell = board.execute();
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (cell[i][j].getState() == 1) {

                            buttons[i][j].setText("X");
                            buttons[i][j].setBackground(Color.WHITE);

                        } else {
                            buttons[i][j].setText("O");
                            buttons[i][j].setBackground(Color.BLACK);

                        }
                    }
                }
            }
        }




    }

    public static void main(String[] args)
    {
        JFrame window = new JFrame("Game Of Life");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new ScreenLayout());
        window.setBounds(300,200,300,300);
        window.setVisible(true);
    }
}