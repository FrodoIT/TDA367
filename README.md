TDA367
======


To compile the project the VM needs to be supplied with the argument "-Djava.library.path=lib/"

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