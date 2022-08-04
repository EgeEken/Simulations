import math
import numpy as np
import matplotlib.pyplot as plt

class canvas:
    def __init__(self, width, height, c):
        self.x = (0 - width/2, width/2)
        self.y = (0 - height/2, height/2)
        self.color = c
    
    def show(self):
        plt.show()

class square:
    def __init__(self, x, y, length, c):
        self.center = (x, y)
        self.length = length
        self.color = c

    def draw(self, canvas):
        plt.gca().add_patch(plt.Rectangle((self.center[0] - self.length/2, self.center[1] - self.length/2), self.length, self.length, fill=False, edgecolor=self.color))
        plt.axis('scaled')
        plt.axis([canvas.x[0], canvas.x[1], canvas.y[0], canvas.y[1]])

class circle:
    def __init__(self, x, y, r, c):
        self.center = (x, y)
        self.radius = r
        self.color = c

    def draw(self, canvas):
        plt.gca().add_patch(plt.Circle(self.center, self.radius, color=self.color))
        plt.axis('scaled')
        plt.axis([canvas.x[0], canvas.x[1], canvas.y[0], canvas.y[1]])

class point:
    def __init__(self, x, y, c):
        self.coords = (x, y)
        self.color = c

    def is_in_circle(self, circle):
        return (math.sqrt((self.coords[0] - circle.center[0])**2 + (self.coords[1] - circle.center[1])**2) <= circle.radius)

    def draw(self, canvas):
        plt.gca().add_patch(plt.Circle(self.coords, 0.1, color=self.color))
        plt.axis('scaled')
        plt.axis([canvas.x[0], canvas.x[1], canvas.y[0], canvas.y[1]])

def random_point_in_square(Square):
    return (np.random.uniform(Square.center[0] + 0.000001 - Square.length/2, Square.center[0] - 0.000001 + Square.length/2), 
                np.random.uniform(Square.center[1] + 0.000001 - Square.length/2, Square.center[1] - 0.000001 + Square.length/2))

def main():
    print("--------------------------------------------------------")
    while True:
        try:
            width = int(input("Enter width of canvas: "))
            height = int(input("Enter height of canvas: "))
            c = input("Enter color of canvas: ")
            Canvas = canvas(width, height, c)
            break
        except:
            print("Invalid input, please try again")
    print("--------------------------------------------------------")
    while True:
        try:
            c = input("Enter color of square: ")
            length = min(width, height) * 0.8
            Square = square(0, 0, length, c)
            Square.draw(Canvas)
            break
        except:
            print("Invalid input, please try again")
    print("--------------------------------------------------------")
    while True:
        try:
            c = input("Enter color of circle: ")
            Circle = circle(0, 0, length/2, c)
            Circle.draw(Canvas)
            break
        except:
            print("Invalid input, please try again")
    print("--------------------------------------------------------")
    while True:
        try:
            count = int(input("Enter number of points: "))   
            c = input("Enter color of points: ")
            points = [random_point_in_square(Square) for i in range(count)]
            inside = 0
            for i in points:
                Point = point(i[0], i[1], c)
                if Point.is_in_circle(Circle):
                    inside += 1
                Point.draw(Canvas)
            break
        except:
            print("Invalid input, please try again")
    print("Number of points inside circle: " + str(inside))
    print("Number of total points: " + str(count))
    print("| PI Estimation: " + str(round(4*inside/count, 4)) + " |")
    Canvas.show()
    print("--------------------------------------------------------")

def continueornot():
    main()
    while True:
        c = input("Continue? (\"done\", \"stop\", or \"no\" to exit) : ")
        if c.lower() in {"done", "stop", "no", "n"}:
            a = input("Exiting program, press enter to exit")
            break
        else:
            main()


continueornot()
