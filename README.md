
##Object Tracking in Java
In order to use this code you must properly install the Open Computer Vision Library for Java on your system. 

This package was built at a hackathon, and aims to track the center of certain colored objects on screen.  The program is best used while adjusting the slider, which can change the Hue, Saturation and Value variables for the image filter. There are two modes. The default mode is configured for bright orange in a well lit room. It is currently recomended that you configure the slider to your location's lighting. This program works best with sphere or round shape objects. The program connects to the default webcam of your system.

**Usage:**
* This was originally configured using a bright orange ball, of which the default values look for.
* To generate new values hit the ENTER key to change to editable mode, then use the hsv slider to adjust values, these values are printed to the terminal.
* The SHIFT key can be used to switch views of the webcam with the object circled in green and a filtered view of the current object for value adjusting.
* Objects with bright colors work best along with a room with lots of light.
* To exit close the window or hit the escape key.


