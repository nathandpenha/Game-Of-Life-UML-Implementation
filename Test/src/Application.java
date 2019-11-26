import javax.swing.*;
import java.awt.*;
class Application extends Frame{
    Application(){
        Checkbox[][]chk =new Checkbox[10][100];
        JFrame jf=new JFrame();
       // add()
        for(int i=0;i<10;i++){

            for(int j=0;j<100;j++){
                chk[i][j]=new Checkbox();
                jf.add(chk[i][j]);
            }
        }


        setSize(300,300);//frame size 300 width and 300 height
        setLayout(null);//no layout manager
        setVisible(true);//now frame will be visible, by default not visible
    }
    public static void main(String args[]){
        Application f=new Application();
    }}