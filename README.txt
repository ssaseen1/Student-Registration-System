README file:
------------
Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=reg-preference.txt -Darg1=add-drop.txt -Darg2=output.txt -Darg3=NUM_THREADS -Darg4=DEBUG_VALUE

-----------------------------------------------------------------------

## To create tarball for submission

tar -czvf shali_saseendran_assign_2.tar.gz shali_saseendran_assign_2

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense."

[Date: 07-03-2017 ] 

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)

Data Structure used: Hashtable
Advantage: synchronized (thread safe).
Space Complexity: O(n)
Time Complexity: O(1)

TreeMap data Structure is used to sort the content with respect to studentIds to 
print it on Screen and output file.
-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).

NA

