/*
Name: Bùi Văn Minh Triều
Purpose:
*/

package view;

import model.LoadData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame{
    private LoadData loadData;
    private GamePanel gamePanel;
    public GameFrame() {
        createMenuBar();

        loadData = new LoadData();
        gamePanel = new GamePanel(9, 9, 10, this);
        //Add ô chơi vào GameFrame
        add(gamePanel);
        //Add ảnh icon vào
        BufferedImage originalIcon = loadData.getTitleIcon().get("title");
        BufferedImage resizedIcon = resizeImage(originalIcon, 200, 200);
        setIconImage(resizedIcon);

        //Set Frame
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem exitMenuItem = new JMenuItem("Exit");


        fileMenu.add(newGameMenuItem);
        fileMenu.add(undo);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        newGameMenuItem.addActionListener((ActionEvent e) -> {
            setVisible(false);
            new GameFrame();
        });

        exitMenuItem.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        undo.addActionListener((ActionEvent e) -> {
            gamePanel.undoLastMove();
        });

        setJMenuBar(menuBar);
    }

    // Method to resize BufferedImage
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    public LoadData getLoadData() {
        return  loadData;
    }
    public void setLoadData(LoadData loadData) {
        this.loadData = loadData;
    }
}
