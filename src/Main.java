import javax.swing.*;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

//Main was created by Marcus
//GUI portion was created by Ivan and Bernard

public class Main extends JFrame implements ActionListener {
    static boolean flag = false;
    //Main GUI
    private static JPanel inputPanel;
    private static JFrame inputFrame;
    private static JButton displayButton;
    private static JButton clearButton;
    private static JTextField fileName;
    private static JLabel fileLabel;
    private static JLabel titleLabel;
    private static String CurrentLine;
    private static String file, newFileName;
    private static JButton writeFileButton;

    //Input GUI
    //Main GUI
    private static JPanel inputPanel2;
    private static JTextArea pane2;

    //Output GUI
    private static JPanel outputPanel;
    private static JFrame outputFrame;
    private static JTextArea pane;

    //Combines 2 GUI
    private static JPanel container;

    public static void main(String[] args) throws IOException {
    	
    	//GUI implementation for input Created by Bernard----------------------------------------------------------
    	//B-- = Bernard's modifications
        //----- = Ivan's Modification
        container = new JPanel();//-----
        container.setLayout(new BoxLayout(container,BoxLayout.X_AXIS));//-----
        inputPanel = new JPanel();
        container.add(inputPanel);//----
        
        inputFrame = new JFrame(); //B-- Initializes the input display Frame
        inputFrame.setSize(1920, 1080); //B-- Configures the input Frame size
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//B-- Configures the input Frame closing protocal 
        inputFrame.add(container); //----
        inputPanel.setLayout(null); //B-- Configures the Layout of the First input Panel


        titleLabel = new JLabel("Java-to->Python"); //B-- Initializes a Header label
        titleLabel.setBounds(100, 20, 350, 20); //B-- Configures the Header ladel bounds 
        inputPanel.add(titleLabel); //B-- Adds the title label to the input Panel

        displayButton = new JButton("GO"); //B-- Initializes a Button to display results upon being clicked
        displayButton.setBounds(50, 120, 100, 25); //B-- Configures the bounds for the display button
        displayButton.addActionListener(new Main()); //B-- Adds an Action Listener to the display button 
        inputPanel.add(displayButton); //B-- Adds the display button to the input Panel

        //Button that removes the previously shown input and output panels
        clearButton = new JButton("CLEAR");
        clearButton.setBounds(150, 120, 100, 25);
        clearButton.addActionListener(new Main());
        inputPanel.add(clearButton);

        fileLabel = new JLabel("Which file would you like to translate?"); //B-- Initializes a prompting label. Prompts users for file to translate.
        fileLabel.setBounds(40, 60, 300, 25); //B-- Configures the file prompt label bounds
        inputPanel.add(fileLabel); //B-- Adds the file prompt label to the input Panel
        
        fileName = new JTextField(20); //B-- Initializes the text field for the user to select a file name
        fileName.setBounds(50, 90, 200, 25); //B-- Configures the input text field bounds
        fileName.addActionListener(new Main()); //Adds on Action Listener to the the text field
        inputPanel.add(fileName); //B-- Adds the file text field to the input Panel

        inputFrame.setVisible(true); //B-- Configures the input Frame to be visible

    }

    public void InitiateTranslation() throws IOException{
        ArrayList<Word> Words;
        Input in=new Input();
        Words = in.Read(newFileName);

        Output out = new Output();
        out.Translate(Words);
        DisplayInput();
        DisplayOutput();
        Words.clear();


    }
    public void DisplayInput()throws IOException{
        //GUI implementation for output Created by Ivan----------------------------------------------------------
        outputPanel = new JPanel();
        outputFrame = new JFrame();
        container.add(outputPanel);//----

        outputPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        outputPanel.setLayout(new GridLayout(0,1));
        outputFrame.add(container, BorderLayout.CENTER);

        pane = new JTextArea();

        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setBold(set, true);
        outputPanel.add(pane);
        try {
            file = fileName.getText();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            pane.read(reader, null);
            reader.close();

            pane.requestFocus();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        outputFrame.setSize(1920, 1080);
        outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        outputFrame.setTitle("Input In Python");

        outputFrame.setVisible(true);
    }

    public void DisplayOutput()throws IOException{
        flag = true;
        //GUI implementation for output Created by Ivan----------------------------------------------------------
        inputPanel2 = new JPanel();
        container.add(inputPanel2);//----

        inputPanel2.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        inputPanel2.setLayout(new GridLayout(0,1));

        pane2 = new JTextArea();

        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setBold(set, true);
        inputPanel2.add(pane2);
        try {

            BufferedReader reader = new BufferedReader(new FileReader("Output.py"));
            pane2.read(reader, null);
            reader.close();

            pane2.requestFocus();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == displayButton){
            if(flag == true) {
                container.remove(inputPanel2);
                container.remove(outputPanel);
                container.revalidate();
                container.repaint();
                outputFrame.dispose();
            }
            //Assign the User Input to a String
            file = fileName.getText();

            File FileCheck = new File(file);

            if (file.length() > 0 && file.contains(".java") && e.getSource() == displayButton) {
                //Assign the User Input to a new string to be printed & disposes of input frame
                newFileName = file;

                if (FileCheck.exists()){
                    try {
                        InitiateTranslation();
                        inputFrame.dispose();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "That File does not Exist, Try Again.");
                }
            }

        }
        //Deletes any panels from previous translations
        else if (e.getSource() == clearButton){
            container.remove(inputPanel2);
            container.remove(outputPanel);
            container.revalidate();
            container.repaint();
        }

        }

    }



