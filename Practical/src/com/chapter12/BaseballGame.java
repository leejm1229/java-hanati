package com.chapter12;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BaseballGame extends JFrame {

    private JPanel panel;
    private JLabel resultLabel;
    private JTextField inputField;
    private JButton submitButton;
    private JTextArea historyArea;
    
    public BaseballGame() {
        initUI();
    }
    
    private void initUI() {
        setTitle("Baseball Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        resultLabel = new JLabel("숫자를 입력하세요 (1~9):");
        panel.add(resultLabel, BorderLayout.NORTH);
        
        inputField = new JTextField();
        panel.add(inputField, BorderLayout.CENTER);
        
        submitButton = new JButton("확인");
        submitButton.addActionListener(new SubmitListener());
        panel.add(submitButton, BorderLayout.EAST);
        
        historyArea = new JTextArea(10, 30);
        panel.add(historyArea, BorderLayout.SOUTH);
        
        add(panel);
        pack();
        setVisible(true);
    }
    
    private class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String guess = inputField.getText();
            // guess를 이용하여 게임 로직 구현
            // 결과를 resultLabel과 historyArea에 출력
        }
    }
    
    public static void main(String[] args) {
        BaseballGame game = new BaseballGame();
    }
    
}
