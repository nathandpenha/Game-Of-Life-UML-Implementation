package Game2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GamePanel extends JPanel
{
    //Setting Buttons on UI
    JButton buttons[][] = new JButton[10][10];

    //Using a GameOfLife
    GameOfLife gameOfLife=new GameOfLife();

    //Setting other buttons
    JButton start=new JButton("Next Step");
    JButton reset=new JButton("Reset");

    //Constructor for the screen
    public GamePanel()
    {
        setLayout(new GridLayout(11,10));
        initializeButtons();
    }

    public void initializeButtons()
    {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to set the first cells? Else we will generateNextLayout it." +
                "", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(dialogResult==JOptionPane.YES_OPTION) {
         try {
             int userSet = Integer.parseInt(JOptionPane.showInputDialog("Enter number of cells you want alive"));
             if (userSet > 0) {
                 int userSetArray[][] = new int[userSet][2];
                 for (int i = 0; i < userSet; i++) {
                     userSetArray[i][0] = Integer.parseInt(JOptionPane.showInputDialog("For Alive Cell " + (i + 1) + " Enter the Row Number (between 1 to 10)")) - 1;
                     userSetArray[i][1] = Integer.parseInt(JOptionPane.showInputDialog("For Alive Cell " + (i + 1) + " Enter the Column Number (between 1 to 10)")) - 1;
                 }


                 gameOfLife = new GameOfLife(userSetArray);

             }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null," You entered an invalid value ");
         }
        }
        int firstState[][]=gameOfLife.getInitialState();// get initial states from the GameOfLife
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();


                if(firstState[i][j]==1){
                    //if the cell is alive

                    buttons[i][j].setBackground(Color.WHITE);

                }else {
                    //if the cell is dead

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
                gameOfLife=new GameOfLife();
                int firstState[][]=gameOfLife.getInitialState();
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(firstState[i][j]==1){
                            buttons[i][j].setBackground(Color.WHITE);

                        }else {
                            buttons[i][j].setBackground(Color.BLACK);
                        }
                    }
                }
            }else{

                int[][] cell;
                cell = gameOfLife.generateNextLayout();
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (cell[i][j]== 1) {
                            buttons[i][j].setBackground(Color.WHITE);
                        } else {
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
        window.getContentPane().add(new GamePanel());
        window.setBounds(300,200,300,300);
        window.setVisible(true);
    }
}