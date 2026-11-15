
def knapsack(profit, weight, capacity, n):
    items = []

    for i in range(n):
        ratio = profit[i] / weight[i]
        items.append((ratio, profit[i], weight[i]))

    items.sort(reverse=True)

    best = 0
    queue = [(0, 0, 0, 0)]

    while queue:
        i, p, w, bound = queue.pop()

        if i == n:
            continue

        ratio, value, wt = items[i]

        # Include item
        if w + wt <= capacity:
            new_p = p + value
            new_w = w + wt
            best = max(best, new_p)

            if i + 1 < n:
                queue.append((i + 1, new_p, new_w, 0))

        # Exclude item
        if i + 1 < n:
            queue.append((i + 1, p, w, 0))

    return best


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

#time complexity: O(2^n)
#space complexity: O(n)
