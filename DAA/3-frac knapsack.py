
def knapsack(profit, weight, capacity):
    items = []

    for i in range(len(profit)):
        ratio = profit[i] / weight[i]
        items.append((ratio, profit[i], weight[i]))

    items.sort(reverse=True)

    total_profit = 0

    for ratio, p, w in items:
        if capacity >= w:
            total_profit += p
            capacity -= w
        else:
            total_profit += ratio * capacity
            break

    return total_profit


n = int(input("Enter number of items: "))

profit = []
weight = []

for i in range(n):
    p = int(input("Enter profit: "))
    w = int(input("Enter weight: "))
    profit.append(p)
    weight.append(w)

capacity = int(input("Enter capacity: "))

print("Maximum Profit:", knapsack(profit, weight, capacity))

#time complexity: O(n log n)
#space complexity: O(n)
