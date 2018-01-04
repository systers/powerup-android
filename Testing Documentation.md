# PowerUp: Current Testing Status

## Alpha Testing
PowerUp currently has tests for the following activities. The tests use the Robolectric framework, which tests the application without
using an emulator and allows a test style that is similar to black box testing.
* MineSweeperGameActivity ([MineSweeperGameTests.java](https://github.com/systers/powerup-android/blob/GSoC17/PowerUp/app/src/test/java/powerup/systers/com/powerup/test/MinesweeperGameTests.java)):
all tests are successful.
* ProsAndConsActivity ([ProsConsTest.java](https://github.com/systers/powerup-android/blob/GSoC17/PowerUp/app/src/test/java/powerup/systers/com/powerup/test/ProsConsTests.java)): 
all tests are successful.
* SinkToSwimGame ([SinkToSwimTests.java](https://github.com/systers/powerup-android/blob/GSoC17/PowerUp/app/src/test/java/powerup/systers/com/powerup/test/SinkToSwimTests.java)): 
all tests are successful.
* VocabMatchEndActivity ([VocabMatchEndTests.java](https://github.com/systers/powerup-android/blob/GSoC17/PowerUp/app/src/test/java/powerup/systers/com/powerup/test/VocabMatchEndTests.java)): 
all tests are successful.
* VocabMatchGameActivity ([VocabMatchTests.java](https://github.com/systers/powerup-android/blob/GSoC17/PowerUp/app/src/test/java/powerup/systers/com/powerup/test/VocabMatchTests.java)): 
all tests are successful.

When running the tests with code coverage, however, only 12% of the classes and 16% of the lines are covered. Code coverage will have to 
be improved so that each class is being tested.

![Code Coverage](https://c1.staticflickr.com/5/4638/39470115442_0f37262cff_b.jpg)

## System and Integration Testing
PowerUp currently does not have any system or integration tests, although some of the tests above, such as VocabMatchEndTests.java, do 
check if the correct activity is launched next. 

## Performance and Stress Testing
Performance and stress tests still need to be written for PowerUp. There are, however, app performance reports that may be generated using 
various tools in Android Studio, which already indicate battery and CPU usage. 

Android Profiler data upon completion of game (358.98 MB of memory have been used)
![Android Profiler](https://c1.staticflickr.com/5/4596/38604427535_c0ea2968b8_b.jpg)
