"""
STARTER CODE
Homework 3: Ice Cream Stand
Topics Covered:
- Lists (append, pop)
- For and while loops
- Getting user inputs
- Validating user inputs
- Functions and helper functions
- Formatted Strings
"""

# TODO: Students, fill out statement of work header
# Student Name in Canvas: Qiwen Luo
# Penn ID: 28188168
# Did you do this homework on your own (yes / no): no
# Resources used outside course materials:
# 1. python try except: https://www.w3schools.com/python/python_try_except.asp

# import statements
from random import randint, choice


def print_welcome_and_menu(list_of_flavors, list_of_sizes, list_of_prices):
    """
    Prints the following:
    1. Welcome message (Must contain word 'welcome')
    2. Message on what flavors are available in the ice cream store.
        Hint: Loop through the list_of_flavors
    3. Message on how much each size cost.
        Hint: Loop through the list_of_sizes, list_of_prices
        Format should be: Our {size} ice cream is ${price}.
    """
    # welcome message
    print("Welcome to Penn's Student Run Ice Cream Stand!\n")
    
    # flavors
    print("Our current flavors for today are:")
    for i in range(len(list_of_flavors)):
        print(list_of_flavors[i])
    print("")

    # cost of each size
    for i in range(len(list_of_sizes)):
        print("Our %s is $%.2f" %(list_of_sizes[i], list_of_prices[i]))
    print("")
    

    return 0



def get_order_qty(customer_name):
    """
    Ask the customer how many orders of ice cream they want.
    Valid order quantity should be an integer 1-5 inclusive. If outside the range or non-int, re-prompt.
    Hint: When asking for user input, cast it to an integer. If the input cannot be cast-ed to an integer, re-prompt.
    "2.55", "abc", "   ", are a few examples of what should all re-prompt the user.
    Returns: How many orders of ice cream the customer wants.
    """
    order_qty = 0
    while True:
        # handle invalid input for order_qty
        try:
            order_qty = int(input("Welcome %s! How many ice creams will you be ordering (1 to 5)? " %(customer_name)))
            if order_qty > 0 and order_qty < 6:
                return order_qty
        except ValueError:
            print("Please enter a valid integer")
    


def get_ice_cream_flavor(ice_cream_flavors):
    """
    Ask the customer 'Which flavor would you like (v/c/s)? '
    Then, processes and cleans the input and returns the equivalent flavor from ice_cream_flavors list.
    Hint:   Use the indices set in the main function for the flavors.
            Call the get_first_letter_of_user_input function to get and process inputs.
            Note: Only the first letter of the input will be considered so an input of 'Cookies and Cream'
            will be considered as 'c' which corresponds to 'Chocolate'.
            Ask again if it is not a valid flavor.
    Returns: String of ice cream flavor picked (e.g "Vanilla")
    """
    flavor_picked = ""
    while True:
        # get the user input
        flavor_picked = get_first_letter_of_user_input("Which flavor would you like (v/c/s)? ")
        # choose the flavor
        if flavor_picked == "v":
            flavor_picked = ice_cream_flavors[0]
            return flavor_picked
        elif flavor_picked == "c":
            flavor_picked = ice_cream_flavors[1]
            return flavor_picked
        elif flavor_picked == "s":
            flavor_picked = ice_cream_flavors[2]
            return flavor_picked


def get_ice_cream_size(ice_cream_sizes):
    """
    Ask the customer 'Which size would you like (s/m/l)? '
    Then, processes and cleans the input and returns the equivalent size from ice_cream_sizes list.
    Hint:   Use the indices set in the main function for the sizes.
            Call the get_first_letter_of_user_input function to get and process inputs.
            Note: Only the first letter of the input will be considered so an input of 'Super Large'
            will be considered as 's' which corresponds to 'Small'.
            Ask again if it is not a valid size.
    Returns: String of Size picked (e.g "Small")
    """
    size_picked = ""
    while True:
        # get the user input
        size_picked = get_first_letter_of_user_input("Which size would you like (s/m/l)? ")
        # choose the size
        if size_picked == "s":
            size_picked = ice_cream_sizes[0]
            return size_picked
        elif size_picked == "m":
            size_picked = ice_cream_sizes[1]
            return size_picked
        elif size_picked == "l":
            size_picked = ice_cream_sizes[2]
            return size_picked


def get_ice_cream_order_price(ice_cream_size, ice_cream_prices, ice_cream_sizes):
    """
    Hint:   Use the indices set in the main function for the prices of Small, Medium and Large.
    Returns: The equivalent price of an ice cream size. Example: Returns 4.99 if ice_cream_size is 'Small'
    """
    # return the same index in price list
    
    return ice_cream_prices[ice_cream_sizes.index(ice_cream_size)]


def take_customer_order(customer_name, ice_cream_flavors, ice_cream_sizes, ice_cream_prices):
    """
    This function runs when a customer reaches the front of the queue. It should print
    the current customer's name being served, and take their order(s).
    If the customer can pay for their order, returns the amount of revenue from the sale.
    If the customer cancels their order, returns 0.
    Hint: Use other helper functions we required you to write whenever needed here.
    Returns: Amount of Revenue from the sale with customer
    """

    total_bill = 0

    # Print a message "Now serving customer: X" where X is the current customer's name
    print("Now serving customer: %s" %(customer_name))

    # Call the get_order_qty and save the value to order_qty
    order_qty = 0
    order_qty = get_order_qty(customer_name)

    # For Each order you need to get a flavor, and size
    for order in range(order_qty):
        print("Order No.:", order + 1)
        # Write code to get the ice cream flavor for this order
        flavor_chosen = get_ice_cream_flavor(ice_cream_flavors)

        # Write code to get the ice cream size for this order
        size_chosen = get_ice_cream_size(ice_cream_sizes)

        # Write code to get the price for this order
        order_cost = get_ice_cream_order_price(size_chosen, ice_cream_prices, ice_cream_sizes)

        # Update the total_bill
        total_bill += order_cost

        # Print the details for this order
        #   Hint: See https://www.w3schools.com/python/python_string_formatting.asp for string formatting examples on rounding to 2 decimal places
        print("You ordered a %s %s for $%.2f" %(size_chosen, flavor_chosen, order_cost))
        
    # Print the customer's total_bill
    print("Your total bill is: $%.2f" %(total_bill))

    #   Once orders are all taken, the customer should be asked if they still want to Pay or Cancel
    #  "Would you like to pay or cancel the order (p/c)? "
    #   Hint: Use the get_first_letter_of_user_input() Re-prompt if answer does not start with 'p' or 'c'
    while True:
        abd_flag = get_first_letter_of_user_input("Would you like to pay or cancel the order (p/c)? ")
        if abd_flag == "p":
            return total_bill
        elif abd_flag == "c":
            return 0



def get_first_letter_of_user_input(question):
    """
    Takes in a string as its argument, to be used as the question you want the user to be asked.
    Gets input from the user, removes whitespace and makes all letters lowercase
    Hint: Use the strip() and lower() functions
    Returns: The first letter of the input the user provides. Ask again if the input is empty.
    """

    first_letter = ""
    user_in = input(question + "")
    user_in = user_in.strip(" ")
    user_in = user_in.lower()
    

    if user_in == "":
        return -1
    else:
        first_letter = user_in[0]
        return first_letter


def are_all_customers_served(customer_queue_length):
    """
    If there are no customers in the queue, returns True, and all customers have been served.
    Otherwise, returns False.
    Returns: True or False
    """
    # TODO: Write your code here
    if customer_queue_length == 0:
        return True
    else:
        return False


def print_current_status(customers_served, tracking_revenue):
    """
    Prints a message of how many customers have been served and the total sales of the ice cream stand.
    Hint: See https://www.w3schools.com/python/python_string_formatting.asp for string formatting examples on rounding to 2 decimal places
    No Return, only print statements
    """
    print("\nWe have now served %d customer(s), and received $%.2f in revenue\n" %(customers_served, tracking_revenue))
    


def print_sales_summary(customers_served, tracking_revenue):
    """
    Takes in the arguments customers_served and tracking_revenue. Prints both
    arguments as strings to let the user know what those values are.
    Output should look something like:
        Total customers served: 3
        Total sales           : $xx.xx
    Hint: See https://www.w3schools.com/python/python_string_formatting.asp for string formatting examples on rounding to 2 decimal places
    No Return, only print statements
    """
    print("Customers served: %d" %(customers_served))
    print("Total revenue: $%.2f" %(tracking_revenue))

    


def random_queue_length():
    """
    Takes no arguments.
    Uses the imported randint function to generate a random integer between 2 and 5 inclusive.
    Hint: See https://www.w3schools.com/python/ref_random_randint.asp
    Returns: The resulting random integer.
    """
    return randint(1,5)
    


def main():
    """
    Lists of available flavors, sizes and prices. DO NOT CHANGE.
    For sizes and prices, we will use the following convention:
    Index 0 for Small
    Index 1 for Medium
    Index 2 for Large
    """
    ice_cream_flavors = ['Vanilla', 'Chocolate', 'Strawberry']
    ice_cream_sizes = ['Small', 'Medium', 'Large']
    ice_cream_prices = [4.99, 7.49, 8.49]

    #List of names of possible customers
    customer_names = ["Alice", "Bob", "Charlie", "Dan", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy"]

    program_running = True
    while program_running:
        # set shop to open
        input('Press any key to open the shop! ')
        queue_is_open = True

        #  Call the print_welcome_and_menu function with the parameters in the following order -
        #  ice_cream_flavors, ice_cream_sizes, ice_cream_prices
        print_welcome_and_menu(ice_cream_flavors, ice_cream_sizes,ice_cream_prices)

        # set initial values
        tracking_revenue = 0

        # will hold the list of names of the customers in the queue
        customers_in_queue = []
        customers_served = 0

        # Call the random_queue_length function and save the result to num_of_customers_in_queue
        num_of_customers_in_queue = 0
        num_of_customers_in_queue = random_queue_length()

        # Print how many customers are in the queue
        print("Num of customers in queue: %d" %(num_of_customers_in_queue))

        #   all the imported choice function to generate a random name from customer_names.
        #   Then, append each name to the end of the customers_in_queue list.
        #   The total number of customer names added should be equal to num_of_customers_in_queue
        #   Hint: See https://www.w3schools.com/python/ref_random_choice.asp
        #   Note: It is OK to have duplicate names in the queue.
        for i in range(num_of_customers_in_queue):
            customers_in_queue.append(choice(customer_names))
            # print(customers_in_queue)

        while queue_is_open:
            #  Extract the first customer (index 0) from the customers_in_queue and save it to
            #  the current_customer_name variable.
            #  After extraction, the customer should now be removed from the customers_in_queue list.
            #  Hint: Use the pop function with an index argument
            current_customer_name = ""
            current_customer_name = customers_in_queue[0]
            customers_in_queue.pop(0)

            # Take a customer at the window and update the revenue by calling the take_customer_order function
            tracking_revenue += take_customer_order(current_customer_name, ice_cream_flavors, ice_cream_sizes, ice_cream_prices)

            # Update the customers_served variable
            customers_served += 1

            # Call the print_current_status
            print_current_status(customers_served, tracking_revenue)

            #  Call the are_all_customers_served(customer_queue_length) function to check if there are any more
            #  customers in the queue.
            #  If False, continue the loop.
            #  If True, call the print_sales_summary(customers_served, tracking_revenue) and close the queue
            if are_all_customers_served(len(customers_in_queue)) == True:
                queue_is_open = False
                print_sales_summary(customers_served, tracking_revenue)


        #  Ask if you want to open the ice cream stand again "Do you want to open again (y/n)? "
        #  Hint: Use the get_first_letter_of_user_input function
        #  Update the program_running variable if you get a valid answer either 'y' or 'n'
        #  Otherwise, re-prompt until a valid answer is given
        while True:
            rst_flag = get_first_letter_of_user_input("Do you want to open again (y/n)? ")
            if rst_flag == 'n':
                return 0 # quit main function
            elif rst_flag == "y":
                break

if __name__ == '__main__':
    main()