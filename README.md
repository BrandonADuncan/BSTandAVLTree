# Project 2

Project 2 is focused on performance using our driving main method MainTimer.java
The first iteration uses a Binary Search Tree Map and Set in conjunction with our 
DEA class. The objective of the project was to read in an old data set of Opiod
purchases for a region (.tsv file), put them into a map using the Buyer's name
and additional info as the key, with a List of purchases as the value. Then the
method flagSuspiciousBuyers reads a new data set and compares it to our old data map.
If any of the new purchases include either a new buyer, or a purchase greater than 
the mean + 4* the Standard Deviation it would be flagged as suspicious and added 
to a set to be returned and printed. If flagged for an unusually large purchase, the 
transaction ID will also be included. The main objective of this project is to
perfom these actions in the most efficient manner using a preferred data structure.
the data sets should be in .tsv format.

## Usage

```java 

usage: MainTimer historicFilename newFilename

DEA.readHistoricData(filename) # returns a balanced AVL Tree Map.

DEA.flagSuspiciousBuyers(filename) # returns a set of suspiciouis buyers based on new buyers or purchases above the standard deviation
```

## Sources
    [IC312](https://www.usna.edu/Users/cs/crabbe/IC312/current/project/opiates/ "Course Website")

## Performance
    The AVLTree implementation of this project is designed to be balanced in order to allow our insert and get functions to be O(log(n)). Additionally, I used ArrayList's instead of LinkedList's due to the performance advantages. 

| Function        | ArrayList          | AVLTree |
| ------------- |:-------------:| -----:|
| insert(k,e)/add()      | O(n)| O(log(n)) |
| get(k)/get()     | O(1)      |   O(log(n)) |
| size() | O(1)     |    O(1) |


## Optimization Techniques
```
BLUF: I did not spend a lot of time optimizing my methods/algorithims. However I did find some interesting things.

1. I used VisualVM to profile the CPU time during the running of the program. The glaring hot zones occured in the ArcosIterator.next(), scanner.nextLine(),and AVLMap.set(). for the obvious reason that these occur a lot in a large data set

2. Using AVLMap.get()== null as a condition compared to find == false, proved to be slightly faster for whatever reason.

3. java.lang.String.split() occupies a suprsing amount of time.

4. I believe the key to reducing this runtime is to be an optimization of arcosReader/ figuring out a faster way to split these headings. 
```

## Fun Facts:
```
1. After my BST implementation of this project I decided I wanted to try a new IDE (Eclipse vs VsCode). However as I was messing around with transferring my project files I lost all my data and had to start over...

2. The vast majority of time spent on this project was with nullPointerExceptions... Additionally I probably spent 4 hours today switching my methods around only to realize one of my ">" symbols was supposed to be reversed in my conditions for rotation.

3. The AVLTree implementation was not difficult at all, the course lecture notes provided solid pseudo code.

4. The BST Implementation was INCDREDIBLY helpful, being able to have confident working methods in the DEA class meant all I had to do was swap out my Map and Set types when the tim arrived and it was much easier to debug. 

5. I have the Java 14 JDK installed on my computer for whatever reason so I did not have access to -Xprof which apparently oracle deemed irrelevant in today's age.

6. I really enjoyed seeing this come together, and visualizing the different trees in my head as I wrote the code. It was extremley rewarding to put the big data set in and recieve positive results. 
```

