# Archery-Game
Welcome to my Archery game (enjoy it)
please hit like if you like the game

Project Overview
The Archery Game Application is a desktop-based game developed using Java and the Swing library. This game simulates an archery experience where players aim and shoot arrows at a moving target. The application includes a user-friendly graphical interface, interactive gameplay, and a system to record and display high scores.

 
Here are some glimpse of my game how it looks like and how it works


![Screenshot (372)](https://github.com/user-attachments/assets/9fe27e24-13b6-44f5-8ce2-834340b5921f)
so basically this is my Archery game interface how its looks 
so let me explain what is going on in this game(interface)
step1: we have to add username which is mentioned on the wooden board and press start to play the game
step2: secondly on the same wooden board two options are there start and high score options
basically in highscore options saved the top 10 scores in the game it shows the top 10 scores

![Screenshot (380)](https://github.com/user-attachments/assets/46634d32-6a03-4138-9f01-25c28bbec776)
Key features
Graphical Interface(GUI Elements):

Background Image: Provides a visually appealing game environment.
Background Panel (backgroundPanel): Displays a background image (bg.jpg) using a custom JPanel subclass (backgroundPanel) with overridden paintComponent method to draw the image.

Bow and Arrow: Player-controlled bow that rotates based on mouse movement.
Bow (bowLabel): Represented by an ImageIcon (bow.png), allows rotation based on mouse movement to simulate aiming.

Moving Target: Target that moves vertically, increasing the challenge.
Score Display: Real-time display of the player's score.
Target (targetPanel): A JLabel displaying the target image (target.png). It moves up and down (targetDirection) across the screen.

Lives (Hearts): Display of remaining attempts before the game ends.
User Interaction:
Hearts (hearts): Representing player lives, initially displayed as red (heart_red.png) and turn grey (heart_grey.png) upon missed shots.


Mouse Controls: Aim the bow by dragging the mouse, shoot by releasing it.
Aim Label (aimLabel): Provides instructions for aiming.

Control Panel: Initial interface for entering username, starting the game, and viewing high scores.
Control Panel (controlPanel): Contains username input field, start button (startButton), and high scores button (highScoresButton).

Game Mechanics:

Scoring System: Points awarded for hitting the target.
Lives System: Players have 5 attempts; a heart is lost for each missed shot.
High Score Tracking: High scores are saved to a file and can be viewed from the control panel.
High Scores:

Save Scores: Usernames and scores are saved to a file.
Display High Scores: Top 10 high scores displayed in a separate window.
Project Modules
Main Game Interface:

Setup of the main game window using JFrame and JLayeredPane.
Background panel with a background image.
Bow and target images layered appropriately.
Control Panel:

Username input field.
Start button to initiate the game.
High score button to display high scores.
Event Handling:

Mouse listeners for aiming and shooting arrows.
Mouse Events: Enable aiming (mouseDragged) and firing (mouseReleased) of arrows from the bow.

Action listeners for start and high score buttons.


Arrow Mechanics:
Arrow Animation (fireArrow method): Animates the arrow (arrow.png) towards the target based on calculated trajectory and collision detection with the target (targetPanel).


Method to fire arrows, check for collision with the target, and update the score.
Method to update the display of hearts based on missed shots.


High Score Management:
Score Management (saveScore and displayHighScores methods): Writes and reads scores to/from highscores.txt. Displays high scores in a separate JFrame when highScoresButton is clicked.


Methods to save scores to a file.
Methods to read scores from the file and display them.

Game Over Condition: Displays the control panel (controlPanel) and resets the game upon losing all lives (hearts).








![Screenshot (374)](https://github.com/user-attachments/assets/345d5b06-67dc-4021-84dd-ffcaeaccab40)
so thats how our game starts the image is showing thats how it the game is
in this archery game we have to aim the target from the our bow and arrow and if our arrow
hit the target then our score increases +1 and if it misses than our life or chances to
shoot decreases the hearts shows our life line in every miss of our arrow to the target one heart decreases







![Screenshot (375)](https://github.com/user-attachments/assets/76d7503a-2178-4c2b-9d84-46fff0f6dfa8)


![Screenshot (376)](https://github.com/user-attachments/assets/562d7d0a-b7b4-47e2-88ba-a148ee51ce79)
we can use multiples arrow at the same time to shoot our target





![Screenshot (377)](https://github.com/user-attachments/assets/28e82153-b771-497c-81c2-6749ae60d00f)
we also Drag our bow upward and downward according to our choose there is one more option there
if we have to aim our arrow upward (Aim Cursor here)




![Screenshot (378)](https://github.com/user-attachments/assets/786bf827-83be-4f8a-b32c-c3d9a3fdc29d)



![Screenshot (379)](https://github.com/user-attachments/assets/1cdab55f-82f0-43c0-8d51-23ea0ce3e84f)


functioning and Technologies used in the game:
 
Java: Core programming language used for development.
Swing: Java's GUI toolkit used to create the graphical user interface.
JLayeredPane: Used for layering different components (background, bow, arrow, target, and control panel).
File I/O: Used to save and read high scores from a file.
Event Handling: Mouse events for aiming and shooting, action events for buttons.








Development Experience
Working on this project provided valuable experience in several areas:

Java Programming: Deepened understanding of Java, including object-oriented programming principles.
Swing Library: Gained proficiency in creating and managing complex graphical user interfaces using Swing.
Event Handling: Enhanced skills in managing user interactions through event listeners.
File I/O: Learned to implement file reading and writing to manage persistent data (high scores).
Problem-Solving: Developed problem-solving skills by debugging and refining game mechanics and interface elements.

Conclusion
The Archery Game Application is a comprehensive project that showcases the ability to design and implement a fully-functional game using Java. It highlights proficiency in GUI development, event handling, file management, and overall game design principles. This project demonstrates the ability to manage a complete software development lifecycle, from initial design to final implementation and testing.




