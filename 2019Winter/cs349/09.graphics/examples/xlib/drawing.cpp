/*
CS 349 Code Examples: X Windows and XLib

    drawing     Demos drawing functions and graphics contexts

- - - - - - - - - - - - - - - - - - - - - -

See associated makefile for compiling instructions

*/

#include <iostream>
#include <list>
#include <cstdlib>
#include <unistd.h>

// Header files for X functions
#include <X11/Xlib.h>
#include <X11/Xutil.h>

using namespace std;

// handy struct to save display, window, and screen
struct XInfo {
	Display	 *display;
	int		 screen;
	Window	 window;
};

// Function to put out a message on error and exits
void error( string str ) {
	cerr << str << endl;
	exit(0);
}

void drawRectanglesInCorners(XInfo &xinfo, GC gc) {
	Display  *display = xinfo.display;
	Window   win = xinfo.window;

	XWindowAttributes windowInfo;
	XGetWindowAttributes(display, win, &windowInfo);
	// max x and y coordinate
	unsigned int maxHeight = windowInfo.height - 1;
	unsigned int maxWidth = windowInfo.width - 1;

	// draw rectangles in each corner of the window 
	int s = 32; // rectangle size
	XDrawRectangle(display, win, gc, 0, 0, s, s); // top left
	XDrawRectangle(display, win, gc, 0, maxHeight - s, s, s); // bottom left
	XDrawRectangle(display, win, gc, maxWidth - s, 0, s, s); // top right
	XDrawRectangle(display, win, gc, maxWidth - s, maxHeight - s, s, s); // bottom right
}

void drawStuff(XInfo &xinfo, GC gc, int x, int y) {
	Display  *display = xinfo.display;
	Window   win = xinfo.window;

	// draw two intersecting lines, one horizontal and one vertical,
	// which intersect at point "x,y".                            
	XDrawLine(display, win, gc, x, y - 30, x, y + 200);
	XDrawLine(display, win, gc, x - 30, y, x + 200, y);

	// now use the XDrawArc() function to draw a circle whose diameter
	// is 30 pixels, and whose center is at location 'x,y'.        
	XDrawArc(display, win, gc, x - (30 / 2), y - (30 / 2), 30, 30, 0, 360 * 64);

	// draw a small triangle at the top-left corner of the window.
	// the triangle is made of a set of consecutive lines, whose  
	// end-point pixels are specified in the 'points' array.      
	{
		XPoint points[] = {
			{x + 200, y + 50},
			{x + 250, y + 80},
			{x + 200, y + 80},
			{x + 200, y + 50}
		};
		int npoints = sizeof(points) / sizeof(XPoint);

		XDrawLines(display, win, gc, points, npoints, CoordModeOrigin);
	}

	// draw a rectangle whose top-left corner is at 'x+120,y+50', its width is 
	// 50 pixels, and height is 60 pixels.                                  
	XDrawRectangle(display, win, gc, x + 120, y + 50, 50, 60);

	// draw a filled rectangle of the same size as above, to the left of the 
	// previous rectangle.                                                   
	XFillRectangle(display, win, gc, x + 60, y + 50, 50, 60);
}


// Initialize X and create a window
void initX(int argc, char *argv[], XInfo &xinfo) {

	XSizeHints hints;
	unsigned long white, black;


	// Display opening uses the DISPLAY	environment variable.
	// It can go wrong if DISPLAY isn't set, or you don't have permission.
	xinfo.display = XOpenDisplay( "" );
	if ( !xinfo.display )	{
		error( "Can't open display." );
	}

	// Find out some things about the display you're using.
	xinfo.screen = DefaultScreen( xinfo.display );

	white = XWhitePixel( xinfo.display, xinfo.screen );
	black = XBlackPixel( xinfo.display, xinfo.screen );

	hints.x = 100;
	hints.y = 100;
	hints.width = 600;
	hints.height = 600;
	hints.flags = PPosition | PSize;

	xinfo.window = XCreateSimpleWindow(
	                   xinfo.display,				// display where window appears
	                   DefaultRootWindow( xinfo.display ), // window's parent in window tree
	                   hints.x, hints.y,			// upper left corner location
	                   hints.width, hints.height,	// size of the window
	                   5,						// width of window's border
	                   black,						// window border colour
	                   white );					// window background colour

	XSetStandardProperties(
	    xinfo.display,		// display containing the window
	    xinfo.window,		// window whose properties are set
	    "drawing",			// window's title
	    "SD",				// icon's title
	    None,				// pixmap for the icon
	    argv, argc,			// applications command line args
	    &hints );			// size hints for the window

	// Put the window on the screen.
	XMapRaised( xinfo.display, xinfo.window );

	XFlush(xinfo.display);
	
    // give server time to setup before sending drawing commands
    sleep(1);
}



/*
 * Start executing here.
 */
int main ( int argc, char *argv[] ) {
	XInfo xinfo;

	initX(argc, argv, xinfo);

	// an array of graphics contexts to demo
	GC  gc[3];

	// Create 3 Graphics Contexts
	int i = 0;
	gc[i] = XCreateGC(xinfo.display, xinfo.window, 0, 0);
	XSetForeground(xinfo.display, gc[i], BlackPixel(xinfo.display, xinfo.screen));
	XSetBackground(xinfo.display, gc[i], WhitePixel(xinfo.display, xinfo.screen));
	XSetFillStyle(xinfo.display, gc[i], FillSolid);
	XSetLineAttributes(xinfo.display, gc[i],
	                   1, LineSolid, CapButt, JoinRound);

	i = 1;
	gc[i] = XCreateGC(xinfo.display, xinfo.window, 0, 0);
	XSetForeground(xinfo.display, gc[i], BlackPixel(xinfo.display, xinfo.screen));
	XSetBackground(xinfo.display, gc[i], WhitePixel(xinfo.display, xinfo.screen));
	XSetFillStyle(xinfo.display, gc[i], FillSolid);
	XSetLineAttributes(xinfo.display, gc[i],
	                   7, LineSolid, CapRound, JoinMiter);

	i = 2;
	gc[i] = XCreateGC(xinfo.display, xinfo.window, 0, 0);
	XSetForeground(xinfo.display, gc[i], BlackPixel(xinfo.display, xinfo.screen));
	XSetBackground(xinfo.display, gc[i], WhitePixel(xinfo.display, xinfo.screen));
	XSetFillStyle(xinfo.display, gc[i], FillSolid);
	XSetLineAttributes(xinfo.display, gc[i],
	                   7, LineOnOffDash, CapButt, JoinBevel);

	// make the drawing
	drawRectanglesInCorners(xinfo, gc[1]);
	drawStuff(xinfo, gc[0], 50, 50);
	drawStuff(xinfo, gc[1], 150, 200);
	drawStuff(xinfo, gc[2], 250, 350);

	// flush all pending requests to the X server.
	XFlush(xinfo.display);

	// wait for user input to quit
	cout << "press ENTER to exit";
	cin.get();

	XCloseDisplay(xinfo.display);
}
