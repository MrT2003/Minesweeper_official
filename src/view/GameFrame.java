package view;
import model.LoadData;

import javax.swing.*;
import java.awt.event.ActionEvent;

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
        //setIconImage(loadData.getTitleIcon().get("title"));
        setIconImage(loadData.getListImage().get("title"));

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

    public LoadData getLoadData() {
        return  loadData;
    }
    public void setLoadData(LoadData loadData) {
        this.loadData = loadData;
    }
}
