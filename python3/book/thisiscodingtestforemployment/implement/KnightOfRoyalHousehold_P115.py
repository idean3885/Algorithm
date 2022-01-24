h, v = input()
h = ord(h) - ord('a') + 1
v = int(v)

dhv = [(2, 1), (2, -1), (-2, 1), (-2, -1), (1, 2), (1, -2), (-1, 2), (-1, -2)]

count = 0
for hv in dhv:
    mh = h + hv[0]
    mv = v + hv[1]

    if mh < 1  or 8 < mh or mv < 1 or 8 < mv:
        continue
    count += 1

print(count)