/*
CS 349 Code Examples: X Windows and XLib

    openwindow     Opens a single blank window

- - - - - - - - - - - - - - - - - - - - - -

See associated makefile for compiling instructions

*/

#include <cstdlib>
#include <iostream>
#include <list>

/*
 * Header files for X functions
 */
#include <X11/Xlib.h>
#include <X11/Xutil.h>

using namespace std;


/*
 * Information to draw on the window.
 */
struct XInfo {
    Display*    display;
    int      screen;
    Window   window;
};


/*
 * Function to put out a message on error exits.
 */
void error( string str ) {
    cerr << str << endl;
    exit(0);
}


/*
 * Create a window
 */
void initX(int argc, char* argv[], XInfo& xinfo) {
    XSizeHints hints;

    hints.x = 100;
    hints.y = 100;
    hints.width = 400;
    hints.height = 400;
    hints.flags = PPosition | PSize;

    /*
    * Display opening uses the DISPLAY  environment variable.
    * It can go wrong if DISPLAY isn't set, or you don't have permission.
    */
    xinfo.display = XOpenDisplay( "" );
    if ( !xinfo.display )   {
        error( "Can't open display." );
    }

    /*
    * Find out some things about the display you're using.
    */
    // DefaultScreen is as macro to get default screen index
    xinfo.screen = DefaultScreen( xinfo.display ); 



    unsigned long white, black;
    white = XWhitePixel( xinfo.display, xinfo.screen ); 
    black = XBlackPixel( xinfo.display, xinfo.screen );

    xinfo.window = XCreateSimpleWindow(
        xinfo.display,              // display where window appears
        DefaultRootWindow( xinfo.display ), // window's parent in window tree
        hints.x, hints.y,           // upper left corner location
        hints.width, hints.height,  // size of the window
        10,                         // width of window's border
        black,                      // window border colour
        white );                        // window background colour

    // extra window properties like a window title
    XSetStandardProperties(
        xinfo.display,      // display containing the window
        xinfo.window,       // window whose properties are set
        "x1_openWindow",    // window's title
        "OW",               // icon's title
        None,               // pixmap for the icon
        argv, argc,         // applications command line args
        &hints);            // size hints for the window

    /*
     * Put the window on the screen.
     */
    XMapRaised( xinfo.display, xinfo.window );

    XFlush(xinfo.display);
}


int main ( int argc, char* argv[] ) {

    XInfo xinfo;

    initX(argc, argv, xinfo);

    // wait for user input to quit (a concole event for now)
    cout << "press ENTER to exit";
    cin.get();

    XCloseDisplay(xinfo.display);
}
