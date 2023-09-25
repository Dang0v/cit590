# Student Name in Canvas: QIWEN LUO
# Penn ID: 28188168
# Did you do this homework on your own (yes / no): yes
# Resources used outside course materials:
# 

# import statements
from random import shuffle
from random import randint

# global variables
# power cards tuples initial setting. These will not change in the game.
power_cards_name = ("SOH", "DOT", "DMT")
power_cards_qty = (10, 2, 3)


water_cards_value = (1, 5, 10)
water_cards_qty = (30, 15, 8)


def get_user_input(question):
    ''' 
    Get the user input, keep get the input for none input. 
    Power cards will be in upper class string.
    Water cards will be an integer.
    Others will be in lower class.
    '''
    while True:
        # Prompt the user with the given question and process the input
        user_in = input(question + "")
        # Remove leading and trailing whitespaces.
        user_in = user_in.strip(" ")
        # format the input into upper case
        user_in = user_in.upper()

        # no input, repompt the question
        if user_in == "" or user_in == "0":
            # TODO: check if the input is none or 0
            continue
        
        # handle input
        else:
            # number input
            while True:
                try:
                    user_in = int(user_in)
                    return user_in
                except ValueError:
                    # not a number, check for other input
                    break

            # power card input
            if user_in in power_cards_name:
                return user_in
            # other string input
            else:
                return user_in.lower()

def setup_water_cards():
    '''
    Initialize the water cards, return the water cards list
    '''
    # creat a initial list of water cards
    water_cards_list =[]
    for i in range(len(water_cards_value)):
        water_cards_list.extend([water_cards_value[i]] * water_cards_qty[i])
    # shuffle cards
    shuffle(water_cards_list)
    return water_cards_list

def setup_power_cards():
    '''
    Initialize the power cards, return the power cards list
    '''
    # creat a initial list of power cards
    power_cards_list = []
    for i in range(len(power_cards_name)):
        power_cards_list.extend([power_cards_name[i]] * power_cards_qty[i])
    # shuffle cards
    shuffle(power_cards_list)
    return power_cards_list

def setup_cards():
    '''
    Initialize cards pool, retrun tuple of water cards list and power cards list
    '''
    water_cards_list = setup_water_cards()
    power_cards_list = setup_power_cards()
    return (water_cards_list, power_cards_list)

def get_card_from_pile(pile, index):
    '''
    Remove the cards of given index in given pile
    pile shoule be list.
    '''
    return pile.pop(index)

def arrange_cards(cards_list):
    '''
    Arrange cards after dealt, modify the input list directly.
    Nothing will be return.
    '''
    int_list = []
    str_list = []
    for i in cards_list:
        if type(i) == type(1):
            int_list.append(i)
        elif type(i) == type("1"):
            str_list.append(i)
    int_list.sort()
    str_list.sort()
    for i in range(len(cards_list)):
        cards_list.pop()
    cards_list.extend(int_list)
    cards_list.extend(str_list)
    
            
def deal_cards(water_cards_pile, power_cards_pile):
    '''
    Deal cards for 2 players
    Return tuple of 2 list
    '''
    player_1_list = []
    player_2_list = []
    # deal water cards
    for i in range(3):
        player_1_list.extend([get_card_from_pile(water_cards_pile, 0)])
        player_2_list.extend([get_card_from_pile(water_cards_pile, 0)])
    # deal power cards
    for i in range(2):
        player_1_list.extend([get_card_from_pile(power_cards_pile, 0)])
        player_2_list.extend([get_card_from_pile(power_cards_pile, 0)])
    # arrange cards
    arrange_cards(player_1_list)
    arrange_cards(player_2_list)

    return (player_1_list, player_2_list)

def apply_overflow(tank_level):
    '''
    Apply overflow if applicable
    '''
    if tank_level > 80:
        overflow = tank_level - 80
        return 80 - overflow
    else: 
        return tank_level

def use_card(player_tank, card_to_use, player_cards, opponent_tank):
    '''
    apply card and return tuples of both player tank level.
    return false when card invalid
    '''
    # card validation, return flase if is not in hand
    if card_to_use in player_cards:
        # use warter cards
        if type(card_to_use) == type(1):
            player_tank += card_to_use
            player_tank = apply_overflow(player_tank)
            player_cards.remove(card_to_use)
        # use power cards
        elif card_to_use == "DMT":
            player_tank = apply_overflow(player_tank * 2)
            player_cards.remove(card_to_use)
        elif card_to_use == "DOT":
            opponent_tank = 0
            player_cards.remove(card_to_use)
        elif card_to_use == "SOH":
            level_steal = int(opponent_tank / 2)
            player_tank += level_steal
            opponent_tank -= level_steal
            player_tank = apply_overflow(player_tank)
            player_cards.remove(card_to_use)
            
        return (player_tank, opponent_tank)
    # invalid card
    else:
        return (-1, -1)   

def discard_card(card_to_discard, player_cards, water_cards_pile, power_cards_pile):
    '''
    Discard cards and return to the bottom of appropriate pile.
    '''
    # card validation
    if card_to_discard in player_cards:
        # discard cards
        player_cards.remove(card_to_discard)
        # return to the bottom of water pile
        if type(card_to_discard) == type(1):
            water_cards_pile.append(card_to_discard)
        # return to the bottom of power pile
        else:
            power_cards_pile.append(card_to_discard)

def filled_tank(tank):
    '''
    Check whether the tank is filled
    '''
    if tank >= 75 and tank <= 80:
        return True
    else:
        return False

def check_pile(pile, pile_type):
    '''
    Check the pile.
    If empty setup again.
    '''
    if len(pile) == 0:
        if pile_type == "water": 
            pile = pile.extend(setup_water_cards())
        elif pile_type == "power": 
            pile = pile.extend(setup_power_cards()) 

def check_card_type(card):
    '''
    Check if the card is water card or power card.
    Return True if it is water card.
    Retrun False if it is power card.
    '''
    if type(card) == type(1):
        return True
    else:
        return False

def human_play(human_tank, human_cards, water_cards_pile, power_cards_pile, opponent_tank):
    '''
    Human play function, ask for user input.
    Return 2-tuple of tank level
    '''
    # basic info prompt
    print("\n=== Human Player's turn ===")
    print("Your water level is at:  %d" %(human_tank))
    print("Computer's water level is at:  %d" %(opponent_tank))
    print("Your cards are:  ", end="")
    print(human_cards)

    # use or discard a card
    while True: # in to the loop
        use_flag = get_user_input("Do you want to use a card or discard a card? (u / d): ")
        # use a card
        if use_flag == "u":
            while True: # into use loop
                card_to_use = get_user_input("Which card do you want to use?: ")
                if card_to_use in human_cards:
                    print("Playing with cards: ", end="")
                    print(card_to_use)
                    human_tank, opponent_tank = use_card(human_tank, card_to_use, human_cards, opponent_tank)
                    draw_flag = check_card_type(card_to_use) # a flag for pile where card will be drawn 
                    break
            break
        # discard a card
        elif use_flag == "d":
            while True: # into discard loop
                card_to_discard = get_user_input("Which card do you want to discard?: ")
                if card_to_discard in human_cards:
                    print("Discarding with cards: ", end="")
                    print(card_to_discard)
                    discard_card(card_to_discard, human_cards, water_cards_pile, power_cards_pile)
                    draw_flag = check_card_type(card_to_discard) # a flag for pile where card will be drawn 
                    break
            break

    # draw a new card
    # draw a power card
    if draw_flag: 
        card_drawn = get_card_from_pile(water_cards_pile, 0)
        print("Drawing water card: ", end="")
        print(card_drawn)
    # draw a water card
    else:
        card_drawn = get_card_from_pile(power_cards_pile, 0)
        print("Drawing power card: ", end="")
        print(card_drawn)
    human_cards.append(card_drawn)
    arrange_cards(human_cards)

    return (human_tank, opponent_tank)
    
    

def computer_play(computer_tank, computer_cards, water_cards_pile, power_cards_pile, opponent_tank):
    '''
    Computer play function, ask for user input.
    Return 2-tuple of tank level
    '''
    # basic info prompt
    print("\n=== Computer Player's turn ===")
    print("Your water level is at:  %d" %(computer_tank))
    print("Computer's water level is at:  %d" %(opponent_tank))

    # computer use a card
    # simple logic to win the game
    if "DOT" in computer_cards and opponent_tank > 40:
        card_to_use = "DOT"
    if "DMT" in computer_cards and computer_tank < 40 and computer_tank > computer_cards[2]:
        card_to_use = "DMT"
    if "SOH" in computer_cards and opponent_tank / 2 > computer_cards[2] and computer_tank + int(opponent_tank / 2) < 80:
        card_to_use = "SOH"
    else:
        card_to_use = computer_cards[2]

    computer_tank, opponent_tank = use_card(computer_tank, card_to_use, computer_cards, opponent_tank)

    # draw a new card
    draw_flag = check_card_type(card_to_use)
    # draw a water card
    if draw_flag: 
        card_drawn = get_card_from_pile(water_cards_pile, 0)
    # draw a power card
    else:
        card_drawn = get_card_from_pile(power_cards_pile, 0)
    computer_cards.append(card_drawn)
    arrange_cards(computer_cards)

    return (computer_tank, opponent_tank)


def main():
    # Welcome message
    print("Welcome to the WATER TANK game and play against the computer!")
    print("The first player to fill their tank wins the game.")
    print("Good luck!\n")

    # select who goes first
    play_flag = randint(0, 1)
    # 1 represents human trun
    if play_flag:
        print("The Human Player has been selected to go first.\n")
    # 0 represents computer trun
    else:
        print("The Computer Player has been selected to go first.\n")

    # card initialize
    cards_piles = setup_cards()
    human_cards, computer_cards = deal_cards(cards_piles[0], cards_piles[1])
    human_tank = 0
    computer_tank = 0

    while True:
        # check pile befor drawn
        check_pile(cards_piles[0], "water")
        check_pile(cards_piles[1], "power")
        if play_flag:
            human_tank, computer_tank = human_play(human_tank, human_cards, cards_piles[0], cards_piles[1], computer_tank)
            
            # round end message prompt
            print("Your water level is now at: %d" %(human_tank))
            if filled_tank(human_tank): 
                print("\n=== Game Over ===")
                print("Human Player won.")
                break
            print("Computer's water level is now at: %d" %(computer_tank))
            # This will not result in a victory for the opponent in this round, no need to check
            print("Your cards are now: ", end="")
            print(human_cards)

            # switch to computer
            play_flag = 0
            
        else:
            computer_tank, human_tank = computer_play(computer_tank, computer_cards, cards_piles[0], cards_piles[1], human_tank)
            
            # round end message prompt
            print("Computer's water level is now at: %d" %(computer_tank))
            if filled_tank(computer_tank): 
                print("\n=== Game Over ===")
                print("Computer Player won.")
                break
            print("Your water level is now at: %d" %(human_tank))
            # This will not result in a victory for the opponent in this round, no need to check

            # switch to human
            play_flag = 1



if __name__ == '__main__':
    main()
