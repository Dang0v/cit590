class Number:
    def __init__(self, number) -> None:
        self.number = number
    def __add__(self, number2) -> None:
        return self.number * number2.number
    
a = Number(5)
b = Number(7)
print (a+b)