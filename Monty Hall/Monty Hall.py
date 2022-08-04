import random
import math

def empty_doors(n):
    return [2 for i in range(n)]
    #2 = empty
    #0 = goat
    #1 = car
    #3 = revealed goat

def random_fill(a):
    correct_door = random.randint(0,len(a) - 1)
    a[correct_door] = 1
    for i in range(len(a)):
        if a[i] != 1:
            a[i] = 0

def choose_random(a):
    return random.randint(0, len(a) - 1)

def kcount(k, li):
    res = 0
    for i in li:
        if i == k:
            res += 1
    return res

def goatindex(a, choice):
    res = []
    for i in range(len(a)):
        if a[i] == 0 and i != choice:
            res.append(i)
    return res
        
def reveal_goat(a, choice):
    for i in random.sample(goatindex(a, choice), k=(len(goatindex(a, choice)))):
        #print(a)
        if kcount(0, a) > 1:
            a[i] = 3

def switch(a, choice):
    for i in range(len(a)):
        if a[i] != 3 and i != choice:
            #print('second choice: ' + str(i) + ' (which is ' + str(a[i]) + ')')
            return a[i]

def switchrun(n):
    a = empty_doors(n)
    random_fill(a)
    choice = choose_random(a)
    #print(a)
    #print('first choice: ' + str(choice) + ' (which is ' + str(a[choice]) + ')')
    reveal_goat(a, choice)
    #print(a)
    return switch(a, choice)

def noswitchrun(n):
    a = empty_doors(n)
    random_fill(a)
    #print(a)
    choice = choose_random(a)
    #print(choice)
    return a[choice]

def Demonstration(runtype, doorcount, testcount):
    '''runtype: switchrun or noswitchrun
    doorcount >= 3
    testcount > 0 (10000+ recommended)'''
    if testcount >= 10000:
        acc = 'Good'
    elif testcount < 10000:
        acc = 'Low'
    print('--------------------------------')
    print('Accuracy Level: ' + str(testcount) + ' (' + acc + ')')
    print('--------------------------------')
    if runtype == switchrun:
        hypo = (doorcount-1)/doorcount
        print('Hypothesis: '+ str(round(hypo*100,4)) + '% (' + str(doorcount-1) + '/' + str(doorcount) + ')' )
    elif runtype == noswitchrun:
        hypo = 1/doorcount
        print('Hypothesis: '+ str(round(hypo*100,4)) + '% (1/' + str(doorcount) + ')' )
    res = 0
    for i in range(testcount):
        res += runtype(doorcount)
    result = res/testcount
    print('Result: ' + str(round(result*100,4)) + '%')
    print('Off by: ' + str(round(abs(result - hypo)*100,4)) + '%')

status = True
while status:
    doorcheck = False
    testcheck = False
    print('-------------------------------')
    print('Switch or not?')
    rtype = input().lower()
    while doorcheck == False:
        print('Door count? (Minimum 3)')
        dcount = input()
        if dcount == '' or ord(dcount[0]) < 48 or ord(dcount[0]) > 57:
            print('Enter a positive integer please')
        else:
            dcount = int(dcount)
            doorcheck = True
    while testcheck == False:
        print('Test count? (Recommended: 10000+)')
        tcount = input()
        if tcount == '' or ord(tcount[0]) < 48 or ord(tcount[0]) > 57:
            print('Enter a positive integer please')
        else:
            tcount = int(tcount)
            testcheck = True
    if rtype == 'switch' or rtype == 'switchrun' or rtype == 'yes' and dcount >= 3 and tcount >= 1:
        Demonstration(switchrun, dcount, tcount)
    elif rtype == 'noswitch' or rtype == 'no' or rtype == 'not' or rtype == 'noswitchrun' and dcount >= 3 and tcount >= 1:
        Demonstration(noswitchrun, dcount, tcount)
    else:
        print('Input error, enter the parameters as instructed please')
    print('')
    print('Press enter to try another simulation, anything else and enter to quit')
    stop = input()
    if stop != '':
        status = False
