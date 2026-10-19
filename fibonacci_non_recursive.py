"""Non-Recursive Fibonacci Program

Simple script that prints the first n terms of the Fibonacci sequence.
"""

def main():
    try:
        n = int(input("Enter number of terms: "))
    except ValueError:
        print("Please enter an integer.")
        return

    if n <= 0:
        print("Please enter a positive integer.")
        return

    a, b = 0, 1
    print("Fibonacci Series:")
    for _ in range(n):
        print(a, end=" ")
        a, b = b, a + b
    print()


if __name__ == "__main__":
    main()
