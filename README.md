# DOCUMENTATION
## DRABBLES
<p>Recently, I have been having difficulty in obtaining the hexadecimal values of the colors on screen for development purposes
so I thought, why not write a program for myself since I am able to do so? And thus the Mantis Color Picker was born...</p>

## NAME
<p>Mantis Color Picker (Mantis shrimps are really colorful... Lookit that!)</p>

<p align="center"><a data-flickr-embed="true"  href="https://www.flickr.com/photos/ursonate/4481222867" title="mantis shrimp"><img src="https://c4.staticflickr.com/3/2717/4481222867_6b301c0a11.jpg" width="500" height="333" alt="mantis shrimp"></a><script async src="//embedr.flickr.com/assets/client-code.js" charset="utf-8"></script></p>

## DESCRIPTION
A color picker tool programmed in Java.

## USAGE
<p>This program was written in Java and packaged into a .jar file. Ensure that you have Java(TM) Platform installed in your system if you would like to run it.</p>

1. Download the .jar file from Downloadables/Mantis.jar
2. Open up the program.
3. Hold CTRL + left-click on anywhere on the screen (supports dragging as well).
4. The hexadecimal is automatically copied onto your clipboard.

(Note: The program only works if it is focused)

![Alt text](/Downloadables/ColorPicker_Demo.png "Mantis Color Picker Demo")

## HOW DOES IT WORK?
When CTRL is first pressed, a screenshot of your current screen is saved and an (almost) transparent window is created. The transparent window has an alpha of 1 (a transparent window cannot intercept mouse activities). A mouse listener is attached to this window in order to detect the user's mouse activities. The presence of the extra window is apparent on your task bar:

![Alt text](/Downloadables/ColorPicker_HowItWorks.png "Mantis Color Picker Workings")

Now when you click anywhere on the screen, the coordinates of your mouse (X, Y) is recorded. The program proceeds to obtain the color of the pixel at (X, Y) from the saved screenshot, translate it into its hexadecimal value, paint a 100x100 colored square of the same color (for you to see!), and copy the hexadecimal value to your clipboard.

Once CTRL is released, the window is disposed.

## KNOWN ISSUES
Tested and working on Windows 10.

## CONTACT
If there is an issue, do contact me at tseqin@gmail.com

## LAST EDITED
9/24/2016
