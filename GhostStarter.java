import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import java.util.Random;
import java.io.IOException;

public class GhostStarter extends JFrame implements ActionListener,KeyListener
{
	private static final String COMPUTER_TURN="Computer's Turn";
	private static final String USER_TURN="Your Turn";
	private Connect dictionary;
	private String f;
	Random rndm=new Random();
	
//*************Coding for UI*****************

	
	//Lable all word and other for status
	
	JLabel status=new JLabel("");
	JLabel spelling=new JLabel("");	
	
	JTextField text=new JTextField(10);
	JButton ok=new JButton("Start");
	JButton challange=new JButton("Challange");
	JButton reset=new JButton(" Reset ");
	
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		
	public GhostStarter()
	{
		
		
		super("Ghost Starter");
		setSize(400,400);
		setVisible(true);
		setResizable(true);
		
		Container con=getContentPane();
		
		
		con.add(p1);
		con.add(p2);
		con.add(p3);
		con.add(p4);
		con.setLayout(new FlowLayout());
		p1.add(spelling);
		p2.add(text);
		p2.add(status);
		p3.add(ok);
		p3.add(challange);
		p3.add(reset);
		
		text.addKeyListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ok.addActionListener(this);
		challange.addActionListener(this);
		reset.addActionListener(this); 
		try
		{
			dictionary=new Connect();
			//System.out.println(dictionary.etime-dictionary.stime);
		}
		catch(IOException e)
		{
			
		}
	}
//***********************************************************
	
	
	public void actionPerformed(ActionEvent e)
	{
		Object source=e.getSource();
		
		if(source==ok)
		{
			spelling.setText("");
			text.setText("");
			start();
			
			//text.enable(false);
			/*String temp_spell=spelling.getText();
			temp_spell+=text.getText().charAt(0);
			spelling.setText(temp_spell);
			text.setText("");
			computerTurn();*/
		}
		
		if(source==challange)
		{
			challange();
		}
		
		if(source==reset)
		{
			spelling.setText("");
			text.setText("");
			start();
			
		}
	}
	
	public void start()
	{
		
		boolean ut=rndm.nextBoolean();
		
		if(ut)
		{
			f="u";
			status.setText(USER_TURN);
		}
		else
		{
			status.setText(COMPUTER_TURN);
			f="c";
			computerTurn();
		}
		
	}
	
	public void computerTurn()
	{
		String temp_text=spelling.getText();
		
		if(temp_text.length() >= 4 && dictionary.isWord(temp_text))
		{
			status.setText("Computer Win!");
		}
		else
		{
			String word=dictionary.getChar(temp_text);
			if(word==null && f=="u")
			{
				f="u";
				status.setText("This word not in dictionary");
			}
			else
			{
				if(f=="u")
				{
					temp_text=temp_text+word.charAt(temp_text.length());
					spelling.setText(temp_text);
				}
				else
				{
					f="u";
					final String alphabet="abcdefghijklmnopqrstuvwxyz";
					int i=rndm.nextInt(26);
					String firstChar=Character.toString(alphabet.charAt(i));
					
					spelling.setText(firstChar.trim());
				}
				status.setText(USER_TURN);
		
			}
		}
		//userTurn=true;
		//status.setText(USER_TURN);
	}
	
	public void restart()
	{
		start();
	}
	
	
	public void challange()
	{
		String text=spelling.getText();
		
		if(text.length() >= 4 && dictionary.isWord(text))
		{
			status.setText("You Win!");
		}
		else
		{
			String word=dictionary.getChar(text);
			
			if(word!=null)
			{
				status.setText("Computer win!");
			}
			else
			{
				status.setText("This word not in dictionary");
			}
		}
	}
	
	public void keyPressed(KeyEvent e) throws StringIndexOutOfBoundsException{
		// TODO Auto-generated method stub

		String temp=""+e.getKeyChar();
		if(!Character.isAlphabetic(e.getKeyChar()))
		{
			JOptionPane.showMessageDialog(null,"Enter an alphabet");
			text.setText("");
			return;
		}
		
			
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//text.setText("");
		String temp_text=spelling.getText();
		temp_text+=text.getText();
		spelling.setText(temp_text);
		text.setText("");
		computerTurn();
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
			
	}
	
	public static void main(String[] args)
	{
		GhostStarter gs=new GhostStarter();
	}

}