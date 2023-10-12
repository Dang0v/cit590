from Expense import *


class ExpensesLoader(object):
    """A class for loading expenses from a file.
    """

    # We do not have an __init__ function and will call the default constructor

    def import_expenses(self, expenses, file):
        """Reads data from the given file and stores the expenses in the given expenses dictionary, where the expense
        type is the key and the value is an Expense object with the parameters expense type and total amount for that
        expense type.

        The same expense type may appear multiple times in the given file, so add all the amounts for the same
        expense types.

        Ignore expenses with missing amounts. If a line contains both an expense type and an expense amount,
        they will be separated by a colon (:).

        You can assume that if they exist, expense types are one-word strings and the amounts are numerical and can
        be casted to a float data type.

        Strip any whitespace before or after the expense types and amounts. Blank lines should be ignored.

        Expenses are case-sensitive. "coffee" is different from "Coffee".

        This method will be called twice in the main function in expenses.py with the same dictionary, but different
        files.

        This method doesn't return anything.  Rather, it updates the given expenses dictionary based
        on the expenses in the given file.

        For example, after loading the expenses from the file, the expenses dictionary should look like
        this: {'food': Expense('food', 5.00), 'coffee': Expense('coffee', 12.40),
               'rent': Expense('rent', 825.00), 'clothes': Expense('clothes', 45.00),
               'entertainment': Expense('entertainment', 135.62), 'music': Expense('music', 324.00),
               'family': Expense('family', 32.45)}

        Note: You are not expected to handle negative numbers in your code
        """

        f = open(file)
        lines = f.readlines()
        # strip all the empty lines
        new_lines = []
        for line in lines:
            if line.strip():
                new_lines.append(line.strip("\n"))
        # each line will be split into a single list
        lines_split = []
        for i in new_lines:
            temp_list = i.split(":")
            # strip all the spaces
            for k, j in enumerate(temp_list):
                temp_list[k] = j.strip(" \t")
            # append all these to a big list
            # will not append if there is no amount
            if temp_list[1] != "":
                # convert string to float
                temp_list[1] = float(temp_list[1])
                lines_split.append(temp_list)
        # process these and add to dictionary
        for i in lines_split:
            if i[0] in expenses:
                expenses.get(i[0]).add_amount(i[1])
            else:
                # will not add a new expense type when the amount is 0 or negative
                if i[1] > 0:
                    expenses[i[0]] = Expense(i[0], i[1])
        f.close()
