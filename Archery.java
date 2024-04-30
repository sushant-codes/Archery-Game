package project; //please run the project in ecclipse for better experience

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.swing.Timer;

public class Archery {

    private static boolean isDragging = false;
    private static double bowAngle = 0;
    private static final int BOW_X = 10;
    private static final int BOW_Y = 310;
    private static int score = 0;
    private static String username = "";
    private static File highScoresFile = new File("highscores.txt");
    private static TreeMap<Integer, String> highScoresMap = new TreeMap<>();
    private static JPanel controlPanel;
    private static JLabel[] hearts;
    private static int missedShotsCount = 0;
    private static boolean controlPanelVisible = true; // Flag to track control panel visibility
    private static int target_Y = 200; // Initial Y-coordinate of the target
    private static int targetDirection = 1; // Initial movement direction (1 for down, -1 for up)
    private static int targetSpeed = 4;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Designing Application(Gaming application(Archery))");
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        f.setContentPane(layeredPane);
 
        // Background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\hp\\Desktop\\archery\\bg.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setBounds(0, 0, 1600, 800);
        layeredPane.add(backgroundPanel, new Integer(0));

        // Bow label
        ImageIcon bowIcon = new ImageIcon("C:\\Users\\hp\\Desktop\\archery\\bow.png");
        JLabel bowLabel = new JLabel();
        bowLabel.setIcon(bowIcon);
        bowLabel.setSize(bowIcon.getIconHeight(), bowIcon.getIconHeight());
        bowLabel.setOpaque(false);
        bowLabel.setBounds(10, 250, 300, 300);
        layeredPane.add(bowLabel, new Integer(1));

        // Target label
        JLabel targetPanel = new JLabel();
        ImageIcon targetImage = new ImageIcon("C:\\Users\\hp\\Desktop\\archery\\target.png");
        targetPanel.setIcon(targetImage);
        targetPanel.setOpaque(false);
        targetPanel.setBounds(1000, 200, 149, 188);
        layeredPane.add(targetPanel, new Integer(1));

        // Score label
        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(20, 20, 100, 30);
        layeredPane.add(scoreLabel, new Integer(2));

        // Initialize hearts
        // Initialize hearts
        hearts = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            ImageIcon heartIcon = new ImageIcon("C:\\Users\\hp\\Desktop\\archery\\heart_red.png"); // Change the file path
            hearts[i] = new JLabel();
            hearts[i].setIcon(heartIcon);
            hearts[i].setOpaque(false);
            hearts[i].setBounds(20 + i * 30, 60, 30, 30);
            layeredPane.add(hearts[i], new Integer(2));
        }
        JLabel aimLabel = new JLabel("Aim Cursor Here ↓");
        aimLabel.setFont(new Font("Arial", Font.BOLD, 20));
        aimLabel.setForeground(Color.WHITE);
        aimLabel.setBounds(900,20,200,30);
        layeredPane.add(aimLabel, new Integer(2));


        // Control panel
        
        controlPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("C:\\Users\\hp\\Desktop\\archery\\control.png"); // Change the file path
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        controlPanel.setBounds(300, 100, 600, 800);
        controlPanel.setOpaque(false);
        controlPanel.setBackground(Color.LIGHT_GRAY);
        controlPanel.setLayout(null); // Using absolute layout for precise positioning
        layeredPane.add(controlPanel, new Integer(3));

        // Username input field
        JTextField usernameField = new JTextField("username");
        usernameField.setBounds(150, 130, 200, 30);
        controlPanel.add(usernameField);

        // Start button
        ImageIcon startButtonIcon = new ImageIcon("C:\\Users\\hp\\Desktop\\archery\\start.png");
        JButton startButton = new JButton(startButtonIcon);
        startButton.setBounds(150, 180, 211,57);
        startButton.setOpaque(false);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        controlPanel.add(startButton);

        // High score button
        
        ImageIcon highScoreButton = new ImageIcon("C:\\Users\\hp\\Desktop\\archery\\highscore.png");
        JButton highScoresButton = new JButton(highScoreButton);
        highScoresButton.setBounds(150, 250, 211,57);
        highScoresButton.setOpaque(false);
        highScoresButton.setBorderPainted(false);
        highScoresButton.setContentAreaFilled(false);
        controlPanel.add(highScoresButton);

        f.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                controlPanel.setVisible(false); // Hide the control panel when start button is clicked
                controlPanelVisible = false; // Set the flag to indicate control panel is not visible
                enableMouseEvents(backgroundPanel); // Enable mouse events
               // fireArrow(layeredPane, 1000, 200, BOW_X, BOW_Y, bowAngle, targetPanel, bowLabel, scoreLabel, backgroundPanel);
            }
        });

        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHighScores();
            }
        });

        backgroundPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (controlPanelVisible) { // Check if control panel is visible
                    return; // Return without doing anything if control panel is visible
                }
                isDragging = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (controlPanelVisible) { // Check if control panel is visible
                    return; // Return without doing anything if control panel is visible
                }
                isDragging = false;
                fireArrow(layeredPane, e.getX(), e.getY(), BOW_X, BOW_Y, bowAngle, targetPanel, bowLabel, scoreLabel, backgroundPanel);
            }
        });

        backgroundPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (controlPanelVisible) { // Check if control panel is visible
                    return; // Return without doing anything if control panel is visible
                }
                if (isDragging) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();
                    int bowX = bowLabel.getX() + bowLabel.getWidth() / 2;
                    int bowY = bowLabel.getY() + bowLabel.getHeight() / 2;

                    double angle = Math.toDegrees(Math.atan2(mouseY - bowY, mouseX - bowX));
                    bowAngle = angle;
                    bowLabel.setIcon(rotateIcon(bowIcon, angle));
                }
            }
        });
    }

    private static void fireArrow(JLayeredPane layeredPane, int targetX, int targetY, int startX, int startY,
        double bowAngle, JLabel targetPanel, JLabel bowLabel, JLabel scoreLabel, JPanel backgroundPanel) {

        ImageIcon arrowIcon = new ImageIcon("C:\\Users\\hp\\Desktop\\archery\\arrow.png");
        JLabel arrowLabel = new JLabel();
        arrowLabel.setIcon(rotateIcon(arrowIcon, bowAngle));
        arrowLabel.setSize(arrowIcon.getIconWidth(), arrowIcon.getIconHeight());
        arrowLabel.setOpaque(false);

        int arrowX = bowLabel.getWidth() / 2;
        int arrowY = bowLabel.getHeight() / 2;

        arrowLabel.setBounds(arrowX - arrowIcon.getIconWidth() / 2, arrowY - arrowIcon.getIconHeight() / 2 +250 ,
            arrowIcon.getIconWidth(), arrowIcon.getIconHeight());
        layeredPane.add(arrowLabel, new Integer(2));

    Timer timer = new Timer(10, new ActionListener() {
        double dx = (targetX - startX) / 100.0;
        double dy = (targetY - startY) / 100.0;
        int steps = 0;
        boolean hitTarget = false;

        @Override
        public void actionPerformed(ActionEvent e) {

            // Update target's Y-coordinate
        target_Y += targetDirection * targetSpeed;
        targetPanel.setLocation(targetPanel.getX(), target_Y);

        // Reverse movement direction if target reaches boundaries
        if (target_Y <= 50 || target_Y >= 600) {
            targetDirection *= -1;
        }

            arrowLabel.setLocation((int) (arrowLabel.getX() + dx), (int) (arrowLabel.getY() + dy));
            steps++;

            if (!hitTarget) {
                Rectangle arrowBounds = arrowLabel.getBounds();
                Rectangle targetBounds = targetPanel.getBounds();
                if (arrowBounds.intersects(targetBounds)) {
                    System.out.println("Arrow hit krdiyasxcnkfv");
                    hitTarget = true;
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
            }

            Rectangle arrowBounds = arrowLabel.getBounds();
            Rectangle targetBounds = targetPanel.getBounds();// Two rectangles are made arrow bounds and target bounds
            if (arrowBounds.intersects(targetBounds)) {
                System.out.println("Arrow hit the target!");
                Timer delayTimer = new Timer(200, new ActionListener() {// arrow executed from the bow before waiting 200 miliseconds
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ((Timer) e.getSource()).stop();
                        layeredPane.remove(arrowLabel);
                    }
                });
                delayTimer.setRepeats(false); // Ensure it runs only once
                delayTimer.start();
            }

            if (steps >= 110) {
                ((Timer) e.getSource()).stop();
                layeredPane.remove(arrowLabel);
                if(!hitTarget){
                    missedShotsCount++;
                    updateHearts();
                }
                saveScore(username, score);
                // Check if all hearts are used
                if (missedShotsCount >= 5) {
                    controlPanel.setVisible(true); // Show the control panel again
                    controlPanelVisible = true; // Set the flag to indicate control panel is visible
                    enableMouseEvents(backgroundPanel); // Enable mouse events
                    missedShotsCount = 0; // Reset missed shots count
                    updateHearts();
                }
            }
        }
    });
    timer.start();
}

private static void updateHearts() {
    if (missedShotsCount < 5) {
        for (int i = 0; i < 5; i++) {
            if (i < missedShotsCount) {
                hearts[i].setIcon(new ImageIcon("C:\\Users\\hp\\Desktop\\archery\\heart_grey.png"));
            } else {
                hearts[i].setIcon(new ImageIcon("C:\\Users\\hp\\Desktop\\archery\\heart_red.png"));
            }
        }
    }
}
    private static void saveScore(String username, int score) {
        try (FileWriter writer = new FileWriter(highScoresFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(username + "," + score);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayHighScores() {
        highScoresMap.clear();
        try (Scanner scanner = new Scanner(highScoresFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                highScoresMap.put(score, name);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    
        JFrame highScoresFrame = new JFrame("High Scores");
        highScoresFrame.setSize(300, 400);
        highScoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        highScoresFrame.setLocationRelativeTo(null);
    
        JPanel highScoresPanel = new JPanel();
        highScoresPanel.setLayout(new GridLayout(0, 1));
    
        int count = 0;
        for (Map.Entry<Integer, String> entry : highScoresMap.descendingMap().entrySet()) {
            if (count >= 10) {
                break;
            }
            JLabel scoreLabel = new JLabel(entry.getValue() + " - " + entry.getKey());
            highScoresPanel.add(scoreLabel);
            count++;
        }
    
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highScoresFrame.dispose();
            }
        });
    
        highScoresFrame.add(highScoresPanel, BorderLayout.CENTER);
        highScoresFrame.add(closeButton, BorderLayout.SOUTH);
        highScoresFrame.setVisible(true);
    }
    

    private static ImageIcon rotateIcon(ImageIcon icon, double angle) {
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.rotate(Math.toRadians(angle), icon.getIconWidth() / 2, icon.getIconHeight() / 2);
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        return new ImageIcon(image);
    }

    // Enable mouse events for a component
    private static void enableMouseEvents(Component component) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isDragging = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
            }
        });

        // component.addMouseMotionListener(new MouseAdapter() {
        //     @Override
        //     public void mouseDragged(MouseEvent e) {
        //         if (isDragging) {
        //         }
        //     }
        // });
    }
}