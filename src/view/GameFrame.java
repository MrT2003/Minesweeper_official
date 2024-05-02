package view;
import model.LoadData;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
    private LoadData loadData;
    private GamePanel gamePanel;
    public GameFrame() {
        loadData = new LoadData();
        gamePanel = new GamePanel(9, 9, 10, this);
        //Add ô chơi vào GameFrame
        add(gamePanel);
        //Add ảnh icon vào
//        setIconImage(loadData.getTitleIcon().get("title"));
        setIconImage(loadData.getListImage().get("title"));


        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }

    public LoadData getLoadData() {
        return  loadData;
    }
    public void setLoadData(LoadData loadData) {
        this.loadData = loadData;
    }
}
