#*********functions**********
def power (n,e):
    if (e == 0):
        return 1 
    return n * power(n,e-1)

#**********MAIN**********

print(power(14,5))
