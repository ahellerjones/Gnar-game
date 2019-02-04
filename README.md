# Gnar-game
A little game I'm making where you adventure into the unknown! Very much still a work in progress.
So far: The player can move around, interact with the NPCs, and complete a simple quest with branch choices. 
There is also an inventory and list of quests that are updated by the actions of the player. Items can be bought from stores or gathered in the world, each with its own attributes and stats.

Commands: 
1. mov *up, down, left, right* -- move the character 
2. questlist -- opens up a list of current quests
3. inventory -- opens up a list of items in your inventory
4. exit -- takes you to the previous map, exit a building
5. l 'x'
  t -- gives you 9 hides or something
  p -- teleports you to cathedral tavern
  m -- begins marcy's quest and gives you the cat.
-- 01/22/2019 update:
Finished Cat quest, debugged dialogue.
Quest engine is pretty much finished.



-- 01/28/2019 update:
Added fully functioning stores, equip-able items, and equipment slots -- head, armor, necklace, weapon
added commands: 

1. equip *itemNumberInList* -- removes an item from inventory and equips it, 
  
2. unequip *itemNumberInList* -- removes an item from equipment to inventory, 
  
3. equipment -- lists all equipment slots and items in them
