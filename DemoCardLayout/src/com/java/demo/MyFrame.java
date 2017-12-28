/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.demo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author pc
 */
public class MyFrame extends JFrame{

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JLabel lblTime;
    public MyFrame() {
        super("Cardlayout Demo");
        
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initLayout();
        this.setVisible(true);
    }
    
    public void initLayout() {
        this.setLayout(new BorderLayout());
        // tao card panel
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        
        cardPanel.setLayout(cardLayout);
        
        // tao ra cac la bai - card
        JPanel card1 = new JPanel();
        card1.add(new JLabel("HELLO"));
        
        JPanel card2 = new JPanel();
        card2.add(new JLabel("BOOK"));
        
        JPanel card3 = new JPanel();
        card3.add(new JLabel("SCHOOL"));

        // them vao hop
        cardPanel.add(card1, "card1");
        cardPanel.add(card2, "card2");
        cardPanel.add(card3, "card3");
        
        // tao panel chua nut dieu khien
        JPanel navPanel = new JPanel();
        JButton btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        
        JButton btnPrev = new JButton("Previous");
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prev();
            }
        });
        
        navPanel.add(btnNext);
        navPanel.add(btnPrev);
        
        JPanel timePanel = new JPanel();
        lblTime = new JLabel("00:00");
        timePanel.add(lblTime);
                
        this.add(timePanel, BorderLayout.NORTH);
        this.add(cardPanel, BorderLayout.CENTER);
        this.add(navPanel, BorderLayout.SOUTH);
        
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            setTimeLabel();
                            Thread.sleep(1000);    
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                }
            });
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void setTimeLabel() {
        Date currTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        this.lblTime.setText(format.format(currTime));
    }
    
    public void next() {
        cardLayout.next(cardPanel);
    }
    
    public void prev() {
        cardLayout.previous(cardPanel);
    }
    
}
