cr = '#'
import shapes

def draw_square(size):
    for h in range(size):
        for w in range(size):
            print(cr, end='')
            print()

def draw_rect(height, width):
    for h in range(height):
            for w in range(width):
            print(cr, end='')
            print()

shapes.draw_square(3)