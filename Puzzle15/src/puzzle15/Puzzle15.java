
package puzzle15;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Puzzle15 {
    static int a[];
   static  int blankPos=0;
    int pox=0;
    int poy=0;
   Puzzle15(){        
        a = new int[20];
        for(int i =0;i<16;i++){
            a[i] = i;
        }
    }  
   private  void shuffle() {
    int n =15;
    Random R = new Random();
    while (n > 1) {
        int r = R.nextInt(n--);
        int tmp = a[r];
        a[r] = a[n];
        a[n] = tmp;
  }
}
   
   private boolean isSolvable() {
    int countInversions = 0;
    
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < i; j++) {
        if (a[j] > a[i])
          countInversions++;
      }
    }
    
    return countInversions % 2 == 0;
  }
   
   private static boolean isSolved(){
       int i;
       for( i =0;i<16;i++){
           if(a[i] != i){
               break;
           }
       }
       if(i == 16){
           return  true;
       }else{
           return false;
       }
   }
   
   public static  int[] getPos(JButton [][] grid,String s){
       int pos[] = new int[2];int k=0;
       for(int i=0;i<4;i++){
           for(int j =0;j<4;j++){
               if((grid[i][j].getText().equals(s))){
                   pos[0]=i;pos[1]=j;
                 //  System.out.println(grid[i][j].getText());
          //         System.out.println(s);
            //       System.out.println("i"+i+" j "+j );
                   break;
               }
               k++;
           }
       }
       blankPos=k;
       return pos;
   }
   
    public static void main(String[] args) {
        Puzzle15 Game = new Puzzle15();
        do{
            Game.shuffle();
        }while(!Game.isSolvable());
        JFrame frame = new JFrame("JFrame Example");
        JButton [][] grid = new JButton[4][4];
        frame.setLayout(new GridLayout(4,4));
        
        for(int i = 0,k=0;i<4;i++){
            for(int j=0;j<4;j++,k++){
            if(Game.a[k] != 0)
                grid[i][j]=new JButton(""+Game.a[k]); //creates new button
            else{
                Game.blankPos=k;
                Game.pox=i;
                Game.poy=j;
                grid[i][j]=new JButton("");
            }
            frame.add(grid[i][j]);
            }
        }
        
       
        
        ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                String text = ((JButton) e.getSource()).getText();
                System.out.println(Game.pox+"   "+Game.poy);
                int temp = Game.blankPos;
                int locpos[] = new int[2];
                locpos =getPos(grid, text);
                grid[Game.pox][Game.poy].setText(text);
                System.out.println(locpos[0]+"   "+locpos[1]);
                grid[locpos[0]][locpos[1]].setText("");
                Game.pox=locpos[0];
                Game.poy=locpos[1];
                int temp1 =Game.blankPos;
                int t = Game.a[temp];
                int g= Game.a[temp1];
                Game.a[temp1]=t;
                Game.a[temp]=g;
                if(isSolved()){
                   JOptionPane.showMessageDialog(null, "Solved");
                }
            }
        }
    };
        
        for(int i =0;i<4;i++){
            for(int j =0;j<4;j++){
                grid[i][j].addActionListener(listener);
            }
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,600);
        frame.setVisible(true);
    }
    
}
    