<p>Adapted Java Maze program from WCTC's Java I course for use for homework for Modules 6, 7, and 8.
Software Architecture.</p>


<p>You will create a game in which a Player explores a Maze. The Maze is composed of different types of Room objects, some of which are Lootable and/or Interactable. One Room is also Exitable, from which the Player can leave the Maze and end the game.</p>
<p>Rooms are connected to each other in a grid shape. The Player moves between Rooms along the cardinal compass directions: north, south, east, and west. Rooms may also be above or below each other.</p>
<p>The Player may earn points by interacting with certain Rooms or looting others. The game continues until the Player exits the Maze, at which time they are given their final score.</p>
<p>You have complete creative control over the theme of your maze. It could be a classic dungeon crawler, a rabbit warren, or an office building.</p>
<p>&nbsp;</p>
<h3>Requirements</h3>
<p><span>Accept and clone the <span style="font-size: 12pt;">assignment</span> from GitHub Classroom using the following link:&nbsp;</span></p>
<p>Create the following interfaces.</p>
<ul>
    <li><strong>Lootable</strong>: Contains one method named <em>loot</em> that takes a Player object as an argument and returns a String. The String will be displayed to the Player after they loot the Room.</li>
    <li><strong>Interactable</strong>: Contains one method named&nbsp;<em>interact</em> that takes a Player object as an argument and returns a String. The String will be displayed to the Player after they interact with the Room.</li>
    <li><strong>Exitable</strong>: Contains one method named&nbsp;<em>exit</em> that takes a Player object as an argument and returns a String. The String will be displayed to the Player after they exit the Room.</li>
</ul>
<p>&nbsp;</p>
<p>Create the following classes.</p>
<ul>
    <li><strong>Room</strong>: An abstract class that contains a String field called <em>name</em> and six Room fields called&nbsp;<em>north, south, east, west, up,</em> and&nbsp;<em>down.</em> The Room class contains the following methods.
        <ul>
            <li>A constructor that accepts a String for the name of the Room.</li>
            <li>An abstract method named&nbsp;<em>getDescription</em> that returns a String. The String will be displayed to the Player when they enter the Room and should contain a description of what they see.</li>
            <li>A method named&nbsp;<em>getAdjoiningRoom</em> that accepts a char argument for the direction. If this Room is connected to another Room in the given direction, that Room is returned. Otherwise, return null.</li>
            <li>A method named&nbsp;<em>getExits</em> that returns a String. The String should be a list of all of the directions the Player could move from this Room to another Room.</li>
            <li>A method named&nbsp;<em>getName</em> that returns the name of the Room.</li>
            <li>A method named&nbsp;<em>isValidDirection</em> that accepts a char argument for the direction. If this Room is connected to another Room in the given direction, return true. Otherwise, return false.</li>
            <li>Six setter methods -- one for each direction -- that accept a Room object and assign it to the appropriate field.</li>
        </ul>
    </li>
    <li><strong>Player</strong>: A concrete class that contains an int field called&nbsp;<em>score</em>. It also contains either an array or ArrayList of Strings called <em>inventory</em>. The Player class contains the following methods.
        <ul>
            <li>A method named&nbsp;<em>addToInventory</em> that accepts a String argument and adds it to the Player's inventory list.</li>
            <li>A method named&nbsp;<em>addToScore</em> that accepts an int argument and adds it to the Player's score.</li>
            <li>A method named&nbsp;<em>getInventory</em> that returns a String. The String should contain the contents of the Player's inventory list. If the inventory is empty, return a message that says so.</li>
            <li>A method named&nbsp;<em>getScore</em> that return's the Player's <em>score</em> field (an int).</li>
        </ul>
    </li>
    <li><strong>Maze</strong>: A concrete class that contains a Room field called&nbsp;<em>currentRoom</em> and a Player field called&nbsp;<em>player</em>. It also contains a boolean field called&nbsp;<em>isFinished</em> that is initially false. The Maze class contains the following methods.
        <ul>
            <li>A constructor that creates a Player object and assigns it to the field. The Maze constructor creates all of the Room objects in the game and connects them using the Rooms' setter methods. The Room in which the Player begins the game is assigned to the <em>currentRoom</em> field. (See below for instructions on creating concrete Room classes.)</li>
            <li>A method named&nbsp;<em>exitCurrentRoom</em> that returns a String. If the&nbsp;<em>currentRoom</em> is Exitable, it returns the result of calling <em>exit()</em> on the Room. If not, it returns a message that the current room is not exitable.</li>
            <li>A method named&nbsp;<em>interactWithCurrentRoom</em> that returns a String. If the&nbsp;<em>currentRoom</em> is Interactable, it returns the result of calling&nbsp;<em>interact</em> on the Room. If not, it returns a message that no interactions are possible.</li>
            <li>A method named&nbsp;<em>lootCurrentRoom</em> that returns a String. If the&nbsp;<em>currentRoom</em> is Lootable, it returns the result of calling&nbsp;<em>loot</em> on the Room. If not, it returns a message that the current room is not lootable.</li>
            <li>A method named&nbsp;<em>move</em> that accepts a char argument for the direction and returns a boolean. If the direction is valid to move from within the <em>currentRoom</em>, the adjoining Room becomes the new&nbsp;<em>currentRoom</em> and the method returns true. If not, the method returns false.</li>
            <li>Methods named&nbsp;<em>getPlayerScore</em> and&nbsp;<em>getPlayerInventory</em> that return the result of calling the Player's&nbsp;<em>getScore</em>&nbsp;and&nbsp;<em>getInventory</em> methods.</li>
            <li>Methods named&nbsp;<em>getCurrentRoomDescription</em> and&nbsp;<em>getCurrentRoomExits</em> that return the result of calling the <em>getDescription</em> and&nbsp;<em>getExits</em> method of the&nbsp;<em>currentRoom.</em></li>
            <li>A method named&nbsp;<em>isFinished</em> that returns the Maze's&nbsp;<em>isFinished</em> field (a boolean).</li>
        </ul>
    </li>
    <li><strong>Main</strong>: This class is the driver and contains the&nbsp;<em>main&nbsp;</em>method. Implement the following logic within&nbsp;<em>main.</em>
        <ul>
            <li>Create the Maze, and a Scanner to read keyboard input.</li>
            <li>Begin a loop that will run until the Maze is finished. Within the loop:
                <ul>
                    <li>Print the current room description.</li>
                    <li>Print the current room exits.</li>
                    <li>Ask the player for a command and read the input as a char.</li>
                    <li>The commands 'n', 's', 'e', 'w', 'u', and 'd' will attempt to move the player in that direction. If the movement is not valid, print a message that says so.</li>
                    <li>The command 'i' will attempt to interact with the current room.</li>
                    <li>The command 'l' (lower case L) will attempt to loot the current room.</li>
                    <li>The command 'x' will attempt to exit the current room. A successful exit means that the maze is finished.</li>
                    <li>The command 'v' prints the player's inventory.</li>
                </ul>
            </li>
            <li>When the maze is finished, print the player's score.</li>
        </ul>
    </li>
</ul>
<p>&nbsp;</p>
<p><strong>In addition</strong> to the above classes, create at least three concrete Room classes so that you have one for each interface: Lootable, Interactable, Exitable. You may create more than one Room of each type, and a Room may implement more than one type (for example, a Room may be both Interactable and Exitable).</p>
<p>&nbsp;</p>
<p>Only the driver class should perform input or output. Do not create Scanners or use System.out anywhere but in the <em>main</em> method.</p>