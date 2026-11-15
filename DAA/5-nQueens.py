
def is_safe(board, row, col, n):
    for i in range(row):
        if board[i][col] == 1:
            return False

        if abs(row - i) == abs(col - board[i].index(1)):
            return False

    return True


def solve(board, row, n):
    if row == n:
        return True

    for col in range(n):
        if is_safe(board, row, col, n):
            board[row][col] = 1

            if solve(board, row + 1, n):
                return True

            board[row][col] = 0

    return False


n = int(input("Enter number of Queens: "))
board = [[0 for j in range(n)] for i in range(n)]

col = int(input("Enter first Queen column (0 to n-1): "))

board[0][col] = 1

if solve(board, 1, n):
    print("Final N-Queens Matrix:")
    for row in board:
        print(row)
else:
    print("No solution exists")

#time complexity: O(n!)
#space complexity: O(n^2)