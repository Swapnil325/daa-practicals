
def knapsack(profit, weight, capacity, n):
    dp = [[0 for j in range(capacity + 1)] for i in range(n + 1)]

    for i in range(1, n + 1):
        for j in range(1, capacity + 1):

            if weight[i-1] <= j:
                dp[i][j] = max(
                    profit[i-1] + dp[i-1][j-weight[i-1]],
                    dp[i-1][j]
                )
            else:
                dp[i][j] = dp[i-1][j]

    return dp[n][capacity]


n = int(input("Enter number of items: "))

profit = []
weight = []

for i in range(n):
    p = int(input("Enter profit: "))
    w = int(input("Enter weight: "))
    profit.append(p)
    weight.append(w)

capacity = int(input("Enter capacity: "))

print("Maximum Profit:", knapsack(profit, weight, capacity, n))

#time complexity: O(n*capacity)
#space complexity: O(n*capacity)
