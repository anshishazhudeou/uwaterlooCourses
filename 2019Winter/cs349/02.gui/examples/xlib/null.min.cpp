/*
CS 349 Code Examples: X Windows and XLib

    null        Creates and destroys a display (a good first test to see
                if X Windows is working).

- - - - - - - - - - - - - - - - - - - - - -

See associated makefile for compiling instructions

*/

#include <cstdlib>
#include <iostream>
#include <X11/Xlib.h> // main Xlib header

Display* display;

int main() {
    display = XOpenDisplay(""); // open display (using DISPLAY env var)
    if (display == NULL) {
        std::cout << "error\n";
        exit (-1);
    } else {
        std::cout << "success!: ";
    	XCloseDisplay(display); // close display
    }
}
