/*
CS 349 Code Examples: X Windows and XLib

    dispatch     Opens and draws into two windows

  					usage:

  					to create sibling window:

  					   ./muliwindow sib

  					to create child window:

  					   ./multiwindow child

- - - - - - - - - - - - - - - - - - - - - -

See associated makefile for compiling instructions

*/

#include <iostream>
#include <list>
#include <cstdlib>
#include <unistd.h>
#include <sstream>

/*
 * Header files for X functions
 */
#include <X11/Xlib.h>
#include <X11/Xutil.h>

using namespace std;

const int Border = 4;


/*
 * Information to draw on the window.
 */
struct XInfo {
	Display	 *display;
	int		 screen;
	Window	 window;
	GC		 gc;
};



/*
 * Function to put out a message on error exits.
 */
void error( string str ) {
	cerr << str << endl;
	exit(0);
}


/*
 * Initialize X and create a window
 */
void initX(int argc, char *argv[], XInfo &xinfo, Window root, 
	int x, int y, int width, int height, string name) {

	XSizeHints hints;
	unsigned long white, black;

	white = XWhitePixel( xinfo.display, xinfo.screen );
	black = XBlackPixel( xinfo.display, xinfo.screen );

	hints.x = x;
	hints.y = y;
	hints.width = width;
	hints.height = height;
	hints.flags = PPosition | PSize;

	xinfo.window = XCreateSimpleWindow(
	                   xinfo.display,				// display where window appears
	                   root, 						// window's parent in window tree
	                   hints.x, hints.y,			// upper left corner location
	                   hints.width, hints.height,	// size of the window
	                   Border,						// width of window's border
	                   black,						// window border colour
	                   white );					// window background colour

	XSetStandardProperties(
	    xinfo.display,		// display containing the window
	    xinfo.window,		// window whose properties are set
	    name.c_str(),	// window's title
	    "",				// icon's title
	    None,				// pixmap for the icon
	    argv, argc,			// applications command line args
	    &hints );			// size hints for the window

	/*
	 * Create Graphics Context
	 */
	xinfo.gc = XCreateGC(xinfo.display, xinfo.window, 0, 0);
	XSetForeground(xinfo.display, xinfo.gc, BlackPixel(xinfo.display, xinfo.screen));
	XSetBackground(xinfo.display, xinfo.gc, WhitePixel(xinfo.display, xinfo.screen));
	XSetFillStyle(xinfo.display, xinfo.gc, FillSolid);
	XSetLineAttributes(xinfo.display, xinfo.gc,
	                   1, LineSolid, CapButt, JoinRound);

	XSelectInput(xinfo.display, xinfo.window,
	             ButtonPressMask | KeyPressMask | ExposureMask);

	   // load a larger font
    XFontStruct * font;
    font = XLoadQueryFont (xinfo.display, "12x24");
    XSetFont (xinfo.display, xinfo.gc, font->fid);

	/*
	 * Put the window on the screen.
	 */
	XMapRaised( xinfo.display, xinfo.window );

}


// convert to string
string toString(int i) {
    stringstream ss;
    ss << i;
    return ss.str();
}



/*
 * Start executing here.
 *	 First initialize window.
 *	 Next loop responding to events.
 *	 Exit forcing window manager to clean up - cheesy, but easy.
 */
int main ( int argc, char *argv[] ) {

	XInfo xinfo1;
	XInfo xinfo2;

	// true: window is like heavyweight widget embedded in window
	// false: window is just another window
	bool isSibling = false;

		/*
	* Display opening uses the DISPLAY	environment variable.
	* It can go wrong if DISPLAY isn't set, or you don't have permission.
	*/
	Display * display = XOpenDisplay( "" );
	if ( !display )	{
		error( "Can't open display." );
	}

	/*
	* Find out some things about the display you're using.
	*/
	int screen = DefaultScreen( display );
	xinfo1.display = display;
	xinfo1.screen = screen;

	// create window 1
	initX(argc, argv, xinfo1, DefaultRootWindow(display), 75, 75, 200, 200, "One");

	// create window 2
	xinfo2.display = display;
	xinfo2.screen = screen;

	if (isSibling)
		// make window 2 a sibling of window 1
		initX(argc, argv, xinfo2, DefaultRootWindow( display ), 25, 150, 150, 50, "Two");
	else
		// make window 2 a child of window 1
		initX(argc, argv, xinfo2, xinfo1.window, 25, 50, 150, 50, "Two");

	XFlush(display);

	// need to repaint both windows

	int click1 = 0;
	int click2 = 0;


	XEvent event;

	while ( true ) {
		XNextEvent( display, &event );
		switch ( event.type ) {
		case ButtonPress:
			if (event.xany.window == xinfo1.window) {
				cout << "ButtonPress Window One\n";
				click1++;
			} else if (event.xany.window == xinfo2.window) {
				cout << "ButtonPress Window Two\n";
				click2++;
			}
			break;
		}

		// draw in the windows
		string s;
		s = toString(click1);
		XDrawImageString( xinfo1.display, xinfo1.window, xinfo1.gc,
                      25, 40, s.c_str(), s.length() );
 		s = toString(click2);
		XDrawImageString( xinfo2.display, xinfo2.window, xinfo2.gc,
                      25, 40, s.c_str(), s.length() );


	}

	/* flush all pending requests to the X server. */
	XFlush(display);

	XCloseDisplay(display);
}
