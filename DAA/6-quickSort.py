
import random

def quicksort(arr, randomized=False):
    if len(arr) <= 1:
        return arr

    if randomized:
        pivot = random.choice(arr)
    else:
        pivot = arr[0]

    left = []
    middle = []
    right = []

    for x in arr:
        if x < pivot:
            left.append(x)
        elif x == pivot:
            middle.append(x)
        else:
            right.append(x)

    return quicksort(left, randomized) + middle + quicksort(right, randomized)


n = int(input("Enter number of elements: "))

arr = []
for i in range(n):
    x = int(input("Enter element: "))
    arr.append(x)

print("Deterministic Quick Sort:", quicksort(arr))
print("Randomized Quick Sort:", quicksort(arr, True))

#time complexity: O(n log n) on average, O(n^2) in the worst case
#space complexity: O(n) for the recursive stack