import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel MenuPanel = new JPanel();
	private JPanel WordPanel = new JPanel();
	private JPanel LetterPanel = new JPanel();
	private JPanel ImagePanel = new JPanel();

	private Data data;
	private JLabel score,lives,difficulty_banner;
	private JButton NewGame;
	private JRadioButton easy,medium,hard;
	private ButtonGroup difficulty;
	
	private ImageIcon image;
	
	private TheWord newWord;
	private JLabel word,imageLabel;
	private JButton Key_A,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,
	Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�,Key_�;
	
	Load load;
	Save save;
	DataBase database;
	
	public GUI(){
		super("HangMan!");
		
		//Setting the Layouts
		mainPanel = new JPanel(new BorderLayout());
		MenuPanel.setLayout(new BoxLayout(MenuPanel,BoxLayout.Y_AXIS));
		LetterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		WordPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ImagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Loading
		load = new Load();
		if(load.flag == false)
			data = new Data(2);
		else
			data = load.getData();
		
		// Image GUI
		ImagePanel = new JPanel(new FlowLayout());
		image = new ImageIcon(imageReturn(data.getLives_left(),data.getDifficulty()));
	    imageLabel = new JLabel(image);
        ImagePanel.add(imageLabel);

		//Score, lives, Banner GUI
		score= new JLabel("Score: " + data.getPlayer_score() + " - " + data.getComputer_score());
		score.setSize(getSize());
		lives = new JLabel("Lives Left: " + data.getLives_left());
		difficulty_banner = new JLabel("Set Difficulty");
		MenuPanel.add(score);
		MenuPanel.add(lives);
		MenuPanel.add(difficulty_banner);
		
		// Difficulty GUI
		if(data.getDifficulty() == 1){
			easy = new JRadioButton("Easy",true);
			medium = new JRadioButton("Medium",false);
			hard = new JRadioButton("Hard",false);
		}
		else if (data.getDifficulty() == 2){
			easy = new JRadioButton("Easy",false);
			medium = new JRadioButton("Medium",true);
			hard = new JRadioButton("Hard",false);
		}

		else{
			easy = new JRadioButton("Easy",false);
			medium = new JRadioButton("Medium",false);
			hard = new JRadioButton("Hard",true);
		}
		
		difficulty = new ButtonGroup();
		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);
		
		// Difficulty Buttons
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);

		MenuPanel.add(easy);
		MenuPanel.add(medium);
		MenuPanel.add(hard);
		
		//New Game GUI
		
		NewGame = new JButton("New Game!");
		NewGame.addActionListener(this);
		MenuPanel.add(NewGame);
		
		//Word GUI
		database = new DataBase();
		newWord = new TheWord(database.randomWord());
		word = new JLabel();
		word.setText(newWord.getTheCurrentWord());
		word.setFont(new Font("Arial",1,18));
		WordPanel.add(word);
		
		//Alphabet Buttons
		alphabetButtons();
		
		//Layouts Organization
		mainPanel.add(MenuPanel, BorderLayout.EAST);
		mainPanel.add(WordPanel,BorderLayout.NORTH);
		mainPanel.add(LetterPanel,BorderLayout.CENTER);
		mainPanel.add(ImagePanel,BorderLayout.WEST);
		
		this.setContentPane(mainPanel);
		this.setSize(725, 350);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Difficulty Buttons
		if(e.getSource().equals(easy)){
			 data.modifyData(1);
			 newWord = new TheWord(database.randomWord());
			 save = new Save(data);
			 buttonReset();	 
		}
		if(e.getSource().equals(medium)){
			 data.modifyData(2);
			 newWord = new TheWord(database.randomWord());
			 save = new Save(data);
			 buttonReset();	 
		}
		if(e.getSource().equals(hard)){
			 data.modifyData(3);
			 save = new Save(data);
			 newWord = new TheWord(database.randomWord());
			 buttonReset();	 
		}
		
		//New Game Button
		
		if(e.getSource().equals(NewGame))
		{
			data.newGameData(data.getDifficulty());
			newWord = new TheWord(database.randomWord());
			save = new Save(data);
			buttonReset();
		}
		//


		// Alphabet Buttons
		
		if(e.getSource().getClass().equals(JButton.class) && !e.getSource().equals(NewGame)){
			if(newWord.searchLetter(((JButton) e.getSource()).getText(), data))
				word.setText(newWord.getTheCurrentWord());
			((JButton) e.getSource()).setEnabled(false);
			if(newWord.ifWon(data)){
				data.wonData(data.getDifficulty());
				save = new Save(data);
				JOptionPane.showMessageDialog(null,"You Won!");
				newWord = new TheWord(database.randomWord());
				buttonReset();
			}
			else
			{
				if(data.ifLost()){
					data.setLives_left(0);
					newWord.showWord();
					image = new ImageIcon(imageReturn(data.getLives_left(),data.getDifficulty()));
					imageLabel.setIcon(image);
					data.lostData(data.getDifficulty());
					save = new Save(data);
					JOptionPane.showMessageDialog(null,"You Lost!\n The word was: " + newWord.getTheCurrentWord());
					newWord = new TheWord(database.randomWord());
					buttonReset();
				}
			}
			lives.setText("Lives Left: " + data.getLives_left());

		}
		
		//Always do
		word.setText(newWord.getTheCurrentWord());
		score.setText("Score: " + data.getPlayer_score() + " - " + data.getComputer_score());
		lives.setText("Lives Left: " + data.getLives_left());
		image = new ImageIcon(imageReturn(data.getLives_left(),data.getDifficulty()));
		imageLabel.setIcon(image);
	}
	
	
	//Image Chooser
	public String imageReturn(int lives_left, int difficulty){
		if(difficulty == 1){
			switch (lives_left) 
			{
				case 0:  return ("res/8.jpg");
		        case 1:  return ("res/7.jpg");
		        case 2:  return ("res/6.jpg");
		        case 3:  return ("res/5.jpg");
		        case 4:  return ("res/4.jpg");
		        case 5:  return ("res/3.jpg");
		        case 6:  return ("res/2.jpg");
		        case 7:  return ("res/1.jpg");
		        case 8:  return ("res/0.jpg");
		    }
	     }
		else if(difficulty == 2)
		{
			switch (lives_left) 
			{
				case 0:  return ("res/8.jpg");
		        case 1:  return ("res/7.jpg");
		        case 2:  return ("res/6.jpg");
		        case 3:  return ("res/4.jpg");
		        case 4:  return ("res/2.jpg");
		        case 5:  return ("res/1.jpg");
		        case 6:  return ("res/0.jpg");

		    }
		}
		else
		{
			switch (lives_left) 
			{
				case 0:  return ("res/8.jpg");
		        case 1:  return ("res/6.jpg");
		        case 2:  return ("res/4.jpg");
		        case 3:  return ("res/2.jpg");
		        case 4:  return ("res/1.jpg");
		    }
		}
		return "res/0.jpg";
	}
	
	// Enabling Buttons
	public void buttonReset(){
		Key_A.setEnabled(true);
		Key_A.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
		Key_�.setEnabled(true);
	}
	
	//Creating Alphabet Buttons
	public void alphabetButtons(){
		Key_A = new JButton("�"); LetterPanel.add(Key_A);
		Key_A.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
		Key_� = new JButton("�"); LetterPanel.add(Key_�);
		Key_�.addActionListener(this);
	}
}
