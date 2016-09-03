package com.plateauu.mycalc.domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalcView extends JFrame {

	/**
	 * Simple calculator code
	 */

	private static final long serialVersionUID = 2880283256149273424L;
	private JButton buttons[] = new JButton[14];
	private JPanel panel;
	private JTextField result;
	private Actions operation = Actions.Other;
	private int actualEquationValue;
	private StringBuilder newNumber;
	private int previousNumber;
	private int actualNumber;
	private boolean cleaningFlash;

	public CalcView() {
		actualEquationValue = 0;
		cleaningFlash = true;
		newNumber = new StringBuilder();

		this.setTitle("MyCalc");
		this.setSize(150, 210);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		this.getContentPane().add(panel);
		result = new JTextField(10);
		result.setEditable(false);

		result.setText(Integer.toString(actualEquationValue));
		panel.add(result);

		for (int i = 1; i < 10; i++) {
			buttons[i] = new JButton(Integer.toString(i));
			panel.add(buttons[i]);
			System.out.println(i + " Button added");

		}

		buttons[0] = new JButton(Integer.toString(0));
		panel.add(buttons[0]);

		buttons[1].addActionListener(new OneListener());
		buttons[2].addActionListener(new TwoListener());
		buttons[3].addActionListener(new ThreeListener());
		buttons[4].addActionListener(new FourListener());
		buttons[5].addActionListener(new FiveListener());
		buttons[6].addActionListener(new SixListener());
		buttons[7].addActionListener(new SevenListener());
		buttons[8].addActionListener(new EightListener());
		buttons[9].addActionListener(new NineListener());
		buttons[0].addActionListener(new ZeroListener());

		JButton add = new JButton("+");
		JButton minus = new JButton("-");
		JButton divide = new JButton("\\");
		JButton multiply = new JButton("*");
		JButton eq = new JButton("=");

		add.addActionListener(new AddButtonListener());
		minus.addActionListener(new MinusButtonListener());
		multiply.addActionListener(new MultiplyButtonListener());
		divide.addActionListener(new DivideButtonListener());
		eq.addActionListener(new ResultButtonListener());

		panel.add(add);
		panel.add(minus);
		panel.add(divide);
		panel.add(multiply);
		panel.add(eq);

		this.setVisible(true);

	}

	public void listener(int digit) {

		String tempStringResult;

		if (!cleaningFlash) {
			tempStringResult = result.getText();
		} else {
			tempStringResult = "";
		}

		if (!cleaningFlash) {
			result.setText(tempStringResult + Integer.toString(digit));
			newNumber.append(digit);
		} else {
			result.setText(Integer.toString(digit));
			newNumber.append(digit);
			cleaningFlash = false;
		}
	}

	public int addCalculation(int actualNumber, int previousNumber) {
		result.setText(Integer.toString(previousNumber + actualNumber));
		return previousNumber + actualNumber;
	}

	public int minusCalculation(int actualNumber, int previousNumber) {
		result.setText(Integer.toString(previousNumber - actualNumber));
		return previousNumber - actualNumber;
	}

	public int multiplyCalculation(int actualNumber, int previousNumber) {
		result.setText(Integer.toString(previousNumber * actualNumber));
		return previousNumber * actualNumber;
	}

	public int divideCalculation(int actualNumber, int previousNumber) {
		if (actualNumber == 0) {
			result.setText(Integer.toString(0));
			return 0;
		} else {
			result.setText(Integer.toString(previousNumber / actualNumber));
			return previousNumber / actualNumber;
		}
	}

	public int showResult(int actualNumber, int previousNumber) {

		switch (operation) {
		case Add:
			actualEquationValue = addCalculation(actualNumber, previousNumber);
			result.setText(Integer.toString(actualEquationValue));
			break;
		case Minus:
			actualEquationValue = minusCalculation(actualNumber, previousNumber);
			result.setText(Integer.toString(actualEquationValue));
			break;
		case Multiply:
			actualEquationValue = multiplyCalculation(actualNumber,
					previousNumber);
			result.setText(Integer.toString(actualEquationValue));
			break;
		case Divide:
			actualEquationValue = divideCalculation(actualNumber,
					previousNumber);
			result.setText(Integer.toString(actualEquationValue));
			break;
		default:
			break;
		}

		cleaningFlash = true;

		return actualEquationValue;
	}

	public class ZeroListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			listener(0);
		}
	}

	public class OneListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			listener(1);
		}
	}

	public class TwoListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			listener(2);
		}
	}

	public class ThreeListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			listener(3);
		}
	}

	public class FourListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			listener(4);
		}
	}

	public class FiveListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			listener(5);
		}
	}

	public class SixListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			listener(6);
		}
	}

	public class SevenListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			listener(7);
		}
	}

	public class EightListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			listener(8);
		}
	}

	public class NineListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			listener(9);
		}
	}

	public class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			result.setText(result.getText() + "+");
			actualNumber = Integer.parseInt(newNumber.toString());
			newNumber = new StringBuilder();
			operation = Actions.Add;
		}
	}

	public class MinusButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			result.setText(result.getText() + "-");
			actualNumber = Integer.parseInt(newNumber.toString());
			newNumber = new StringBuilder();
			operation = Actions.Minus;
		}
	}

	public class MultiplyButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			result.setText(result.getText() + "*");
			actualNumber = Integer.parseInt(newNumber.toString());
			newNumber = new StringBuilder();
			operation = Actions.Multiply;
		}
	}

	public class DivideButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			result.setText(result.getText() + "/");
			actualNumber = Integer.parseInt(newNumber.toString());
			newNumber = new StringBuilder();
			operation = Actions.Divide;
		}
	}

	public class ResultButtonListener implements ActionListener {

		private boolean firstOperation = true;

		@Override
		public void actionPerformed(ActionEvent ae) {

			if (firstOperation) {
				previousNumber = actualNumber;
			}

			actualNumber = Integer.parseInt(newNumber.toString());
			previousNumber = showResult(actualNumber, previousNumber);
			firstOperation = false;

		}
	}
}