# Name: QIWEN LUO
# Penn ID: 28188168
# Statement of Work: worked alone.


# import the random module
# use "winnings = random.randint(2, 10)" to generate a random int from 2 - 10 and store in a variable "winnings"
import random

# unit price of a lottery ticket
constant_lottery_unit_price = 2

# unit price of an apple
constant_apple_unit_price = .99

# unit price of a can of beans
constant_canned_beans_unit_price = 1.58

# unit price of a soda
constant_soda_unit_price = 1.23

# the user has initial $5 for shopping
money = 5

# the user has spent $0 initially
money_spent = 0

# the amounts of lottery tickets, apples, cans of beans, and sodas the user has purchased
lottery_amount, apple_amount, canned_beans_amount, soda_amount = 0, 0, 0, 0


# --------------------function definition--------------------

# Y or N validation
def val_Y_or_N(x):
    if (x == "Y") | (x == "y"):
        return 1
    elif (x == "N") | (x == "n"):
        return 0
    else:
        print("Invalid input, try again.")
        return -1

# balance check
def check_balance(money, money_spent):
    if money_spent > money:
        return 0
    else:
        return 1
    
# lottery ticket configuration
def win_lottery():
    if random.randint(0,2) == 2: # win
        winnings = random.randint(2,10)
        print("Congrats! You won $%d!\n" %(winnings))
        return winnings
    else:
        print("Sorry! You did not win the lottery.\n")
        return 0

# amount input validation
def val_amount():
    while True:
        while True:
            try:
                x = int(input())
                break
            except ValueError:
                print("Inavlid input, try again.")
        if x > 0:
            return x
        else:
            print("Invalid input, try again.")


# --------------------welcome message--------------------
print("Welcome to the supermarket!  Here's what we have in stock:")
print("- Lottery tickets cost $2 each")
print("- Apples cost $0.99 each")
print("- Cans of beans cost $1.58 each")
print("- Sodas cost $1.23 each\n")

# lottery ticket
print("You have $%.2f available" %(money))
print("First, do you want to buy a $2 lottery ticket for a chance at winning $2-$10? (y/n)")
while True: # loop for a valid input
    lottery_amount = val_Y_or_N(input())
    # buy lottery ticket
    if lottery_amount == 1:
        money_spent += 2
        # check_balance(money, money_spent)  
        # unnecessary to check balance in lottery ticket purchase
        # check winning
        lottery_winnings = win_lottery()
        # add to balance
        money_spent -= lottery_winnings
        break
    # no lottery ticket
    elif lottery_amount == 0:
        lottery_winnings = 0
        break

# apples
print("You have $%.2f available" %(money - money_spent))
print("Do you want to buy apple(s)? (y/n)")
while True: # loop for a valid input
    apple_amount = val_Y_or_N(input())
    # buy apples
    if apple_amount > 0:
        # ask for the amount
        print("How many apple(s) do you want to buy?")
        apple_amount = val_amount()
        # chekc for the balance when purchased
        money_spent_temp = money_spent + apple_amount * constant_apple_unit_price
        if check_balance(money,money_spent_temp):
            money_spent = money_spent_temp
            print("The user wants to buy %d apple(s). This will cost $%.2f.\n" %(apple_amount, apple_amount * constant_apple_unit_price))
            break
        else:
            print("Not enough money! No apples purchased.\n")
            apple_amount = 0
            break
        # prompt when not purchased
    elif apple_amount == 0:
        print("No apples purchased.\n")
        break

# beans
print("You have $%.2f available" %(money - money_spent))
print("Do you want to buy can(s) of beans? (y/n)")
while True: # loop for a valid input
    canned_beans_amount = val_Y_or_N(input())
    # buy beans
    if canned_beans_amount > 0:
        # ask for the amount
        print("How many can(s) of beans do you want to buy?")
        canned_beans_amount = val_amount()
        # chekc for the balance when purchased
        money_spent_temp = money_spent + canned_beans_amount * constant_canned_beans_unit_price
        if check_balance(money,money_spent_temp):
            money_spent = money_spent_temp
            print("The user wants to buy %d can(s) of beans. This will cost $%.2f.\n" %(canned_beans_amount, canned_beans_amount * constant_canned_beans_unit_price))
            break
        else:
            print("Not enough money! No cans of beans purchased.\n")
            canned_beans_amount = 0
            break
        # prompt when not purchased
    elif canned_beans_amount == 0:
        print("No cans of beans purchased.\n")
        break
        
# soda
print("You have $%.2f available" %(money - money_spent))
print("Do you want to buy soda(s)? (y/n)")
while True: # loop for a valid input
    soda_amount = val_Y_or_N(input())
    # buy sodas
    if soda_amount > 0:
        # ask for the amount
        print("How many soda(s) do you want to buy?")
        soda_amount = val_amount()
        # chekc for the balance when purchased
        money_spent_temp = money_spent + soda_amount * constant_soda_unit_price
        if check_balance(money,money_spent_temp):
            money_spent = money_spent_temp
            print("The user wants to buy %d soda(s). This will cost $%.2f.\n" %(soda_amount, soda_amount * constant_soda_unit_price))
            break
        else:
            print("Not enough money! No sodas purchased.\n")
            soda_amount = 0
            break
        # prompt when not purchased
    elif soda_amount == 0:
        print("No sodas purchased.\n")
        break

# conclusion
print("Money left: $%.2f" %(money - money_spent))
print("Lottery ticket(s) purchased: %d" %(lottery_amount))
print("Lottery winnings: %d" %(lottery_winnings))
print("Apple(s) purchased: %d" %(apple_amount))
print("Can(s) of beans purchased: %d" %(canned_beans_amount))
print("Soda(s) purchased: %d" %(soda_amount))
print("Good bye!")

