TDA367
======

Gameplay
=======
Arrowkeys for movement
X for attack
Z for interact


XML
===
To configure XML, look at the current XML files. Your xml for monster files should have the following tags:

<NPCType id=(Any number/Redacted)>
	<health>10 (max health of NPC)</health>
	<imagePath>(Path to the imageSprite the NPC should use)</imagePath>
	<weapon damage="10">
		<range>2 (how far the weapon should reach)</range>
		<attackInterval>100 (Delay between attacks, in ms)</attackInterval>
	</weapon>
	<unitTile>32, 32 (Size of the NPC, should not be changed)</unitTile>

	<movementPattern>2(what AI the NPC uses)</movementPattern>
	<movementSpeed>1 (speed of the NPC movement) </movementSpeed>
</NPCType>



To place a monster, create an interactive layer npc and place an object in the map.
Give the object a property by the name "npcType" without quotationmarks and where the name of the XML file is given as value, e.g "StandardEnemy".


Compilation
===========

To compile the project the VM needs to be supplied with the argument "-Djava.library.path=lib/"


Sprites for characters
======================
How to change Sprite for PlayerView:
	Create your sprite using Tiled : mapeditor.org
	You will need 4 direcitons. and each direciton will use an equal number of tiles. for example a row in tiled can look like
	
	DDRRUULL

	where the first 2 'D' tiles are used for the down animation
	'R' right
	'U' up
	'L' left

	Example: https://dl.dropboxusercontent.com/u/19786811/Screenshot%202014-04-06%2022.44.08.png
	
	observe that you can use as many images for each direction as you want aslong as all directions use an equal number of images
	IMPORTANT: name your layer: "sprite"


