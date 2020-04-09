import java.awt.Color;
import java.util.*;
import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame obj= new JFrame();
		Gameplay gp= new Gameplay();
		
		obj.setBounds(10, 10, 905, 700);
		obj.setBackground(Color.GRAY);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gp);

	}

}
