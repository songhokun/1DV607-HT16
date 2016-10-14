README

This workshop 2 is based on MODEL-VIEW model, without CONTROLLER.
We have created two views: one is console based and the other is GUI-based by Java-FX.
In order to run Java-FX, JDK 1.8 or higher is required. You may even need to install e(fx)clipse if you are running on eclipse.
If you have problems to set up your IDE, we also included our runnable JAR file in our folder 'GUI_Runnable_Jar_File'.

EDITED: In order to execute Java programme it requires Java runtime environment.

If you want to execute the programme, run ConsoleMain or GUI_Main inside /src/main.
We store information in Members.txt and Boats.txt inside the project directory (default project path of IDE).

Should you have questions contact to either,
1. Sarpreet Singh Buttar sb223ce [at] student.lnu.se
2. Songho Lee sl222xk [at] student.lnu.se

Enjoy the programme!

Växjö, October 6, 2016

Sarpreet Singh Buttar
Songho Lee.

++++ CHANGE LOG ++++
After submission of workshop 2 for peer reviews, we have made following changes:
1. Some responsibilities of the class 'Registery' inside model are now moved to 'Member' class.
The shifted methods are following: registerBoat, updateBoat, deleteBoat.
The decision is made in order to make our model to follow more GRASP principle;
particularly information expert, as Member class holds a list of 'Boat'. (2016-10-12)

2. Beppe Karlsson pointed out in his peer review that Model is somewhat dependent in View as we have all validator methods of user's input in view side and therefore it may lead to hidden dependency. Therefore we are shifting logical part of validator (e.x. checking correct date in personal number, or reasonable length of boat) to model side, and view is only checking if provided input is characters or numbers depends on the situation. (2016-10-12)

3. Beppe Karlsson discovered an exception that is not being handled after catched inside read-write-file class.
Thus we changed throw - catch part so that model throws an error and view prints an error message correspondingly. (2016-10-13)

4. After we migrated logical validator methods into model side, we are now using throw-catch surroundings to display error message. (2016-10-12)

5. Probable code duplications in console class is resolved by merging up the validator methods, and removing global variable for handling user input. This means we removed method checkBoatType and checkBoatIndex, and we replaced those with checkIndex method. (2016-10-13)

5-1. Previously, methods which get input from users were 'void' type so  stored input into global variable. Now this behaviour is replaced by returning the input by methods.

5-2. Over-usage of registry.lookUpMember is removed. Since we are asking user to enter a member id for one time, there is no need to use this methods repeatedly. In stead, we added a member as an argument to VIEW's methods.

6. Mechanism of selective information update is changed. (2016-10-13)

7. For-loop of reading file is replaced to extended-for loop. (2016-10-13)

8. Operation of quit is renamed to save and quit since we save all changes when we quite the programme in console. (2016-10-14)
