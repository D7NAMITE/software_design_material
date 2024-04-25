package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import objects.*;

public class Window extends JFrame {

	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private DrawingBoard drawPanel = new DrawingBoard();

	private JTextField fieldX = new InputField("400");
	private JTextField fieldY = new InputField("300");
	private JTextField fieldWidth = new InputField("50");
	private JTextField fieldHeight = new InputField("50");

	private JButton ovalButton = new JButton("Create Oval");
	private JButton rectButton = new JButton("Create Rect");
	private JButton groupAllButton = new JButton("Group All");
	private JButton deleteButton = new JButton("Delete");
	private JButton clearButton = new JButton("Clear All");

	public Window() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		initComponents();
		pack();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		add(drawPanel);

		topPanel.setLayout(new FlowLayout());
		add(topPanel, BorderLayout.NORTH);
		topPanel.add(new JLabel("x: "));
		topPanel.add(fieldX);
		topPanel.add(new JLabel("y: "));
		topPanel.add(fieldY);
		topPanel.add(new JLabel("width: "));
		topPanel.add(fieldWidth);
		topPanel.add(new JLabel("height: "));
		topPanel.add(fieldHeight);
		topPanel.add(ovalButton);
		topPanel.add(rectButton);

		bottomPanel.setLayout(new FlowLayout());
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.add(groupAllButton);
		bottomPanel.add(deleteButton);
		bottomPanel.add(clearButton);

		initButtons();
	}

	private void initButtons() {
		ovalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addOval();
			}
		});
		rectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addRectangle();
			}
		});
		groupAllButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.groupAll();
			}
		});
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.deleteSelected();
			}
		});
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.clear();
			}
		});
	}

	private void addRectangle() {
		int x = Integer.parseInt(fieldX.getText());
		int y = Integer.parseInt(fieldY.getText());
		int w = Integer.parseInt(fieldWidth.getText());
		int h = Integer.parseInt(fieldHeight.getText());
		/* A good starting point */
		drawPanel.addGObject(new Rect(x, y, w, h, randomColor()));
	}

	private void addOval() {
		int x = Integer.parseInt(fieldX.getText());
		int y = Integer.parseInt(fieldY.getText());
		int w = Integer.parseInt(fieldWidth.getText());
		int h = Integer.parseInt(fieldHeight.getText());
		/* A good starting point */
		drawPanel.addGObject(new Oval(x, y, w, h, randomColor()));
	}

	private Color randomColor() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		return new Color(r, g, b);
	}

	class InputField extends JTextField {

		public InputField(String text) {
			super(text);
			setPreferredSize(new Dimension(50, 20));
		}

	}
	
	public static void main(String[] args) {
		Window window = new Window();
		window.setVisible(true);
	}

}
