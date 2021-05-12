
# Overview

My distributed systems assignment is multithreaded server application that has two functionalities:
* Generate a password
* Encrypt a given password. 

The idea behind my assignment was to solve a reoccurring problem that often happens when internet users attempt to register on websites. Often times when you quickly register for a website for something that you are going to use maybe once or twice, it is dangerous to reuse a password that you use for more private and confidential accounts. Therefore, the best solution is to make a new password on the spot. However, nowadays with increasing privacy concerns, it can be difficult to think of a password on the spot that fulfills all the requirements of a website. Therefore, my application can solve this problem and generate a password on the spot as well as encrypt any other password you want to use.

## How to run the application

1) When you start up the application, you are met with a simple interface asking you to select a specific functionality of the application: either password generator, or password encryption.



2) If you select password encryption, you will be met with the first novel feature, which prompts the user to select a difficulty (difficulty choice being the first novel feature). Once a difficulty has been selected, it will prompt the user to select a password length and will then display the generated password and print to file:
“list.txt” (2nd novel feature).


3) The program will then terminate.

4) The second functional feature is accessed by selecting it as an option when you first run the program.

5) The program will prompt the user to enter a password, and then will encrypt that password into a 128-bit MD5 hashing algorithm.
