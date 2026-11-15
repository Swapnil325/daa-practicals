
import heapq

def huffman(chars, freq):
    heap = []

    for i in range(len(chars)):
        heapq.heappush(heap, (freq[i], chars[i]))

    while len(heap) > 1:
        a = heapq.heappop(heap)
        b = heapq.heappop(heap)

        heapq.heappush(heap, (a[0] + b[0], [a[1], b[1]]))

    return heap[0][1]

def print_codes(tree, code=""):
    if type(tree) == str:
        print(tree, ":", code or "0")
        return

    print_codes(tree[0], code + "0")
    print_codes(tree[1], code + "1")

chars = ['a', 'b', 'c', 'd', 'e', 'f']
freq = [5, 9, 12, 13, 16, 45]

tree = huffman(chars, freq)
print_codes(tree)

#time complexity: O(n log n)
# space complexity: O(n) 