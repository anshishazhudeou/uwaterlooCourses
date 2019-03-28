/*
CS 349 Code Examples: X Windows and XLib

    drawing.min     Demos drawing and graphics context (minimal version).

- - - - - - - - - - - - - - - - - - - - - -

See associated makefile for compiling instructions

*/

#include <cstdlib>
#include <iostream>
#include <X11/Xlib.h>

#include <unistd.h> // needed for sleep

Display* display;
Window window;

int main( int argc, char* argv[] ) {
    // see openwindow.min for the setup code ...
    display = XOpenDisplay("");
    if (!display) exit (-1);
    int screen = XDefaultScreen(display);
    int w = 300;
    int h = 300;
    window = XCreateSimpleWindow(display,
                                 DefaultRootWindow(display),
                                 10, 10, w, h, 2,
                                 XBlackPixel(display, screen),
                                 XWhitePixel(display, screen));
    XMapRaised(display, window);
    XFlush(display);

    // give server time to setup
    sleep(1);  


    // drawing demo with graphics context here ...
    GC gc = XCreateGC(display, window, 0, 0);       // create a graphics context
    XSetForeground(display, gc, XBlackPixel(display, screen));
    XSetBackground(display, gc, XWhitePixel(display, screen));
    XSetFillStyle(display,  gc, FillSolid);
    XSetLineAttributes(display, gc,
                       3,       // 3 is line width
                       LineSolid, CapButt, JoinRound);         // other line options

    // draw some things
    XDrawLine(display, window, gc, 10, 10, w - 10, h - 10);
    XFillRectangle(display, window, gc, 50, 50, w - (2 * 50), h - (2 * 50));
    XSetForeground(display, gc, XWhitePixel(display, screen));
    XDrawLine(display, window, gc, w - 10, 10, 10, h - 10);

    XFlush(display);

    std::cout << "ENTER2exit"; std::cin.get(); // wait for input
    XCloseDisplay(display);
}
