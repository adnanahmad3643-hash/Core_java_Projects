package com.Calculater;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

 class MyFram implements ActionListener {

    Frame f;
    TextField t1, t2, t3;
    Label lb1, lb2, lb3;
    Button add, sub, mul, div;

    MyFram() {

        f = new Frame("AWT Calculator");

        lb1 = new Label("First Number");
        lb2 = new Label("Second Number");
        lb3 = new Label("Result");

        t1 = new TextField();
        t2 = new TextField();
        t3 = new TextField();

        add = new Button("Add");
        sub = new Button("Sub");
        mul = new Button("Mul");
        div = new Button("Div");

        f.setSize(450, 350);
        f.setLayout(null);

        lb1.setBounds(30, 50, 100, 25);
        t1.setBounds(150, 50, 120, 25);

        lb2.setBounds(30, 90, 100, 25);
        t2.setBounds(150, 90, 120, 25);

        lb3.setBounds(30, 130, 100, 25);
        t3.setBounds(150, 130, 120, 25);

        add.setBounds(30, 180, 80, 30);
        sub.setBounds(120, 180, 80, 30);
        mul.setBounds(210, 180, 80, 30);
        div.setBounds(300, 180, 80, 30);

        f.add(lb1);  f.add(t1);
        f.add(lb2);  f.add(t2);
        f.add(lb3);  f.add(t3);

        f.add(add);
        f.add(sub);
        f.add(mul);
        f.add(div);

        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                f.dispose();
            }
        });

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int a = Integer.parseInt(t1.getText());
            int b = Integer.parseInt(t2.getText());
            int result = 0;

            if (e.getSource() == add)
                result = a + b;
            else if (e.getSource() == sub)
                result = a - b;
            else if (e.getSource() == mul)
                result = a * b;
            else if (e.getSource() == div) {
                if (b == 0) {
                    t3.setText("Cannot divide by zero");
                    return;
                }
                result = a / b;
            }

            t3.setText(String.valueOf(result));
        }
        catch (Exception ex) {
            t3.setText("Invalid Input");
        }
    }

    public static void main(String[] args) {
        new MyFram();
    }

	
}
