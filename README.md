# Gnar-game
A little game I'm making where you adventure into the unknown! Very much still a work in progress.
So far: The player can move around, interact with the NPCs, and complete a simple quest with branch choices. 
There is also an inventory and list of quests that are updated by the actions of the player. Items can be bought from stores or gathered in the world, each with its own attributes and stats.

This project is slow moving to begin, since the entirety of the game engine has to be written from scratch, but once the engine is complete the game will begin to really take form. 

Commands: 
1. mov *up, down, left, right* -- move the character 
2. questlist -- opens up a list of current quests
3. inventory -- opens up a list of items in your inventory
4. exit -- takes you to the previous map, exit a building
5. l 'x'
  
  h -- gives you 12 hides to trade with
  
  p -- teleports you to cathedral tavern
  
  m -- begins marcy's quest and gives you the cat.
  
  l -- gives you two Turkey Legs and the Plastic Blade

6. stats -- shows your current equipment bunuses
7. equipment -- shows current equipment
8. equip 'x' -- removes the item specified at inventory index 'x' and adds it to an equipment slot
             -- consumables are consumed with equip 'x' instead of worn.
9. unequip 'x' -- removes the item specified at eqipment slot 'x' and returns it to your inventory. Also removes all bonuses associated with said item. 
-- 01/22/2019 update:
Finished Cat quest, debugged dialogue.
Quest engine is pretty much finished.


-- 01/28/2019 update:
Added fully functioning stores, equip-able items, and equipment slots -- head, armor, necklace, weapon
added commands: 

1. equip *itemNumberInList* -- removes an item from inventory and equips it, 
  
2. unequip *itemNumberInList* -- removes an item from equipment to inventory, 
  
3. equipment -- lists all equipment slots and items in them


-- 03/14/2019 update:
Spring break is here and that means I actually have time to work on this pet project. I also learned how 
to use the debugger (lol) which has made things move much faster than before.
1. Added combat! Roll the dice and fight enemies and collect their loots. Right now all you can do is
fight a single cow in the initial map, but more will come once all the basic components of the game engine
are written. 
  Combat is turn based and singleway at all times. Some enemies will have the option to fight, while some 
  might automatically be aggressive towards you. It is modeled on Old School RuneScape's combat.
  Hits are calculated based on what items you have equipped. You have 4 equipment stats, attack, defence, and
  hpModifier. 
  
  Attack alters the accuracy of your hit, the higher you accuracy, the more often you hit
  
  Strength alters the umph of your hit, the higher your strength bonus the higher max hit you have
  
  Defence is your counter to your enemies attack, your accuracy is determined based on the ratio
  between your attack rating and your opponent's defence rating, and vice versa when enemies hit you. 
  
  Between each turn of combat you have the option to attack again, run, or change equipment or heal.
  choosing run ends combat, but the enemey gets one extra hit on you as you run, so you can't just flee
  if you a fight isn't going your way! 
  
  hpModifier boosts your hitpoints above your base health (10), but the boost is only active if you have 
  the modifying equipment on.
  
  The command 'stats' will show you your current equipment bonuses.
  
2. Food can now be eaten in and out of combat and heals a certain amount. You cannot heal over your hpModifier + base health.

3. Added the start of the main quest, the ability to cook on ranges, and some new equipment.

-- 04/14/2019

Added the ability to sell items, some new equipment, and a miniboss: the **Evil Lizard Dude** who drops the first gear upgrade in the game: the Evil Lizard Sword, which is a significant upgrade over the plastic blade and can be sold for a fair amount. 
