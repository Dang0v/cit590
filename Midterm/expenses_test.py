import unittest

from ExpensesLoader import *
from ExpensesManager import *


class Expenses_Test(unittest.TestCase):

    def setUp(self):
        """The setUp function runs before every test function."""

        # create expenses dictionary and populate with data
        self.expenses = {'food': Expense('food', 5.00), 'coffee': Expense('coffee', 12.40),
                         'rent': Expense('rent', 825.00), 'clothes': Expense('clothes', 45.00),
                         'entertainment': Expense('entertainment', 135.62), 'music': Expense('music', 324.00),
                         'family': Expense('family', 32.45)}

    def test_import_expenses(self):
        # import expenses files
        expenses = {}

        # create instance of ExpensesLoader class
        expenses_loader = ExpensesLoader()

        # call import_expenses method in ExpensesLoader class
        # to import expense files and store in dictionary
        expenses_loader.import_expenses(expenses, 'expenses.txt')
        expenses_loader.import_expenses(expenses, 'expenses_2.txt')

        # test existing expense amounts
        self.assertAlmostEqual(45, expenses['clothes'].amount)
        self.assertAlmostEqual(12.40, expenses['coffee'].amount)
        self.assertAlmostEqual(135.62, expenses['entertainment'].amount)

    def test_get_expense(self):
        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        # test getting expenses based on expense type
        self.assertAlmostEqual(12.40, expenses_manager.get_expense(self.expenses, "coffee").amount)

        # test non-existing expense types
        self.assertEqual(None, expenses_manager.get_expense(self.expenses, "phone"))

        # Test both existing and non-existing expense types
        self.assertAlmostEqual(5, expenses_manager.get_expense(self.expenses, "food").amount)

        self.assertEqual(None, expenses_manager.get_expense(self.expenses, "abcd"))

    def test_add_expense(self):
        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        # test adding a new expense
        expenses_manager.add_expense(self.expenses, "fios", 84.5)
        self.assertAlmostEqual(84.5, self.expenses.get("fios").amount)

        # insert 2 additional test cases

        # test adding an existing expense
        expenses_manager.add_expense(self.expenses, "food", 5.0)
        self.assertAlmostEqual(10.0, self.expenses.get("food").amount)

        # test adding a negative value to a existing expense
        expenses_manager.add_expense(self.expenses, "clothes", -2)
        self.assertAlmostEqual(45, self.expenses.get("clothes").amount)

        

    def test_deduct_expense(self):
        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        # test deducting from expense
        expenses_manager.deduct_expense(self.expenses, "coffee", .99)
        self.assertAlmostEqual(11.41, self.expenses.get("coffee").amount)

        # test deducting from expense
        expenses_manager.deduct_expense(self.expenses, "entertainment", 100)
        self.assertAlmostEqual(35.62, self.expenses.get("entertainment").amount)

        # insert 2 additional test cases
        # Test deducting too much from expense
        try:
            expenses_manager.deduct_expense(self.expenses, "food", 6)
            # There should be a run time error
        except RuntimeError:
            print('RuntimeError Caught!')

        # Test deducting from non-existing expense
        # Here should be a prompt message
        expenses_manager.deduct_expense(self.expenses, "abcd", 6)
        
        # Test deducting a negtive value
        expenses_manager.deduct_expense(self.expenses, "food", -2)
        self.assertAlmostEqual(5, self.expenses.get("food").amount)



    def test_update_expense(self):
        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        # test updating an expense
        expenses_manager.update_expense(self.expenses, "clothes", 19.99)
        self.assertAlmostEqual(19.99, expenses_manager.get_expense(self.expenses, "clothes").amount)

        # insert 2 additional test cases

        # Test updating an expense with negative value
        expenses_manager.update_expense(self.expenses, "food", -2)
        self.assertAlmostEqual(5, expenses_manager.get_expense(self.expenses, "food").amount)

        # Test updating a non-existing expense
        # Here should be a prompt message
        expenses_manager.update_expense(self.expenses, "abcd", 6)

    def test_sort_expenses(self):
        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        # test sorting expenses by 'expense_type'
        expense_type_sorted_expenses = [('clothes', 45.0),
                                        ('coffee', 12.4),
                                        ('entertainment', 135.62),
                                        ('family', 32.45),
                                        ('food', 5.0),
                                        ('music', 324.0),
                                        ('rent', 825.0)]

        self.assertListEqual(expense_type_sorted_expenses,
                             expenses_manager.sort_expenses(self.expenses, "expense_type"))

        # insert 1 additional test case
        
        # Test sorting expenses by 'amount'
        expense_type_sorted_expenses_amount = [('rent', 825.0),
                                               ('music', 324.0),
                                               ('entertainment', 135.62),
                                               ('clothes', 45.0),
                                               ('family', 32.45),
                                               ('coffee', 12.4),
                                               ('food', 5.0)]
        
        self.assertListEqual(expense_type_sorted_expenses_amount,
                             expenses_manager.sort_expenses(self.expenses, "amount"))

    def test_export_expenses(self):
        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        # test export with existing expense types
        file = 'export1.txt'
        expense_types = ['coffee', 'clothes']
        expenses_manager.export_expenses(self.expenses, expense_types, file)

        # read the exported file
        f = open(file)
        lines = f.readlines()
        f.close()
        self.assertEqual('coffee: 12.40', lines[0].strip())
        self.assertEqual('clothes: 45.00', lines[1].strip())

        # insert 1 additional test case

        # export with a non-existing expnese type
        expense_types = ['coffee', 'clothes', 'sports']
        expenses_manager.export_expenses(self.expenses, expense_types, file)

        f = open(file)
        lines = f.readlines()
        f.close()
        self.assertEqual('coffee: 12.40', lines[0].strip())
        self.assertEqual('clothes: 45.00', lines[1].strip())
        self.assertEqual(2, len(lines))



if __name__ == '__main__':
    unittest.main()
