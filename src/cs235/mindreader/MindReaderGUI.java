

package cs235.mindreader;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;


public class MindReaderGUI {

    public static void main(String[] params) {

	MindReader model = Factory.createMindReader();
	model.loadPlayerProfile("profile.txt");
	View view = new View(model);

    }

}

class View extends JFrame {

    private MindReader model;

    private JLabel resultLabel1;
    private JLabel resultLabel2;
    private JLabel resultLabel3;
    private JTextField playerField;
    private JTextField mindReaderField;

    public View(MindReader model) {
	super("Mind Reader");

	this.model = model;

	JPanel mainPanel;
	mainPanel = new MainPanel();
	setContentPane(mainPanel);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	addWindowListener(
	  new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
		  saveProfile();
		  System.exit(0);
	      }
	  }
			  );

	pack();
	setVisible(true);

    }

    public void saveProfile() {
	model.savePlayerProfile("profile.txt");
    }

    public void updateView(String choice) {

	String prediction = model.getPrediction();
	model.makeChoice(choice);

	resultLabel1.setText("My prediction: " + prediction + ".");
	resultLabel2.setText("Your choice: " + choice + ".");

	if (prediction.equals(choice))
	    resultLabel3.setText("I win!");
	else
	    resultLabel3.setText("You win!");

	playerField.setText("" + model.getPlayerScore());
	mindReaderField.setText("" + model.getMindReaderScore());

    }

    class MainPanel extends JPanel {

	public MainPanel() {

	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    add(new ScorePanel());
	    add(new ResultPanel());
	    add(new ButtonPanel());

	}

    }

    class ResultPanel extends JPanel {

	public ResultPanel() {

	    JLabel resultLabel;
	    JLabel resultLabel4;

	    resultLabel = new JLabel(" ");
	    resultLabel1 = new JLabel("I've predicted your choice.");
	    resultLabel2 = new JLabel("Please choose heads or tails.");
	    resultLabel3 = new JLabel(" ");
	    resultLabel4 = new JLabel(" ");

	    resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    resultLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
	    resultLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
	    resultLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
	    resultLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);

	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    add(resultLabel);
	    add(resultLabel1);
	    add(resultLabel2);
	    add(resultLabel3);
	    add(resultLabel4);

	}

    }

    class ScorePanel extends JPanel {

	public ScorePanel() {

	    JLabel playerLabel;
	    JLabel mindReaderLabel;

	    final int fieldSize = 3;

	    playerLabel = new JLabel("Your Score: ");
	    playerField = new JTextField(fieldSize);
	    mindReaderLabel = new JLabel("My Score: ");
	    mindReaderField = new JTextField(fieldSize);

	    playerField.setText("0");
	    mindReaderField.setText("0");

	    add(playerLabel);
	    add(playerField);
	    add(mindReaderLabel);
	    add(mindReaderField);

	}

    }

    class ButtonPanel extends JPanel {

	public ButtonPanel() {

	    JButton headsButton;
	    JButton tailsButton;

	    headsButton = new JButton("Heads");
	    headsButton.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			updateView("heads");
		    }
		}
					 );

	    tailsButton = new JButton("Tails");
	    tailsButton.addActionListener(
		new ActionListener() {
		    public void actionPerformed( ActionEvent e ) {
			updateView("tails");
		    }
		}
					 );

	    add(headsButton);
	    add(tailsButton);
    
	}
    }

}

